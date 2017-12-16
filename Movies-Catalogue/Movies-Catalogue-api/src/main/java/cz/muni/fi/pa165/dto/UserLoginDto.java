package cz.muni.fi.pa165.dto;

import java.util.Objects;

/**
 * @author Michal
 */
public class UserLoginDto {
    private String mail;
    private String password;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
