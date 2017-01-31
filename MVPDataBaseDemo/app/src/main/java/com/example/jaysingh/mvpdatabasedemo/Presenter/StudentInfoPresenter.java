package com.example.jaysingh.mvpdatabasedemo.Presenter;

import android.content.Context;

import java.util.List;

/**
 * Created by jaysingh on 15/12/16.
 */

public interface StudentInfoPresenter {

    void studentInsert(Context context, String sid, String sname,String saddress,String sphone);
    void studentUpdate(Context context, String sid, String sname,String saddress,String sphone);
    void studentDelete(Context context, String sid);
    List<String> studentSelect(Context context);




}
