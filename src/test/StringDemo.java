package test;

/**
 * @Author: LLXX
 * @Date: 2021/6/3 19:57
 * @Description:
 */
public class StringDemo {
    public static void main(String[] args) {
        String a = new String("abc");
        String b = new String("abc");
        String c = a.intern();
        String d = b.intern();
        System.out.println(a == b);
        System.out.println(c == d);

        // =====================================
        Dog dog = new Dog("A");
        System.out.println(dog.getObjectAddress()); // test.Dog@7c
        func(dog);
        System.out.println(dog.getObjectAddress()); // test.Dog@7d
        System.out.println(dog.getName());          // B
    }

    private  static void  func(Dog dog){dog.setName("BC");}

}
