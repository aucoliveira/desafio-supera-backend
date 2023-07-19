package br.com.banco.service;

import br.com.banco.model.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.service.dto.TransferenciaEntradaDto;
import br.com.banco.service.dto.TransferenciaSaidaDto;
import br.com.banco.service.exceptions.TransferenciaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class BancoService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    public List<Object> list() {
        List<Object> listDto = new ArrayList<>();
        List<Transferencia> listaTransferencia = transferenciaRepository.findAll();
        for (Transferencia transferencia: listaTransferencia ) {
            if (transferencia.getOperacaoBancaria().equals("DEPOSITO")
                    || transferencia.getOperacaoBancaria().equals("SAQUE")) {
                TransferenciaEntradaDto entradaDto = new TransferenciaEntradaDto(transferencia);
                listDto.add(entradaDto);
            } else {
                TransferenciaSaidaDto saidaDto = new TransferenciaSaidaDto(transferencia);
                listDto.add(saidaDto);
            }
        }
        return listDto;
    }

    public Transferencia buscaPorId(Long id) {
        Transferencia transferencia = transferenciaRepository.findById(id).orElse(null);
        if (transferencia == null) {
            throw new TransferenciaNaoEncontradaException("Transferencia não encontrada!");
        }
        return transferencia;
    }

    public List<Transferencia> buscaConta(String nomeDoOperador) {
        List<Transferencia> transferencia = transferenciaRepository.findByNomeDoOperador(nomeDoOperador);
        //System.out.println(transferencia.getNomeDoOperador());
        return transferencia;
    }

    public Page<Transferencia> buscaPorData(String minDate, String maxDate, Pageable pageable) {
        LocalDate today =LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate min = minDate.equals("") ? today.minusYears(1) : LocalDate.parse(minDate);
        LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);

        return transferenciaRepository.findTransferencia(min, max, pageable);
    }

    public Page<Transferencia> buscaPorDataNome(String minDate, String maxDate, String nomeDoOperador, Pageable pageable) {
        LocalDate today =LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate min = minDate.equals("") ? today.minusYears(1) : LocalDate.parse(minDate);
        LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);

        return transferenciaRepository.findTransferenciaPorDataNome(min, max,nomeDoOperador, pageable);
    }



}