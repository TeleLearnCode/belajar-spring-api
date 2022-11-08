package teleanjar.belajarspring.api.post.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
public class PostTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String title;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = true, length = 20)
    private String createdBy;

    @Basic(optional = false)
    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    public void setDescription(String description) {
        this.description = description;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
