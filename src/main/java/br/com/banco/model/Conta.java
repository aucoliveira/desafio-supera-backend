package br.com.banco.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Long id;
    @Column(name = "nome_responsavel")
    private String nome;

    public Conta(){}

    public Conta(String nome) {
        this.nome = nome;
    }
}
