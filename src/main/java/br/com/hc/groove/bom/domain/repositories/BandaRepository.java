package br.com.hc.groove.bom.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hc.groove.bom.domain.models.entities.Banda;

public interface BandaRepository extends JpaRepository<Banda, Long>{
}