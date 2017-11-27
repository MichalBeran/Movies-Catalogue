package cz.muni.fi.pa165.dto;

import java.util.Objects;

/**
 * @author Michal
 */
public class UserDto {
    private Long id;
    private String nick;
    private String password;
    private String firstName;
    private String lastName;
    private String mail;

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

    @Override
    public String toString() {
        return "UserDto{" +
                "mail='" + mail + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", password='" + password + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = 17;
        int prime = 47;
        result = prime * result + nick.hashCode();
        result = prime * result + mail.hashCode();
        result = prime * result + firstName.hashCode();
        result = prime * result + lastName.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof RatingDto))
            return false;
        final UserDto other = (UserDto) obj;

        return Objects.equals(nick, other.getNick())
                && Objects.equals(mail, other.getMail())
                && Objects.equals(firstName, other.getFirstName())
                && Objects.equals(lastName, other.getLastName());
    }
}
