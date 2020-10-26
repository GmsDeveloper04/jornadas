package com.github.gmsdeveloper04.controllers.tarefas.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id", "descricao","ordemExecucao","ativo","instanteCriacao"})
public class TarefasPostResponseDto {
	
	private UUID id;
	private String descricao;
	private int ordemExecucao;
	private boolean ativo;
	private LocalDateTime instanteCriacao;
	
}
