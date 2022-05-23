package kg.kuraido.docgenerator.helper;

import kg.kuraido.docgenerator.model.Fields;
import kg.kuraido.docgenerator.repository.FieldsRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
    //static String uniLogo = "https://99designs-blog.imgix.net/blog/wp-content/uploads/2016/08/featured.png?auto=format&q=60&w=2060&h=2060&fit=crop&crop=faces";

/*    @Autowired
    FieldsRepository repository;*/
    WordHelper(){
    }
    public static ByteArrayInputStream generateWord(Fields fields)
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


        /*try (XWPFDocument doc = new XWPFDocument()) {
            // add png image
            XWPFRun imeeji = doc.   createParagraph().createRun();

            imeeji.addBreak();
            XWPFParagraph p = doc.createParagraph();
            XWPFRun r = p.createRun();

*//*            try (FileInputStream is = new FileInputStream(uniLogo)) {
                r.addPicture(is, Document.PICTURE_TYPE_PNG, uniLogo,
                        Units.toEMU(1500), Units.toEMU(500));
            }*/



    }

}
