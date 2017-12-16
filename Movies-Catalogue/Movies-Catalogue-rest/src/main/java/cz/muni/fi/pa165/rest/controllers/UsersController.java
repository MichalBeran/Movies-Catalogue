package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.UserDto;
import cz.muni.fi.pa165.dto.UserLoginDto;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.rest.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
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
    final static Logger logger = LoggerFactory.getLogger(GenresController.class);

    @Inject
    private UserFacade userFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> index() {return userFacade.findAllUsers();}

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
        return userDto;
    }
}
