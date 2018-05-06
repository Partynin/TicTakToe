package com.partinin.app.veiw;

import com.partinin.app.controller.Game;
import com.partinin.app.model.Field;
import com.partinin.app.model.Point;
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
    private static final int SIDE_OF_FIELD = 3;
    private Label lblStatus = new Label("X's turn to play"); // Create and initialize a status label
    private Field field;
    private Cell[][] cellsGrid;
    private boolean forbidChangeColor = true;
    private Pane winnerPane = new Pane();
    private GridPane paneGrid = new GridPane(); // Pane to hold cell
    private Point position;

    public FieldView(Field field) {
        this.field = field;
        cellsGrid = new Cell[SIDE_OF_FIELD][SIDE_OF_FIELD];
        fillPaneGirdWithCells();
    }

    private void fillPaneGirdWithCells() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Point newPosition = new Point(i, j);
                paneGrid.add(cellsGrid[i][j] = new Cell(newPosition), j, i); // Fill paneGird with cells
            }
        }

        this.setCenter(paneGrid);
        HBox hBoxForLabel = new HBox(5);
        hBoxForLabel.getChildren().addAll(lblStatus);
        hBoxForLabel.setAlignment(Pos.CENTER);
        hBoxForLabel.setStyle("-fx-border-color: black");
        this.setBottom(hBoxForLabel);
        this.setStyle("-fx-background-color: lightgreen");
    }


    public Field getField() {
        return field;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }


    public void setWinnerOnSmallFieldView() {
        if (field.getWhoseWon() == 'O') {
            setForbidChangeColor(false);
            winnerPane.setStyle("-fx-background-color: palevioletred");
            Ellipse ellipse = new Ellipse(winnerPane.getWidth() / 2, winnerPane.getHeight() / 2,
                    winnerPane.getWidth() / 2 - 10, winnerPane.getHeight() / 2 - 10);
            ellipse.centerXProperty().bind(winnerPane.widthProperty().divide(2));
            ellipse.centerYProperty().bind(winnerPane.heightProperty().divide(2));
            ellipse.radiusXProperty().bind(winnerPane.widthProperty().divide(2).subtract(10));
            ellipse.radiusYProperty().bind(winnerPane.heightProperty().divide(2).subtract(10));
            ellipse.setStroke(Color.GREEN);
            ellipse.setStrokeWidth(8);
            ellipse.setFill(null);
            // Add the ellipse to the pane
            winnerPane.getChildren().add(ellipse);
            winnerPane.setPrefSize(6000, 6000);
            this.setCenter(winnerPane);
        } else {
            setForbidChangeColor(false);
            winnerPane.setStyle("-fx-background-color: palevioletred");
            Line line1 = new Line(10, 10,
                    winnerPane.getWidth() - 10, winnerPane.getHeight() - 10);
            line1.endXProperty().bind(winnerPane.widthProperty().subtract(10));
            line1.endYProperty().bind(winnerPane.heightProperty().subtract(10));
            Line line2 = new Line(10, winnerPane.getHeight() - 10,
                    winnerPane.getWidth() - 10, 10);
            line2.startYProperty().bind(winnerPane.heightProperty().subtract(10));
            line2.endXProperty().bind(winnerPane.widthProperty().subtract(10));
            line1.setStroke(Color.BLUE);
            line1.setStrokeWidth(8);
            line2.setStroke(Color.BLUE);
            line2.setStrokeWidth(8);
            // Add the lines to the pane
            winnerPane.getChildren().addAll(line1, line2);
            winnerPane.setPrefSize(6000, 6000);
            this.setCenter(winnerPane);
        }
    }

    public void changeBackgroundColorToRed() {
        if (forbidChangeColor)
            this.setStyle("-fx-background-color: palevioletred");
    }

    public void changeBackgroundColorToGreen() {
        if (forbidChangeColor)
            this.setStyle("-fx-background-color: lightgreen");
    }

    private void setForbidChangeColor(boolean forbidChangeColor) {
        this.forbidChangeColor = forbidChangeColor;
    }

    public void setLblStatus(String text) {
        lblStatus.setText(text);
    }

    public class Cell extends Pane {
        private char token = ' ';
        private Point position;

        public Cell(Point position) {
            setStyle("-fx-border-color: black");
            this.setPrefSize(2000, 2000);
            this.setOnMouseClicked(e -> {
                Game.handleMouseClick(this, FieldView.this, field);
            });
            this.position = position;
        }

        public void setToken(char c) {
            token = c;

            if (token == 'X') {
                Line line1 = new Line(10, 10,
                        this.getWidth() - 10, this.getHeight() - 10);
                line1.endXProperty().bind(this.widthProperty().subtract(10));
                line1.endYProperty().bind(this.heightProperty().subtract(10));
                Line line2 = new Line(10, this.getHeight() - 10,
                        this.getWidth() - 10, 10);
                line2.startYProperty().bind(this.heightProperty().subtract(10));
                line2.endXProperty().bind(this.widthProperty().subtract(10));
                line1.setStroke(Color.BLUE);
                line1.setStrokeWidth(4);
                line2.setStroke(Color.BLUE);
                line2.setStrokeWidth(4);

                // Add the lines to the pane
                this.getChildren().addAll(line1, line2);
            } else if (token == 'O') {
                Ellipse ellipse = new Ellipse(this.getWidth() / 2, this.getHeight() / 2,
                        this.getWidth() / 2 - 10, this.getHeight() / 2 - 10);
                ellipse.centerXProperty().bind(this.widthProperty().divide(2));
                ellipse.centerYProperty().bind(this.heightProperty().divide(2));
                ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
                ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
                ellipse.setStroke(Color.GREEN);
                ellipse.setStrokeWidth(4);
                ellipse.setFill(null);

                // Add the ellipse to the pane
                this.getChildren().add(ellipse);
            }
        }

        public Point getPosition() {
            return position;
        }
    }
}
