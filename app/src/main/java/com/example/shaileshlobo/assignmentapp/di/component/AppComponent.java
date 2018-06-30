package com.example.shaileshlobo.assignmentapp.di.component;

import com.example.shaileshlobo.assignmentapp.di.module.AppModule;
import com.example.shaileshlobo.assignmentapp.di.module.NetworkModule;
import com.example.shaileshlobo.assignmentapp.ui.MainAcitivityViewModel;
import com.example.shaileshlobo.assignmentapp.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 *
 *
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(MainActivity activity);

//    void inject(SearchActivity activity);

    void inject(MainAcitivityViewModel activity);

//    void inject(RetrofitInterceptor interceptor);

}
