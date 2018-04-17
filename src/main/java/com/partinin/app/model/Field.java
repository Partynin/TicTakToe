package com.partinin.app.model;


import com.partinin.app.veiw.FieldView;

public class Field {
    // Indicate which player has a turn, initially it is the X player
    private char whoseTurn = 'X';
    private char[][] cells = new char[3][3];
    public static final int SIDE_OF_FIELD = 3;

    public Field() {
        fillCells();
    }

    private void fillCells() {
        for (int i = 0; i < SIDE_OF_FIELD; i++) {
            for (int j = 0; j < SIDE_OF_FIELD; j++) {
                cells[i][j] = ' ';
            }
        }
    }

    /** Determines if the cells are all occupied */
    public boolean isFull() {
         for (int i = 0; i < SIDE_OF_FIELD; i++)
             for (int j = 0; j < SIDE_OF_FIELD; j++)
             if (cells[i][j] == ' ')
             return false;

         return true;
    }

    /** Determines if the player with the specified token wins */
    public boolean isWon(char token) {
        // Horizon lines
        for (int i = 0; i < SIDE_OF_FIELD; i++)
             if (cells[i][0] == token
         && cells[i][1] == token
         && cells[i][2] == token) {
             return true;
        }

        // Vertical lines
        for (int j = 0; j < SIDE_OF_FIELD; j++)
             if (cells[0][j] == token
         && cells[1][j] == token
         && cells[2][j] == token) {
             return true;
        }

        // Left-right diagonal
        if (cells[0][0] == token
         && cells[1][1] == token
         && cells[2][2] == token) {
             return true;
        }

        // Right-left diagonal
        if (cells[0][2] == token
         && cells[1][1] == token
         && cells[2][0] == token) {
             return true;
        }

         return false;
    }

    /** Puts token in the specified cell */
    public void setTokenInCells(int x, int y, FieldView view, FieldView.Cell cell) {
        // If cell is empty and game is not over
        if (cells[x][y] == ' ' && whoseTurn != ' ') {
            cells[x][y] = whoseTurn; // Set token in the cell

            // Check game status
            if (isWon(whoseTurn)) {
                cell.setToken(whoseTurn);
                view.setLblStatus(whoseTurn + " won! The game is over");
                whoseTurn = ' '; // Game is over
            }
            else if (isFull()) {
                view.setLblStatus("Draw! The game is over");
                whoseTurn = ' '; // Game is over
            }
            else {
                // Set token in cell
                cell.setToken(whoseTurn);
                // Change the turn
                whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
                // Display whose turn
                view.setLblStatus(whoseTurn + "'s turn");
            }
        }
    }
}
