package com.github.gmsdeveloper04.controllers.jornadas;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gmsdeveloper04.controllers.jornadas.dto.JornadasPostDto;
import com.github.gmsdeveloper04.controllers.jornadas.dto.JornadasPostResponseDto;
import com.github.gmsdeveloper04.domain.Jornada;
import com.github.gmsdeveloper04.exception.InternalServerErrorException;
import com.github.gmsdeveloper04.usecases.CriadorDeJornadaUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("gestao-jornadas/v1/jornadas")
public class JornadasController {


	private CriadorDeJornadaUseCase criadorDeJornada;
	private ObjectMapper mapper;

	public JornadasController(CriadorDeJornadaUseCase criador, ObjectMapper mapper) {
		this.criadorDeJornada = criador;
		this.mapper = mapper;
	}

	@PostMapping()
	public ResponseEntity<JornadasPostResponseDto> criarJornada(
			@RequestHeader("x-correlationId") String correlationId,
			@RequestBody JornadasPostDto jornadaDto){
		
		log.info("Recebendo request de criação de jornada. correlation={}",correlationId);
		
		try {

			log.info("Convertendo JornadasPostDto para o domain Jornada correlation={}",correlationId);

			//CONVERTENDO DTO EM DOMINIO
			Jornada jornadaDomain = this.mapper.convertValue(jornadaDto, Jornada.class);

			log.info("Executando use case CriadorDeJornada.criarJornada correlation={}",correlationId);

			//EXECUTANDO USE CASE COM DOMINIO E COLETANDO JORNADA COM IDs
			Jornada jornadaCriada = this.criadorDeJornada.criarJornada(jornadaDomain);
			
			log.info("Use case executado com sucesso! correlation={}",correlationId);
			
			log.info("Convertendo Jornada em JornadasPostResponseDto correlation={}",correlationId);

			//MAPEANDO RETORNO API
			JornadasPostResponseDto jornadaReturnPostDto = this.mapper.convertValue(jornadaCriada, JornadasPostResponseDto.class);
			
			log.info("Criando URI location header. correlation={}",correlationId);
			
			//CRIANDO URI POST
			URI uri  = new URI("/gestao-jornadas/v1/jornadas/"+jornadaReturnPostDto.getId().toString());

			log.info("URI Criada. {}, correlation={}",uri.toString(),correlationId);
			
			//RETORNANDO
			return ResponseEntity.created(uri).body(jornadaReturnPostDto);

		}catch(IllegalArgumentException e) {
			log.info("Falha ao converter objeto com objectMapper. e={} correlation={}",e.getMessage(),correlationId);
			throw new InternalServerErrorException("Ocorreu um erro inesperado, tente novamente mais tarde");
		}catch(RuntimeException e) { 
			throw e;
		}catch(Exception e) {
			log.info("Falha ao processar solicitação. e={} correlation={}",e.getMessage(),correlationId);
			throw new InternalServerErrorException("Ocorreu um erro inesperado, tente novamente mais tarde");
		}
	}
}
