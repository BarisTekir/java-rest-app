package rest.application.java_rest.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DemoRestController {

    //adaugam codul pentru endpoint-ul /hello
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }
}
