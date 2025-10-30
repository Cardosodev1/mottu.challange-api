package br.com.mottu.challange.controller;

import br.com.mottu.challange.domain.dto.brand.BrandDTO;
import br.com.mottu.challange.domain.dto.brand.BrandDetailsDTO;
import br.com.mottu.challange.domain.entity.Brand;
import br.com.mottu.challange.domain.repository.BrandRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    BrandRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid BrandDTO brandDTO, UriComponentsBuilder uriBuilder) {
        var brand = new Brand(brandDTO);
        repository.save(brand);

        var uri = uriBuilder.path("/brands/{id}").buildAndExpand(brand.getId()).toUri();

        return ResponseEntity.created(uri).body(new BrandDetailsDTO(brand));
    }

    @GetMapping
    public ResponseEntity<Page<BrandDetailsDTO>> list(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        var page = repository.findAll(pageable)
                .map(BrandDetailsDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        var brand = repository.getReferenceById(id);
        return ResponseEntity.ok(new BrandDetailsDTO(brand));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid BrandDetailsDTO brandDetailsDTO) {
        var brand = repository.getReferenceById(brandDetailsDTO.id());
        brand.update(brandDetailsDTO);

        return ResponseEntity.ok(new BrandDetailsDTO(brand));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
