import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Writer {
    public void writeFile(String fileName , String text) throws IOException {

        String newLineSeparator = System.lineSeparator();
        java.io.Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName,true), "utf-8"));
        writer.append(text+"\n".replace("\n" , newLineSeparator));
        writer.close();
    }
}
