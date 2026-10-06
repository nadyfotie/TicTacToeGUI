import javax.swing.*;
import java.awt.*;


public class TicTacToeFrame extends JFrame {
        private TicTacToeTile[][] board;
        private JPanel boardPanel;
        private String player = "X";
        private int moveCnt = 0;
        private static final int MOVES_FOR_WIN = 5;
        private String[][] gameBoard;



        public TicTacToeFrame() {
                setTitle("Tic Tac Toe");
                setSize(400, 400);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                board = new TicTacToeTile[3][3];
            boardPanel = new JPanel();
            boardPanel.setLayout(new GridLayout(3, 3));
            gameBoard = new String[3][3];

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    board[row][col] = new TicTacToeTile(row, col);
                    board[row][col].setText(" ");
                    gameBoard[row][col] = " ";
                    board[row][col].addActionListener(e -> handleTileClick((TicTacToeTile) e.getSource()));
                    boardPanel.add(board[row][col]);
                }
            }

            add(boardPanel);
            JButton quitButtom = new JButton("Quit");
            quitButtom.addActionListener(e -> quitGame());
            add(quitButtom, BorderLayout.SOUTH);
            setVisible(true);

        }

        private void quitGame() {
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to quit?",
                    "Quit",
                    JOptionPane.YES_NO_OPTION
            );
            if (choice == JOptionPane.YES_OPTION) {
                dispose();
            }
        }

        private void handleTileClick(TicTacToeTile tile) {
            int row = tile.getRow();
            int col = tile.getCol();
            if (!tile.getText().equals(" ")) {
                JOptionPane.showMessageDialog(this, "That square is already taken!");
                return;
            }
                tile.setText(player);
            gameBoard[row][col] = player;
            moveCnt++;
            if (moveCnt >= MOVES_FOR_WIN) {
                if (isWin(player)) {
                    disableBoard();
                    JOptionPane.showMessageDialog(this,
                            "Player " + player + " wins!");
                   int choice = JOptionPane.showConfirmDialog(this, "Would you like to play another game",
                           "Play again", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        resetBoard();
                    }
                    else{
                        dispose();
                    }
                   return;
                }
            }
            if (moveCnt >= 7 && isTie()){
                disableBoard();
                JOptionPane.showMessageDialog(this, "its a tie");
                int choice = JOptionPane.showConfirmDialog(this, "Would you like to play another game",
                        "Play again", JOptionPane.YES_NO_OPTION);
                if  (choice == JOptionPane.YES_OPTION) {
                    resetBoard();
                }
                else{
                    dispose();
                }
                return;
            }
            if (player.equals("X")) {
                player = "O";
            } else {
                player = "X";
            }



        }
        private void resetBoard(){
            player = "X";
            moveCnt = 0;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    gameBoard[row][col] = " ";
                    board[row][col].setText(" ");
                    board[row][col].setEnabled(true);
                }
            }
        }
        private boolean isWin(String player) {
            if (isColWin(player) || isRowWin(player) || isDiagnalWin(player)) {
                return true;
            }
            return false;
        }

        private void disableBoard(){
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    board[row][col].setEnabled(false);
                }
            }
        }

        private boolean isColWin(String player) {
            for (int col = 0; col < 3; col++) {
                if (gameBoard[0][col].equals(player) &&
                        gameBoard[1][col].equals(player) &&
                        gameBoard[2][col].equals(player)) {
                    return true;
                }
            }
            return false;
        }
        private boolean isRowWin(String player) {
            for (int row = 0; row < 3; row++) {
                if (gameBoard[row][0].equals(player) &&
                        gameBoard[row][1].equals(player) &&
                        gameBoard[row][2].equals(player)) {
                    return true;
                }
            }
            return false;
        }
        private boolean isDiagnalWin(String player) {
            if (gameBoard[0][0].equals(player) &&
                    gameBoard[1][1].equals(player) &&
                    gameBoard[2][2].equals(player)) {
                return true;
            }

            if (gameBoard[0][2].equals(player) &&
                    gameBoard[1][1].equals(player) &&
                    gameBoard[2][0].equals(player)) {
                return true;
            }

            return false;
        }
        private boolean isTie() {
            boolean hasX;
            boolean hasO;

            for (int row = 0; row < 3; row++) {
                hasX = false;
                hasO = false;
                for (int col = 0; col < 3; col++) {
                    if (gameBoard[row][col].equals("X")) {
                        hasX = true;
                    }
                    if (gameBoard[row][col].equals("O")) {
                        hasO = true;
                    }
                }
                if (!(hasX && hasO)) {
                    return false;
                }
            }

            // checking the tie with the columns.
            for (int col = 0; col < 3; col++) {
                hasX = false;
                hasO = false;
                for (int row = 0; row < 3; row++) {
                    if (gameBoard[row][col].equals("X")) {
                        hasX = true;
                    }
                    if (gameBoard[row][col].equals("O")) {
                        hasO = true;
                    }
                }
                if (!(hasX && hasO)) {
                    return false;
                }
            }
            // check tie for diagonal top left to right
            hasX = false;
            hasO = false;

            for (int i = 0; i < 3; i++) {
                if (gameBoard[i][i].equals("X")) {
                    hasX = true;
                }
                if (gameBoard[i][i].equals("O")) {
                    hasO = true;

                }
            }
            if (!(hasX && hasO)) {
                return false;

            }
            // checking for diogonal tie from bottom left to right
            hasX = false;
            hasO = false;

            for (int i = 0; i < 3; i++) {
                if (gameBoard[i][2 - i].equals("X")) {
                    hasX = true;
                }
                if (gameBoard[i][2 - i].equals("O")) {
                    hasO = true;
                }
            }
            if (!(hasX && hasO)) {
                return false;
            }
            return true;

        }

}
