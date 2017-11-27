package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.UserDto;

import java.util.List;

/**
 * @author Michal
 */
public interface UserFacade {
    /**
     * Register user u with given password.
     */
    Long registerUser(UserDto u, String password);
    /**
     * Check if user can sign in
     */
    boolean signIn(UserDto u, String password);
    /**
     * Check if user is admin.
     */
    boolean isAdmin(UserDto u);
    /**
     * Get all users.
     */
    List<UserDto> findAllUsers();
    /**
     * Get user by id.
     */
    UserDto findUserById(Long id);
    /**
     * Get user by e-mail
     */
    UserDto findUserByMail(String mail);
    /**
     * Get user by nick.
     */
    UserDto findUserByNick(String nick);
    /**
     * Update user.
     */
    UserDto update(UserDto u, String password);
    /**
     * Remove user.
     */
    void delete(UserDto u);
    /**
     * Make user admin
     */
    void makeAdmin(UserDto u);
}
