package br.com.mottu.challange.controller;

import br.com.mottu.challange.domain.entity.Brand;
import br.com.mottu.challange.domain.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web/brands")
public class BrandWebController {

    @Autowired
    private BrandRepository brandRepository;

    @GetMapping
    public String listBrands(@PageableDefault(size = 10, sort = "name") Pageable pageable, Model model) {
        Page<Brand> brandPage = brandRepository.findAll(pageable);
        model.addAttribute("brandPage", brandPage);
        return "brands/list";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("brand", new Brand());
        model.addAttribute("pageTitle", "Cadastrar Nova Marca");
        return "brands/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID da marca inválido: " + id));
        model.addAttribute("brand", brand);
        model.addAttribute("pageTitle", "Editar Marca (ID: " + id + ")");
        return "brands/form";
    }

    @PostMapping("/save")
    public String saveBrand(@ModelAttribute Brand brand, RedirectAttributes redirectAttributes) {
        brandRepository.save(brand);
        redirectAttributes.addFlashAttribute("message", "Marca salva com sucesso!");
        return "redirect:/web/brands";
    }

    @GetMapping("/delete/{id}")
    public String deleteBrand(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            brandRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Marca com ID " + id + " foi deletada.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message_error", "Erro ao deletar marca. Verifique se ela não está sendo usada por um modelo.");
        }
        return "redirect:/web/brands";
    }
}