package ba.unsa.etf.rpr.controllers.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

/**
 * Custom component for rendering table cell with one button (VIEW)
 * @param <T> - Bean class represented in the table cells
 */
public class ButtonTableCell<T> extends TableCell<T, T> {

    private Button view;

    /**
     * Default constructor
     * @param buttonOne - event handler for action on first button (VIEW)
     */
    public ButtonTableCell(EventHandler<ActionEvent> buttonOne){
        view = new Button("VIEW");
        view.setOnAction(buttonOne);
    }
    @Override
    protected void updateItem(T o, boolean b) {
        super.updateItem(o, b);
        if (o != null) {
            HBox box = new HBox();
            box.setAlignment(Pos.CENTER);
            view.setUserData(o);
            box.getChildren().add(view);
            setGraphic(box);
        }
    }
}
