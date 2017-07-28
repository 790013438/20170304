import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by 79001 on 2017/7/28.
 */
public class SuperMarket implements Runnable {
    /**
     * 【2】SuperMarket类:
     有一个可变数组作为成员变量: List<Food> foodArray;
     1）类方法：
     a）单例方法：创建SuperMarket类的单例对象
     public static SuperMarket singleSuperMarket();(3分)
 
     2）成员方法: 每个方法的实现效果如图所示
     a）添加食品	void addFoodWith(Food food ) (2分)
     b）根据食品名称查询食品  void searchFoodWithFoodName(String foodName); (3分)
 
 
     c）显示所有食品信息 void showAllFood()(2分)
     */
    
    List<Food> foodArray;
    private volatile boolean volatileBoolean;
    private Scanner scanner;
    
    private SuperMarket () {
        scanner = new Scanner(System.in);
        foodArray = new ArrayList();
    }
    
    public static SuperMarket singleSuperMarket () {
        return new SuperMarket();
    }
    
    public void addFoodWidth (Food food) {
        if (!foodArray.contains(food)) {
            foodArray.add(food);
        }
    }
    
    public void searchFoodWithFoodName(String foodName) {
        if (foodName == null)
            return;
        Iterator<Food> iterator = foodArray.iterator();
        Food food;
        boolean find = false;
        while (iterator.hasNext()) {
            food = iterator.next();
            if (foodName.equalsIgnoreCase(food.getFoodName())) {
                System.out.println(food);
                find = true;
            }
        }
        if (!find)
            System.out.println(foodName + "没有");
    }
    
    public void showAllFood() {
        Iterator<Food> iterator = foodArray.iterator();
        Food food;
        while (iterator.hasNext()) {
            food = iterator.next();
            System.out.println(food);
        }
    }
    
    public void run () {
        volatileBoolean = true;
        while (volatileBoolean) {
            print();
            int integer = -1;
            try {
                integer = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                System.out.println("请输入数字 :)");
            }
            switch (integer) {
                case 0 :
                    volatileBoolean = false;
                    break;
                case 1 :
                    String name;
                    System.out.println("----------------------------");
                    System.out.println("请输入商品名称：");
                    name = scanner.next();
                    String type;
                    System.out.println("请输入商品类型：");
                    type = scanner.next();
                    double price;
                    System.out.println("请输入商品价格：");
                    price = scanner.nextDouble();
                    Food food = new Food(name, type, price);
                    addFoodWidth (food);
                    System.out.println("\t" + name + "成功上货");
                    System.out.println(food);
                    break;
                case 2 :
                    System.out.println("请输入要查询的商品名称");
                    String string = scanner.next();
                    searchFoodWithFoodName(string);
                    break;
                case 3 :
                    showAllFood();
                    break;
                default:
            }
        }
    }
    
    public void print () {
        System.out.println("********************************");
        System.out.println("1 上货");
        System.out.println("2 查货");
        System.out.println("3 查看库存");
        System.out.println("0 退出");
        System.out.println("********************************");
        System.out.println("请选择：");
    }
}
