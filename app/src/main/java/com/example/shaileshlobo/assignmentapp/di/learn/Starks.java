package com.example.shaileshlobo.assignmentapp.di.learn;

import javax.inject.Inject;

/**
 * Created by spl on 24/6/18.
 */

public class Starks implements House {

    @Inject
    public Starks(){
    }

    @Override
    public void prepareForWar() {
        //do something
        System.out.println(this.getClass().getSimpleName()+" prepared for war");
    }

    @Override
    public void reportForWar() {
        //do something
        System.out.println(this.getClass().getSimpleName()+" reporting..");
    }

}
