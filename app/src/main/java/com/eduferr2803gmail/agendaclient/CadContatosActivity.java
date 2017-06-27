package com.eduferr2803gmail.agendaclient;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import com.eduferr2803gmail.agendaclient.app.MessageBox;
import com.eduferr2803gmail.agendaclient.app.ViewHelper;
import com.eduferr2803gmail.agendaclient.database.DataBase;
import com.eduferr2803gmail.agendaclient.dominio.RepositorioContato;
import com.eduferr2803gmail.agendaclient.dominio.entidades.Contato;
import com.eduferr2803gmail.agendaclient.util.DateUtils;


/**
 * Created by Eduferr on 21/05/2017.
 */

public class CadContatosActivity extends ActionBarActivity{

    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtTelefone;
    private EditText edtEndereco;
    private EditText edtDataOcorrencia;
    private EditText edtDescricao;

    private Spinner spnTipoTelefone;

    private ArrayAdapter<String> adpTipoTelefone;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioContato repositorioContato;
    private Contato contato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_contato);

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtEndereco = (EditText)findViewById(R.id.edtEndereco);
        edtDataOcorrencia = (EditText)findViewById(R.id.edtDataOcorrencia);
        edtDescricao = (EditText)findViewById(R.id.edtDescricao);
        edtTelefone = (EditText)findViewById(R.id.edtTelefone);

        spnTipoTelefone = (Spinner)findViewById(R.id.spnTipoTelefone);

        adpTipoTelefone       = ViewHelper.createArrayAdapter(this, spnTipoTelefone);


        adpTipoTelefone.add("Selecione...");
        adpTipoTelefone.add("Casa");
        adpTipoTelefone.add("Celular");
        adpTipoTelefone.add("Outros");

        ExibeDataListener listener = new ExibeDataListener();

        edtDataOcorrencia.setOnClickListener(  listener );
        edtDataOcorrencia.setOnFocusChangeListener( listener );
        edtDataOcorrencia.setKeyListener(null);

        Bundle bundle = getIntent().getExtras();

        if ((bundle != null) && (bundle.containsKey(MainActivity.PAR_CONTATO)))
        {
            contato = (Contato)bundle.getSerializable(MainActivity.PAR_CONTATO);
            preencheDados();
        }
        else
            contato = new Contato();

        try {


            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioContato = new RepositorioContato(conn);

        }catch(SQLException ex)
        {
            MessageBox.show(this, "Erro", "Erro ao criar o banco: " + ex.getMessage());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (conn != null){
            conn.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_cad_contato, menu);

        if (contato.getId() != 0)
            menu.getItem(1).setVisible(true);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {

            case R.id.mni_acao1:

                salvar();
                finish();

                break;

            case R.id.mni_acao2:

                excluir();
                finish();

                break;
        }


        return super.onOptionsItemSelected(item);
    }


    private void preencheDados(){
        edtNome.setText( contato.getNome() );
        edtTelefone.setText( contato.getTelefone() );
        spnTipoTelefone.setSelection(  Integer.parseInt(contato.getTipoTelefone()) );
        edtEmail.setText( contato.getEmail() );
        edtEndereco.setText( contato.getEndereco() );

        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        String dt = format.format( contato.getDataOcorrencia() );

        edtDataOcorrencia.setText( dt );

        edtDescricao.setText( contato.getDescricao() );
    }

    private void excluir(){
        try
        {
            repositorioContato.excluir( contato.getId() );

        }catch(Exception ex)
        {
            MessageBox.show(this, "Erro", "Erro ao excluir os dados: " + ex.getMessage());
        }

    }

    private void salvar(){

        try
        {

            contato.setNome(edtNome.getText().toString());
            contato.setTelefone(edtTelefone.getText().toString());
            contato.setEmail(edtEmail.getText().toString());
            contato.setEndereco(edtEndereco.getText().toString());

            contato.setDescricao(edtDescricao.getText().toString());

            contato.setTipoTelefone( String.valueOf(spnTipoTelefone.getSelectedItemPosition()) );

            if (contato.getId() == 0)
                repositorioContato.inserir(contato);
            else
                repositorioContato.alterar(contato);

        }catch(Exception ex)
        {
            MessageBox.show(this, "Erro", "Erro ao salvar os dados: " + ex.getMessage());
        }
    }

    private void exibeData() {
        Calendar calendar = Calendar.getInstance();

        int ano =  calendar.get(Calendar.YEAR);
        int mes =  calendar.get(Calendar.MONTH);
        int dia =  calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dlg = new DatePickerDialog(this, new SelecionaDataListener(), ano, mes, dia);
        dlg.show();
    }

    private class ExibeDataListener implements View.OnClickListener, View.OnFocusChangeListener {
        @Override
        public void onClick(View v) {
            exibeData();
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus)
                exibeData();
        }
    }

    private class SelecionaDataListener implements DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            String dt = DateUtils.dateToString(year, monthOfYear, dayOfMonth);
            Date data = DateUtils.getDate(year, monthOfYear, dayOfMonth);

            edtDataOcorrencia.setText(dt);

            contato.setDataOcorrencia(data);

        }

    }


}
