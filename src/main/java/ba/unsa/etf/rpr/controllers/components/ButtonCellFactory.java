package ba.unsa.etf.rpr.controllers.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * Button cell factory for creation of buttons for each cell in the table
 * @param <T> The type of the elements contained within the table column.
 */
public class ButtonCellFactory<T> implements Callback<TableColumn<T, T>, TableCell<T, T>> {

    private final EventHandler<ActionEvent> buttonOne;

    /**
     * Creates a new instance of the `ButtonCellFactory` class.
     * @param buttonOne The event handler for the button (VIEW).
     */
    public ButtonCellFactory(EventHandler<ActionEvent> buttonOne){
        this.buttonOne = buttonOne;
    }

    /**
     * Returns a new instance of the `ButtonTableCell` class.
     * @param quoteObjectTableColumn The table column.
     * @return A new instance of the `ButtonTableCell` class.
     */
    @Override
    public TableCell<T, T> call(TableColumn<T, T> quoteObjectTableColumn) {
        return new ButtonTableCell<>(buttonOne);
    }
}

