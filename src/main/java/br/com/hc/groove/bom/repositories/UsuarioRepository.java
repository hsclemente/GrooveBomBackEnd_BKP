package br.com.hc.groove.bom.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.hc.groove.bom.models.entities.Banda;
import br.com.hc.groove.bom.models.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByIdAndAtivoIsTrue(Long id);

    List<Usuario> findAllByBanda(Banda orElseThrow);

    @Modifying
    @Transactional
    @Query(value = """
        DO
            $$
                BEGIN
            	    IF EXISTS (SELECT FROM gb_bandas gb WHERE id = :bandaId) AND EXISTS (SELECT FROM gb_usuarios gu WHERE id = :usuarioId) THEN
            		    UPDATE gb_usuarios SET banda = :bandaId WHERE id = :usuarioId;
            	    END IF;
                END
            $$
    """, nativeQuery = true)
    void UpdateBanda(@Param("usuarioId") Long usuarioId, @Param("bandaId") Long bandaId);
}
