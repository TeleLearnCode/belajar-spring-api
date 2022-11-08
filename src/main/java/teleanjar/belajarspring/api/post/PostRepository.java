package teleanjar.belajarspring.api.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import teleanjar.belajarspring.api.post.model.PostTable;

public interface PostRepository extends JpaRepository<PostTable, Long> {

}
