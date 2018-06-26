package com.example.shaileshlobo.assignmentapp.di.learn;

import dagger.Module;
import dagger.Provides;

/**
 * Created by spl on 24/6/18.
 */
@Module
public class BraavosModule {

    private Cash cash;
    private Soldiers soldiers;

    BraavosModule(Cash cash , Soldiers soldiers){
        this.cash = cash;
        this.soldiers = soldiers;
    }

    @Provides
    Cash providesCash(){
        return cash;
    }

    @Provides
    Soldiers provideSoldiers(){
        return soldiers;
    }
}
