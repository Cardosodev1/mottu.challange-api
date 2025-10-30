package br.com.mottu.challange.controller;

import br.com.mottu.challange.domain.dto.model.ModelDTO;
import br.com.mottu.challange.domain.dto.model.ModelDetailsDTO;
import br.com.mottu.challange.domain.entity.Model;
import br.com.mottu.challange.domain.repository.ModelRepository;
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
@RequestMapping("/models")
public class ModelController {

    @Autowired
    ModelRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid ModelDTO modelDTO, UriComponentsBuilder uriBuilder) {
        var model = new Model(modelDTO);
        repository.save(model);

        var uri = uriBuilder.path("/models/{id}").buildAndExpand(model.getId()).toUri();

        return ResponseEntity.created(uri).body(new ModelDetailsDTO(model));
    }

    @GetMapping
    public ResponseEntity<Page<ModelDetailsDTO>> list(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        var page = repository.findAll(pageable)
                .map(ModelDetailsDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        var model = repository.getReferenceById(id);
        return ResponseEntity.ok(new ModelDetailsDTO(model));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid ModelDetailsDTO modelDetailsDTO) {
        var model = repository.getReferenceById(modelDetailsDTO.id());
        model.update(modelDetailsDTO);

        return ResponseEntity.ok(new ModelDetailsDTO(model));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
