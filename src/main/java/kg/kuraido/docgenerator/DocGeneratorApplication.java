package kg.kuraido.docgenerator;

import kg.kuraido.docgenerator.model.Fields;
import kg.kuraido.docgenerator.repository.FieldsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DocGeneratorApplication {

    @Autowired
    FieldsRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(DocGeneratorApplication.class, args);
    }


    @Bean
    public CommandLineRunner executions(FieldsRepository repository){
        return (args) -> {
       /* repository.save(new Fields("Python Game", "pooploser@gmail.com", "Bakyt Tashitov"));
            repository.save(new Fields("Java AI chess", "murat.eshimov@mail.ru", "Murat Eshimov"));*/
        };
    }
}
