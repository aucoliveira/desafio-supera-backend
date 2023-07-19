package br.com.banco.controller;

import br.com.banco.model.Transferencia;
import br.com.banco.service.BancoService;
import br.com.banco.service.dto.TransferenciaEntradaDto;
import br.com.banco.service.dto.TransferenciaSaidaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/banco")
public class BancoController {

    @Autowired
    private BancoService bancoService;

    @GetMapping
    public List<Object> list() {
        return bancoService.list();
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<Transferencia> buscaPorId(@PathVariable("id") Long id) {
        Transferencia transferencia = bancoService.buscaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(transferencia);
    }

    @GetMapping(value = "/pornome/{nomeDoOperador}")
    @ResponseBody
    public ResponseEntity<List<Transferencia>> buscaConta(@PathVariable("nomeDoOperador") String nomeDoOperador) {
        List<Transferencia> transferencia = bancoService.buscaConta(nomeDoOperador);
        return ResponseEntity.status(HttpStatus.OK).body(transferencia);
    }

    @GetMapping("/porData")
    public Page<Transferencia> buscaPorData(
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
            Pageable pageable) {
        return bancoService.buscaPorData(minDate, maxDate, pageable);
    }

    @GetMapping("/porDataNome")
    public Page<Transferencia> buscaPorDataNome(
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
            @RequestParam(value = "nome", defaultValue = "") String nomeDoOperador, Pageable pageable) {
        return bancoService.buscaPorDataNome(minDate, maxDate, nomeDoOperador, pageable);
    }
}
