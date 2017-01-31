package com.example.jaysingh.mvpdatabasedemo.Presenter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.jaysingh.mvpdatabasedemo.model.LoginView;
import com.example.jaysingh.mvpdatabasedemo.utility.ProjectUtilityClass;
import com.example.jaysingh.mvpdatabasedemo.viewactivitys.StudentInfoActivity;

/**
 * Created by jaysingh on 15/12/16.
 */

public class LoginPresenterImpl implements LoginPresenter {


    Context con;
    SQLiteDatabase db;
    /**
     * Login View to talk back to activity
     */
    private LoginView mLoginView;

    /**
     * Default constructor
     *
     * @param loginView - Login view
     */
    public LoginPresenterImpl(LoginView loginView) {
        this.mLoginView = loginView;
    }




    @Override
    public void validateCredentials(Context context,String username, String password) {

        con = context;

        db = ProjectUtilityClass.databaseConnection(con);


        mLoginView.showProgress();

        Boolean result =false;
        Cursor c = db.rawQuery("SELECT * FROM LoginStudent",null);

        if(c!= null)
        {
            if(c.moveToFirst())
            {
                do
                {
                    String uid = c.getString(c.getColumnIndex("UserName"));
                    String upass = c.getString(c.getColumnIndex("PassWord"));

                    if(uid.equals(username) && upass.equals(password))
                    {
                        System.out.println("In condition");
                        result = true;
                        break;
                    }
                }
                while(c.moveToNext());
            }
        }
        else
        {
            result=false;
        }
        c.close();
        db.close();

        if(result)
        {
            mLoginView.onLoginSuccess();
            Intent loginIntent = new Intent(con,StudentInfoActivity.class);
            con.startActivity(loginIntent);
            mLoginView.dismissProgress();
        }
        else
        {
            mLoginView.dismissProgress();
            mLoginView.onLoginFailure("Login Fail");
        }



    }


}
