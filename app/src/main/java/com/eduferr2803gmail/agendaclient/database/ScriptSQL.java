package com.eduferr2803gmail.agendaclient.database;

/**
 * Created by Eduferr on 21/05/2017.
 */

public class ScriptSQL {


       public static String getCreateContato()
       {

            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append(" CREATE TABLE IF NOT EXISTS CONTATO ( ");
            sqlBuilder.append("_id                INTEGER       NOT NULL ");
            sqlBuilder.append("PRIMARY KEY AUTOINCREMENT, ");
            sqlBuilder.append("NOME               VARCHAR (100), ");
            sqlBuilder.append("TELEFONE           VARCHAR (14), ");
            sqlBuilder.append("TIPOTELEFONE       VARCHAR (1), ");
            sqlBuilder.append("EMAIL              VARCHAR (100), ");
            sqlBuilder.append("ENDERECO           VARCHAR (150), ");
            sqlBuilder.append("DATAOCORRENCIA     DATE, ");
            sqlBuilder.append("DESCRICAO             VARCHAR (255) ");
            sqlBuilder.append(");");

            return sqlBuilder.toString();

       }



}
