package cc.darhao.duck.office;

import cc.darhao.duck.chef.Chef;
import cc.darhao.duck.chef.ChineseChef;
import cc.darhao.duck.chef.FrenchChef;
import cc.darhao.duck.chef.JapaneseChef;
import cc.darhao.duck.main.Main;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Hr implements Observer {
    private int newLevel = 1000;

    @Override
    public void update(Observable o, Object arg) {
        if ((Integer) arg > newLevel){
            //招聘员工
            List<Chef> chefTeam = Main.getChefTeam();
            Random random = new Random();
            Chef chef = getChef(random.nextInt(3));
            chefTeam.add(chef);
            System.out.println("人事：欢迎新来的厨师！现在共有"+chefTeam.size()+"位厨师啦");
            newLevel += 1000;
        }
    }

    private Chef getChef(int random){
        if (random == 0){
            return new ChineseChef();
        }else if (random == 1){
            return new JapaneseChef();
        }else {
            return new FrenchChef();
        }
    }
}
