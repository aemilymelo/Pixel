package br.edu.utfpr.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_estacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Estacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 150)
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "propriedade_id")
    private Propriedade propriedade;
}
