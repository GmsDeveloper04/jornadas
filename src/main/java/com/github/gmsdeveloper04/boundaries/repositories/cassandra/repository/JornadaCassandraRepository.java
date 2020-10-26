package com.github.gmsdeveloper04.boundaries.repositories.cassandra.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.github.gmsdeveloper04.boundaries.repositories.cassandra.tables.Jornadas;

@Repository
public interface JornadaCassandraRepository extends CassandraRepository<Jornadas,UUID>{

}
