package teleanjar.belajarspring.api.post;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import teleanjar.belajarspring.api.post.model.PostTable;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @RequestMapping(value="/create", method= RequestMethod.GET)
    public void create() {
        System.out.println("masuk API Create Post");
        PostTable dataPost1 = new PostTable();
        dataPost1.setTitle("Tele 1");
        dataPost1.setDescription("Belajar Spring 1");
        postRepository.save(dataPost1);
    }

    @RequestMapping(value="/list", method= RequestMethod.GET)
    public void list() {
        System.out.println("masuk API List Post");
    }
}
