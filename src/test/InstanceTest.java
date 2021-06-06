package test;

/**
 * @Author: LLXX
 * @Date: 2021/3/11 21:04
 * @Description:
 */
// 单例设计模式
public class InstanceTest {
    private static InstanceTest instanceTest = null;

    private InstanceTest() {
    }

    public static  InstanceTest getInstance(){
        if(instanceTest == null){
            synchronized (InstanceTest.class){
                if(instanceTest == null){
                    instanceTest = new InstanceTest();
                }
            }
        }
       return instanceTest;
    }
}

class TestSingInstance{
    public static void main(String[] args) {
            for (int i = 1; i < 100; i++) {
                new Thread(() ->{
                    InstanceTest instance = InstanceTest.getInstance();
                    System.out.println("线程"+Thread.currentThread().getName()+"  instance = " + instance);
                },""+i).start();
            }
    }
}