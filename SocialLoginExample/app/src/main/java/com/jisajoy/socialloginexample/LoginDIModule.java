package com.jisajoy.socialloginexample;

import android.content.Context;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LoginDIModule {

    @Binds
    abstract LoginMvpPresenter getLoginMvpPresenterObject(LoginMvpModel loginMvpModel);

}
