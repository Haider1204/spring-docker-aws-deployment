package co.edu.escuelaing.dockerprimer;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServiceApplication {
    
    public static void main(String[] args) {
        // Crear la aplicación Spring Boot
        SpringApplication app = new SpringApplication(RestServiceApplication.class);
        
        // Configurar el puerto
        app.setDefaultProperties(Collections.singletonMap("server.port", getPort()));
        
        // Iniciar la aplicación
        app.run(args);
    }
    
    // Método para obtener el puerto
    private static int getPort() {
        // Si existe la variable de entorno PORT, usarla
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        // Si no, usar el puerto 33025 por defecto
        return 33025;
    }
}