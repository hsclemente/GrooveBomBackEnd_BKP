package br.com.hc.groove.bom.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hc.groove.bom.models.dtos.SaldoDTO;
import br.com.hc.groove.bom.models.dtos.UsuarioDTO;
import br.com.hc.groove.bom.models.entities.Usuario;
import br.com.hc.groove.bom.repositories.BandaRepository;
import br.com.hc.groove.bom.repositories.UsuarioRepository;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BandaRepository bandaRepository;
        
    public List<UsuarioDTO> buscarUsuariosDaBanda(Long bandaId) {
        return usuarioRepository.findAllByBanda(bandaRepository.findById(bandaId).orElseThrow(NoResultException::new)).stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }

    public UsuarioDTO buscarUsuario(Long usuarioId) throws NoResultException, Exception {
        return new UsuarioDTO(usuarioRepository.findByIdAndAtivoIsTrue(usuarioId).orElseThrow(NoResultException::new));
    }

    public UsuarioDTO criarUsuario(@Valid UsuarioDTO usuario) throws Exception {
        return new UsuarioDTO(usuarioRepository.save(new Usuario(usuario)));
    }

    public UsuarioDTO alterarUsuario(UsuarioDTO usuario, Long usuarioId) throws NoResultException, Exception {
        return new UsuarioDTO(usuarioRepository.save(usuarioRepository.findByIdAndAtivoIsTrue(usuarioId).orElseThrow(NoResultException::new).put(usuario)));
    }

    public String desativarUsuario(Long usuarioId) throws NoResultException, Exception {
        Usuario usuario = usuarioRepository.findByIdAndAtivoIsTrue(usuarioId).orElseThrow(NoResultException::new);
        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
        
        return "Usuario desativado com sucesso";
    }

    public UsuarioDTO alterarSaldo(SaldoDTO saldo, Long usuarioId) throws NoResultException, IllegalArgumentException, Exception {
        Usuario usuario = usuarioRepository.findByIdAndAtivoIsTrue(usuarioId).orElseThrow(NoResultException::new);
        if (saldo.valor() != null && saldo.valor() > 0) {
            usuario.setSaldo(saldo.valor());
        } else {
            throw new IllegalArgumentException();
        }
        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    public String adicionarBanda(Long usuarioId, Long bandaId) {
        usuarioRepository.UpdateBanda(usuarioId, bandaId);
        return "Usuario adicionado à banda com sucesso";
    }

    public String removerBanda(Long usuarioId) {
        Usuario usuario = usuarioRepository.findByIdAndAtivoIsTrue(usuarioId).orElseThrow(NoResultException::new);
        usuario.setBanda(null);
        usuarioRepository.save(usuario);
        return "Banda removida com sucesso";
    }


}
