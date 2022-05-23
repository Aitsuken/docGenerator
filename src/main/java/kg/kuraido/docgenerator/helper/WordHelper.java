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

    @Autowired
    FieldsRepository repository;
    //Fields fields = repository.findById();
    WordHelper(){
    }
    public static ByteArrayInputStream generateWord(Fields fields, long id)
            throws FileNotFoundException, IOException, InvalidFormatException {

        try(XWPFDocument sourceDoc = new XWPFDocument(new FileInputStream("./src/main/resources/uploads/test.docx"))){
            XWPFDocument destDoc = sourceDoc;
            for (XWPFParagraph p : destDoc.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if (runs != null) {
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        if (text != null && text.contains("needle")) {
                            text = text.replace("needle", "haystack");
                            r.setText(text, 0);
                        }
                    }
                }
            }
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            destDoc.write(b);

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
            }*//*

*//*            XWPFParagraph p1 = doc.createParagraph();
            p1.setAlignment(ParagraphAlignment.CENTER);
            // Set Text to Bold and font size to 22 for first paragraph
            XWPFRun r1 = p1.createRun();
            r1.setBold(true);
            r1.setItalic(true);
            r1.setFontSize(22);
            r1.setText("BACHELOR'S DEGREE DIPLOMA THESIS PROPOSAL");
            r1.setFontFamily("Courier");
            r1.setColor("008000");
            r1.addBreak();

            XWPFParagraph p2 = doc.createParagraph();

            XWPFRun r2 = p2.createRun();

            // Here is the trouble in fields
            r2.setText("TITLE OF PROPOSAL: " + fields.getTitle());
            r2.setColor("000000");
            //r2.setEmbossed(true);
            //r2.setStrikeThrough(true);
            r2.addBreak();
            r2.addBreak();

            XWPFRun r3 = p2.createRun();

            r3.setText("Full name: " + fields.getFullName());
            r3.setColor("000000");
            r3.setEmbossed(false);
            r3.addBreak();
            r3.addBreak();

            XWPFRun r4 = p2.createRun();

            r4.setText("Email address: " + fields.getEmail());
            r4.setColor("FFFFFF");
            r4.setEmbossed(false);
            r4.addBreak();
            r4.addBreak();*//*

            ByteArrayOutputStream b = new ByteArrayOutputStream();
            doc.write(b);

            return new ByteArrayInputStream(b.toByteArray());
        }*/

/*    public void wordDocProcessor(AnotherVO anotherData, ArrayList<String> list,
                                 String sourse, String destination) throws IOException,
            InvalidFormatException {
        XWPFDocument doc = new XWPFDocument(OPCPackage.open(sourse
                + "XXXXX_TestReport_URL_Document.doc"));
        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            String text = r.getText(0);
                            if (text != null
                                    && text.contains("var_source_code")) {
                                text = text.replace("var_source_code",
                                        list.get(1));
                                r.setText(text, 0);
                            }
                            if (text != null && text.contains("var_rsvp_code")) {
                                text = text.replace("var_rsvp_code",
                                        list.get(2));
                                r.setText(text, 0);
                            }
                            if (text != null && text.contains("var_ssn")) {
                                text = text.replace("var_ssn", list.get(3));
                                r.setText(text, 0);
                            }
                            if (text != null && text.contains("var_zip_code")) {
                                text = text
                                        .replace("var_zip_code", list.get(4));
                                r.setText(text, 0);
                            }
                            if (text != null
                                    && text.contains("var_point_for_business")) {
                                text = text.replace("var_point_for_business",
                                        anotherData.getPointForBusiness());
                                r.setText(text, 0);
                            }
                            if (text != null && text.contains("var_E1_url")) {
                                text = text.replace("var_E1_url",
                                        anotherData.getE1url());
                                r.setText(text, 0);
                            }
                            if (text != null && text.contains("var_E2_url")) {
                                text = text.replace("var_E2_url",
                                        anotherData.getE2url());
                                r.setText(text, 0);
                            }
                            if (text != null && text.contains("var_E3_url")) {
                                text = text.replace("var_E3_url",
                                        anotherData.getE3url());
                                r.setText(text, 0);
                            }
                        }
                    }
                }
            }
        }
        doc.write(new FileOutputStream(destination + list.get(0)
                + "_TestReport_URL_Document.doc"));
    }*/



    }

}
