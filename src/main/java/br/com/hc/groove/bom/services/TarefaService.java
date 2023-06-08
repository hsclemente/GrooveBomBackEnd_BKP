package br.com.hc.groove.bom.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hc.groove.bom.domain.models.dtos.TarefaDTO;
import br.com.hc.groove.bom.domain.models.entities.Tarefa;
import br.com.hc.groove.bom.domain.models.forms.TarefaForm;
import br.com.hc.groove.bom.domain.repositories.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    public List<TarefaDTO> buscarTarefas(Boolean nConcluidos, Long usuarioId, Integer pageSize, Integer pageIndex) {
        return tarefaRepository.buscarTarefas(nConcluidos, pageSize, pageIndex).stream().map(TarefaDTO::new).collect(Collectors.toList());
    }

    public TarefaDTO criarTarefa(@Valid TarefaForm form) {
        return new TarefaDTO(tarefaRepository.save(new Tarefa(form)));
    }

    public TarefaDTO concluirTarefa(Long tarefaId) {
        Tarefa tarefa = tarefaRepository.findById(tarefaId).orElseThrow(EntityNotFoundException::new);
        tarefa.setConcluido(true);
        return new TarefaDTO(tarefaRepository.save(tarefa));
    }

    public String deletarTarefa(Long tarefaId) {
        tarefaRepository.deleteById(tarefaId);
        return "tarefa excluida com sucesso";
    }
}
