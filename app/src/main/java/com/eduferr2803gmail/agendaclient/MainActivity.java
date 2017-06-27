package com.eduferr2803gmail.agendaclient;

import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.*;
import android.content.*;

import android.database.sqlite.*;
import android.database.*;

import com.eduferr2803gmail.agendaclient.app.MessageBox;
import com.eduferr2803gmail.agendaclient.database.DataBase;
import com.eduferr2803gmail.agendaclient.dominio.RepositorioContato;
import com.eduferr2803gmail.agendaclient.dominio.entidades.Contato;

public class MainActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText edtPesquisa;
    private ListView lstContatos;
    private ArrayAdapter<Contato> adpContatos;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioContato repositorioContato;
    private FiltraDados filtraDados;


    public static final String PAR_CONTATO = "CONTATO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPesquisa = (EditText) findViewById(R.id.edtPesquisa);
        lstContatos = (ListView) findViewById(R.id.lstContatos);

        lstContatos.setOnItemClickListener(this);

        try {

            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();
            repositorioContato = new RepositorioContato(conn);

            adpContatos = repositorioContato.buscaContatos(this);

            lstContatos.setAdapter(adpContatos);

            filtraDados = new FiltraDados(adpContatos);
            edtPesquisa.addTextChangedListener(filtraDados);


        } catch (SQLException ex) {
            MessageBox.show(this, "Erro", "Erro ao criar o banco: " + ex.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (conn != null) {
            conn.close();
        }
    }


    @Override
    public void onClick(View v) {

        //Intent it = new Intent(this,LoginActivity.class);
        Intent it = new Intent(this, CadContatosActivity.class);
        startActivityForResult(it, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        adpContatos = repositorioContato.buscaContatos(this);
        filtraDados.setArrayAdapter(adpContatos);
        lstContatos.setAdapter(adpContatos);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Contato contato = adpContatos.getItem(position);

        Intent it = new Intent(this, CadContatosActivity.class);

        it.putExtra(PAR_CONTATO, contato);

        startActivityForResult(it, 0);

    }


    private class FiltraDados implements TextWatcher {

        private ArrayAdapter<Contato> arrayAdapter;

        private FiltraDados(ArrayAdapter<Contato> arrayAdapter) {
            this.arrayAdapter = arrayAdapter;
        }

        public void setArrayAdapter(ArrayAdapter<Contato> arrayAdapter) {
            this.arrayAdapter = arrayAdapter;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            arrayAdapter.getFilter().filter(s);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.mni_novo:

                Intent novoCadastro = new Intent(MainActivity.this, CadContatosActivity.class);

                startActivity(novoCadastro);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(final int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder alertbox = new AlertDialog.Builder(MainActivity.this);
            alertbox.setTitle("Deseja sair");
            alertbox.setCancelable(false);
            alertbox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishAffinity();
                }
            });
            alertbox.setNegativeButton("não", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertbox.show();
        }
        return super.onKeyDown(keyCode, event);
    }


}
