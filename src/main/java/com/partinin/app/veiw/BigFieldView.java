package com.partinin.app.veiw;

import com.partinin.app.model.BigField;
import com.partinin.app.model.Point;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BigFieldView extends GridPane {
    public static final int COUNT_OF_CELL = 3;
    private BigField bigField;
    private BigCell[][] bigCells = new BigCell[COUNT_OF_CELL][COUNT_OF_CELL];


    public BigFieldView(BigField bigField) {
        this.bigField = bigField;

        for (int i = 0; i < COUNT_OF_CELL; i++) {
            for (int j = 0; j < COUNT_OF_CELL; j++) {
                Point position = new Point(i, j);
                bigCells[i][j] = new BigCell(position);
            }
        }
    }

    private class BigCell extends Pane {
        private Point position;

        public BigCell(Point position) {
            this.position = position;
        }
    }
}
