package com.partinin.app.controller;

import com.partinin.app.model.BigField;
import com.partinin.app.model.Point;
import com.partinin.app.view.BigFieldView;
import com.partinin.app.view.FieldView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The Game class represent controller for the game.
 * <p>
 * The game rules:
 * 1. Each turn, you mark one of the small squares.
 * 2. When you get three in a row on a small board, you’ve won that board.
 * 3. To win the game, you need to win three small boards in a row.
 * <p>
 * You don’t get to pick which of the nine boards to play on. That’s
 * determined by your opponent’s previous move. Whichever square he picks,
 * that’s the board you must play in next.
 * <p>
 * What if my opponent sends me to a board that’s already been won? In that
 * case, congratulations – you get to go anywhere you like, on any of the
 * other boards.
 * <p>
 * What if one of the small boards results in a tie? I recommend that the board
 * counts for neither X nor O. But, if you feel like a crazy variant, you could
 * agree before the game to count a tied board for both X and O.
 */

public class Game extends Application {

    private static final int PANE_WIDTH = 700;
    private static final int PANE_HEIGHT = 700;
    private static BigField bigField;
    private static BigFieldView view;

    @Override
    public final void start(final Stage primaryStage) {
        // Create a scene and place the pane in the stage.
        Scene scene = new Scene(getPane(), PANE_WIDTH, PANE_HEIGHT);
        primaryStage.setTitle("Tic-Tak-Toe"); // Set the stage title.
        primaryStage.setScene(scene); // Place the scene in the stage.
        primaryStage.show(); // Display the stage.
    }

    /**
     * Creates a pane for the scene.
     */
    private BorderPane getPane() {
        bigField = new BigField(); // Create a Field model
        view = new BigFieldView(bigField); // Create a pane view
        BorderPane pane = new BorderPane();
        pane.setCenter(view);

        return pane;
    }

    /**
     * Handles mouse invents.
     */
    public static void handleMouseClick(final Point position,
                                        final FieldView fieldView) {
        fieldView.getField().setTokenInCells(position, fieldView);
        bigField.checkWinnerOnBigField();
    }

    /**
     * Returns the BigField instance.
     */
    public static BigField getBigField() {
        return bigField;
    }

    /**
     * Returns the BigFieldView instance.
     */
    public static BigFieldView getBigFieldView() {
        return view;
    }
}
