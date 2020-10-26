package com.github.gmsdeveloper04.boundaries.repositories.cassandra.adapter;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gmsdeveloper04.boundaries.repositories.cassandra.repository.JornadaCassandraRepository;
import com.github.gmsdeveloper04.boundaries.repositories.cassandra.tables.Jornadas;
import com.github.gmsdeveloper04.domain.Jornada;
import com.github.gmsdeveloper04.usecases.respository.JornadaRepository;

@Service
public class JornadaRepositoryAdapter  implements JornadaRepository{

	private JornadaCassandraRepository cassandraRepository;
	private ObjectMapper mapper;

	public JornadaRepositoryAdapter(JornadaCassandraRepository repo, ObjectMapper mapper) {
		this.cassandraRepository = repo;
		this.mapper = mapper;
	}

	@Override
	public Jornada criarJornada(Jornada jornada) {

		Jornadas novaJornada = mapper.convertValue(jornada, Jornadas.class);

		novaJornada.setId(UUID.randomUUID()); //GENRANDO ID
		novaJornada.setInstanteCriacao(LocalDateTime.now()); //DATA HORA CRIAÇÃO

		cassandraRepository.save(novaJornada);

		return mapper.convertValue(novaJornada, Jornada.class);
	}

	@Override
	public Optional<Jornada> obterJornada(UUID jornadaId) {

		Optional<Jornadas> findById = this.cassandraRepository.findById(jornadaId);

		if(findById.isPresent()) {
			Jornada convertValue = this.mapper.convertValue(findById.get(),Jornada.class);
			return Optional.of(convertValue);
		}

		return Optional.empty();
	}

	@Override
	public void salvarJornada(Jornada jornada) {

		Jornadas jornadaToSave = mapper.convertValue(jornada, Jornadas.class);

		this.cassandraRepository.save(jornadaToSave);
	}

}
