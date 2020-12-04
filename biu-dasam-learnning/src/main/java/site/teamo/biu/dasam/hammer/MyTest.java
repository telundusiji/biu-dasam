package site.teamo.biu.dasam.hammer;

import org.junit.Test;
import site.teamo.biu.dasam.hammer.list.temp.ArrayList;
import site.teamo.biu.dasam.hammer.list.List;

/**
 * @author 爱做梦的锤子
 * @create 2020/12/3
 */
public class MyTest {


    @Test
    public void arrayListTest() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.addLast(i);
        }
        for (int i = 0; i < 15; i++) {
            System.out.println(list.removeLast());
        }
        System.out.println();
    }
}
