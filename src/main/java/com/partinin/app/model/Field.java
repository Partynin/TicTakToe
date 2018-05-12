package com.partinin.app.model;


import com.partinin.app.controller.Game;
import com.partinin.app.view.BigFieldView;
import com.partinin.app.view.FieldView;

/**
 * This class represent a small field of the game.
 */

public class Field implements ConstantsTicTakToe {

    // Indicate which player has a turn, initially it is the X player.
    private char whoseTurn = 'X';
    private char whoseWon = ' '; // Determine who is the winner on this field.
    private boolean blocked = false; // Block the field for next turn, if it true the turn blocked.
    private char[][] cells;

    public Field() {
        cells = new char[COUNT_OF_CELL][COUNT_OF_CELL];
        fillCells();
    }

    /**
     * Fill the array cells with spaces.
     */
    private void fillCells() {
        for (int i = 0; i < COUNT_OF_CELL; i++) {
            for (int j = 0; j < COUNT_OF_CELL; j++) {
                cells[i][j] = ' ';
            }
        }
    }

    /**
     * Puts token in the specified cell.
     */
    public void setTokenInCells(Point position, FieldView view) {
        // If cell is empty and game is not over and the field not blocked.
        if (cells[position.getX()][position.getY()] == ' ' && !blocked) {
            cells[position.getX()][position.getY()] = whoseTurn; // Set token in the cell

            checkGameStatus(view, view.getCell(position));
            blockOtherFields(position);
            changeWhoseTurnTextFields(position);
            changeColorFieldView(position);
        }
    }

    private void checkGameStatus(FieldView view, FieldView.Cell cell) {
        // Check game status.
        if (isWon(whoseTurn)) {
            cell.setToken(whoseTurn);
            view.setLblStatus(whoseTurn + " won in this field");
            blockedFieldPutWinner(whoseTurn, view.getPosition());
            changeAllTurn();
            whoseTurn = ' '; // Game is over on this field.
        } else if (isFull()) {
            view.setLblStatus("Draw! The game on this field over");
            whoseTurn = ' '; // Game is over on this field.
        } else {
            // Set token in cell.
            cell.setToken(whoseTurn);
            // Change the turn.
            changeAllTurn();
        }
    }

    /**
     * Determines if the player with the specified token wins.
     */
    private boolean isWon(char token) {
        // Horizon lines.
        for (int i = 0; i < COUNT_OF_CELL; i++)
            if (cells[i][0] == token
                    && cells[i][1] == token
                    && cells[i][2] == token) {
                return true;
            }

        // Vertical lines.
        for (int j = 0; j < COUNT_OF_CELL; j++)
            if (cells[0][j] == token
                    && cells[1][j] == token
                    && cells[2][j] == token) {
                return true;
            }

        // Left-right diagonal.
        if (cells[0][0] == token
                && cells[1][1] == token
                && cells[2][2] == token) {
            return true;
        }

        // Right-left diagonal.
        if (cells[0][2] == token
                && cells[1][1] == token
                && cells[2][0] == token) {
            return true;
        }

        return false;
    }

    private void blockedFieldPutWinner(char whoseWon, Point position) {
        this.whoseWon = whoseWon;
        BigFieldView bigFieldView = Game.getBigFieldView();
        bigFieldView.setWinner(position);
    }

    /**
     * Blocks other fields on BigField bord.
     */
    private void blockOtherFields(Point position) {
        BigField bigField = Game.getBigField();
        bigField.blockOtherFields(position);
    }

    private void changeWhoseTurnTextFields(Point position) {
        BigFieldView bigFieldView = Game.getBigFieldView();
        bigFieldView.changeWhoseTurn(position, whoseTurn);
    }

    /**
     * Changes color on big field view.
     */
    private void changeColorFieldView(Point position) {
        BigFieldView bigFieldView = Game.getBigFieldView();
        bigFieldView.changeColorFieldView(position);
    }

    /**
     * Determines if the cells are all occupied.
     */
    private boolean isFull() {
        for (int i = 0; i < COUNT_OF_CELL; i++)
            for (int j = 0; j < COUNT_OF_CELL; j++)
                if (cells[i][j] == ' ')
                    return false;

        return true;
    }

    /**
     * Block the field.
     */
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    /**
     * Changes turn token on 'X' or 'O'.
     */
    public void changeTurn() {
        if (whoseTurn == 'X')
            whoseTurn = 'O';
        else whoseTurn = 'X';
    }


    public char getWhoseWon() {
        return whoseWon;
    }

    private void changeAllTurn() {
        BigField bigField = Game.getBigField();
        bigField.changeTurn();
    }


}
