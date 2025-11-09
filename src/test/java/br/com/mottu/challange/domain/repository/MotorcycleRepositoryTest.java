package br.com.mottu.challange.domain.repository;

import br.com.mottu.challange.domain.entity.Motorcycle;
import br.com.mottu.challange.domain.entity.StatusColor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class MotorcycleRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Test
    void deveSalvarMotorcycleComSucesso() {
        Motorcycle newMotorcycle = new Motorcycle();
        newMotorcycle.setLicense("NEW-1234");
        newMotorcycle.setChassis("chassis123");
        newMotorcycle.setEngine("engine123");
        newMotorcycle.setStatusColor(StatusColor.DARK_GREEN);
        Motorcycle savedMotorcycle = motorcycleRepository.save(newMotorcycle);


        assertThat(savedMotorcycle).isNotNull();
        assertThat(savedMotorcycle.getId()).isNotNull();
        assertThat(savedMotorcycle.getLicense()).isEqualTo("NEW-1234");

        Motorcycle foundInDb = entityManager.find(Motorcycle.class, savedMotorcycle.getId());
        assertThat(foundInDb).isNotNull();
        assertThat(foundInDb.getLicense()).isEqualTo(savedMotorcycle.getLicense());
    }

    @Test
    void deveEncontrarMotorcyclePorId() {
        Motorcycle existingMotorcycle = new Motorcycle();
        existingMotorcycle.setLicense("OLD-5678");
        existingMotorcycle.setChassis("chassis567");
        existingMotorcycle.setEngine("engine567");
        existingMotorcycle.setStatusColor(StatusColor.YELLOW);

        existingMotorcycle = entityManager.persistAndFlush(existingMotorcycle);
        Long id = existingMotorcycle.getId();

        Optional<Motorcycle> foundOptional = motorcycleRepository.findById(id);
        assertThat(foundOptional).isPresent();
        Motorcycle foundMotorcycle = foundOptional.get();

        assertThat(foundMotorcycle.getId()).isEqualTo(id);
        assertThat(foundMotorcycle.getLicense()).isEqualTo("OLD-5678");
        assertThat(foundMotorcycle.getChassis()).isEqualTo("chassis567");
    }

}
