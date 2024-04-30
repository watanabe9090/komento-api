package br.com.takaedev.komento.comments;

import br.com.takaedev.komento.base.InvalidAttributeException;
import br.com.takaedev.komento.threads.ThreadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @PreAuthorize("hasRole('K_ADMIN')")
    public ResponseEntity<Void> savePost(@RequestBody CommentService.DTOSaveCommentReq dto) throws InvalidAttributeException {
        commentService.save(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
