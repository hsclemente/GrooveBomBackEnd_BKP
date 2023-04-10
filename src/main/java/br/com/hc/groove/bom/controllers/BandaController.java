package br.com.hc.groove.bom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.hc.groove.bom.models.dtos.BandaDTO;
import br.com.hc.groove.bom.services.BandaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/banda")
public class BandaController {
    @Autowired
    private BandaService bandaService;

    @GetMapping
    public ResponseEntity<?> buscarBandas(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        try {
            return ResponseEntity.ok(bandaService.buscarBandas(paginacao));
        } catch (Exception ex1) {
            ex1.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro ao buscar as bandas");
        }
    }

    @PostMapping
    public ResponseEntity<?> criarBanda(@RequestBody@Valid BandaDTO banda, UriComponentsBuilder uriBuilder) {
        try {
            BandaDTO bandaDTO = bandaService.criarBanda(banda);
            return ResponseEntity.created(uriBuilder.path("banda/{id}").buildAndExpand(bandaDTO.id()).toUri()).body(bandaDTO);
        } catch (Exception ex1) {
            ex1.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro ao criar a banda");
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> alterarNome(@RequestBody @Valid BandaDTO banda, @PathVariable("id")Long bandaId) {
        try {
            return ResponseEntity.ok(bandaService.alterarNome(banda, bandaId));
        } catch (Exception ex1) {
            return ResponseEntity.internalServerError().body("erro ao alterar o nome da banda");
        }
    }
}
