package teleanjar.belajarspring.api.comment.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class CommentTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String info;

    @Column(nullable = false)
    private Integer vote = 0;

    @Column(nullable = true, length = 20)
    private String createdBy;

    @Basic(optional = false)
    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();
}
