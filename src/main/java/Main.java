import javax.swing.*;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        openFrame();
    }

    private static void openFrame() throws SQLException, ClassNotFoundException {
        JFrame jframe = new JFrame("Paper Generator");
        jframe.setContentPane((new MainInterface().getJpanel()));
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setVisible(true);
    }
}
