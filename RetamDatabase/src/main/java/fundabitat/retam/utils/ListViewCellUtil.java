/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.utils;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 *
 * @author marcos
 */
public class ListViewCellUtil {

    private ListViewCellUtil() {
    }

    // Make multiple selection using click instead of crtl + click
    // As seen at: http://stackoverflow.com/questions/23622703/deselect-an-item-on-an-javafx-listview-on-click
    public static <E> void setMultipleSelect(final ListCell<E> cell, final ListView<E> listView) {

        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        cell.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<Event>() {

            MultipleSelectionModel<E> selectionModel = listView.getSelectionModel();

            @Override
            public void handle(Event event) {
                listView.requestFocus();
                if (!cell.isEmpty()) {
                    int index = cell.getIndex();

                    // There's a bug with contains, when index is 0 contains
                    // returns false...
                    if (selectionModel.getSelectedIndices().indexOf(index) >= 0) {
                        selectionModel.clearSelection(index);
                    } else {
                        selectionModel.select(index);
                    }
                    event.consume();
                }
            }
        });
    }

    /**
     * Sets up ListView cells
     *
     * @param <E> the type of the ListView
     * @param listView the ListView itself
     * @param displayFunc The function used to display the object
     * @param multipleSelect true if the list needs multiple selection
     */
    public static <E> void setupCell(final ListView<E> listView,
            final Function<E, String> displayFunc, final boolean multipleSelect) {

        listView.setCellFactory(new Callback<ListView<E>, ListCell<E>>() {

            @Override
            public ListCell<E> call(ListView<E> p) {

                // Sets the text to be displayed
                final ListCell<E> cell = new ListCell<E>() {

                    @Override
                    protected void updateItem(E c, boolean b) {

                        super.updateItem(c, b);

                        if (c != null) {
                            setText(displayFunc.apply(c));
                        }
                    }
                };

                if (multipleSelect) {
                    ListViewCellUtil.setMultipleSelect(cell, listView);
                }

                return cell;
            }
        });
    }

    /**
     * Polymorphism to call setupCell with multiselect as false
     */
    public static <E> void setupCell(final ListView<E> listView,
            final Function<E, String> displayFunc) {
        setupCell(listView, displayFunc, false);
    }

}
