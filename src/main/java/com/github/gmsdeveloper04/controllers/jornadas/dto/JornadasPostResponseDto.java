package com.github.gmsdeveloper04.controllers.jornadas.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.gmsdeveloper04.boundaries.repositories.cassandra.udt.Tarefas;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id", "descricao", "instanteCriacao", "tarefas"})
public class JornadasPostResponseDto {

	private UUID id;
	private String descricao;
	private boolean ativo;
	private Set<Tarefas> tarefas;
	LocalDateTime instanteCriacao;
}
