package teleanjar.belajarspring.api.post;

import org.springframework.web.bind.annotation.*;
import teleanjar.belajarspring.api.post.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public PostResponse create(@RequestBody PostCreateRequest req) {
        PostTable dataPost1 = new PostTable();
        dataPost1.setTitle(req.title);
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

    @RequestMapping(value="/update", method=RequestMethod.POST)
    public PostResponse update(@RequestBody PostUpdateRequest req) {
        Optional<PostTable> postTable = postRepository.findById(req.id);

        if(postTable.isEmpty()){
            PostResponse res = new PostResponse();
            res.message = "ID Post tidak ditemukan";
            res.rc = 99;
            return res;
        }

        PostTable post = postTable.get();
        post.setTitle(req.title);
        post.setDescription(req.description);

        try{
            postRepository.save(post);
        } catch (Exception e){
            String errorMsg = "Gagal update post, error => " + e.getMessage();
            System.out.println(errorMsg);
            PostResponse res = new PostResponse();
            res.message = "Gagal update post, title sudah terpakai";
            res.rc = 99;
            return res;
        }

        PostResponse res = new PostResponse();
        res.message = "Sukses update post";
        res.rc = 0;
        return res;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public PostResponse delete(@PathVariable("id") Long id) {
        Optional<PostTable> post =  postRepository.findById(id);

        if(post.isEmpty()){
            PostResponse res = new PostResponse();
            res.message = "Data post tidak ditemukan";
            res.rc = 99;
            return res;
        }

        try{
            postRepository.delete(post.get());
        } catch (Exception e){
            String errorMsg = "Gagal delete post, error => " + e.getMessage();
            System.out.println(errorMsg);
            PostResponse res = new PostResponse();
            res.message = "Gagal deelet post";
            res.rc = 99;
            return res;
        }

        PostResponse res = new PostResponse();
        res.message = "Sukses delete post";
        res.rc = 0;
        return res;
    }

    @RequestMapping(value="/list", method= RequestMethod.GET)
    public List<PostResponseWithData> list() {
        List<PostResponseWithData> responses = new ArrayList<>();
        List<PostTable> posts =  postRepository.findAll();

        posts.forEach(postTable -> {
            PostResponseWithData res = new PostResponseWithData();
            res.id = postTable.getId();
            res.title = postTable.getTitle();
            res.description = postTable.getDescription();

            responses.add(res);
        });

        return responses;
    }
}
