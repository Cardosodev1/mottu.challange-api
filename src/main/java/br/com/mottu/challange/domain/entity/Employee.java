package br.com.mottu.challange.domain.entity;

import br.com.mottu.challange.domain.dto.employee.EmployeeDTO;
import br.com.mottu.challange.domain.dto.employee.EmployeeUpdateDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_employees")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unit")
    private Unit unit;

    public Employee(EmployeeDTO employeeDTO) {
        this.name = employeeDTO.name();
        this.email = employeeDTO.email();
        this.role = employeeDTO.role();
        this.unit = new Unit(employeeDTO.idUnit());
    }

    public void update(@Valid EmployeeUpdateDTO employeeUpdateDTO) {
        if (employeeUpdateDTO.name() != null) {
            this.name = employeeUpdateDTO.name();
        }
        if (employeeUpdateDTO.role() != null) {
            this.role = employeeUpdateDTO.role();
        }
        if (employeeUpdateDTO.idUnit() != null) {
            this.unit = new Unit(employeeUpdateDTO.idUnit());
        }
    }

}
