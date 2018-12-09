import jdk.internal.org.objectweb.asm.Handle;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Controller {
    private Writer writer;
    private String subjects;
    private Database database;
    private Config config;
    public Controller(){
        writer = new Writer();
        database = new Database();
        config  = new Config();
    }

    public String[] getComboItems() {
        String[] subjectsArray;
       // Reader reader = new Reader();
       //
        // subjects = reader.readingFile("subjects.txt");
        if(subjects == ""){
           subjectsArray = new String[]{"There is no subject!"};
           return subjectsArray;
        }
        String newline = System.lineSeparator();
        subjectsArray = subjects.split(newline);
        return subjectsArray;
    }

    public HashMap<String, ArrayList<String>> getQuestion() throws SQLException, ClassNotFoundException {

        HashMap<String, ArrayList<String>> questionMap = new HashMap<>(); //["maths" : <question1 , question2>]
        ResultSet questionRS = database.getAllQuesion();
        while (questionRS.next()){
            Integer key = questionRS.getInt(2);
            String keySub = database.getSubjectById(key);
            if(questionMap.containsKey(keySub)){
                questionMap.get(keySub).add(questionRS.getString(3));
            }
            else {
                if(keySub != "") {
                    ArrayList<String> question = new ArrayList<>();
                    question.add(questionRS.getString(3));
                    questionMap.put(keySub, question);
                }
            }
        }
    return questionMap;


    }

    public HashMap<String , ArrayList<String>> selectRandom(HashMap<String , ArrayList<String>> questionsMap){
        int countQuestion = Integer.parseInt(config.getProp().getProperty("count"));

        for (String subject : questionsMap.keySet()) {
            if(questionsMap.get(subject).size() <= countQuestion){
                continue;
            }
            while (questionsMap.get(subject).size() != countQuestion){
                Random r = new Random();
                int randInt = r.nextInt(questionsMap.get(subject).size());
                questionsMap.get(subject).remove(randInt);
            }

        }

        return questionsMap;
    }

   /* public static void main(String[] args){
        Controller c = new Controller();
        HashMap <String , ArrayList<String>> hashMap = new HashMap<>();
        ArrayList<String> a = new ArrayList<>();
        a.add("djfndjfdjfjdf");
        a.add("2");
        a.add("SS");
        a.add("fknfkd");
        a.add("jfnjnfnf");
        a.add("fefefef");
        hashMap.put("maths" , a);

        System.out.println(c.selectRandom(hashMap).get("maths").size());
    }*/



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

    public void writeQuestionFile() throws IOException, SQLException, ClassNotFoundException {
        HashMap<String , ArrayList<String>> allquestions = this.getQuestion();
        HashMap<String , ArrayList<String>> questionsMap = this.selectRandom(allquestions);
        String finalQs = "";
        for (String sub:questionsMap.keySet()) {
            finalQs = finalQs + sub + "\r\n\r\n";
            int count = 1;
            for (String ques : questionsMap.get(sub)) {
                finalQs = finalQs + count+". "+ques+"\r\n";
                count++;
            }
            finalQs = finalQs + "\r\n\r\n";
        }
        writer.writeFile("exam.txt" , finalQs);
    }
}
