package br.com.banco.model;

import br.com.banco.model.enums.OperacaoBancaria;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "transferencia")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_transferencia")
    private LocalDate dataTranferencia;
    private BigDecimal valor;
    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private OperacaoBancaria operacaoBancaria;
    @Column(name = "nome_operador_transacao")
    private String nomeDoOperador;
    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    public Transferencia(){}
    public Transferencia(LocalDate dataTranferencia, BigDecimal valor,
                         String tipo, String nomeDoOperador, Conta conta) {
        this.dataTranferencia = dataTranferencia;
        this.valor = valor;
        this.operacaoBancaria = OperacaoBancaria.valueOf(tipo);
        this.nomeDoOperador = nomeDoOperador;
        this.conta =  conta;
    }



}
