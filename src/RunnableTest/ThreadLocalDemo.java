package RunnableTest;

/**
 * @Auther: LLXX
 * @Date: 2020/11/23 19:47
 * @Description:
 */
public class ThreadLocalDemo {

    //线程本地存储变量
    private static final ThreadLocal<Integer> THREAD_LOCAL_NUM
            = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) {
        for (int i = 1; i <=3; i++) {//启动三个线程
            new Thread(()->{add10ByThreadLocal();},String.valueOf(i)).start();
        }
    }

    /**
     * 线程本地存储变量加 5
     */
    private static void add10ByThreadLocal() {
        for (int i = 0; i <5; i++) {
            Integer n = THREAD_LOCAL_NUM.get();
            n += 1;
            THREAD_LOCAL_NUM.set(n);
            System.out.println(Thread.currentThread().getName() + " : ThreadLocal num=" + n);
//            THREAD_LOCAL_NUM.remove();
        }
    }
}
