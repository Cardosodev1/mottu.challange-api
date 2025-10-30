package br.com.mottu.challange.domain.repository;

import br.com.mottu.challange.domain.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
