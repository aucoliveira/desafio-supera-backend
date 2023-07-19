package br.com.banco.service.dto;

import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.model.enums.OperacaoBancaria;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransferenciaSaidaDto {

    private Long id;
    private LocalDate dataTranferencia;
    private BigDecimal valor;
    private String tipo;
    private String nomeDoOperador;
    private Conta conta;

    public TransferenciaSaidaDto(){
    }
    public TransferenciaSaidaDto(Transferencia transferencia) {
        this.id = transferencia.getId();
        this.dataTranferencia = transferencia.getDataTranferencia();
        this.valor = transferencia.getValor();
        this.tipo = String.valueOf(transferencia.getOperacaoBancaria());
        this.nomeDoOperador = transferencia.getNomeDoOperador();
        this.conta =  transferencia.getConta();
    }

}
