package cc.darhao.duck.chef;

import cc.darhao.duck.entity.Duck;

/**
 * 印度厨师
 */
public class IndiaChef extends Chef {
    private int level = 30;
    private int picklingTime = 60;
    private int price = 30;

    @Override
    protected void doWithSecretRecipe(Duck duck) {
        System.out.println("开始烹饪印度料理");
        duck.wash();
        duck.pickling(picklingTime);
        duck.roast(25);
        //每完成一只烤鸭增加熟练度5
        addLevel(5);
    }

    @Override
    protected int saleBySecretRecipe() {
        return price;
    }

    /**
     * 根据烹饪烤鸭数量增加而增加熟练度
     */
    private int addLevel(int level){
        reducePicklingTime(level);
        addSalePrice();
       return this.level +=level;
    }

    /**
     * 腌制鸭子的时间随着熟练度的增加而减少
     * 腌制时间不少于10分钟
     */
    private int reducePicklingTime(int time){
        if (picklingTime>10){
            picklingTime -= time;
        }
        return picklingTime;
    }

    /**
     *烤鸭价格与熟练度成正比
     * 价格不能高于70
     */
    private int addSalePrice(){
        if (this.price<70){
            price += 2;
        }
        return price;
    }
}

