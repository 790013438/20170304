package javagame.threads;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 79001 on 2017/7/13.
 */
public class Day12_2 {
    /**
     * 集合练习2
     * 汽车管理系统
     **************************
     * 设计类卡车：车牌号，归属地、吨位、价格；
     * 设计类轿车：车牌号，归属地，型号；
     * 现要求设计汽车管理类；
     * 1、新车入库；
     * 2、根据车牌号删除指定车辆；
     * 3、查看所有车辆信息；
     */
    class Manage {
        private List<Car> carArrayList = new ArrayList();
        private List<Truck> truckArrayList = new ArrayList();
    
        public List<Car> getCarArrayList() {
            return carArrayList;
        }
    
        public void setCarArrayList(List<Car> carArrayList) {
            this.carArrayList = carArrayList;
        }
    
        public List<Truck> getTruckArrayList() {
            return truckArrayList;
        }
    
        public void setTruckArrayList(List<Truck> truckArrayList) {
            this.truckArrayList = truckArrayList;
        }
        
        public void remove (Object o) {
            
            carArrayList.remove(o);
            truckArrayList.remove(o);
        }
    
        @Override
        public String toString() {
            return "Manage{" +
                    "carArrayList=" + carArrayList +
                    ", truckArrayList=" + truckArrayList +
                    '}';
        }
    }
    
    class Car {
        private String plate;
        private String attribution;
        private String model;
    
        public Car() {
        }
    
        public Car(String plate, String attribution, String model) {
            this.plate = plate;
            this.attribution = attribution;
            this.model = model;
        }
    
        public String getPlate() {
            return plate;
        }
    
        public void setPlate(String plate) {
            this.plate = plate;
        }
    
        public String getAttribution() {
            return attribution;
        }
    
        public void setAttribution(String attribution) {
            this.attribution = attribution;
        }
    
        public String getModel() {
            return model;
        }
    
        public void setModel(String model) {
            this.model = model;
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
        
            Car car = (Car) o;
        
            if (plate != null ? !plate.equals(car.plate) : car.plate != null) return false;
            if (attribution != null ? !attribution.equals(car.attribution) : car.attribution != null) return false;
            return model != null ? model.equals(car.model) : car.model == null;
        }
    
        @Override
        public int hashCode() {
            int result = plate != null ? plate.hashCode() : 0;
            result = 31 * result + (attribution != null ? attribution.hashCode() : 0);
            result = 31 * result + (model != null ? model.hashCode() : 0);
            return result;
        }
    }
    
    class Truck {
        private String plate;
        private String attribution;
        private double tonnage;
        private double price;
    
        public Truck() {
        }
    
        public Truck(String plate, String attribution, double tonnage, double price) {
            this.plate = plate;
            this.attribution = attribution;
            this.tonnage = tonnage;
            this.price = price;
        }
    
        public String getPlate() {
            return plate;
        }
    
        public void setPlate(String plate) {
            this.plate = plate;
        }
    
        public String getAttribution() {
            return attribution;
        }
    
        public void setAttribution(String attribution) {
            this.attribution = attribution;
        }
    
        public double getTonnage() {
            return tonnage;
        }
    
        public void setTonnage(double tonnage) {
            this.tonnage = tonnage;
        }
    
        public double getPrice() {
            return price;
        }
    
        public void setPrice(double price) {
            this.price = price;
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
        
            Truck truck = (Truck) o;
        
            if (Double.compare(truck.tonnage, tonnage) != 0) return false;
            if (Double.compare(truck.price, price) != 0) return false;
            if (plate != null ? !plate.equals(truck.plate) : truck.plate != null) return false;
            return attribution != null ? attribution.equals(truck.attribution) : truck.attribution == null;
        }
    
        @Override
        public int hashCode() {
            int result;
            long temp;
            result = plate != null ? plate.hashCode() : 0;
            result = 31 * result + (attribution != null ? attribution.hashCode() : 0);
            temp = Double.doubleToLongBits(tonnage);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(price);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }
    }
}
