package uyutov.flower_shop.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import uyutov.flower_shop.models.users.Admin;
import uyutov.flower_shop.models.users.Customer;
import uyutov.flower_shop.models.users.Packer;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Parser {
    static public void parseJson(List<Packer> packers) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("D:/JavaProjects/Курсач/flower_shop/src/main/resources/static/packers-json/students.json"), packers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static public void parsePdf(Admin admin) {
        Document document = new Document();

        try {

            PdfWriter.getInstance(document,
                    new FileOutputStream(new File("D:/JavaProjects/Курсач/flower_shop/src/main/resources/static/admin-pdf/admin.pdf")));

            document.open();

            Paragraph p = new Paragraph();
            p.add(admin.getPhone_number()+ "\n " + admin.getPassword() + "\n " + admin.getRole() + "\n " + admin.getAdmin_id());
            p.setAlignment(Element.ALIGN_CENTER);

            document.add(p);

            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}
