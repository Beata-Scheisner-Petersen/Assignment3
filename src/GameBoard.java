import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class GameBoard extends JFrame implements ActionListener {
    JPanel mainPanel = new JPanel();
    JPanel southPanel = new JPanel();
    JPanel northPanel = new JPanel(new GridLayout(4, 4));
    JLabel test = new JLabel("test");
    List<JButton> buttonList = new ArrayList<>();


    public GameBoard() {


        this.add(mainPanel);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);


        int buttonNumbers = 15;


        for (int i = 1; i <= buttonNumbers; i++) {
            JButton button = new JButton(String.valueOf(i));
            northPanel.add(button);
            button.addActionListener(this);
            buttonList.add(button);

        }

        JButton empty = new JButton(" ");
        buttonList.add(empty);
        northPanel.add(empty);
        empty.addActionListener(this);
        southPanel.add(test);


        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public boolean isEmpty(JButton button) {

        return button.getText().equals(" ");
    }

    public JButton findEmptyButton() {

        for (JButton emptyButton : buttonList) {
            if (isEmpty(emptyButton))
                return emptyButton;
        }return null;
    }


    public void moveButton(JButton clickedButton, JButton emptyButton) {
        String buttonNumber = clickedButton.getText();
        if (!isEmpty(clickedButton)) {
            emptyButton.setText(buttonNumber);
        }
        clickedButton.setText(" ");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // moveButtons((JButton) e.getSource());
        JButton emptyButton = findEmptyButton();
        JButton clicked = (JButton) e.getSource();

        for (int i = 0; i < buttonList.size(); i++) {
            if (buttonList.get(i) == clicked) {
                if (buttonList.get(i + 1) != buttonList.get(15)) {
                    if (buttonList.get(i + 1) == emptyButton) {
                        moveButton(clicked, emptyButton);
                        break;
                    }
                } else {
                    if (buttonList.get(i - 1) == emptyButton) {
                        moveButton(clicked, emptyButton);
                        break;
                    }
                }
            }
        }


    }


}

