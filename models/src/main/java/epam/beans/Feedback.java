package epam.beans;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "feedbacks")
public class Feedback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String text;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User author;
    @Column
    private Integer rate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    private Integer identifier;

    @Column
    private Date date = Date.valueOf(LocalDate.now());

    public Feedback() {
    }

    public Feedback(Long id, String text, User author, Integer rate, Integer identifier) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.rate = rate;
        this.identifier = identifier;
    }

    

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", text=" + text + ",  rate=" + rate + ", organization=" + organization + ", identifier=" + identifier + ", date=" + date + '}';
    }

  

   

}
