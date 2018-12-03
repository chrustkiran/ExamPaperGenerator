import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        openFrame();
    }

    private static void openFrame(){
        JFrame jframe = new JFrame("Paper Generator");
        jframe.setContentPane((new MainInterface().getJpanel()));
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setVisible(true);
    }
}
