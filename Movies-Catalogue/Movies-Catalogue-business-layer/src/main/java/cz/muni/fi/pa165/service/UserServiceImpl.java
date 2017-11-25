package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.List;

/**
 * @author Michal
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void registerUser(User u, String password) {
        u.setPassword(getSha256(password));
        userDao.create(u);
    }

    @Override
    public boolean signIn(User u, String password) {
        User user = userDao.findByMail(u.getMail());
        return(user.getPassword().equals(getSha256(password)));
    }

    @Override
    public boolean isAdmin(User u) {
        return u.getRoles().contains(Role.ADMINISTRATOR);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public User findUserByMail(String mail) {
        return userDao.findByMail(mail);
    }

    @Override
    public User findUserByNick(String nick) {
        return userDao.findByNick(nick);
    }

    @Override
    public User update(User u, String password) {
        u.setPassword(getSha256(password));
        userDao.update(u);
        return u;
    }

    @Override
    public void delete(User u) {
        userDao.delete(u.getId());
    }

    public static String getSha256(String value) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(value.getBytes());
            return bytesToHex(md.digest());
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
}
