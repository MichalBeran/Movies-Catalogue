package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.dto.UserDto;
import cz.muni.fi.pa165.entities.User;
import cz.muni.fi.pa165.mapping.BeanMappingService;
import cz.muni.fi.pa165.service.UserService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Michal
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {
    @Autowired
    private UserService userService;

    @Autowired
    private BeanMappingService mapper;

    @Override
    public Long registerUser(UserDto u, String password) {
        return userService.registerUser(mapper.mapTo(u, User.class), password);
    }

    @Override
    public boolean signIn(UserDto u, String password) {
        return userService.signIn(mapper.mapTo(u, User.class), password);
    }

    @Override
    public boolean isAdmin(UserDto u) {
        return userService.isAdmin(mapper.mapTo(u, User.class));
    }

    @Override
    public List<UserDto> findAllUsers() {
        return mapper.mapTo(userService.findAllUsers(), UserDto.class);
    }

    @Override
    public UserDto findUserById(Long id) {
        return mapper.mapTo(userService.findUserById(id), UserDto.class);
    }

    @Override
    public UserDto findUserByMail(String mail) {
        return mapper.mapTo(userService.findUserByMail(mail), UserDto.class);
    }

    @Override
    public UserDto findUserByNick(String nick) {
        return mapper.mapTo(userService.findUserByNick(nick), UserDto.class);
    }

    @Override
    public UserDto update(UserDto u, String password) {
        return mapper.mapTo(userService.update(mapper.mapTo(u, User.class), password), UserDto.class);
    }

    @Override
    public void delete(UserDto u) {
        userService.delete(mapper.mapTo(u, User.class));
    }

    @Override
    public void makeAdmin(UserDto u){
        userService.makeAdmin(mapper.mapTo(u, User.class));
    }
}
