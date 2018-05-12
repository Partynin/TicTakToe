package com.partinin.app.view;

import com.partinin.app.model.BigField;
import com.partinin.app.model.ConstantsTicTakToe;
import com.partinin.app.model.Point;
import javafx.scene.layout.GridPane;

/**
 * This class create a big field view and put small field views into it.
 */

public class BigFieldView extends GridPane implements ConstantsTicTakToe {

    private FieldView[][] bigCellsOfFieldViews;
    private BigField bigField;
    private char nextTurn = 'O';

    public BigFieldView(BigField bigField) {
        this.bigField = bigField;
        bigCellsOfFieldViews = new FieldView[COUNT_OF_CELL][COUNT_OF_CELL];
        fillBigFieldViewArray();
    }

    private void fillBigFieldViewArray() {
        for (int i = 0; i < COUNT_OF_CELL; i++) {
            for (int j = 0; j < COUNT_OF_CELL; j++) {
                Point position = new Point(i, j);
                FieldView fieldV = new FieldView(bigField.getSmallField(position));
                fieldV.setPosition(position);
                bigCellsOfFieldViews[i][j] = fieldV;
            }
        }
        fillBigViewWithSmallFieldViews();
        this.setHgap(5);
        this.setVgap(5);
        this.setStyle("-fx-background-color: black");
    }

    /**
     * Puts the small fields into the gird pane.
     */
    private void fillBigViewWithSmallFieldViews() {
        for (int i = 0; i < COUNT_OF_CELL; i++) {
            for (int j = 0; j < COUNT_OF_CELL; j++) {
                this.add(bigCellsOfFieldViews[i][j], j, i);
            }
        }
    }

    /**
     * Changes the colors of locked fields as red, not locked as green.
     */
    public void changeColorFieldView(Point position) {
        if (bigCellsOfFieldViews[position.getX()][position.getY()].getField().getWhoseWon() == ' ') {
            for (int i = 0; i < COUNT_OF_CELL; i++) {
                for (int j = 0; j < COUNT_OF_CELL; j++) {
                    bigCellsOfFieldViews[i][j].changeBackgroundColorToRed();
                }
            }
            bigCellsOfFieldViews[position.getX()][position.getY()].changeBackgroundColorToGreen();
        } else {
            for (int i = 0; i < COUNT_OF_CELL; i++) {
                for (int j = 0; j < COUNT_OF_CELL; j++) {
                    bigCellsOfFieldViews[i][j].changeBackgroundColorToGreen();
                }
            }
        }
    }

    public void changeWhoseTurn(Point position, char whoseTurn) {
        if (whoseTurn == ' ') {  // If game on small field ends
            for (int i = 0; i < COUNT_OF_CELL; i++) {
                for (int j = 0; j < COUNT_OF_CELL; j++) {
                    // Check if game on small field ends not change text field on the field
                    if (bigCellsOfFieldViews[i][j].getField().getWhoseWon() == ' ') {
                        bigCellsOfFieldViews[i][j].setLblStatus(nextTurn + "'s turn");
                    }
                }
            }
        } else {
            if (bigCellsOfFieldViews[position.getX()][position.getY()].getField().getWhoseWon() == ' ') {
                bigCellsOfFieldViews[position.getX()][position.getY()].setLblStatus(whoseTurn + "'s turn");
                changeNextTurnToken();
            } else {
                for (int i = 0; i < COUNT_OF_CELL; i++) {
                    for (int j = 0; j < COUNT_OF_CELL; j++) {
                        if (bigCellsOfFieldViews[i][j].getField().getWhoseWon() == ' ') {
                            bigCellsOfFieldViews[i][j].setLblStatus(whoseTurn + "'s turn");
                        }
                    }
                }
            }
        }
    }

    private void changeNextTurnToken() {
        if (nextTurn == 'O')
            nextTurn = 'X';
        else nextTurn = 'O';
    }

    public void setWinner(Point position) {
        bigCellsOfFieldViews[position.getX()][position.getY()].setWinnerOnSmallFieldView();
    }
}
