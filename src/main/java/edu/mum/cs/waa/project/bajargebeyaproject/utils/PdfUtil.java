package edu.mum.cs.waa.project.bajargebeyaproject.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.Receipt;
import edu.mum.cs.waa.project.bajargebeyaproject.domain.ReceiptEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import static com.itextpdf.text.Font.BOLD;

public class PdfUtil {
    private static final Logger logger = LoggerFactory.getLogger(PdfUtil.class);

    public static ByteArrayInputStream ReceiptEntries(Receipt receipt) {
        List<ReceiptEntry> receiptEntries = receipt.getReceiptEntries();
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
//            |---id---|---ProductName---|---Qty---|---Rate---|---Amt---|
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(80);
            table.setWidths(new int[]{1, 7, 3, 4, 5});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Id", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Product Name", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Quantity", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Rate", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Amount", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (ReceiptEntry receiptEntry : receiptEntries) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(receiptEntry.getId().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(receiptEntry.getProductName()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(receiptEntry.getQuantity())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(receiptEntry.getPrice()/receiptEntry.getQuantity())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(receiptEntry.getPrice())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }

            PdfPCell cell2 = new PdfPCell(new Phrase());
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfWriter.getInstance(document, out);
            document.open();
            Paragraph p = new Paragraph();
            p.add("Bajar-Gebeya Shopping");
            p.setAlignment(Element.ALIGN_CENTER);
            Font bigFont = new Font();
            bigFont.setFamily("Serif");
            bigFont.setSize(18);
            p.setFont(bigFont);
            document.add(p);

            PdfPCell date = new PdfPCell(new Phrase("Date:"+receipt.getDate().toString()));
            date.setHorizontalAlignment(Element.ALIGN_LEFT);
            document.add(date);

            document.add(table);

            PdfPCell total = new PdfPCell(new Phrase("Total :"+Double.toString(receipt.getTotal())));
            total.setHorizontalAlignment(Element.ALIGN_RIGHT);
            document.add(total);

            document.close();

        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}