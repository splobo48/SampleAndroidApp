package com.example.shaileshlobo.assignmentapp

import android.app.Application
import android.os.StrictMode
import com.example.shaileshlobo.assignmentapp.di.component.AppComponent
import com.example.shaileshlobo.assignmentapp.di.component.DaggerAppComponent
import com.example.shaileshlobo.assignmentapp.di.module.AppModule
import com.example.shaileshlobo.assignmentapp.di.module.NetworkModule


/**
 * Created by spl on 20/6/18.
 */
class CustomApplicaton: Application(){

    lateinit var component : AppComponent

            override fun onCreate() {
        if (BuildConfig.DEBUG) {
            setStrictMode()
        }
        super.onCreate()


        super.onCreate()
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .build()

    }

    private fun setStrictMode() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build())

        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build())
    }


}