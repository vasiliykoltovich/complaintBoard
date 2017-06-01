package epam.beans;

import java.io.Serializable;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.hibernate.annotations.Cascade;

@Component
@Entity
@Table(name="organizations")
public class Organization implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column
    private String name;

    @OneToMany(mappedBy = "organization")
    @OrderBy("name ASC ")
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToOne(mappedBy = "organization",cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
// @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
    private User user;

    public Organization() {
    }

    public Organization(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    private List<String> feedbackstoString(List<Feedback> list) {
        return list.stream()
                .map(object -> Objects.toString(object, null))
                .collect(Collectors.toList());
    }
}
