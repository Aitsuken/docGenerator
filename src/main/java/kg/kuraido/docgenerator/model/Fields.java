package kg.kuraido.docgenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fields {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String Title;
    String email;
    String fullName;

    public Fields(String Title, String email, String fullName){
        this.Title = Title;
        this.email = email;
        this.fullName = fullName;
    }
}
