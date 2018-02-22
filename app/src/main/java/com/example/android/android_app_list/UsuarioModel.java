package com.example.android.android_app_list;

public class UsuarioModel {
    String nome;
    String tipo;
    String numeroDaVersao;
    String lancamento;

    public UsuarioModel(String nome, String tipo, String price, String lancamento) {
        this.nome = nome;
        this.tipo = tipo;
        this.numeroDaVersao = price;
        this.lancamento = lancamento;
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

    public String getLancamento() {
        return lancamento;
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }
}
