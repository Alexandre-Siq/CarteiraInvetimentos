package com.consultoria.investimentos.model; // ⚠️ Verifique se o nome do pacote bate com suas pastas

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    // --- O RELACIONAMENTO 1:N ---
    // Uma Categoria tem MUITOS ativos.
    // "mappedBy" diz: "Quem manda nessa relação é o campo 'categoria' lá na classe Ativo"
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Ativo> ativos;

    public Categoria() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public List<Ativo> getAtivos() { return ativos; }
    public void setAtivos(List<Ativo> ativos) { this.ativos = ativos; }
}