package RunnableTest;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Auther: LLXX
 * @Date: 2020/11/7 17:09
 * @Description:
 */
public class JUC_ArrayList {
    /**
      * @param args
     *      1. ArrayList 是非线程安全,在多线程的工作环境下,会出现 ConcurrentModificationException 异常
     *      2. 使用 Vector 可以解决上面出现的异常,但对程序并发性能有一定的影响
     *      3. 也可以使用Collections 工具类下的 synchronizedList 方法使用同步List
     *      4.  JUC下的CopyOnWriteArrayList 同样可以解决 ConcurrentModificationException 问题 （推荐使用）
     *      5. Set，Map 都有对应的类处理,分别为 CopyOnWriteArraySet 以及 ConCurrentHashMap
     */
    public static void main(String[] args) {
//        List<String> list =  new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>());// new Vector(); //
        Map<String, String> map = new ConcurrentHashMap();//Collections.synchronizedMap(new HashMap<>());//new HashMap();
        Set<String> set = new CopyOnWriteArraySet<>();//Collections.synchronizedSet(new HashSet<>());//new HashSet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() ->{
//                list.add(UUID.randomUUID().toString().substring(0,8));
//                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
