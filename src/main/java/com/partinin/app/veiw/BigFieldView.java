package com.partinin.app.veiw;

import com.partinin.app.model.BigField;
import com.partinin.app.model.Point;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * This class create a big field view and put small field views into it.
 */

public class BigFieldView extends BorderPane {
    public static final int COUNT_OF_CELL = 3;
    private FieldView[][] bigCells = new FieldView[COUNT_OF_CELL][COUNT_OF_CELL];
    private GridPane gridPane = new GridPane();

    public BigFieldView(BigField bigField) {
        for (int i = 0; i < COUNT_OF_CELL; i++) {
            for (int j = 0; j < COUNT_OF_CELL; j++) {
                Point position = new Point(i, j);
                FieldView fieldV = new FieldView(bigField.getSmallField(position));
                bigCells[i][j] = fieldV;
            }
        }
        fillGridPane();
        addGridPaneInBigFieldView();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setStyle("-fx-background-color: black");
    }

    /**
     * Puts the small fields into the gird pane.
     */
    private void fillGridPane() {
        for (int i = 0; i < COUNT_OF_CELL; i++) {
            for (int j = 0; j < COUNT_OF_CELL; j++) {
                gridPane.add(bigCells[i][j], j, i);
            }
        }
    }

    /**
     * Changes the colors of locked fields as red, not locked as green.
     */
    public void changeColorFieldView(Point position) {
        if (bigCells[position.getX()][position.getY()].getField().getWhoseWon() == ' ') {
            for (int i = 0; i < COUNT_OF_CELL; i++) {
                for (int j = 0; j < COUNT_OF_CELL; j++) {
                    bigCells[i][j].changeBackgroundColorToRed();
                }
            }
            bigCells[position.getX()][position.getY()].changeBackgroundColorToGreen();
        } else {
            for (int i = 0; i < COUNT_OF_CELL; i++) {
                for (int j = 0; j < COUNT_OF_CELL; j++) {
                    bigCells[i][j].changeBackgroundColorToGreen();
                }
            }
        }
    }

    private void addGridPaneInBigFieldView() {
        this.setCenter(gridPane);
    }

    public void setWinner() {
        for (int i = 0; i < COUNT_OF_CELL; i++) {
            for (int j = 0; j < COUNT_OF_CELL; j++) {
                if (bigCells[i][j].getField().getWhoseWon() != ' ')
                    bigCells[i][j].setWinnerOnSmallFieldView();
            }
        }
    }
}
