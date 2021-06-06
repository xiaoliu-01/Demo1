package RunnableTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: LLXX
 * @Date: 2020/11/9 13:52
 * @Description:
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
class User
{
    private Integer id ;
    private String userName;
    private Integer age;
}

public class StreamDemo {
    public static void main(String[] args) {
        // 四大函数式接口
        // 1、函数式接口
        /*Function<String,String> function = new Function() {
            public String apply(String o) {
                return null;
            }
        };*/
        /*Function<String,Integer> function = s ->{
            System.out.println("函数式接口,有参数有返回"+s);
            return 1024;
        };
        Integer hello = function.apply("Hello");
        System.out.println("返回值为"+hello);*/

        // 2、 断定行接口（也称Boolean形接口）
        /*Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return false;
            }
        };*/

        /*Predicate<Integer> predicate = s ->{
            System.out.println("断定行接口,传入一个参数返回为一个Boolean形的结果"+s);
            return s > 0;
        };

        boolean test = predicate.test(12);
        System.out.println("返回值为"+test);*/

        // 3、 消费形接口
       /* Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("消费形接口，有参数，无返回"+s);
            }
        };*/

       /* Consumer<Integer> consumer = s -> {
            System.out.println("参数为"+s);
        };

        consumer.accept(11);*/

       // 4、供给型接口
        /*Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "供给型接口，有返回无参数";
            }
        };*/

        /*Supplier<String> supplier = () ->{
            return "供给型接口，有返回无参数";
        };

        String s = supplier.get();
        System.out.println(s);*/

        User user1 = new User(1,"A",12);
        User user2 = new User(2,"B",14);
        User user3 = new User(3,"C",16);
        User user4 = new User(4,"D",18);
        User user5 = new User(5,"E",20);

        List<User> users = Arrays.asList(user1,user2,user3,user4,user5);

        // 使用流式编程，得出Id为奇数，年龄大于14的用户，且将他们的名字小写，倒叙排列，且只输出名字
        users.stream().filter(u ->{
            return u.getId() %2 == 1;
        }).filter(u ->{
            return u.getAge() > 14;
        }).map(u ->{
           return u.getUserName().toLowerCase();
        }).sorted((a,b) ->{
            return b.compareTo(a);
        }).forEach(System.out::println);


    }
}
