package br.com.fiap.sprint.soa_demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RegistrarApostaRequest {

    @NotNull(message = "O valor da aposta não pode ser nulo.")
    @Positive(message = "O valor da aposta deve ser positivo.")
    private Double valor;

}
