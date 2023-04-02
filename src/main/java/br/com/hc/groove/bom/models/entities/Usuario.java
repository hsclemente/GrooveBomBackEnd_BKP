package br.com.hc.groove.bom.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "gb_usuarios")
@AllArgsConstructor@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = true, columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "especialidade", nullable = false)
    private String especialidade;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "saldo", nullable = false, columnDefinition = "DOUBLE PRESICION default 0.00")
    private Double saldo;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banda")
    private Banda banda;
}
