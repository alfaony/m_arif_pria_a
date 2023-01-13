package ist.challenge.m_arif_pria_a.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ist.challenge.m_arif_pria_a.model.entities.User;
import ist.challenge.m_arif_pria_a.respone.ResponseData;
import ist.challenge.m_arif_pria_a.services.UserService;

@RestController
@Validated
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserService userService; 

    @PostMapping
    public ResponseEntity<ResponseData<User>> create(@Valid @RequestBody User user, Errors errors)
    {
        ResponseData<User> responseData = new ResponseData<>(); 
        if(valid(user))
        {
            responseData.setStatus(true);
            responseData.setPayload(userService.create(user));
            return ResponseEntity.ok(responseData);
        }else{
            responseData.setStatus(false);
            responseData.setPayload(null);
            responseData.getMessage().add("Field Harus Diisi");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @GetMapping
    public Iterable<User> findAll()
    {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findOnUser(@PathVariable("id") Long id)
    {
        return userService.find(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<User>> update(@Valid @RequestBody User user, Errors errors)
    {
        ResponseData<User> responseData = new ResponseData<>(); 
        User checkUser = new User();

        checkUser = userService.find(user.getId());
        if(errors.hasErrors())
        {
            for (ObjectError error : errors.getAllErrors()) 
            {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);

        }
        
        if(checkUser.getPassword().equals(user.getPassword()))
        {
            responseData.setStatus(false);
            responseData.setPayload(null);
            responseData.getMessage().add(0, "Password Sama Dengan Password Sebelumnya");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(userService.create(user));
        return ResponseEntity.ok(responseData);
    } 

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id)
    {
        userService.removeOne(id);
    }

    public Boolean valid(User user)
    {
        if(user.getPassword() == null || user.getPassword().isBlank())
        {
            return false;
        }
       
        if(user.getUsername().isBlank() || user.getUsername() == null)
        {
            return false;
        }
        
        return true;
    }
}
