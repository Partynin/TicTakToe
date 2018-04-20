package com.partinin.app.veiw;

import com.partinin.app.model.BigField;
import com.partinin.app.model.Point;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

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
    }

    private void fillGridPane() {
        for (int i = 0; i < COUNT_OF_CELL; i++) {
            for (int j = 0; j < COUNT_OF_CELL; j++) {
                gridPane.add(bigCells[i][j], j, i);
            }
        }
    }

    private void addGridPaneInBigFieldView() {
        this.setCenter(gridPane);
    }

}
