package RunnableTest;

/**
 * @Auther: LLXX
 * @Date: 2020/11/8 15:45
 * @Description:
 */
class people
{
    private String name;
    people(){

    }

    people(String name){
           this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "people{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Method
{
   public void method1(int a){
       a = 10;
   }

    public void method1(String s){
        s = "aaa";
        System.out.println(s);
    }

    public void method1(people people){
        people.setName("Rule");
    }
}

public class TransFerValuesDemo {
    public static void main(String[] args) {
        Method method = new Method();
        int a = 20 ;
        method.method1(a);
        System.out.println(a);

        people people = new people("Tom");
        method.method1(people);
        System.out.println(people);

        String s = "aa";
        method.method1(s);
        System.out.println(s);
    }


}
