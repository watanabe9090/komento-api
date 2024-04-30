package br.com.takaedev.komento.threads;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/threads")
public class ThreadController {
    @PostMapping
    public ResponseEntity<Void> saveThread() {
        return null;
    }
}
