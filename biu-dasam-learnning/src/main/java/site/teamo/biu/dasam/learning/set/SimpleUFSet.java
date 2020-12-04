package site.teamo.biu.dasam.learning.set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 爱做梦的锤子
 * @create 2020/11/28
 */
public class SimpleUFSet<E> implements UFSet<E> {
    AtomicInteger flag;
    Map<E, Integer> e2Flag;
    Map<Integer, List<E>> flag2e;

    public SimpleUFSet() {
        e2Flag = new HashMap<>();
        flag = new AtomicInteger();
        flag2e = new HashMap<>();
    }

    @Override
    public void union(E p, E q) {
        if (p == null || q == null) {
            return;
        }
        if (p.equals(q)) {
            return;
        }
        if (isConnected(p, q)) {
            return;
        }
        Integer pFlag = e2Flag.get(p);
        Integer qFlag = e2Flag.get(q);

        if (pFlag == null && qFlag == null) {
            int i = flag.getAndIncrement();
            List<E> eList = getOrCreat(i);
            e2Flag.put(p, i);
            e2Flag.put(q, i);
            eList.add(p);
            eList.add(q);
        } else if (pFlag != null && qFlag == null) {
            List<E> eList = getOrCreat(pFlag);
            e2Flag.put(q, pFlag);
            eList.add(q);
        } else if (pFlag == null && qFlag != null) {
            List<E> eList = getOrCreat(qFlag);
            e2Flag.put(p, qFlag);
            eList.add(p);
        } else {
            int pFlagT = pFlag.intValue();
            int qFlagT = qFlag.intValue();
            if (pFlagT == qFlagT) {
                return;
            }
            List<E> eList = getOrCreat(qFlagT);
            flag2e.remove(qFlagT);
            eList.forEach(e -> e2Flag.put(e, pFlagT));
            flag2e.get(pFlagT).addAll(eList);
        }
    }

    @Override
    public boolean isConnected(E p, E q) {
        Integer pFlag = e2Flag.get(p);
        if (pFlag == null) {
            return false;
        }
        Integer qFlag = e2Flag.get(q);
        if (qFlag == null) {
            return false;
        }
        return e2Flag.get(p) == e2Flag.get(q);
    }

    private List<E> getOrCreat(Integer flag) {
        if (flag == null) {
            return null;
        }
        List<E> es = flag2e.get(flag);
        if (es == null) {
            es = new ArrayList<>();
            flag2e.put(flag, es);
        }
        return es;
    }
}
