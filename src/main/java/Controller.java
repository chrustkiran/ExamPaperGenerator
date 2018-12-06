import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Controller {
    private Writer writer;
    private String subjects;
    private Database database;

    public Controller(){
        writer = new Writer();
        database = new Database();
    }

    public String[] getComboItems() {
        String[] subjectsArray;
        Reader reader = new Reader();
        subjects = reader.readingFile("subjects.txt");
        if(subjects == ""){
           subjectsArray = new String[]{"There is no subject!"};
           return subjectsArray;
        }
        String newline = System.lineSeparator();
        subjectsArray = subjects.split(newline);
        return subjectsArray;
    }


    public void addSubjects() throws IOException, SQLException, ClassNotFoundException {
        String newSubject = JOptionPane.showInputDialog("Here you can add the subject.");
        database.createSubjectTable();
        database.insertSubject(newSubject);
       // writer.writeFile("subjects.txt" , newSubject);
    }

    public void openFile(Component parent , Object selectedItem) throws IOException {
        File file  = new File("papers/"+selectedItem+".doc");
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);
        else JOptionPane.showMessageDialog( parent , "Do data in your file");
    }

    public String validateQuestion(String[] questionSet){
       for(int i = 0 ; i<questionSet.length ; i++ ){
           if(Character.isDigit(questionSet[i].charAt(0))){

               return "Error may be in question number "+i+1;
           }
           String indexNum = "";
           int ind = 0;
           Character chara = questionSet[i].charAt(ind);
           while (chara != ' '){
               if(Character.isAlphabetic(chara)){

                   return "Error may be in question number "+i+1;
               }
               indexNum = indexNum + chara;
               ind++;
               chara = questionSet[i].charAt(ind);
           }

       }
       return "";

    }
}
