package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entities.User;

import java.util.List;

/**
 * @author Michal
 */
public interface UserService {
    /**
     * Register user u with given password.
     */
    void registerUser(User u, String password);
    /**
     * Check if user can sign in
     */
    boolean signIn(User u, String password);
    /**
     * Check if user is admin.
     */
    boolean isAdmin(User u);
    /**
     * Get all users.
     */
    List<User> findAllUsers();
    /**
     * Get user by id.
     */
    User findUserById(Long id);
    /**
     * Get user by e-mail
     */
    User findUserByMail(String mail);
    /**
     * Get user by nick.
     */
    User findUserByNick(String nick);
}
