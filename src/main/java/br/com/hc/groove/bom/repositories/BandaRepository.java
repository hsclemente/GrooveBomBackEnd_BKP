package br.com.hc.groove.bom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hc.groove.bom.models.entities.Banda;

public interface BandaRepository extends JpaRepository<Banda, Long>{
}
