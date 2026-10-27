package br.com.sistemaestoque.model;

public class Fornecedor {
    private int id;
    private String nome, contato, telefone, email, endereco;
    public Fornecedor(int id, String nome, String contato, String telefone, String email, String endereco) {
        this.id=id; this.nome=nome; this.contato=contato; this.telefone=telefone; this.email=email; this.endereco=endereco;
    }
    public int getId(){return id;} public String getNome(){return nome;}
    public String getContato(){return contato;} public String getTelefone(){return telefone;}
    public String getEmail(){return email;} public String getEndereco(){return endereco;}
}
