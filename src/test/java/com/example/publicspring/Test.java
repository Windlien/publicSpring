import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 3, 1, 5);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println("1："+list);

        list = Arrays.asList(2, 3, 1, 5);
        list.sort(((i1,i2)-> i1 - i2));
        System.out.println("2："+list);

        list = Arrays.asList(2, 3, 1, 5);
        list.sort(Comparator.comparingInt(i -> i));
        System.out.println("2："+list);
        list.forEach(i -> System.out.print("元素："+i));
    //实例3：把Lambda赋值给变量
        Runnable task = ()->{
            System.out.println("\nhello lambda!");
        };
        new Thread(task).start();

    }
     public  void main1(String[] args) {
        String s1 = "111111111111111111111111111";
        int i1 = 1000000000;
        int i = 1;
        List<String> list1 = new ArrayList<>();
        while (i < i1) {
            i++;
            list1.add(s1);
            System.out.println("list1个数" + list1.size());
        }
    }
}
