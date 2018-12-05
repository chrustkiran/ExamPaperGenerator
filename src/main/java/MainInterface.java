import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class MainInterface {
    private JComboBox comboSubjects;
    private JTextArea textboxqQues;
    private JButton buttonAdd;
    private JButton prepareYourFinalPaperButton;
    private JButton addSubjectsButton;
    private JPanel jpanel;
    private JButton buttonOpen;
    private Controller controller;
    private Writer writer;
    private Database database;

    public MainInterface() {

        //Initializing variables
        controller = new Controller();
        writer = new Writer();
        database = new Database();
        addComboBox();
        buttonAdd.addActionListener(new ActionListener() {      //Add question button listener
            public void actionPerformed(ActionEvent actionEvent) {

                //Reader reader = new Reader();
                //reader.readingDocFile();


                int subjectCode = 0;
                try {
                     subjectCode = database.selectSubjectId((String) comboSubjects.getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    database.insertExam( textboxqQues.getText(), subjectCode) ;
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                /*try {
                    writer.writeFile("maths.txt", textboxqQues.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        });
        addSubjectsButton.addActionListener(new ActionListener() {      // Add subject button listener
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   // controller.addSubjects();
                    database.createSubjectTable();
                }  catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                comboSubjects.removeAllItems();
                addComboBox();
            }
        });


        buttonOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedItem = (String) comboSubjects.getSelectedItem();
                if(selectedItem.equals("There is no subject!")){
                    JOptionPane.showConfirmDialog(jpanel, "No file selected");

                }else {
                    try {
                        controller.openFile(jpanel , comboSubjects.getSelectedItem());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }



            }
        });
    }

    public JPanel getJpanel() {
        return jpanel;
    }

    private void addComboBox() {        //Adding items to combo box.
        String[] subjectsArrays = controller.getComboItems();
        for (String subject : subjectsArrays) {
            comboSubjects.addItem(subject);
        }
    }


}
