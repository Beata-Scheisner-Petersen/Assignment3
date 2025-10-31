import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GameBoard extends JFrame implements ActionListener {
    JPanel mainPanel = new JPanel();
    JPanel southPanel = new JPanel();
    JPanel northPanel = new JPanel(new GridLayout(4, 4));
    JLabel test = new JLabel("test");

    JButton b1 = new JButton("1");
    JButton b2 = new JButton("2");
    JButton b3 = new JButton("3");
    JButton b4 = new JButton("4");
    JButton b5 = new JButton("5");
    JButton b6 = new JButton("6");
    JButton b7 = new JButton("7");
    JButton b8 = new JButton("8");
    JButton b9 = new JButton("9");
    JButton b10 = new JButton("10");
    JButton b11 = new JButton("11");
    JButton b12 = new JButton("12");
    JButton b13 = new JButton("13");
    JButton b14 = new JButton("14");
    JButton b15 = new JButton("15");

    public GameBoard() {


        this.add(mainPanel);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add (southPanel, BorderLayout.SOUTH);

        northPanel.add(b1);
        northPanel.add(b2);
        northPanel.add(b3);
        northPanel.add(b4);
        northPanel.add(b5);
        northPanel.add(b6);
        northPanel.add(b7);
        northPanel.add(b8);
        northPanel.add(b9);
        northPanel.add(b10);
        northPanel.add(b11);
        northPanel.add(b12);
        northPanel.add(b13);
        northPanel.add(b14);
        northPanel.add(b15);
        southPanel.add(test);

        b1.addActionListener(e -> {
            Container c = ((Container)e.getSource()).getParent();
        });

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
