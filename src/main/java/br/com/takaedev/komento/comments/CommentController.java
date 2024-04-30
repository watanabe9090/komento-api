package br.com.takaedev.komento.comments;

import br.com.takaedev.komento.base.InvalidAttributeException;
import br.com.takaedev.komento.base.InvalidStateException;
import br.com.takaedev.komento.threads.ThreadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;


    @GetMapping
    public ResponseEntity<Page<Comment>> getComments(
            @RequestParam(value = "threadId", required = false) Long threadId,
            Pageable pageable
    ) {
        Page<Comment> comments = commentService.findCommentsByThreadId(threadId, pageable);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('K_ADMIN')")
    public ResponseEntity<Void> saveComment(@RequestBody CommentService.DTOSaveCommentReq dto) throws InvalidAttributeException, InvalidStateException {
        commentService.save(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
