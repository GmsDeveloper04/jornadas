package com.github.gmsdeveloper04.boundaries.repositories.cassandra.udt;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Data;

@Data
@UserDefinedType("tarefa")
public class Tarefas {
	
	@Column("id")
	private UUID id;
	@Column("ordemexecucao")
	private int ordemExecucao;
	@Column("descricao")
	private String descricao;
	@Column("ativo")
	private boolean ativo;
	@Column("instantecriacao")
	private LocalDateTime instanteCriacao;
	
}
