package com.example.shaileshlobo.assignmentapp.di.learn;

import dagger.Component;

/**
 * Created by spl on 24/6/18.
 */

@Component(modules = BraavosModule.class)
interface BattleComponent {
    War getWar();


    //adding more methods
    Starks getStarks();
    Boltons getBoltons();

    Cash getCash();
    Soldiers getSoldiers();

}
