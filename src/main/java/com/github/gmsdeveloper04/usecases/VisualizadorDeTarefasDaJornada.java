package com.github.gmsdeveloper04.usecases;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.github.gmsdeveloper04.domain.Jornada;
import com.github.gmsdeveloper04.domain.Tarefa;
import com.github.gmsdeveloper04.exception.ResourceNotFoundException;
import com.github.gmsdeveloper04.usecases.respository.JornadaRepository;

@Service
public class VisualizadorDeTarefasDaJornada {

	private JornadaRepository jornadaRepository;

	public VisualizadorDeTarefasDaJornada(JornadaRepository jornadaRepository) {
		this.jornadaRepository = jornadaRepository;
	}

	public Set<Tarefa> visualizarTodasTarefas(UUID jornadaId){
		Optional<Jornada> jornadaObtida = jornadaRepository.obterJornada(jornadaId);

		if(jornadaObtida.isPresent()) {
			return jornadaObtida.get().getTarefas();
		}
		throw new ResourceNotFoundException("Não encontrada jornadas com este ID.");
	}


	public Tarefa visualizarTarefaPorId(UUID jornadaId,UUID tarefaId) {
		Optional<Jornada> jornadaObtida = jornadaRepository.obterJornada(jornadaId);

		if(jornadaObtida.isPresent()) {
			Set<Tarefa> tarefas = jornadaObtida.get().getTarefas();

			Optional<Tarefa> tarefaDoId = tarefas.stream().filter(x-> x.getId().equals(tarefaId)).findAny();

			if(tarefaDoId.isPresent()) {
				return tarefaDoId.get();
			}
			throw new ResourceNotFoundException("Não encontrada tarefa com este ID");
		}
		throw new ResourceNotFoundException("Não encontrada jornadas com este ID.");
	}
}
