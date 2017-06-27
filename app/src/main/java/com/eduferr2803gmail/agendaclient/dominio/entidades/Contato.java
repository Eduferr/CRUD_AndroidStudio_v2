package com.eduferr2803gmail.agendaclient.dominio.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Eduferr on 21/05/2017.
 */
public class Contato implements Serializable{

    public static String TABELA = "CONTATO";
    public static String ID = "_id";
    public static String NOME = "NOME";
    public static String TELEFONE = "TELEFONE";
    public static String TIPOTELEFONE = "TIPOTELEFONE";
    public static String EMAIL = "EMAIL";
    public static String ENDERECO = "ENDERECO";
    public static String DATAOCORRENCIA = "DATAOCORRENCIA";
    public static String DESCRICAO = "DESCRICAO";

    private long id;
    private String nome;
    private String telefone;
    private String tipoTelefone;
    private String email;
    private String endereco;
    private Date dataOcorrencia;
    private String descricao;

    public Contato()
    {
        id = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(Date dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString()
    {
        return nome + " " + telefone;
    }


}
