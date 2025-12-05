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
    private CategoriaRepository categoriaRepository; // Precisamos disso para o formulário depois

    // 1. LISTAR TUDO
    @GetMapping
    public String listar(Model model) {
        List<Ativo> lista = ativoRepository.findAll();
        model.addAttribute("ativos", lista);
        Double total = 0.0;
        for (Ativo ativo : lista) {
            // Só soma se o valor não for nulo (pra evitar erro)
            if (ativo.getValorAtual() != null) {
                total += ativo.getValorAtual();
            }
        }
        
        // Mandamos o total para a tela numa variável chamada "totalCarteira"
        model.addAttribute("totalCarteira", total);
        
        return "ativos";
    }
    

    // 2. ABRIR FORMULÁRIO (Com o Relacionamento!)
    @GetMapping("/novo")
    public String abrirFormulario(Model model) {
        model.addAttribute("ativo", new Ativo());
        // Aqui está o segredo: mandamos a lista de categorias para popular o <select>
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
        // 1. Busca o ativo antigo
        Ativo ativo = ativoRepository.findById(id).orElse(null);
        
        // 2. Manda ele pra tela
        model.addAttribute("ativo", ativo);
        
        // 3. IMPORTANTÍSSIMO: Manda a lista de categorias de novo pro dropdown funcionar
        model.addAttribute("categorias", categoriaRepository.findAll());
        
        // 4. Abre o mesmo formulário de cadastro
        return "form-ativo";
    }

    // 5. EXCLUIR (DELETE)
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        ativoRepository.deleteById(id);
        return "redirect:/ativos";
    }
}