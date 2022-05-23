package kg.kuraido.docgenerator.controller;

import kg.kuraido.docgenerator.helper.WordHelper;
import kg.kuraido.docgenerator.model.Fields;
import kg.kuraido.docgenerator.repository.FieldsCrud;
import kg.kuraido.docgenerator.repository.FieldsRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

@RestController
    @RequestMapping("api")
public class WordController {
    static int id = 1;
    @Autowired
    FieldsRepository repository;
    @Autowired
    FieldsCrud reposCrud;

    //in this part I am trying to add
/*    @PostMapping(value = "/addId")
    public String showPage(@ModelAttribute("Id") Model model) {
        //here is a mistake

        return "redirect:someOtherPage";
    }*/

//    @GetMapping("/send")
//    public String send(){
//            return "redirect:word";
//
//    }
    @GetMapping(value = "/word", produces = "application/vnd.openxmlformats-" +
    "officedocument.wordprocessingml.document")
        public ResponseEntity<InputStreamResource> word(Model model)
    throws IOException, InvalidFormatException {
        model.addAttribute("field", repository.findAll());
            Fields fields = repository.findById(id++);
            //System.out.println(repository.countFieldsById());
            ByteArrayInputStream bis = WordHelper.generateWord(fields);

            //ZipOutputStream zos =    new ZipOutputStream(bis);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=text.docx");
            System.out.println(id);
            return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bis));
    }

}
