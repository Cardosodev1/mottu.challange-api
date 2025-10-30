package br.com.mottu.challange.controller;

import br.com.mottu.challange.domain.entity.Model;
import br.com.mottu.challange.domain.repository.BrandRepository;
import br.com.mottu.challange.domain.repository.ModelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web/models")
public class ModelWebController {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private BrandRepository brandRepository;

    @GetMapping
    public String listModels(@PageableDefault(size = 10, sort = "name") Pageable pageable, ModelMap model) {
        Page<Model> modelPage = modelRepository.findAll(pageable);
        model.addAttribute("modelPage", modelPage);
        return "models/list";
    }

    @GetMapping("/new")
    public String showNewForm(ModelMap model) {
        model.addAttribute("modelEntity", new Model());
        model.addAttribute("allBrands", brandRepository.findAll());
        model.addAttribute("pageTitle", "Cadastrar Novo Modelo");
        return "models/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, ModelMap model) {
        Model modelEntity = modelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID do modelo inválido: " + id));

        model.addAttribute("modelEntity", modelEntity);
        model.addAttribute("allBrands", brandRepository.findAll());
        model.addAttribute("pageTitle", "Editar Modelo (ID: " + id + ")");
        return "models/form";
    }

    @PostMapping("/save")
    public String saveModel(@ModelAttribute("modelEntity") Model modelEntity, RedirectAttributes redirectAttributes) {
        modelRepository.save(modelEntity);
        redirectAttributes.addFlashAttribute("message", "Modelo salvo com sucesso!");
        return "redirect:/web/models";
    }

    @GetMapping("/delete/{id}")
    public String deleteModel(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            modelRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Modelo com ID " + id + " foi deletado.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message_error", "Erro ao deletar modelo. Verifique se ele não está sendo usado por uma moto.");
        }
        return "redirect:/web/models";
    }
}