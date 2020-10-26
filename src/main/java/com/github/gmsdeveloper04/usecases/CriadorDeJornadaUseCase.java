package com.github.gmsdeveloper04.usecases;

import org.springframework.stereotype.Service;

import com.github.gmsdeveloper04.domain.Jornada;
import com.github.gmsdeveloper04.usecases.respository.JornadaRepository;

@Service
public class CriadorDeJornadaUseCase {
	
	private JornadaRepository jornadaRepository;
	
	public CriadorDeJornadaUseCase(JornadaRepository jornadaRepository) {
		this.jornadaRepository = jornadaRepository;
	}
	
	public Jornada criarJornada(Jornada jornada) {
		return jornadaRepository.criarJornada(jornada);
	}
}
