package br.com.hc.groove.bom.models.dtos;

import br.com.hc.groove.bom.models.entities.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO (
    Long id,
    @NotBlank(message = "O usuario deve conter um nome")
    String nome,
    String descricao,
    String especialidade,
    @NotBlank(message = "O usuario deve conter um email")@Email
    String email,
    Double saldo
){
    public UsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getDescricao(), usuario.getEspecialidade(), usuario.getEmail(), usuario.getSaldo());
    } 
}
