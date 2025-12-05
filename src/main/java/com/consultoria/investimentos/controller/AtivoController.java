package com.consultoria.investimentos.controller;

import com.consultoria.investimentos.model.Ativo;
import com.consultoria.investimentos.repository.AtivoRepository;
import com.consultoria.investimentos.repository.CategoriaRepository; // Import novo!
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ativos")
public class AtivoController {

    @Autowired
    private AtivoRepository ativoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository; 

    // 1. LISTAR TUDO
    @GetMapping
    public String listar(Model model) {
        List<Ativo> lista = ativoRepository.findAll();
        model.addAttribute("ativos", lista);
        Double total = 0.0;
        for (Ativo ativo : lista) {
            // Só soma se o valor não for nulo
            if (ativo.getValorAtual() != null) {
                total += ativo.getValorAtual();
            }
        }
        
        model.addAttribute("totalCarteira", total);
        
        return "ativos";
    }
    

    // 2. ABRIR FORMULÁRIO
    @GetMapping("/novo")
    public String abrirFormulario(Model model) {
        model.addAttribute("ativo", new Ativo());
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "form-ativo";
    }

    // 3. SALVAR
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Ativo ativo) {
        ativoRepository.save(ativo);
        return "redirect:/ativos";
    }

    // 4. EDITAR 
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        
        Ativo ativo = ativoRepository.findById(id).orElse(null);
        
        model.addAttribute("ativo", ativo);
        
        model.addAttribute("categorias", categoriaRepository.findAll());
        
        return "form-ativo";
    }

    // 5. EXCLUIR (DELETE)
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        ativoRepository.deleteById(id);
        return "redirect:/ativos";
    }
}