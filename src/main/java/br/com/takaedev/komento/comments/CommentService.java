package br.com.takaedev.komento.comments;

import br.com.takaedev.komento.base.Auditing;
import br.com.takaedev.komento.base.BaseService;
import br.com.takaedev.komento.base.InvalidAttributeException;
import br.com.takaedev.komento.threads.Thread;
import br.com.takaedev.komento.threads.ThreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService extends BaseService {
    private final CommentRepository repository;
    private final ThreadRepository threadRepository;

    public record DTOSaveCommentReq(String message, Long threadId){}

    public Comment save(DTOSaveCommentReq dto) throws InvalidAttributeException {
        if(dto.message == null || dto.message.isEmpty()) {
            throw new InvalidAttributeException("");
        }
        Optional<Thread> possibleThread = threadRepository.findById(dto.threadId);
        if(possibleThread.isEmpty()) {
            throw new InvalidAttributeException("");
        }
        Comment comment = new Comment();
        comment.setMessage(dto.message);
//        comment.setThread(possibleThread.get());
        Auditing.neow(comment, getPrincipal());
        return repository.save(comment);
    }
}
