package br.com.hc.groove.bom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.hc.groove.bom.domain.models.dtos.TarefaDTO;
import br.com.hc.groove.bom.domain.models.forms.TarefaForm;
import br.com.hc.groove.bom.services.TarefaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefa")
@SecurityRequirement(name = "bearer-key")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping("{usuarioId}")
    public ResponseEntity<?> buscarTarefas(
        Boolean nConcluidos,
        @PathVariable("usuarioId") Long usuarioId,
        @RequestParam(name = "page_size", defaultValue = "10", required = false) int pageSize,
        @RequestParam(name = "page_index", defaultValue = "0", required = false) int pageIndex
    ) {
        return ResponseEntity.ok(tarefaService.buscarTarefas(nConcluidos, usuarioId, pageSize, pageIndex));
    }

    @PostMapping
    public ResponseEntity<?> criarTarefa(@RequestBody@Valid TarefaForm form, UriComponentsBuilder uriBuilder) {
        TarefaDTO tarefaDTO = tarefaService.criarTarefa(form);
        return ResponseEntity.created(uriBuilder.path("/tarefa/{usuarioId}").buildAndExpand(tarefaDTO.usuarioId()).toUri()).body(tarefaDTO);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> concluirTarefa(@PathVariable("id") Long tarefaId) {
        return ResponseEntity.ok(tarefaService.concluirTarefa(tarefaId));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarTarefa(@PathVariable("id") Long tarefaId) {
        return ResponseEntity.ok(tarefaService.deletarTarefa(tarefaId));
    }
}
