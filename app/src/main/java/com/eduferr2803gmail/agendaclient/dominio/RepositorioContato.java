package com.eduferr2803gmail.agendaclient.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.widget.*;
import com.eduferr2803gmail.agendaclient.R;

import java.util.Date;

import com.eduferr2803gmail.agendaclient.ContatoArrayAdapterActivity;
import com.eduferr2803gmail.agendaclient.dominio.entidades.Contato;


/**
 * Created by Eduferr on 21/05/2017.
 */

public class RepositorioContato {

    private SQLiteDatabase conn;

    public RepositorioContato(SQLiteDatabase conn)
    {
        this.conn = conn;
    }

    private ContentValues preencheContentValues(Contato contato) {
        ContentValues values = new ContentValues();

        values.put(Contato.NOME    , contato.getNome());
        values.put(Contato.TELEFONE    , contato.getTelefone());
        values.put(Contato.TIPOTELEFONE, contato.getTipoTelefone());
        values.put(Contato.EMAIL    , contato.getEmail());
        values.put(Contato.ENDERECO, contato.getEndereco());
        values.put(Contato.DATAOCORRENCIA, contato.getDataOcorrencia().getTime());
        values.put(Contato.DESCRICAO, contato.getDescricao());

        return values;

    }

    public void excluir(long id) {
        conn.delete(Contato.TABELA, " _id = ? ", new String[]{ String.valueOf( id ) });
    }

    public void alterar(Contato contato) {
        ContentValues values = preencheContentValues(contato);
        conn.update(Contato.TABELA, values, " _id = ? ", new String[]{ String.valueOf( contato.getId()) } );

    }

    public void inserir(Contato contato) {
        ContentValues values = preencheContentValues(contato);
        conn.insertOrThrow(Contato.TABELA, null, values);
    }

    public ContatoArrayAdapterActivity buscaContatos(Context context) {

        ContatoArrayAdapterActivity adpContatos = new ContatoArrayAdapterActivity(context, R.layout.activity_item_contato );

        Cursor cursor  =  conn.query(Contato.TABELA, null, null, null, null, null, null);

        if (cursor.getCount() > 0 )
        {
            cursor.moveToFirst();
            do {
                Contato contato = new Contato();
                contato.setId( cursor.getLong( cursor.getColumnIndex(Contato.ID) ) );
                contato.setNome( cursor.getString( cursor.getColumnIndex(Contato.NOME ) ) );
                contato.setTelefone( cursor.getString( cursor.getColumnIndex(Contato.TELEFONE ) ) );
                contato.setTipoTelefone( cursor.getString( cursor.getColumnIndex(Contato.TIPOTELEFONE ) ) );
                contato.setEmail(cursor.getString( cursor.getColumnIndex(Contato.EMAIL ) ));
                contato.setEndereco(cursor.getString( cursor.getColumnIndex(Contato.ENDERECO) ));
                contato.setDataOcorrencia( new Date(cursor.getLong( cursor.getColumnIndex(Contato.DATAOCORRENCIA ) )) );
                contato.setDescricao(cursor.getString( cursor.getColumnIndex(Contato.DESCRICAO ) ));

                adpContatos.add(contato);

            }while (cursor.moveToNext());
        }
        return adpContatos;
    }
}
