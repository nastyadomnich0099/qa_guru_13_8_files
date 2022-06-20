package guru_qa;


import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.Assertions.assertThat;


public class FileParseTest{
    ClassLoader classLoader = FileParseTest.class.getClassLoader();



    @DisplayName("CSV file in Zip test")
    @Test
    void checkCSVTest() throws Exception {
        ZipFile zipFile = new ZipFile(Objects.requireNonNull(classLoader.getResource("Archive.zip")).getFile());
        ZipEntry entry = zipFile.getEntry("SampleCSVFile_2kb.csv");
        List<String[]> list;
        InputStream inputStream = zipFile.getInputStream(entry);
        CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
        list = csvReader.readAll();
        assertThat(list).contains(new String[]
                {"4","R380","Clay Rozendal","483","1198.97","195.99","3.99","Nunavut","Telephones and Communication","0.58"});

         }

    @DisplayName("XLS file in Zip test")
    @Test
    void checkXLS() throws Exception {
            ZipFile zipFile = new ZipFile(Objects.requireNonNull(classLoader.getResource("Archive.zip")).getFile());
            ZipEntry entry = zipFile.getEntry("file_example_XLS_10.xls");
            XLS xls;
            InputStream inputStream = zipFile.getInputStream(entry);
            xls = new XLS(inputStream);
            assertThat(xls.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue())
                    .contains("First Name");
        }

    @DisplayName("PDF file in Zip test")
    @Test
    void checkPDF() throws Exception {
        ZipFile zipFile = new ZipFile(Objects.requireNonNull(classLoader.getResource("Archive.zip")).getFile());
        ZipEntry entry = zipFile.getEntry("pdf-test.pdf");
        PDF pdf;
        InputStream inputStream = zipFile.getInputStream(entry);
        pdf = new PDF(inputStream);
        assertThat(pdf.text).contains("PDF Test File");

    }
}






