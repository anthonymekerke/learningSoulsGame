package graphics.panes;

import graphics.widgets.texts.GameLabel;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class TitlePane extends VBox {
    private static final Duration ANIMATION_DURATION = Duration.millis(1500);
    private static final double ZOOM_SCALE = 1.5;
    private static final double ZOOM_Y = 0.25;

    private Scene scene;
    GameLabel titleLabel;

    public TitlePane(Scene scene, String title){
        this.scene = scene;
        this.titleLabel = new GameLabel(title);

        this.getChildren().add(titleLabel);

        this.setAlignment(Pos.TOP_CENTER);
        /*
        //permet d'identifier les elements et leur taille
        titleLabel.setStyle("-fx-border-color: green");
        setStyle("-fx-border-color: red");
         */
    }

    public void zoomIn(EventHandler<ActionEvent> finishedHandler){
        ScaleTransition st = new ScaleTransition(ANIMATION_DURATION);
        st.setToX(ZOOM_SCALE);
        st.setToY(ZOOM_SCALE);

        TranslateTransition tt = new TranslateTransition(ANIMATION_DURATION);
        tt.setToY(scene.getHeight() * ZOOM_Y);

        ParallelTransition pt = new ParallelTransition(tt, st);
        pt.setNode(titleLabel);
        pt.setCycleCount(1); //nb repetition de l'effet
        pt.setOnFinished(finishedHandler);
        pt.play();
    }

    public void zoomOut(EventHandler<ActionEvent> finishedHandler){
        ScaleTransition st = new ScaleTransition(ANIMATION_DURATION);
        st.setToX(1.0 / ZOOM_SCALE);
        st.setToY(1.0 / ZOOM_SCALE);

        TranslateTransition tt = new TranslateTransition(ANIMATION_DURATION);
        tt.setToY(0);

        ParallelTransition pt = new ParallelTransition(tt, st);
        pt.setNode(titleLabel);
        pt.setCycleCount(1); //nb repetition de l'effet
        pt.setOnFinished(finishedHandler);
        pt.play();
    }
}
