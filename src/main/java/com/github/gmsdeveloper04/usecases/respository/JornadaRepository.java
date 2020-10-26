package com.github.gmsdeveloper04.usecases.respository;

import java.util.Optional;
import java.util.UUID;

import com.github.gmsdeveloper04.domain.Jornada;

public interface JornadaRepository{
	
	public Jornada criarJornada(Jornada jornada);

	public Optional<Jornada> obterJornada(UUID jornadaId);
	
	public void salvarJornada(Jornada jornada);
	
}