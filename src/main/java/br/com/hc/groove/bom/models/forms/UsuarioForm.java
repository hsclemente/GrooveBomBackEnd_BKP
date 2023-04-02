package br.com.hc.groove.bom.models.forms;

import br.com.hc.groove.bom.models.entities.Banda;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class UsuarioForm {
    @NotBlank(message = "O usuario deve conter um nome")
    private String nome;
    private String descricao;
    private String especialidade;
    @NotBlank(message = "O usuario deve conter um email")
    private String email;
    private Banda banda;
}
