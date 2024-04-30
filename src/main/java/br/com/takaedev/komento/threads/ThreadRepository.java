package br.com.takaedev.komento.threads;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ThreadRepository extends MongoRepository<Thread, String> {
    Optional<Thread> findByName(String name);
}
