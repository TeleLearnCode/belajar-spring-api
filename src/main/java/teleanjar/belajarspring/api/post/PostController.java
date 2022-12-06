package teleanjar.belajarspring.api.post;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;
import teleanjar.belajarspring.api.post.model.PostRequest;
import teleanjar.belajarspring.api.post.model.PostResponse;
import teleanjar.belajarspring.api.post.model.PostTable;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public PostResponse create(@RequestBody PostRequest req) {
        PostTable dataPost1 = new PostTable();
        dataPost1.setTitle((req.title != null) ? req.title : "-");
        dataPost1.setDescription(req.description);
        try{
            postRepository.save(dataPost1);
        } catch (Exception e){
            String errorMsg = "Gagal insert post, error => " + e.getMessage();
            System.out.println(errorMsg);
            PostResponse res = new PostResponse();
            res.message = "Gagal insert post, title sudah terpakai";
            res.rc = 99;
            return res;
        }

        PostResponse res = new PostResponse();
        res.message = "Sukses insert post";
        res.rc = 0;
        return res;
    }

    @RequestMapping(value="/list", method= RequestMethod.GET)
    public void list() {
        System.out.println("masuk API List Post");
    }
}
