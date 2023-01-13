package ist.challenge.m_arif_pria_a.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/welcome")
public class WelcomController 
{
    @GetMapping
    public String welcome(){
        return "Hello World";    
    }    

}
