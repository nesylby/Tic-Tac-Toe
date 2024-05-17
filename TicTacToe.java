import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private JLabel statusLabel;
    private char currentPlayer;
    private boolean gameOver;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(300, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();
        
        setVisible(true);
    }

    private void initializeComponents() {
        // Status Panel at the top
        JPanel statusPanel = new JPanel();
        statusLabel = new JLabel("Player X's turn");
        statusPanel.add(statusLabel);
        add(statusPanel, BorderLayout.NORTH);

        // Grid Panel in the center
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3, 3));
        add(gridPanel, BorderLayout.CENTER);

        buttons = new JButton[3][3];
        currentPlayer = 'X';
        gameOver = false;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                buttons[row][col].addActionListener(this);
                buttons[row][col].setBackground(Color.WHITE); // Set default background color to white
                buttons[row][col].setForeground(Color.BLACK); // Set default text color to black
                gridPanel.add(buttons[row][col]);
            }
        }

        // New Game Button at the bottom
        JPanel newGamePanel = new JPanel();
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> resetGame());
        newGamePanel.add(newGameButton);
        add(newGamePanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (gameOver)
            return;

        JButton clickedButton = (JButton) e.getSource();

        if (clickedButton.getText().equals("")) {
            clickedButton.setText(String.valueOf(currentPlayer));
            if (currentPlayer == 'X') {
                clickedButton.setForeground(Color.BLUE); // Text color for X
            } else {
                clickedButton.setForeground(Color.RED); // Text color for O
            }
            if (checkWin()) {
                statusLabel.setText("Player " + currentPlayer + " wins!");
                gameOver = true;
            } else if (checkDraw()) {
                statusLabel.setText("It's a draw!");
                gameOver = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                statusLabel.setText("Player " + currentPlayer + "'s turn");
            }
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer))
                    && buttons[i][1].getText().equals(String.valueOf(currentPlayer))
                    && buttons[i][2].getText().equals(String.valueOf(currentPlayer)))
                return true;
            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer))
                    && buttons[1][i].getText().equals(String.valueOf(currentPlayer))
                    && buttons[2][i].getText().equals(String.valueOf(currentPlayer)))
                return true;
        }
        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer))
                && buttons[1][1].getText().equals(String.valueOf(currentPlayer))
                && buttons[2][2].getText().equals(String.valueOf(currentPlayer)))
            return true;
        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer))
                && buttons[1][1].getText().equals(String.valueOf(currentPlayer))
                && buttons[2][0].getText().equals(String.valueOf(currentPlayer)))
            return true;
        return false;
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals(""))
                    return false;
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackground(Color.WHITE); // Reset to default background color
                buttons[i][j].setForeground(Color.BLACK); // Reset to default text color
            }
        }
        currentPlayer = 'X';
        statusLabel.setText("Player X's turn");
        gameOver = false;
    }
}
