package br.com.takaedev.komento.threads;

import br.com.takaedev.komento.base.InvalidAttributeException;
import br.com.takaedev.komento.threads.ThreadService.DTOSaveThreadReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/threads")
public class ThreadController {
    private final ThreadService threadService;

    @GetMapping("/{name}")
    @PreAuthorize("hasRole('K_ADMIN')")
    public ResponseEntity<Thread> getThread(@PathVariable String name) {
        Optional<Thread> possibleThread = threadService.findByName(name);
        return possibleThread
                .map(thread -> new ResponseEntity<>(thread, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<Void> saveThread(@RequestBody DTOSaveThreadReq dto) throws InvalidAttributeException {
        threadService.save(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
