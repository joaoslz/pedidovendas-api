package edu.ifma.dcomp.topicos2.apipedidovendas;

//import edu.ifma.dcomp.topicos2.apipedidovendas.security.utils.GeradorDeSenhasBcrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ApiPedidoVendasApplication {

   public static void main(String[] args) {

       SpringApplication.run(ApiPedidoVendasApplication.class, args);
    }



  /*  @Bean
    public CommandLineRunner commandLineRunner() {
       return args -> {
           String senhaCriptografada = GeradorDeSenhasBcrypt.gerarBCrypt("asdf");
           System.out.println("### Senha criptografada " + senhaCriptografada );

           senhaCriptografada = GeradorDeSenhasBcrypt.gerarBCrypt("asdf");
           System.out.println("### Senha criptografada " + senhaCriptografada );


           System.out.println("### Senha é válida ? "
                + GeradorDeSenhasBcrypt.senhaValida("asdf", senhaCriptografada));
       };
    }
*/

}
