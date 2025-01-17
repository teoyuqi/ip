package duke.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Defines the {@code DialogBox} class.
 * <p>
 *     This control represents a dialog box consisting of an
 *     {@code ImageView} to represent the speaker, and
 *     a label containing text from the speaker.
 * </p>
 */
public class DialogBox extends HBox {
    /** {@code Label} object for dialog text. */
    @FXML
    private Label dialog;

    /** {@code ImageView} object for user/Duke image. */
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for {@code DialogBox}.
     * @param text Text said by speaker to be shown in dialog box.
     * @param img  Image representing the speaker.
     */
    public DialogBox(String text, Image img) {
        try {
            // Load from `FXML` file.
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Set the text and image of the dialog box.
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the image is on the left and
     * text is on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Factory method to instantiate a new dialog box for the user.
     * @param text Text said by user to be shown in dialog box.
     * @param img  Image representing the user.
     * @return     {@code DialogBox} object showing what was
     *             said by the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        // Count number of lines in string
        int numberOfLines = text.split("\r\n|\r|\n").length;
        // Set minimum height of label to be relative to number of lines.
        db.setMinHeight(Math.max(99, numberOfLines * 30));
        return db;
    }

    /**
     * Factory method to instantiate a new dialog box for Duke.
     * @param text Text said by Duke to be shown in dialog box.
     * @param img  Image representing Duke.
     * @return     {@code DialogBox} object showing what was
     *             said by Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        // Flip the dialog box since Duke's dialog boxes should have
        // the image on the left.
        db.flip();
        // Count number of lines in string
        int numberOfLines = text.split("\r\n|\r|\n").length;
        // Set minimum height of label to be relative to number of lines.
        db.setMinHeight(Math.max(99, numberOfLines * 30));
        return db;
    }
}
