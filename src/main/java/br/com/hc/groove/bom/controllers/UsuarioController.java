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

import br.com.hc.groove.bom.models.dtos.UsuarioDTO;
import br.com.hc.groove.bom.services.UsuarioService;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("{id}")
    public ResponseEntity<?> buscarUsuario(@PathVariable("id") Long usuarioId) {
        try {
            return ResponseEntity.ok(usuarioService.buscarUsuario(usuarioId));
        } catch (NoResultException ex0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario não cadastrado");
        } catch (Exception ex1) {
            ex1.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar usuario");
        }
    }

    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody @Valid UsuarioDTO usuario) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criarUsuario(usuario));
        } catch (Exception ex1) {
            ex1.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar o usuario");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> alterarUsuario(@RequestBody UsuarioDTO usuario, @PathVariable("id") Long usuarioId) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.alterarUsuario(usuario, usuarioId));
        } catch (Exception ex1) {
            ex1.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao alterar o usuario");
        }
    }

    @PatchMapping("{id}")//repensar
    public ResponseEntity<?> alterarSaldo(@RequestBody UsuarioDTO usuario, @PathVariable("id") Long usuarioId) {
        try {
            return ResponseEntity.ok("");
        } catch (Exception ex1) {
            ex1.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar usuario");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> desativarUsuario(@PathVariable("id") Long usuarioId) {
        try {
            return ResponseEntity.ok("");
        } catch (Exception ex1) {
            ex1.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao alterar o usuario");
        }
    }
}
