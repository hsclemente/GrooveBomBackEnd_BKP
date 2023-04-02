package br.com.hc.groove.bom.models.dtos;

import br.com.hc.groove.bom.models.entities.Banda;
import lombok.Value;

@Value
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String especialidade;
    private String email;
    private Double saldo;
    private Banda banda;
}
