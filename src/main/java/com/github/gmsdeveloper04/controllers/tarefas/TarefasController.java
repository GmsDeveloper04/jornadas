package com.github.gmsdeveloper04.controllers.tarefas;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gmsdeveloper04.controllers.tarefas.dto.TarefasPostDto;
import com.github.gmsdeveloper04.controllers.tarefas.dto.TarefasPostResponseDto;
import com.github.gmsdeveloper04.domain.Tarefa;
import com.github.gmsdeveloper04.exception.InternalServerErrorException;
import com.github.gmsdeveloper04.usecases.CriadorDeTaskEmJornadasUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("gestao-jornadas/v1/jornadas/{jornadaId}/tarefas")
public class TarefasController {

	private CriadorDeTaskEmJornadasUseCase criador;
	private ObjectMapper mapper;

	public TarefasController(
			CriadorDeTaskEmJornadasUseCase criador,
			ObjectMapper mapper
			) {
		this.criador = criador;
		this.mapper = mapper;
	}

	@PostMapping()
	public ResponseEntity<TarefasPostResponseDto> criarTarefa(
			@RequestHeader("x-correlationId") String correlationId,
			@PathVariable("jornadaId") UUID jornadaId,
			@RequestBody TarefasPostDto terefas){

		log.info("Recebendo request de criação de tarefa, jornada={}, correlationId={}",jornadaId,correlationId);
		try {

			log.info("Convertendo TarefasPostDto em Tarefa, correlationId={}",correlationId);
			//CONVERTENDO DTO EM DOMINIO
			Tarefa tarefa = mapper.convertValue(terefas, Tarefa.class);

			log.info("Executando caso de uso CriadorDeTaskEmJornadasUseCase, correlationId={}",correlationId);
			//EXECUTANDO CASO DE USO
			Tarefa tarefaCriada = criador.criarTask(tarefa, jornadaId);

			log.info("Convertendo resultado da execucao caso de uso em TarefasPostResponseDto, correlationId={}",correlationId);
			//CONVERTENDO RESULTADO DA EXECUCAO EM RESPONSE DTO
			TarefasPostResponseDto response = mapper.convertValue(tarefaCriada, TarefasPostResponseDto.class);

			log.info("Criando URI location, correlationId={}",correlationId);
			//CRIANDO LOCATION URI (201)
			StringBuilder uriBuilder = new StringBuilder()
					.append("gestao-jornadas/v1/jornadas/")
					.append(jornadaId)
					.append("/tarefas/")
					.append(tarefaCriada.getId());

			log.info("URI location criada. location={}, correlationId={}",uriBuilder,correlationId);

			URI location = new URI(uriBuilder.toString());

			//RETORNANDO
			return ResponseEntity.created(location).body(response);

		}catch(RuntimeException e) { 
			throw e;
		}catch (Exception e) {
			log.error("Falha ao criar tarefa para jornada, e={}, correlationId={}",e.getMessage(),correlationId);
			throw new InternalServerErrorException("Falha ao criar tarefa para a jornada. Tente novamente mais tarde");
		}
	}

}
