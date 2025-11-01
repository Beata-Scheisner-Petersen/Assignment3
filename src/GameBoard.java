import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
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
            clickedButton.setText(" ");
        }
    }
    public boolean checkIfMoveIsPossible(int i, JButton emptyButton) {
        boolean moveIsPossible = switch (i) {
            // button 1
            case 0 -> buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 2 & 3
            case 1, 2 -> buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 4
            case 3 -> buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 5
            case 4 -> buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i -4).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 6 & 7
            case 5, 6 -> buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 8
            case 7 -> buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 9
            case 8 -> buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 10 & 11
            case 9, 10 -> buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 12
            case 11 -> buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 13
            case 12 -> buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton);
            // button 14 & 15
            case 13, 14 -> buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton);
            // button "16"
            case 15 -> buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton);
            default -> false;
        };

        return moveIsPossible;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton emptyButton = findEmptyButton();
        JButton clicked = (JButton) e.getSource();

        for (int i = 0; i < buttonList.size(); i++) {
            if (buttonList.get(i).equals(clicked)) {
                if (checkIfMoveIsPossible(i, emptyButton)) {
                    moveButton(clicked, emptyButton);
                }
            }
        }
    }


}

