package com.example.android.android_app_list;

public class UsuarioModel {
    String nome;
    String tipo;
    String numeroDaVersao;
    String dataLancamento;

    public UsuarioModel(String nome, String tipo, String price, String dataLancamento) {
        this.nome = nome;
        this.tipo = tipo;
        this.numeroDaVersao = price;
        this.dataLancamento = dataLancamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumeroDaVersao() {
        return numeroDaVersao;
    }

    public void setNumeroDaVersao(String numeroDaVersao) {
        this.numeroDaVersao = numeroDaVersao;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
}
