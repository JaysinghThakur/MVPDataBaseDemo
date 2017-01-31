package com.example.jaysingh.mvpdatabasedemo.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jaysingh.mvpdatabasedemo.database.MyOpenHelper;

/**
 * Created by jaysingh on 15/12/16.
 */

public class ProjectUtilityClass {


    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void clearFields(EditText... editTexts) {
        for (EditText editText : editTexts) {
            editText.setText("");
        }
    }


    public static SQLiteDatabase databaseConnection(Context con) {

        SQLiteOpenHelper sqLiteOpenHelper = new MyOpenHelper(con);
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();

        return db;
    }


    public static ProgressDialog progressDialogForAll(Context con) {

        ProgressDialog mProgressDialog = new ProgressDialog(con);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(false);
        return mProgressDialog;
      }
}