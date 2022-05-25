package kg.kuraido.docgenerator.helper;

import kg.kuraido.docgenerator.model.Fields;
import kg.kuraido.docgenerator.repository.FieldsCrud;
import kg.kuraido.docgenerator.repository.FieldsRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;




import static org.apache.xmlbeans.XmlBeans.getTitle;

/*@Component
class WordHelper {
    byte[] generateDocument() {
        return TODO();
    }
}

@RestController
class WordController {
    private final WordHelper wordHelper;

    WordController(WordHelper wordHelper) {
        this.wordHelper = wordHelper;
    }

    @GetMapping
    ResponseEntity<byte[]> word() {
        byte[] document = this.wordHelper.generateDocument();
        return ResponseEntity.ok().body(document);
    }
}*/
public class WordHelper {

    static int id = 1;
    String path = "./src/main/resources/uploads/text" + id + ".docx";
/*    @Autowired
    FieldsRepository repository;*/
public static ByteArrayInputStream generateWord(FieldsRepository repository, FieldsCrud repoCrud)
        throws FileNotFoundException, IOException, InvalidFormatException {
    long total = repoCrud.count() + 1;



    ByteArrayOutputStream b = new ByteArrayOutputStream();

    ZipOutputStream outputStream;
    while(id < total) {
        Fields fields = repository.findById(id);
        try (XWPFDocument sourceDoc = new XWPFDocument(new FileInputStream("./src/main/resources/uploads/test.docx"))) {
            XWPFDocument destDoc = sourceDoc;
            for (XWPFParagraph p : destDoc.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if (runs != null) {
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        if (text != null && text.contains("@Title")) {
                            text = text.replace("@Title", fields.getTitle());
                            r.setText(text, 0);
                        }

                        if (text != null && text.contains("@FullName")) {
                            text = text.replace("@FullName", fields.getFullName());
                            r.setText(text, 0);
                        }
                    }
                }
            }
/*
            destDoc.write(b);*/

            FileOutputStream file = new FileOutputStream("./src/main/resources/unloads/text" + id + ".docx");
            destDoc.write(file);


        }
        id++;
    }
        FileOutputStream fos = new FileOutputStream("./src/main/resources/unloads/test.zip");
        outputStream = new ZipOutputStream(fos);



    return new ByteArrayInputStream(b.toByteArray());
    }


    public static void addToZipFile(ZipOutputStream zipStream)
            throws FileNotFoundException, IOException{

    }
}


/*    public static ByteArrayInputStream generateWord(Fields fields)
            throws FileNotFoundException, IOException, InvalidFormatException {

        try(XWPFDocument sourceDoc = new XWPFDocument(new FileInputStream("./src/main/resources/uploads/test.docx"))){
            XWPFDocument destDoc = sourceDoc;
            for (XWPFParagraph p : destDoc.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if (runs != null) {
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        if (text != null && text.contains("@Title")) {
                            text = text.replace("@Title", fields.getTitle());
                            r.setText(text, 0);
                        }

                        if (text != null && text.contains("@FullName")) {
                            text = text.replace("@FullName", fields.getFullName());
                            r.setText(text, 0);
                        }
                    }
                }
            }
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            destDoc.write(b);

            //return b;
            return new ByteArrayInputStream(b.toByteArray());
        }
    }*/
