package br.com.hc.groove.bom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.hc.groove.bom.models.dtos.UsuarioDTO;
import br.com.hc.groove.bom.services.UsuarioService;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("banda/{id}")
    public ResponseEntity<?> buscarUsuarioDaBanda(@PathVariable("id") Long bandaId) {
        try {
            return ResponseEntity.ok(usuarioService.buscarUsuarioDaBanda(bandaId));
        } catch (NoResultException ex0) {
            return ResponseEntity.noContent().build();
        } catch (Exception ex1) {
            ex1.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro ao buscar os usuario da banda");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscarUsuario(@PathVariable("id") Long usuarioId) {
        try {
            return ResponseEntity.ok(usuarioService.buscarUsuario(usuarioId));
        } catch (NoResultException ex0) {
            return ResponseEntity.noContent().build();
        } catch (Exception ex1) {
            ex1.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro ao buscar usuario");
        }
    }

    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody @Valid UsuarioDTO usuario, UriComponentsBuilder uriBuilder) {
        try {
            UsuarioDTO usuarioDTO = usuarioService.criarUsuario(usuario);
            return ResponseEntity.created(uriBuilder.path("/usuario/{id}").buildAndExpand(usuarioDTO.id()).toUri()).body(usuarioDTO);
        } catch (Exception ex1) {
            ex1.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro ao cadastrar o usuario");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> alterarUsuario(@RequestBody UsuarioDTO usuario, @PathVariable("id") Long usuarioId) {
        try {
            return ResponseEntity.ok(usuarioService.alterarUsuario(usuario, usuarioId));
        } catch (NoResultException ex0) {
            return ResponseEntity.noContent().build();
        } catch (Exception ex1) {
            ex1.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro ao alterar o usuario");
        }
    }

    @PatchMapping("{id}")//repensar
    public ResponseEntity<?> alterarSaldo(@RequestBody UsuarioDTO usuario, @PathVariable("id") Long usuarioId) {
        try {
            return ResponseEntity.ok("");
        } catch (NoResultException ex0) {
            return ResponseEntity.noContent().build();
        } catch (Exception ex1) {
            ex1.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro ao cadastrar usuario");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> desativarUsuario(@PathVariable("id") Long usuarioId) {
        try {
            return ResponseEntity.ok(usuarioService.desativarUsuario(usuarioId));
        } catch (NoResultException ex0) {
            return ResponseEntity.noContent().build();
        } catch (Exception ex1) {
            ex1.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro ao alterar o usuario");
        }
    }
}
