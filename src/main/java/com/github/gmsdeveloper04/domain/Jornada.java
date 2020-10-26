package com.github.gmsdeveloper04.domain;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data
public class Jornada {
	
	private UUID id;
	private String descricao;
	private boolean ativo;
	private Set<Tarefa> tarefas;
	LocalDateTime instanteCriacao;
}
