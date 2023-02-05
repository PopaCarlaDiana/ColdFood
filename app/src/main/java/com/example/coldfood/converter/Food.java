//package com.example.fridgeiside;
//
//import java.util.Comparator;
//
//public class Food {
//    private String name;
//    private int fresh;
//    private int num;
//
//    Food(String name, int fresh, int num)
//    {
//        this.name=name;
//        this.fresh=fresh;
//        this.num=num;
//    }
//
//
//
//    //COMPARATORS-------------------------------------------------
//    public static Comparator<Food> FoodAscendingFreshness = new Comparator<Food>() {
//        @Override
//        public int compare(Food f1, Food f2) {
//            boolean ok=f1.getFresh()>f2.getFresh();
//            if(ok==true)
//                return 1;
//            ok=f1.getFresh()<f2.getFresh();
//            if(ok==true)
//                return -1;
//            return 0;
//        }
//    };
//
//
//
//
//    //SET & GET--------------------------------------------------------
//    public String getName() {
//        return name;
//    }
//
//    public int getFresh() {
//        return fresh;
//    }
//
//    public int getNum() {
//        return num;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setFresh(int fresh) {
//        this.fresh = fresh;
//    }
//
//    public void setNum(int num) {
//        this.num = num;
//    }
//}
