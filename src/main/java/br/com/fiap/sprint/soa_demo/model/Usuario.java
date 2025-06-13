package br.com.fiap.sprint.soa_demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    public enum Status { ATIVO, AUTOEXCLUIDO }

    private Long id;
    private String nome;
    private double limiteGastoSemanal;
    private double gastoAtualSemanal;
    private Status status;
}