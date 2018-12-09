import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainInterface {
    private JComboBox comboSubjects;
    private JTextArea textboxqQues;
    private JButton buttonAdd;
    private JButton prepareYourFinalPaperButton;
    private JButton addSubjectsButton;
    private JPanel jpanel;
    private JList questionList;
    private Controller controller;
    private Writer writer;
    private Database database;
    private DefaultListModel listModel;

    public MainInterface() throws SQLException, ClassNotFoundException {

        //Initializing variables

        controller = new Controller();
        writer = new Writer();
        database = new Database();
        addComboBox();

        addList((String) comboSubjects.getSelectedItem());


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
                   controller.addSubjects();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                comboSubjects.removeAllItems();
                try {
                    addComboBox();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });



      /*  buttonOpen.addActionListener(new ActionListener() {
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
        });*/
        comboSubjects.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    addList((String) comboSubjects.getSelectedItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        prepareYourFinalPaperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {

                    controller.writeQuestionFile();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void addList(String subject) throws SQLException, ClassNotFoundException {
        listModel.removeAllElements();
        String selecedsub = subject;
        ArrayList<String> questionSet = controller.getQuestion().get(selecedsub);
        System.out.println(questionSet.get(1));

        int count = 1;
        for (String question: questionSet) {
            listModel.addElement(count + ". "+ question);
            count++;
        }

    }

    public JPanel getJpanel() {
        return jpanel;
    }

    private void addComboBox() throws SQLException, ClassNotFoundException {        //Adding items to combo box.
        ArrayList<String> subjectsArrays = database.getAllSubject();
        for (String subject : subjectsArrays) {
            comboSubjects.addItem(subject);
        }
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        listModel = new DefaultListModel();
        questionList = new JList(listModel);

    }
}
