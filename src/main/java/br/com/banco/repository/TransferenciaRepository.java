package br.com.banco.repository;

import br.com.banco.model.Transferencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {


    @Query(value = "select obj from Transferencia obj where obj.nomeDoOperador like %?1%")
    List<Transferencia> findByNomeDoOperador(String nomeDoOperador);

    @Query(value = "select obj from Transferencia obj where obj.dataTranferencia between ?1 and ?2")
    Page<Transferencia> findTransferencia(LocalDate min, LocalDate max, Pageable pageable);

    @Query(value = "select obj from Transferencia obj where obj.dataTranferencia between ?1 and ?2 and obj.nomeDoOperador like %?3%")
    Page<Transferencia> findTransferenciaPorDataNome(LocalDate min, LocalDate max,String nomeDoOperador, Pageable pageable);

}
