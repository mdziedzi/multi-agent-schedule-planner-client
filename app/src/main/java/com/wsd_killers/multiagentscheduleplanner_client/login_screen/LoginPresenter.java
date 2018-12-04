package com.wsd_killers.multiagentscheduleplanner_client.login_screen;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View loginView;

    public LoginPresenter(LoginContract.View view) {
        loginView = view;
    }
}
