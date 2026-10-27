package br.com.sistemaestoque.model;

public class Movimentacao {
    private int id, quantidade, usuarioId, produtoId;
    private String tipo, observacao, dataHora;
    public Movimentacao(int id, String tipo, String dataHora, int quantidade, String observacao, int usuarioId, int produtoId) {
        this.id=id; this.tipo=tipo; this.dataHora=dataHora; this.quantidade=quantidade;
        this.observacao=observacao; this.usuarioId=usuarioId; this.produtoId=produtoId;
    }
    public int getId(){return id;} public String getTipo(){return tipo;} public String getDataHora(){return dataHora;}
    public int getQuantidade(){return quantidade;} public String getObservacao(){return observacao;}
    public int getUsuarioId(){return usuarioId;} public int getProdutoId(){return produtoId;}
}
