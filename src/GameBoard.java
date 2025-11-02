import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/* Todo
* Fix a reporting board that is easy to solve - Done // Beata
* Fix Function for discover that user solved the board + win message - Done // Beata
* Fix that buttons are in a random order + new game // Valeria
* Check if code need optimize
* * Fix bigger buttons // Beata
* Fix a border around buttons // Beata
* Fix button board + color
* Check if board is solvable */


public class GameBoard extends JFrame implements ActionListener {
    JPanel mainPanel = new JPanel();
    JPanel southPanel = new JPanel();
    JPanel northPanel = new JPanel(new BorderLayout());
    JPanel boardPanel = new JPanel(new GridLayout(4, 4));
    JLabel text = new JLabel(" ", JLabel.CENTER);
    List<JButton> buttonList = new ArrayList<>();

    public GameBoard() {
        this.add(mainPanel);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.CENTER);

        southPanel.setPreferredSize(new Dimension(200, 200));
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        boardPanel.setBorder(new LineBorder(Color.BLACK, 1));
        northPanel.add(boardPanel, BorderLayout.CENTER);

        int buttonNumbers = 15;

        boolean reportBoard = true;

        if (reportBoard) {
            for (int i = 1; i <= buttonNumbers - 1; i++) {
                JButton button = new JButton(String.valueOf(i));

                button.setPreferredSize(new Dimension(100, 100));
                button.setFont(new Font(Font.SERIF, Font.BOLD, 20));
                button.setBorder(new LineBorder(Color.BLACK, 1));
                button.setBackground(Color.WHITE);

                boardPanel.add(button);
                button.addActionListener(this);
                buttonList.add(button);
            }
            JButton empty = new JButton(" ");

            empty.setPreferredSize(new Dimension(100, 100));
            empty.setFont(new Font(Font.SERIF, Font.BOLD, 20));
            empty.setBorder(new LineBorder(Color.BLACK, 1));
            empty.setBackground(Color.WHITE);

            buttonList.add(empty);
            boardPanel.add(empty);
            empty.addActionListener(this);

            JButton b15 = new JButton("15");
            b15.setPreferredSize(new Dimension(100, 100));
            b15.setFont(new Font(Font.SERIF, Font.BOLD, 20));
            b15.setBorder(new LineBorder(Color.BLACK, 1));
            b15.setBorder(new EtchedBorder(EtchedBorder.RAISED));
            b15.setBackground(Color.WHITE);

            buttonList.add(b15);
            boardPanel.add(b15);

            b15.addActionListener(this);
        } else {
            // Random board
        }

        southPanel.add(text);

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
        }
        return null;
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
            case 1, 2 ->
                    buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 4
            case 3 -> buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 5
            case 4 ->
                    buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 6 & 7
            case 5, 6 ->
                    buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 8
            case 7 ->
                    buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 9
            case 8 ->
                    buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 10 & 11
            case 9, 10 ->
                    buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 12
            case 11 ->
                    buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 13
            case 12 -> buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton);
            // button 14 & 15
            case 13, 14 ->
                    buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton);
            // button "16"
            case 15 -> buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton);
            default -> false;
        };
        return moveIsPossible;
    }

    public boolean gameFinish() {
        boolean finish = true;
        for (int i = 0; i < buttonList.size(); i++) {
            if (!(buttonList.get(i).getText().equals(String.valueOf(i + 1)))) {
                if (i == 15 && (!(buttonList.get(i).getText().equals(" ")))) {
                    finish = false;
                }
            }
        }
        return finish;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton emptyButton = findEmptyButton();
        JButton clicked = (JButton) e.getSource();

        for (int i = 0; i < buttonList.size(); i++) {
            if (buttonList.get(i).equals(clicked)) {
                if (checkIfMoveIsPossible(i, emptyButton)) {
                    moveButton(clicked, emptyButton);
                    break;
                }
            }
        }
        if (gameFinish()) {
            text.setSize(50, 50);
            text.setBorder(new EtchedBorder());
            text.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
            text.setText(" \nCongratulation, you solve the board! ");
        }
    }


}

