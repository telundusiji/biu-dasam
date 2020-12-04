package site.teamo.biu.dasam.learning.set;

import java.util.Random;

/**
 * @author 爱做梦的锤子
 * @create 2020/11/28
 */
public class UFSetTest {
    public static void main(String[] args) {

        String a = "/i18n/source/api/v2/translation/products/%s/versions/";
        String substring = a.substring(a.indexOf("products/")+"products/".length(), a.indexOf("/versions"));


        rightCheck(new SimpleUFSet<>());

        int u = 50000000;
        int f = 1;

//        long test1 = test(new SimpleUFSet<>(), u, f);
//        System.out.println("simple UFSet:" + test1 / 1000000000.0 + "s");

//        long test2 = test(new MapTreeUFSet<>(), u, f);
//        System.out.println("map tree UFSet:" + test2 / 1000000000.0 + "s");

        long test3 = test(new ArrayTreeUFSet<>(Integer::intValue), u, f);
        System.out.println("array tree UFSet:" + test3 / 1000000000.0 + "s");
    }

    public static long test(UFSet<Integer> ufSet, int u, int f) {
        Random r = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < u; i++) {
            int p = r.nextInt(u);
            int q = r.nextInt(u);
            ufSet.union(p, q);
        }
        for (int i = 0; i < f; i++) {
            int p = r.nextInt(u);
            int q = r.nextInt(u);
            ufSet.union(p, q);
        }
        return System.nanoTime() - start;
    }

    /**
     * 正确性校验
     *
     * @param ufSet
     * @return
     */
    public static void rightCheck(UFSet<Integer> ufSet) {
        ufSet.union(1, 2);
        ufSet.union(1, 3);
        ufSet.union(1, 4);
        ufSet.union(5, 6);
        ufSet.union(6, 7);
        ufSet.union(7, 8);
        ufSet.union(8, 1);
        ufSet.union(10, 11);
        System.out.println("1<->11:{}" + ufSet.isConnected(1, 11));
        System.out.println("1<->5:{}" + ufSet.isConnected(1, 5));
        System.out.println("4<->10:{}" + ufSet.isConnected(4, 10));
        System.out.println("5<->8:{}" + ufSet.isConnected(5, 8));
    }
}
