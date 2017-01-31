package com.example.jaysingh.mvpdatabasedemo.Presenter;

import android.content.Context;

/**
 * Created by jaysingh on 15/12/16.
 */

public interface LoginPresenter {

    void validateCredentials(Context context, String username, String password);

}
