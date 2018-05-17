package com.partinin.app.view;

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

import static com.partinin.app.model.ConstantsTicTakToe.COUNT_OF_CELL;

/**
 * The class for imaging the field.
 */

public class FieldView extends BorderPane {

    private static final int INDENT_TOKEN_FROM_AGE_OF_FIELD = 10;
    private static final int WIDTH_OF_TOKEN_IMAGE = 8;
    private static final int PREFER_WIDTH_OF_FIELD_WITH_TOKEN_IMAGE = 6000;
    private static final int PREFER_HEIGHT_OF_FIELD_WITH_TOKEN_IMAGE = 6000;
    private static final int PREFER_WIDTH_OF_CELL = 2000;
    private static final int PREFER_HEIGHT_OF_CELL = 2000;
    private static final int WIDTH_OF_TOKEN_IMAGE_ON_CELL = 4;
    private Label lblStatus = new Label("X's turn to play"); // Create and initialize a status label
    private Field field;
    private Cell[][] cellsGrid;
    private boolean forbidChangeColor = true;
    private Pane winnerPane = new Pane();
    private GridPane paneGrid = new GridPane(); // Pane to hold cell
    private Point position;

    FieldView(Field fieldForThisFieldView) {
        field = fieldForThisFieldView;
        cellsGrid = new Cell[COUNT_OF_CELL][COUNT_OF_CELL];
        fillPaneGirdWithCells();
    }

    private void fillPaneGirdWithCells() {
        for (int i = 0; i < COUNT_OF_CELL; i++) {
            for (int j = 0; j < COUNT_OF_CELL; j++) {
                Point newPosition = new Point(i, j);
                // Fill paneGird with cells.
                paneGrid.add(cellsGrid[i][j] = new Cell(newPosition), j, i);
            }
        }

        this.setCenter(paneGrid);
        HBox hBoxForLabel = new HBox();
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

    public void setPosition(Point positionOfFieldViewOnBigView) {
        this.position = positionOfFieldViewOnBigView;
    }


    public void setWinnerOnSmallFieldView() {
        if (field.getWhoseWon() == 'O') {
            setForbidChangeColor(false);
            winnerPane.setStyle("-fx-background-color: palevioletred");
            Ellipse ellipse = new Ellipse(winnerPane.getWidth() / 2, winnerPane.getHeight() / 2,
                    winnerPane.getWidth() / 2 - INDENT_TOKEN_FROM_AGE_OF_FIELD,
                    winnerPane.getHeight() / 2 - INDENT_TOKEN_FROM_AGE_OF_FIELD);
            ellipse.centerXProperty().bind(winnerPane.widthProperty().divide(2));
            ellipse.centerYProperty().bind(winnerPane.heightProperty().divide(2));
            ellipse.radiusXProperty().bind(winnerPane.widthProperty().divide(2)
                    .subtract(INDENT_TOKEN_FROM_AGE_OF_FIELD));
            ellipse.radiusYProperty().bind(winnerPane.heightProperty().divide(2)
                    .subtract(INDENT_TOKEN_FROM_AGE_OF_FIELD));
            ellipse.setStroke(Color.GREEN);
            ellipse.setStrokeWidth(WIDTH_OF_TOKEN_IMAGE);
            ellipse.setFill(null);
            // Add the ellipse to the pane
            winnerPane.getChildren().add(ellipse);
            winnerPane.setPrefSize(PREFER_WIDTH_OF_FIELD_WITH_TOKEN_IMAGE,
                    PREFER_HEIGHT_OF_FIELD_WITH_TOKEN_IMAGE);
            this.setCenter(winnerPane);
        } else {
            setForbidChangeColor(false);
            winnerPane.setStyle("-fx-background-color: palevioletred");
            Line line1 = new Line(INDENT_TOKEN_FROM_AGE_OF_FIELD, INDENT_TOKEN_FROM_AGE_OF_FIELD,
                    winnerPane.getWidth() - INDENT_TOKEN_FROM_AGE_OF_FIELD, winnerPane.getHeight()
                    - INDENT_TOKEN_FROM_AGE_OF_FIELD);
            line1.endXProperty().bind(winnerPane.widthProperty()
                    .subtract(INDENT_TOKEN_FROM_AGE_OF_FIELD));
            line1.endYProperty().bind(winnerPane.heightProperty()
                    .subtract(INDENT_TOKEN_FROM_AGE_OF_FIELD));
            Line line2 = new Line(INDENT_TOKEN_FROM_AGE_OF_FIELD, winnerPane.getHeight()
                    - INDENT_TOKEN_FROM_AGE_OF_FIELD,
                    winnerPane.getWidth() - INDENT_TOKEN_FROM_AGE_OF_FIELD,
                    INDENT_TOKEN_FROM_AGE_OF_FIELD);
            line2.startYProperty().bind(winnerPane.heightProperty()
                    .subtract(INDENT_TOKEN_FROM_AGE_OF_FIELD));
            line2.endXProperty().bind(winnerPane.widthProperty()
                    .subtract(INDENT_TOKEN_FROM_AGE_OF_FIELD));
            line1.setStroke(Color.BLUE);
            line1.setStrokeWidth(WIDTH_OF_TOKEN_IMAGE);
            line2.setStroke(Color.BLUE);
            line2.setStrokeWidth(WIDTH_OF_TOKEN_IMAGE);
            // Add the lines to the pane
            winnerPane.getChildren().addAll(line1, line2);
            winnerPane.setPrefSize(PREFER_WIDTH_OF_FIELD_WITH_TOKEN_IMAGE,
                    PREFER_HEIGHT_OF_FIELD_WITH_TOKEN_IMAGE);
            this.setCenter(winnerPane);
        }
    }

    public void changeBackgroundColorToRed() {
        if (forbidChangeColor) {
            this.setStyle("-fx-background-color: palevioletred");
        }
    }

    public void changeBackgroundColorToGreen() {
        if (forbidChangeColor) {
            this.setStyle("-fx-background-color: lightgreen");
        }
    }

    private void setForbidChangeColor(boolean banChangeColor) {
        forbidChangeColor = banChangeColor;
    }

    public void setLblStatus(String text) {
        lblStatus.setText(text);
    }

    public Cell getCell(Point pointPosition) {
        return cellsGrid[pointPosition.getX()][pointPosition.getY()];
    }

    /**
     * The class cell for imaging a cell on the field view.
     */

    public class Cell extends Pane {

        private char token = ' ';

        public Cell(Point positionClick) {
            setStyle("-fx-border-color: black");
            this.setPrefSize(PREFER_WIDTH_OF_CELL, PREFER_HEIGHT_OF_CELL);
            this.setOnMouseClicked(e -> {
                Game.handleMouseClick(positionClick, FieldView.this);
            });
        }

        public void setToken(char c) {
            token = c;

            if (token == 'X') {
                Line line1 = new Line(INDENT_TOKEN_FROM_AGE_OF_FIELD,
                        INDENT_TOKEN_FROM_AGE_OF_FIELD,
                        this.getWidth() - INDENT_TOKEN_FROM_AGE_OF_FIELD, this.getHeight()
                        - INDENT_TOKEN_FROM_AGE_OF_FIELD);
                line1.endXProperty().bind(this.widthProperty()
                        .subtract(INDENT_TOKEN_FROM_AGE_OF_FIELD));
                line1.endYProperty().bind(this.heightProperty()
                        .subtract(INDENT_TOKEN_FROM_AGE_OF_FIELD));
                Line line2 = new Line(INDENT_TOKEN_FROM_AGE_OF_FIELD, this.getHeight()
                        - INDENT_TOKEN_FROM_AGE_OF_FIELD,
                        this.getWidth() - INDENT_TOKEN_FROM_AGE_OF_FIELD,
                        INDENT_TOKEN_FROM_AGE_OF_FIELD);
                line2.startYProperty().bind(this.heightProperty()
                        .subtract(INDENT_TOKEN_FROM_AGE_OF_FIELD));
                line2.endXProperty().bind(this.widthProperty()
                        .subtract(INDENT_TOKEN_FROM_AGE_OF_FIELD));
                line1.setStroke(Color.BLUE);
                line1.setStrokeWidth(WIDTH_OF_TOKEN_IMAGE_ON_CELL);
                line2.setStroke(Color.BLUE);
                line2.setStrokeWidth(WIDTH_OF_TOKEN_IMAGE_ON_CELL);

                // Add the lines to the pane
                this.getChildren().addAll(line1, line2);
            } else if (token == 'O') {
                Ellipse ellipse = new Ellipse(this.getWidth() / 2, this.getHeight() / 2,
                        this.getWidth() / 2 - INDENT_TOKEN_FROM_AGE_OF_FIELD,
                        this.getHeight() / 2 - INDENT_TOKEN_FROM_AGE_OF_FIELD);
                ellipse.centerXProperty().bind(this.widthProperty().divide(2));
                ellipse.centerYProperty().bind(this.heightProperty().divide(2));
                ellipse.radiusXProperty().bind(this.widthProperty().divide(2)
                        .subtract(INDENT_TOKEN_FROM_AGE_OF_FIELD));
                ellipse.radiusYProperty().bind(this.heightProperty().divide(2)
                        .subtract(INDENT_TOKEN_FROM_AGE_OF_FIELD));
                ellipse.setStroke(Color.GREEN);
                ellipse.setStrokeWidth(WIDTH_OF_TOKEN_IMAGE_ON_CELL);
                ellipse.setFill(null);

                // Add the ellipse to the pane
                this.getChildren().add(ellipse);
            }
        }
    }
}
