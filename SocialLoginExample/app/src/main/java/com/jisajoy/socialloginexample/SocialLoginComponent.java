package com.jisajoy.socialloginexample;

import dagger.Component;

@Component(modules = {LoginDIModule.class})
public interface SocialLoginComponent {

    void getLoginObject(MainActivity mainActivity);

}
