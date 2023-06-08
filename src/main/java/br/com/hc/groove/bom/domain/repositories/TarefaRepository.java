package br.com.hc.groove.bom.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.hc.groove.bom.domain.models.entities.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    @Query(value = """
            SELECT * FROM gb_tarefas
            WHERE fk_usuario_id = :usuarioId
                AND is_concluido = COALESCE(CAST(CAST(:nConcluido AS TEXT) AS BOOLEAN), is_concluido)
            ORDER BY data_tarefa
            LIMIT :pageSize
            OFFSET (:pageSize * :pageIndex)
        """, 
        nativeQuery = true
    )
    List<Tarefa> buscarTarefas(@Param("nConcluido") Boolean nConcluidas, 
                               @Param("pageSize") Integer pageSize,
                               @Param("pageIndex") Integer pageIndex
    );
}
