package br.com.hc.groove.bom.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hc.groove.bom.domain.models.dtos.UsuarioDTO;
import br.com.hc.groove.bom.domain.models.entities.Usuario;
import br.com.hc.groove.bom.domain.models.forms.SaldoForm;
import br.com.hc.groove.bom.domain.models.forms.UsuarioForm;
import br.com.hc.groove.bom.domain.repositories.BandaRepository;
import br.com.hc.groove.bom.domain.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BandaRepository bandaRepository;
        
    public List<UsuarioDTO> buscarUsuariosDaBanda(Long bandaId) {
        return usuarioRepository.findAllByBanda(bandaRepository.findById(bandaId).orElseThrow(EntityNotFoundException::new)).stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }

    public UsuarioDTO buscarUsuario(Long usuarioId) {
        return new UsuarioDTO(usuarioRepository.findByIdAndAtivoIsTrue(usuarioId).orElseThrow(EntityNotFoundException::new));
    }

    public UsuarioDTO criarUsuario(@Valid UsuarioForm usuario) {
        return new UsuarioDTO(usuarioRepository.save(new Usuario(usuario)));
    }

    public UsuarioDTO alterarUsuario(UsuarioForm usuario, Long usuarioId) {
        return new UsuarioDTO(usuarioRepository.save(usuarioRepository.findByIdAndAtivoIsTrue(usuarioId).orElseThrow(EntityNotFoundException::new).put(usuario)));
    }

    public String desativarUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findByIdAndAtivoIsTrue(usuarioId).orElseThrow(EntityNotFoundException::new);
        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
        
        return "Usuario desativado com sucesso";
    }

    public UsuarioDTO alterarSaldo(@Valid SaldoForm saldo, Long usuarioId) {
        Usuario usuario = usuarioRepository.findByIdAndAtivoIsTrue(usuarioId).orElseThrow(EntityNotFoundException::new);
        usuario.setSaldo(saldo.valor());
        
        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    public String adicionarBanda(Long usuarioId, Long bandaId) {
        usuarioRepository.UpdateBanda(usuarioId, bandaId);
        return "Usuario adicionado à banda com sucesso";
    }

    public String removerBanda(Long usuarioId) {
        Usuario usuario = usuarioRepository.findByIdAndAtivoIsTrue(usuarioId).orElseThrow(EntityNotFoundException::new);
        usuario.setBanda(null);
        usuarioRepository.save(usuario);
        return "Banda removida com sucesso";
    }


}
