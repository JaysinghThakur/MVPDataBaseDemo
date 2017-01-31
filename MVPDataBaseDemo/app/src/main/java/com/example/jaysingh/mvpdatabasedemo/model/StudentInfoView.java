package com.example.jaysingh.mvpdatabasedemo.model;

/**
 * Created by jaysingh on 15/12/16.
 */

public interface StudentInfoView {

    void showProgress();

    void dismissProgress();

    void onStudentResultSuccess(String successMsg);

    void onStudentResultFailure(String errorMsg);


}
