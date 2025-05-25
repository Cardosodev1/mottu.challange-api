package br.com.mottu.challange.repository;

import br.com.mottu.challange.entity.Yard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YardRepository extends JpaRepository<Yard, Long> {
}
