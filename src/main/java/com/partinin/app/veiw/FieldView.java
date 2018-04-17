package com.partinin.app.veiw;

import com.partinin.app.controller.Game;
import com.partinin.app.model.Field;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

public class FieldView extends BorderPane {
    // Create and initialize a status label
    private Label lblStatus = new Label("X's turn to play");
    private Field field;
    private Cell[][] cellsGrid = new Cell[3][3];

    public FieldView(Field field) {
        this.field = field;
        // Fill paneGird with cells
        // Pane to hold cell
        GridPane paneGrid = new GridPane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                paneGrid.add(cellsGrid[i][j] = new Cell(i, j), j, i);
            }
        }
        this.setCenter(paneGrid);
        HBox hBoxForLabel = new HBox(5);
        hBoxForLabel.getChildren().add(lblStatus);
        hBoxForLabel.setAlignment(Pos.CENTER);
        this.setBottom(hBoxForLabel);
    }

    public void setLblStatus(String text) {
       lblStatus.setText(text);
    }

    public class Cell extends Pane {
        private char token = ' ';
        private int x;
        private int y;

        public Cell(int x, int y) {
            setStyle("-fx-border-color: black");
            this.setPrefSize(2000, 2000);
            this.setOnMouseClicked(e -> {
                Game.handleMouseClick( this, FieldView.this, field);
            });
            this.x = x;
            this.y = y;
        }

        public void setToken(char c) {
            token = c;

            if (token == 'X') {
                Line line1 = new Line(10, 10,
                        this.getWidth() - 10, this.getHeight() -10);
                line1.endXProperty().bind(this.widthProperty().subtract(10));
                line1.endXProperty().bind(this.heightProperty().subtract(10));
                Line line2 = new Line(10, this.getHeight() - 10,
                        this.getWidth() - 10, 10);
                line2.startYProperty().bind(this.heightProperty().subtract(10));
                line2.endXProperty().bind(this.widthProperty().subtract(10));
                line1.setStroke(Color.BLUE);
                line2.setStroke(Color.BLUE);

                // Add the lines to the pane
                this.getChildren().addAll(line1, line2);
            }
            else if (token == 'O') {
                Ellipse ellipse = new Ellipse(this.getWidth() / 2, this.getHeight() / 2,
                        this.getWidth() / 2 - 10, this.getHeight() / 2 - 10);
                ellipse.centerXProperty().bind(this.widthProperty().divide(2));
                ellipse.centerYProperty().bind(this.heightProperty().divide(2));
                ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
                ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
                ellipse.setStroke(Color.GREEN);
                ellipse.setFill(null);

                // Add the ellipse to the pane
                this.getChildren().add(ellipse);
            }
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
