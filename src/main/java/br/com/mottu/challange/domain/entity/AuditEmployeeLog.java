package br.com.mottu.challange.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_audit_employees_log")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditEmployeeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_log_seq_generator")
    @SequenceGenerator(name = "audit_log_seq_generator", sequenceName = "audit_log_seq", allocationSize = 1)
    private Long id;

    private String operacao;
    private LocalDateTime dataHora;
    private String valoresAntigos;
    private String valoresNovos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unit")
    private Employee employee;

}
