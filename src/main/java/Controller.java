import java.io.*;

public class Controller {

    public void writeFile(String text) throws IOException {

       /* BufferedWriter output = new BufferedWriter(new FileWriter("maths.txt",true));
        output.append(text);

        FileOutputStream fos = new FileOutputStream("maths.txt");
        DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
        outStream.writeUTF(text);
        outStream.close();
        if(output!= null){
            output.close();
        }*/

        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("maths.txt",true), "utf-8"));

            //writer.write(text);
            writer.append(text);
            writer.close();
    }
}
