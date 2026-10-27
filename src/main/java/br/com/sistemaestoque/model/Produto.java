package br.com.sistemaestoque.model;

import java.math.BigDecimal;

public class Produto {
    private int id, estoqueMinimo, estoqueAtual, categoriaId, fornecedorId;
    private String nome, descricao;
    private BigDecimal precoCusto, precoVenda;

    public Produto(int id, String nome, String descricao, BigDecimal precoCusto, BigDecimal precoVenda,
                   int estoqueMinimo, int estoqueAtual, int categoriaId, int fornecedorId) {
        this.id=id; this.nome=nome; this.descricao=descricao; this.precoCusto=precoCusto;
        this.precoVenda=precoVenda; this.estoqueMinimo=estoqueMinimo; this.estoqueAtual=estoqueAtual;
        this.categoriaId=categoriaId; this.fornecedorId=fornecedorId;
    }
    public int getId(){return id;} public String getNome(){return nome;}
    public String getDescricao(){return descricao;} public BigDecimal getPrecoCusto(){return precoCusto;}
    public BigDecimal getPrecoVenda(){return precoVenda;} public int getEstoqueMinimo(){return estoqueMinimo;}
    public int getEstoqueAtual(){return estoqueAtual;} public int getCategoriaId(){return categoriaId;}
    public int getFornecedorId(){return fornecedorId;}
}
