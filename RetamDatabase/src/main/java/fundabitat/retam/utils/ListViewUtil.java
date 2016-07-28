/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;

/**
 *
 * @author marcos
 */
public class ListViewUtil {

    private ListViewUtil() {
    }

    /**
     * Applies a function to the selected items of a ListView. It only works
     * with multiple selection ListViews
     */
    public static <E, V> List<V> mapSelected(ListView<E> listView, Function<E, V> func) {

        MultipleSelectionModel<E> selectionModel = listView.getSelectionModel();
        List<E> selectedDescs = selectionModel.getSelectedItems();
        List<V> ids = new ArrayList();

        for (E e : selectedDescs) {
            ids.add(func.apply(e));
        }

        return ids;
    }

    public static <E> void setItems(ListView<E> listView, Collection<E> collection) {
        ObservableList<E> observableList;
        observableList = FXCollections.observableArrayList(collection);
        listView.setItems(observableList);
    }

}
