/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcos
 */
public class ObservableListTest {

    public ObservableListTest() {
    }

    //@Test
    public void containsFirstElementTest() {

        List<String> arrayList = new ArrayList();

        arrayList.add("foo");
        arrayList.add("bar");
        arrayList.add("baz");
        arrayList.add("qux");

        ObservableList<String> list = FXCollections.observableArrayList(arrayList);

        ListView<String> listview = new ListView<>();
        listview.setItems(list);

        listview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        MultipleSelectionModel<String> selectionModel = listview.getSelectionModel();

        // Select the first element
        selectionModel.select(0);

        ObservableList<Integer> indices = selectionModel.getSelectedIndices();

        // Check that the first element is in the list of indices
        assertTrue(indices.indexOf(0) >= 0);
        assertTrue(indices.contains(0));
    }

    @Test
    public void containsSecondElementTest() {

        System.out.println(System.getProperty("java.version"));

        List<String> arrayList = new ArrayList();

        arrayList.add("foo");
        arrayList.add("bar");
        arrayList.add("baz");
        arrayList.add("qux");

        ObservableList<String> list = FXCollections.observableArrayList(arrayList);

        ListView<String> listview = new ListView<>();
        listview.setItems(list);

        listview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        MultipleSelectionModel<String> selectionModel = listview.getSelectionModel();

        // select second element
        selectionModel.select(1);

        ObservableList<Integer> indices = selectionModel.getSelectedIndices();

        // Check that the second element is in the list of indices
        assertTrue(indices.indexOf(1) >= 0);
        assertTrue(indices.contains(1));
    }
}
