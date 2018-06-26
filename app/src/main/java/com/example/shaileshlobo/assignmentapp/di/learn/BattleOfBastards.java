package com.example.shaileshlobo.assignmentapp.di.learn;

import com.example.shaileshlobo.assignmentapp.di.component.DaggerAppComponent;

import dagger.Component;
import dagger.android.DaggerBroadcastReceiver;

/**
 * Created by spl on 24/6/18.
 */

public class BattleOfBastards {

    public static void main(String[] args){
//        Mannual DI
//        Starks starks = new Starks();
//        Boltons boltons = new Boltons();
//        War war = new War(starks,boltons);
//        war.prepare();
//        war.report();

//      Using Dagger 2

        Cash cash = new Cash();
        Soldiers soldiers = new Soldiers();

        BattleComponent component = DaggerBattleComponent
                .builder().braavosModule(new BraavosModule(cash, soldiers)).build();

        War war = component.getWar();
        war.prepare();
        war.report();

    }


}
/*
@Component
interface BattleComponent2 {
    War getWar();
}*/
