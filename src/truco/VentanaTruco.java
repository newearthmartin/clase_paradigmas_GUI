package truco;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class VentanaTruco extends JFrame {
    public static void main(String[] args) throws IOException {
        new VentanaTruco().setVisible(true);
    }

    public VentanaTruco() throws IOException {
        super("Truco");
        Container cp = getContentPane();
        cp.add(new MesaPanel());
        pack();
    }
}
