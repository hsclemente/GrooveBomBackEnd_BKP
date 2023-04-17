package br.com.hc.groove.bom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.hc.groove.bom.models.dtos.SaldoDTO;
import br.com.hc.groove.bom.models.dtos.UsuarioDTO;
import br.com.hc.groove.bom.services.UsuarioService;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PatchMapping("{usuarioId}/banda/{bandaId}")// em fase de desenvolvimento
    public ResponseEntity<?> adicionarBanda(@PathVariable("usuarioId") Long usuarioId, @PathVariable("bandaId") Long bandaId) {
        try {
            return ResponseEntity.ok(usuarioService.adicionarBanda(usuarioId, bandaId));
        } catch (NoResultException ex0) {
            return ResponseEntity.noContent().build();
        } catch (Exception ex1) {
            ex1.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro ao adicionar o usuario à banda");
        }
    }

    @DeleteMapping("{usuarioId}/banda")
    public ResponseEntity<?> removerBanda(@PathVariable("usuarioId") Long usuarioId) {
        try {
            return ResponseEntity.ok(usuarioService.removerBanda(usuarioId));
        } catch (NoResultException ex0) {
            return ResponseEntity.noContent().build();
        } catch (Exception ex1) {
            ex1.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro ao remover o usuario da banda");
        }
    }

    @GetMapping("banda/{id}")
    public ResponseEntity<?> buscarUsuariosDaBanda(@PathVariable("id") Long bandaId) {
        try {
            return ResponseEntity.ok(usuarioService.buscarUsuariosDaBanda(bandaId));
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

    @PatchMapping("{id}")
    public ResponseEntity<?> alterarSaldo(@RequestBody@Valid SaldoDTO saldo, @PathVariable("id") Long usuarioId) {
        try {
            return ResponseEntity.ok(usuarioService.alterarSaldo(saldo, usuarioId));
        } catch (NoResultException ex0) {
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex1) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (Exception ex2) {
            ex2.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro ao alterar o saldo do usuario");
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
