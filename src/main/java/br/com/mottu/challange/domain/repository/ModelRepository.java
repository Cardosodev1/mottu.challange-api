package br.com.mottu.challange.domain.repository;

import br.com.mottu.challange.domain.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
}
