package cz.muni.fi.pa165.entities;

import cz.muni.fi.pa165.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

/**
 * @author Michal
 */
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique=true)
    private String nick;

    @NotNull
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Column(unique=true)
    private String mail;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    @OneToMany
    private List<Rating> ratings;

    public User(){
        this.roles = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Role> getRoles() { return roles; }

    public void setRoles(List<Role> roles){ this.roles = roles; }

    public void addRole(Role role){ this.roles.add(role); }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void addRating(Rating rating){ this.ratings.add(rating); }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", nick=" + nick + ", mail=" + mail + "}";
    }

    @Override
    public int hashCode() {
        int result = 17;
        int prime = 47;
        result = prime * result + ((nick == null) ? 0 : nick.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof User))
            return false;
        final User other = (User) obj;

        return Objects.equals(nick, other.getNick());
    }
}
