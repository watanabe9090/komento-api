package br.com.takaedev.komento.threads;

import br.com.takaedev.komento.base.Auditing;
import br.com.takaedev.komento.base.BaseService;
import br.com.takaedev.komento.base.InvalidAttributeException;
import br.com.takaedev.komento.base.InvalidStateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThreadService extends BaseService {
    private final ThreadRepository repository;
    public record DTOSaveThreadReq(String name, String description){}

    public Optional<Thread> findByName(String name) {
        return repository.findByName(name);
    }

    public Thread save(DTOSaveThreadReq dto) throws InvalidAttributeException, InvalidStateException {
        if(dto.name == null || dto.name.isEmpty()) {
            throw new InvalidAttributeException("Attribute 'name' must not be empty or null");
        }
        if(repository.findByName(dto.name).isPresent()) {
            throw new InvalidStateException("Thread already exists for name %s", dto.name);
        }
        Thread newThread = new Thread();
        newThread.setName(dto.name);
        newThread.setDescription(dto.description);
        Auditing.neow(newThread, getPrincipal());
        return repository.save(newThread);
    }


}
