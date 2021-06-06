package RunnableTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Auther: LLXX
 * @Date: 2020/11/8 15:44
 * @Description:
 */
class TestReadWriteLock
{
    Map<String,String> map = new HashMap();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    public void put(String K,String V){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t "+"开始写"+K);
            try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace();}
            map.put(K,V);
            System.out.println(Thread.currentThread().getName()+"\t "+"写完成");
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }

    }

    public void get(String K){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t "+"开始读");
            try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace();}
            map.get(K);
            System.out.println(Thread.currentThread().getName()+"\t "+"读完成"+K);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

}
/**
 *  读写锁
 *  写：锁死，只有当前线程释放锁使，其他线程才能进行操作
 *  读：所有线程可以一起来读，不用等待
 *
 *
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        TestReadWriteLock writeLock = new TestReadWriteLock();
        for (int i = 1; i <=10 ; i++) {
            int a = i;
            new Thread(()-> {
                writeLock.put(a+"",a+"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <=10 ; i++) {
            int a = i;
            new Thread(()-> {
                writeLock.get(a+"");
            },String.valueOf(i)).start();
        }
    }
}
