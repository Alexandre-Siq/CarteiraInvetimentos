package com.consultoria.investimentos.controller;

import com.consultoria.investimentos.model.Categoria;
import com.consultoria.investimentos.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller 
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public String listar(Model model) {
        List<Categoria> lista = categoriaRepository.findAll();

        model.addAttribute("categorias", lista);
        
        return "categorias";
    }

    @GetMapping("/nova")
    public String abrirFormulario(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "form-categoria";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Categoria categoria) {
        categoriaRepository.save(categoria);

        return "redirect:/categorias";
    }

    // 4. EDITAR (Abrir o formulário com dados)
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);

        model.addAttribute("categoria", categoria);

        return "form-categoria";
    }

    // 5. EXCLUIR (DELETE)
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        categoriaRepository.deleteById(id);
        return "redirect:/categorias";
    }
}