package med.voll.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);


    @Query("""
                        SELECT M FROM Medico M
                        WHERE
                        M.ativo = TRUE
                        AND
                        M.especialidade = :especialidade
                        AND
                        M.id NOT IN(
                            SELECT C.medico.id FROM Consulta C
                            WHERE
                            C.data = :data
                    AND
                            C.motivoCancelamento IS NULL
                        )
                        ORDER BY RAND()
                        LIMIT 1
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, @NotNull @Future LocalDateTime data);


    @Query(""" 
            SELECT M.ativo
                       FROM Medico M
                                   WHERE
                                               M.id = :idMedico

            """)
    Boolean findAtivoById(Long idMedico);
}
