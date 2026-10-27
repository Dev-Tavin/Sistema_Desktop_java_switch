package br.com.sistemaestoque.model;

public class Categoria {
    private int id;
    private String nome, descricao;
    public Categoria(int id, String nome, String descricao){this.id=id;this.nome=nome;this.descricao=descricao;}
    public int getId(){return id;} public String getNome(){return nome;} public String getDescricao(){return descricao;}
}
