package co.edu.escuelaing.dockerprimer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {
    
    // Plantilla para el mensaje de saludo
    private static final String template = "Hello, %s!";
    
    // Este método maneja las peticiones GET a /greeting
    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format(template, name);
    }
}
