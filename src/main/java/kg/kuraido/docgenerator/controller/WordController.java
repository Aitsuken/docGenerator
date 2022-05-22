package kg.kuraido.docgenerator.controller;

import kg.kuraido.docgenerator.helper.WordHelper;
import kg.kuraido.docgenerator.model.Fields;
import kg.kuraido.docgenerator.repository.FieldsRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
    @RequestMapping("api")
public class WordController {
    static int count = 1;
    @Autowired
    FieldsRepository repository;

    @GetMapping(value = "/word", produces = "application/vnd.openxmlformats-" +
    "officedocument.wordprocessingml.document")
        public ResponseEntity<InputStreamResource> word()
    throws IOException, InvalidFormatException {
        Fields fields = repository.findById(count);
        ByteArrayInputStream bis = WordHelper.generateWord(fields);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=text.docx");
        count++;
        System.out.println(count);
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bis));


    }

}
