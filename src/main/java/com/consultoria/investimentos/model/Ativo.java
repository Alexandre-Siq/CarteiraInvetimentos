package com.consultoria.investimentos.model; // ⚠️ Verifique o pacote

import jakarta.persistence.*;

@Entity
public class Ativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; 
    private String codigo; 
    private Double valorAtual;

    // --- O RELACIONAMENTO N:1 ---
    // Muitos Ativos pertencem a UMA Categoria.
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Ativo() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public Double getValorAtual() { return valorAtual; }
    public void setValorAtual(Double valorAtual) { this.valorAtual = valorAtual; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}