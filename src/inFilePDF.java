import java.io.FileOutputStream;
import java.util.Date;

import javax.swing.JTable;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class inFilePDF {
    public void exportTable(JTable table, String fileName) {
        try {
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            // Add a title to the PDF document
            Paragraph title = new Paragraph("Table Exported as PDF");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Add a date to the PDF document
            Paragraph date = new Paragraph(new Date().toString());
            date.setAlignment(Element.ALIGN_CENTER);
            document.add(date);

            // Create a table with the same number of columns as the JTable
            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());

            // Add the column headers to the table
            for (int i = 0; i < table.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Paragraph(table.getColumnName(i)));
                pdfTable.addCell(cell);
            }

            // Add the data to the table
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    PdfPCell cell = new PdfPCell(new Paragraph(table.getValueAt(i, j).toString()));
                    pdfTable.addCell(cell);
                }
            }

            // Add the table to the PDF document
            document.add(pdfTable);

            // Close the PDF document
            document.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}