import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class Animal implements Comparable<Animal>
{
    protected int age;

    public Animal(int age)
    {
        System.out.println(String.format("Init age=%s", age));
        this.age = age;
    }

    //使用年龄与另一实例比较大小
    @Override
    public int compareTo(Animal other)
    {
        System.out.println(String.format("compareTo age=%s, other age=%s", age, other.age));
        return this.age - other.age;
    }
}

class Dog extends Animal
{
    public Dog(int age)
    {
        super(age);
    }
}
public class testgenerics
{
    //第一种声明：简单，灵活性低
    public static <T extends Comparable<T>> void mySort1(List<T> list)
    {
        Collections.sort(list);
    }

    //第二种声明：复杂，灵活性高
    public static <T extends Comparable<? super T>> void mySort2(List<T> list)
    {
        Collections.sort(list);
        System.out.println(list.get(0));
    }

    public static void main(String[] args)
    {
        //主函数中将分别创建Animal和Dog两个序列，然后调用排序方法对其进行测试
        //main函数中具体的两个版本代码将在下面具体展示
        // 创建一个 Animal List
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal(25));
        animals.add(new Dog(35));

        // 创建一个 Dog List
        List<Dog> dogs = new ArrayList<Dog>();
        dogs.add(new Dog(5));
        dogs.add(new Dog(18));

        // 测试  mySort1() 方法
        mySort1(animals);
        // mySort1(dogs) 报错: 无法将类 test中的方法 mySort1应用到给定类型
        // mySort1(dogs);

        // 测试  mySort2() 方法
        mySort2(animals);
        mySort2(dogs);


    }
}

