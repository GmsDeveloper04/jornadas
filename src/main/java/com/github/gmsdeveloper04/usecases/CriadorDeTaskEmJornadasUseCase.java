package com.github.gmsdeveloper04.usecases;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.github.gmsdeveloper04.domain.Jornada;
import com.github.gmsdeveloper04.domain.Tarefa;
import com.github.gmsdeveloper04.exception.BadRequestException;
import com.github.gmsdeveloper04.exception.ResourceNotFoundException;
import com.github.gmsdeveloper04.usecases.respository.JornadaRepository;

@Service
public class CriadorDeTaskEmJornadasUseCase {
	
	private JornadaRepository jornadaRepository;
	
	public CriadorDeTaskEmJornadasUseCase(JornadaRepository jornadaRepository) {
		this.jornadaRepository = jornadaRepository;
	}
	
	public Tarefa criarTask(Tarefa tarefa, UUID jornadaId) {
		
		Optional<Jornada> jornadaDoRepositorio = jornadaRepository.obterJornada(jornadaId);
		
		if(jornadaDoRepositorio.isPresent()) {
			Jornada jornada = jornadaDoRepositorio.get();
			
			//SE PRIMEIRA TASK
			if(jornada.getTarefas() == null) {
				jornada.setTarefas(new HashSet<>());
			}
			
			//PREPARANDO TASK
			tarefa.setId(UUID.randomUUID());
			tarefa.setInstanteCriacao(LocalDateTime.now());
			
			/* ----------- REGRAS ----------- */
			
			//TASK COM ORDENS IGUAIS 
			if(jornada.getTarefas().stream().anyMatch(x -> x.getOrdemExecucao() == tarefa.getOrdemExecucao())) {
				throw new BadRequestException("Já existe uma tarefa criada com orde de execução "+tarefa.getOrdemExecucao());
			}
			
			//INCLUINDO TAREFA
			jornada.getTarefas().add(tarefa);
			
			//SALVAR JORNADA
			jornadaRepository.salvarJornada(jornada);
			
			return tarefa;
		}
		
		throw new ResourceNotFoundException("Não encontrada jornadas com este ID.");
	}

}
