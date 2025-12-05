package com.consultoria.investimentos.controller;

import com.consultoria.investimentos.model.Categoria;
import com.consultoria.investimentos.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller // <--- Mudou aqui!
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public String listar(Model model) {
        List<Categoria> lista = categoriaRepository.findAll();
        
        // Estamos colocando a lista dentro da "pasta" model
        // para que o HTML consiga pegar esses dados depois.
        model.addAttribute("categorias", lista);
        
        return "categorias"; // Isso indica que o arquivo se chama "categorias.html"
    }

    @GetMapping("/nova")
    public String abrirFormulario(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "form-categoria";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Categoria categoria) {
        categoriaRepository.save(categoria);
        // Depois de salvar, redireciona de volta para a lista
        return "redirect:/categorias";
    }

    // 4. EDITAR (Abrir o formulário com dados)
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        // Busca a categoria antiga no banco ou retorna nulo se não achar
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        
        // Coloca ela na "bandeja" para o formulário mostrar preenchido
        model.addAttribute("categoria", categoria);
        
        // Abre o MESMO arquivo de formulário que usamos para criar
        return "form-categoria";
    }

    // 5. EXCLUIR (DELETE)
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        categoriaRepository.deleteById(id);
        return "redirect:/categorias";
    }
}