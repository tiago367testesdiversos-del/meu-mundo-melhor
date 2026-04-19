package com.mmm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173",
                                "https://mm-mfront.vercel.app")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}

//Serve para permitir que o frontend (React) acesse o backend (Spring Boot),
// mesmo estando em portas diferentes (5173 e 8080).

//Sem isso, o navegador bloqueia as requisições por segurança (erro CORS).

// Essa configuração libera:
// - origem do frontend (localhost:5173)
// - métodos HTTP (GET, POST, etc)
// - headers

  //Como funciona o projeto

  //React (frontend)
    //   ↓
    //fetch (requisição HTTP)
//      ↓
    //Controller (Spring)
//       ↓
               // Service (regra de negócio)
//       ↓
               // Repository (banco de dados)
//      ↓
               //Banco (dados)
//       ↑
               // volta tudo em JSON
//      ↑
//React mostra na tela

    //Fluxo do sistema:

      //1. React faz requisição (fetch)
      //2. Controller recebe a requisição
      //3. Controller chama o Service
      //4. Service processa os dados
      //5. Service usa Repository
     // 6. Repository busca no banco
      //7. Dados voltam como JSON
     // 8. React recebe e exibe na tela