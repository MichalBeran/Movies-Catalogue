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
    Long registerUser(User u, String password);
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
    /**
     * Update user.
     */
    User update(User u, String password);
    /**
     * Remove user.
     */
    void delete(User u);
    /**
     * Make user admin
     */
    void makeAdmin(User u);
}
