package br.com.takaedev.komento.threads;

import br.com.takaedev.komento.base.Auditing;
import br.com.takaedev.komento.base.InvalidAttributeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThreadService {
    private final ThreadRepository repository;

    public Optional<Thread> findByName(String name) {
        return repository.findByName(name);
    }

    public record DTOSaveThreadReq(String name, String description){}

    public void save(DTOSaveThreadReq dto) throws InvalidAttributeException {
        if(dto.name == null || dto.name.isEmpty()) {
            throw new InvalidAttributeException("");
        }
        Thread newThread = new Thread();
        newThread.setName(dto.name);
        newThread.setDescription(dto.description);
        newThread.setAuditing(Auditing.getNew(null));
        repository.save(newThread);
    }
}
