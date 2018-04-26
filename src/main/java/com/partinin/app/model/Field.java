package com.partinin.app.model;


import com.partinin.app.controller.Game;
import com.partinin.app.veiw.BigFieldView;
import com.partinin.app.veiw.FieldView;

/**
 * This class represent a small field of the game
 */

public class Field {
    // Indicate which player has a turn, initially it is the X player
    private char whoseTurn = 'X';
    private char[][] cells = new char[3][3];
    private static final int SIDE_OF_FIELD = 3;
    private boolean blocked = false;
    private char whoseWon = ' ';

    public Field() {
        fillCells();
    }

    /**
     * Initials fill the array cells with empty vales
     */
    private void fillCells() {
        for (int i = 0; i < SIDE_OF_FIELD; i++) {
            for (int j = 0; j < SIDE_OF_FIELD; j++) {
                cells[i][j] = ' ';
            }
        }
    }

    /**
     * Puts token in the specified cell
     */
    public void setTokenInCells(Point position, FieldView view, FieldView.Cell cell) {
        // If cell is empty and game is not over
        if (cells[position.getX()][position.getY()] == ' ' && whoseTurn != ' ' && !blocked) {
            cells[position.getX()][position.getY()] = whoseTurn; // Set token in the cell

            // Check game status
            if (isWon(whoseTurn)) {
                cell.setToken(whoseTurn);
                view.setLblStatus(whoseTurn + " won! The game is over");
                blockedField(whoseTurn);
                whoseTurn = ' '; // Game is over
            } else if (isFull()) {
                view.setLblStatus("Draw! The game is over");
                whoseTurn = ' '; // Game is over
            } else {
                // Set token in cell
                cell.setToken(whoseTurn);
                // Change the turn
                changeAllTurn();
                //whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
                // Display whose turn
                view.setLblStatus(whoseTurn + "'s turn");
            }

            blockOtherFields(position);
            changeColorFieldView(position);
        }
    }

    private void blockedField(char whoseWon) {
        this.whoseWon = whoseWon;
        BigFieldView bigFieldView = Game.getBigFieldView();
        bigFieldView.setWinner();
    }

    public char getWhoseWon() {
        return whoseWon;
    }

    /**
     * Blocks other fields on BigField bord
     */
    private void blockOtherFields(Point position) {
        BigField bigField = Game.getBigField();
        bigField.blockOtherFields(position);
    }

    /**
     * Changes color on big field view
     */
    private void changeColorFieldView(Point position) {
        BigFieldView bigFieldView = Game.getBigFieldView();
        bigFieldView.changeColorFieldView(position);
    }

    private void changeAllTurn() {
       BigField bigField = Game.getBigField();
       bigField.changeTurn();
    }

    /**
     * Determines if the player with the specified token wins
     */
    private boolean isWon(char token) {
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

    /**
     * Determines if the cells are all occupied
     */
    private boolean isFull() {
        for (int i = 0; i < SIDE_OF_FIELD; i++)
            for (int j = 0; j < SIDE_OF_FIELD; j++)
                if (cells[i][j] == ' ')
                    return false;

        return true;
    }

    /**
     * Block the field
     */
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    /** Changes turn token on 'X' or 'O' */
    public void changeTurn() {
        if (whoseTurn == 'X')
            whoseTurn = 'O';
        else whoseTurn = 'X';
    }
}
