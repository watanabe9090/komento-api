package br.com.takaedev.komento.threads;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThreadRepository extends JpaRepository<Thread, Long> {
    Optional<Thread> findByName(String name);
}
