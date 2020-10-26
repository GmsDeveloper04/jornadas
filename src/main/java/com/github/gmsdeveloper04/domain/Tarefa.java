package com.github.gmsdeveloper04.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class Tarefa {
	private UUID id;
	private int ordemExecucao;
	private String descricao;
	private LocalDateTime instanteCriacao;	
}
