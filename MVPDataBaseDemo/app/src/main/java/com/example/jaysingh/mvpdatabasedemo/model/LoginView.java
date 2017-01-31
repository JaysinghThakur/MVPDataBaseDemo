package com.example.jaysingh.mvpdatabasedemo.model;

/**
 * Created by Rohan on 19/5/16.
 */
public interface LoginView {

    void showProgress();

    void dismissProgress();

    void onLoginSuccess();

    void onLoginFailure(String errorMsg);
}
