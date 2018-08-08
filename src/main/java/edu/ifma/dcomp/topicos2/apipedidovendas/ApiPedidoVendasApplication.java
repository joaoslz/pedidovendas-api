package edu.ifma.dcomp.topicos2.apipedidovendas;

//import edu.ifma.dcomp.topicos2.apipedidovendas.security.utils.GeradorDeSenhasBcrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
