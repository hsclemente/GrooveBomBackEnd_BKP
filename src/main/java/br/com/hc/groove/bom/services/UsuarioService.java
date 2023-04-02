package br.com.hc.groove.bom.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hc.groove.bom.models.dtos.UsuarioDTO;
import br.com.hc.groove.bom.models.entities.Usuario;
import br.com.hc.groove.bom.models.forms.UsuarioForm;
import br.com.hc.groove.bom.repositories.UsuarioRepository;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper mapper;

    public UsuarioDTO buscarUsuario(Long usuarioId) throws NoResultException, Exception {
        return mapper.map(usuarioRepository.findByIdAndAtivoIsTrue(usuarioId).orElseThrow(NoResultException::new), UsuarioDTO.class);
    }

    public UsuarioDTO criarUsuario(@Valid UsuarioForm usuario) throws Exception {
        return mapper.map(usuarioRepository.save(mapper.map(usuario, Usuario.class)), UsuarioDTO.class);
    }

    public UsuarioDTO alterarUsuario(UsuarioForm usuario, Long usuarioId) throws NoResultException, Exception {
        Usuario usuarioOriginal = usuarioRepository.findByIdAndAtivoIsTrue(usuarioId).orElseThrow(NoResultException::new);
        
        if (usuario.getBanda() != null) {
            usuarioOriginal.setBanda(usuario.getBanda());
        }

        if (usuario.getDescricao() != null) {
            usuarioOriginal.setDescricao(usuario.getDescricao());
        }

        if (usuario.getEmail() != null) {
            usuarioOriginal.setEmail(usuario.getEmail());
        }

        if (usuario.getEspecialidade() != null) {
            usuarioOriginal.setEspecialidade(usuario.getEspecialidade());
        } 

        if (usuario.getNome() != null) {
            usuarioOriginal.setNome(usuario.getNome());
        }

        return mapper.map(usuarioRepository.save(usuarioOriginal), UsuarioDTO.class);
    }
}
