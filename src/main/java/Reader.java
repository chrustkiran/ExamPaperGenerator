import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.hwpf.usermodel.Picture;

import java.io.*;

public class Reader {

    public String readingFile(String fileName) {
        // This will reference one line at a time
        String line = null;
        String text = "";

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
               if(text == ""){
                   text = text + line;
               }
               else {
                   text = text + "\n" + line;
               }
            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");

        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        return text;
    }



    public void readingDocFile(){
        File file = null;
        WordExtractor extractor = null;
        try
        {

            file = new File("papers/"+"maths.doc");
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            HWPFDocument document = new HWPFDocument(fis);
           // XWPFDocument doc = new XWPFDocument();
            //doc.getAllPictures();


         //   Picture pic = new Picture(fis);

            extractor = new WordExtractor(document);
            String  text= extractor.getText();
            for (String question :text.split("\r\n\r\n")) {
                System.out.println(question);

                System.out.println("-----------------------------------------");
            }

//            for (int i = 0; i < fileData.length; i++)
//            {
//                if (fileData[i] != null)
//                    System.out.println(fileData[i]);
//            }
        }
        catch (Exception exep)
        {
            exep.printStackTrace();
        }
    }



}

