import java.util.Calendar;
import java.util.Date;

/**
 * Created by 79001 on 2017/7/28.
 */
public class Food {
    /**
     * 1】Food类 (2分)
     属性:  进货日期(foodDate)、食品名称(foodName)、食品分类(foodCategory)类型都是String、 价格(foodPrice) double类型。
     成员方法: 展示该食品所有信息:  void showFoodMessage()
     */
    public Calendar foodDate;
    public String foodName;
    public String foodCategory;
    public double foodPrice;
    
    public Food(String foodName, String foodCategory, double foodPrice) {
        this.foodDate = Calendar.getInstance();
        this.foodName = foodName;
        this.foodCategory = foodCategory;
        this.foodPrice = foodPrice;
    }
    
    public String getFoodName() {
        return foodName;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Food food = (Food) o;
        
        if (Double.compare(food.foodPrice, foodPrice) != 0) return false;
        if (foodDate != null ? !foodDate.equals(food.foodDate) : food.foodDate != null) return false;
        if (foodName != null ? !foodName.equals(food.foodName) : food.foodName != null) return false;
        return foodCategory != null ? foodCategory.equals(food.foodCategory) : food.foodCategory == null;
    }
    
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = foodDate != null ? foodDate.hashCode() : 0;
        result = 31 * result + (foodName != null ? foodName.hashCode() : 0);
        result = 31 * result + (foodCategory != null ? foodCategory.hashCode() : 0);
        temp = Double.doubleToLongBits(foodPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
    
    public void showFoodMessage() {
        System.out.println(toString());
    }
    
    @Override
    public String toString() {
        return "[" +
                "进货日期：" + foodDate.get(Calendar.YEAR) + "-" + foodDate.get(Calendar.MONTH) + "-" + foodDate.get(Calendar.DAY_OF_MONTH) +
                ", \t食品名称：'" + foodName + '\'' +
                ", \t食品类型：'" + foodCategory + '\'' +
                ", \t食品价格：" + foodPrice +
                ']';
    }
}
