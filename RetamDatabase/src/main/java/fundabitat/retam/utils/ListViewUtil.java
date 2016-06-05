/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.utils;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;

/**
 *
 * @author marcos
 */
public class ListViewUtil {

    private ListViewUtil() {
    }

    public static <E, V> List<V> mapSelected(ListView<E> listView, Function<E, V> func) {

        MultipleSelectionModel<E> selectionModel = listView.getSelectionModel();
        List<E> selectedDescs = selectionModel.getSelectedItems();
        List<V> ids = new ArrayList();

        for (E e : selectedDescs) {
            ids.add(func.apply(e));
        }

        return ids;
    }

}
