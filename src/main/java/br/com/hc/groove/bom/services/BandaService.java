package br.com.hc.groove.bom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.hc.groove.bom.models.dtos.BandaDTO;
import br.com.hc.groove.bom.models.entities.Banda;
import br.com.hc.groove.bom.repositories.BandaRepository;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;

@Service
public class BandaService {

    @Autowired
    private BandaRepository bandaRepository;

    public Page<BandaDTO> buscarBandas(Pageable paginacao) {
        return bandaRepository.findAll(paginacao).map(BandaDTO::new);
    }

    public BandaDTO criarBanda(BandaDTO banda) {
        return new BandaDTO(bandaRepository.save(new Banda(banda)));
    }

    public BandaDTO alterarNome(@Valid BandaDTO bandaDTO, Long bandaId) {
        Banda banda = bandaRepository.findById(bandaId).orElseThrow(NoResultException::new);
        banda.setNome(bandaDTO.nome());
        
        return new BandaDTO(bandaRepository.save(banda));
    }
    
}
