package br.com.mottu.challange.repository;

import br.com.mottu.challange.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
