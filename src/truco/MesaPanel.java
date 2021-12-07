package truco;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MesaPanel extends JPanel {

    List<Carta> cartas1 = new ArrayList<>();
    List<Carta> cartas2 = new ArrayList<>();
    List<Carta> mesa1 = new ArrayList<>();
    List<Carta> mesa2 = new ArrayList<>();
    private JPanel mano1Panel;
    private JPanel mesa1Panel;
    private JPanel mano2Panel;
    private JPanel mesa2Panel;

    public MesaPanel() throws IOException {
        this.setPreferredSize(new Dimension(500, 500));
        cartas1.add(new Carta(1, Carta.Palo.ESPADAS));
        cartas1.add(new Carta(1, Carta.Palo.BASTOS));
        cartas1.add(new Carta(7, Carta.Palo.ESPADAS));

        cartas2.add(new Carta(4, Carta.Palo.OROS));
        cartas2.add(new Carta(5, Carta.Palo.COPAS));
        cartas2.add(new Carta(6, Carta.Palo.BASTOS));

        setLayout(new GridLayout(4,1));

        mano1Panel = new JPanel(); mano1Panel.setLayout(new FlowLayout());
        mesa1Panel = new JPanel(); mesa1Panel.setLayout(new FlowLayout());
        mano2Panel = new JPanel(); mano2Panel.setLayout(new FlowLayout());
        mesa2Panel = new JPanel(); mesa2Panel.setLayout(new FlowLayout());

//        mano1Panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
//        mesa1Panel.setBorder(BorderFactory.createLineBorder(Color.RED));
//        mesa2Panel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
//        mano2Panel.setBorder(BorderFactory.createLineBorder(Color.PINK));

        add(mano1Panel);
        add(mesa1Panel);
        add(mesa2Panel);
        add(mano2Panel);

        ponerCartasEnPaneles();
    }

    private JPanel getCartaPanel(Carta carta) throws IOException {
        JPanel cartaPanel = new JPanel();
        cartaPanel.setLayout(new GridLayout(2,1));
        cartaPanel.setBackground(Color.WHITE);
        cartaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cartaPanel.setPreferredSize(new Dimension(50, 100));

        JLabel numeroLabel = new JLabel("" + carta.numero);
        numeroLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cartaPanel.add(numeroLabel);

        String imagenPalo = null;
        switch (carta.palo) {
            case ESPADAS -> imagenPalo = "images/palo_espadas.png";
            case OROS -> imagenPalo = "images/palo_oros.png";
            case BASTOS -> imagenPalo = "images/palo_bastos.png";
            case COPAS -> imagenPalo = "images/palo_copas.png";
        }
        Image paloImage = ImageIO.read(new File(imagenPalo));
        ImageIcon paloIcon = new ImageIcon(paloImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JLabel paloLabel = new JLabel();
        paloLabel.setIcon(paloIcon);
        cartaPanel.add(paloLabel);
        cartaPanel.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {}
            @Override public void mousePressed(MouseEvent e) { cartaClickeada(carta, cartaPanel); }
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });
        return cartaPanel;
    }

    void ponerCartasEnPaneles() throws IOException {
        for (Carta carta: cartas1) {
            mano1Panel.add(getCartaPanel(carta));
        }
        for (Carta carta: cartas2) {
            mano2Panel.add(getCartaPanel(carta));
        }
    }

    void cartaClickeada(Carta carta, JPanel panel) {
        System.out.println("click en " + carta.numero + " de " + carta.palo);
        if (cartas1.indexOf(carta) >= 0) {
            mano1Panel.remove(panel);
            mano1Panel.repaint();
            cartas1.remove(carta);
            //en la logica, juga esa carta

            mesa1Panel.add(panel);
            mesa1.add(carta);
            mesa1Panel.repaint();
        } else if (cartas2.indexOf(carta) >= 0){
            mano2Panel.remove(panel);
            mano2Panel.repaint();
            cartas2.remove(carta);

            mesa2Panel.add(panel);
            mesa2.add(carta);
            mesa2Panel.repaint();
        }
    }
}
