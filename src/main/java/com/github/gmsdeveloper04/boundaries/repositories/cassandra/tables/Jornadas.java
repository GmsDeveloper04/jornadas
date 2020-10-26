package com.github.gmsdeveloper04.boundaries.repositories.cassandra.tables;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Frozen;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.github.gmsdeveloper04.boundaries.repositories.cassandra.udt.Tarefas;

import lombok.Data;

@Data
@Table("jornadas")
public class Jornadas {

	@PrimaryKey("id")
	private UUID id;
	@Column("descricao")
	private String descricao;
	@Column("ativo")
	private boolean ativo;
	@Frozen
	@Column("tarefas")
	private Set<Tarefas> tarefas;
	@Column("instanteCriacao")
	LocalDateTime instanteCriacao;
}
