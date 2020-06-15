import site.teamo.biu.dasam.learning.array.Array;

import java.util.ArrayList;

public class ArrayTest {

    public static void main(String[] args) {
        Array<Integer> array = new Array<>(2);
        array.add(0);
        array.removeLast();
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(3);
        array.findAll(3);
        array.removeAll(3);
        array.removeLast();
        array.removeLast();
        array.removeLast();
        array.removeLast();
        System.out.println(array);
    }
}
