import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainInterface {
    private JComboBox comboSubjects;
    private JTextArea textboxqQues;
    private JButton buttonAdd;
    private JButton prepareYourFinalPaperButton;
    private JButton addSubjectsButton;
    private JPanel jpanel;
    private JList existingList;
    private JLabel questionList;

    public MainInterface() {


        addSubjectsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Controller controller = new Controller();
                try {
                    String newLineSeparator  = System.lineSeparator();
                    controller.writeFile(textboxqQues.getText().replace("\n" , newLineSeparator));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JPanel getJpanel() {
        return jpanel;
    }

    private void addComboBox(){

    }


}
