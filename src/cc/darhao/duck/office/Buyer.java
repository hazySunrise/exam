package cc.darhao.duck.office;

import cc.darhao.duck.counter.InventoryCounter;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Buyer implements Observer {

    @Override
    public void update(Observable o, Object arg) {
            //获取库存数
            if((Integer)arg<5){
                //采购鸭子
                InventoryCounter.getInstance().addInventory(buyDuck());
            }
    }

    /**
     * 采购鸭子
     */
    private int buyDuck(){
        Random random = new Random();
        int  i = random.nextInt(10);
        if (i<8) {
            System.out.println("采购员：今天去晚了没有买到鸭子");
            return 0;
        }
        int number = random.nextInt(6)+5;
        System.out.println("采购员：我买来了"+number+"只鸭子");
         return number;
    }

}

