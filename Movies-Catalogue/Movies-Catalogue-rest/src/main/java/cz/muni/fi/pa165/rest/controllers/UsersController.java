package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.UserDto;
import cz.muni.fi.pa165.dto.UserLoginDto;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.rest.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author Michal
 */

@RestController
@RequestMapping(Api.ROOT_URI_USERS)
public class UsersController {
    final static Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Inject
    private UserFacade userFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> index() {
        List<UserDto> list = userFacade.findAllUsers();
        for(int i = 0; i<list.size(); i++){
            UserDto u = list.get(i);
            u.setAdmin(userFacade.isAdmin(u));
            list.set(i, u);
        }
        return list;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDto register(@RequestBody UserDto dto)throws Exception{
        try {
            logger.debug("dto incoming: "+dto.toString());
            Long id = userFacade.registerUser(dto, dto.getPassword());
            return userFacade.findUserById(id);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(),ex);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto get(@PathVariable("id") Long id) throws Exception {
        UserDto userDto = userFacade.findUserById(id);
        if(userDto == null){
            throw new Exception("NOT FOUND");
        }
        return userDto;
    }

    @RequestMapping(value="/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto login(@RequestBody UserLoginDto dto) throws Exception {
        UserDto loginUser = new UserDto();
        loginUser.setMail(dto.getMail());
        UserDto userDto = userFacade.signIn(loginUser, dto.getPassword());
        if(userDto == null){
            throw new Exception("LOGIN FAILED");
        }
        userDto.setAdmin(userFacade.isAdmin(userDto));
        return userDto;
    }

    @RequestMapping(value="/makeAdmin", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean makeAdmin(@RequestBody UserDto dto) throws Exception {
        userFacade.makeAdmin(dto);
        return userFacade.isAdmin(dto);
    }

    @RequestMapping(value="/unmakeAdmin", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean unmakeAdmin(@RequestBody UserDto dto) throws Exception {
        userFacade.unmakeAdmin(dto);
        return userFacade.isAdmin(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        try {
            UserDto stored = userFacade.findUserById(id);
            userFacade.delete(stored);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
