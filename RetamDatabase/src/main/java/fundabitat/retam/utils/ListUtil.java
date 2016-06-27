/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marcos
 */
public class ListUtil {

    private ListUtil() {
    }

    public static <E, V> List<V> map(List<E> list, Function<E, V> func) {
        List<V> res = new ArrayList();

        for (E e : list) {
            res.add(func.apply(e));
        }

        return res;
    }

}
