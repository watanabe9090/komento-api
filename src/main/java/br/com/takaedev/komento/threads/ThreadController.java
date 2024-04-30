package br.com.takaedev.komento.threads;

import br.com.takaedev.komento.base.InvalidAttributeException;
import br.com.takaedev.komento.threads.ThreadService.DTOSaveThreadReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/threads")
public class ThreadController {
    private final ThreadService threadService;

    @GetMapping("/{name}")
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
