package RunnableTest;

/**
 * @Author: LLXX
 * @Date: 2021/2/25 21:07
 * @Description:
 */
public class TestInstance {
    public static void main(String[] args) {
        Bank instance = Bank.getInstance();
        Bank instance1 = Bank.getInstance();
        System.out.println(instance == instance1);

        for (int i = 0; i < 10; i++) {
               new Thread(() ->{
                   Bank instance2 = Bank.getInstance();
                   System.out.println("当前线程名："+Thread.currentThread().getName()+" --> instance2 = " + instance2);
               },i+"").start();
        }
    }
}
// 单例设计模式
class Bank{
    private static Bank bank = null;
    private Bank(){}
    public static Bank getInstance(){
        if(bank == null){
            synchronized (Bank.class){
                if(bank == null){
                    bank = new Bank();
                }
            }
        }
        return bank;
    }
}