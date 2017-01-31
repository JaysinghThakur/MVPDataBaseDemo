package com.example.jaysingh.mvpdatabasedemo.Presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;


import com.example.jaysingh.mvpdatabasedemo.database.MyOpenHelper;
import com.example.jaysingh.mvpdatabasedemo.model.StudentInfoView;
import com.example.jaysingh.mvpdatabasedemo.utility.ProjectUtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaysingh on 15/12/16.
 */

public class StudentInfoPresenterImpl implements StudentInfoPresenter {



    Context con;
    SQLiteDatabase db;

    StudentInfoView mStudentInfoPresenter;

    ArrayList<String> item_InFo = new ArrayList<String>() ;


    public StudentInfoPresenterImpl(StudentInfoView mStudentInfoPresenter) {
        this.mStudentInfoPresenter = mStudentInfoPresenter;
    }

    @Override
    public void studentInsert(Context context, String sid, String sname, String saddress, String sphone) {

        con = context;

        db = ProjectUtilityClass.databaseConnection(con);


        mStudentInfoPresenter.showProgress();

        ContentValues conV = new ContentValues();
        conV.put("Stu_Id", Integer.parseInt(sid));
        conV.put("Stu_Name",sname);
        conV.put("Stu_Address",saddress);
        conV.put("Stu_No", Integer.parseInt(sphone));

        long newrow = db.insert(MyOpenHelper.TABLE_NAME,null,conV);
        if(newrow >= 0)
        {
            mStudentInfoPresenter.onStudentResultSuccess("Record Inserted Successfully");
            mStudentInfoPresenter.dismissProgress();
        }
        else
        {
            mStudentInfoPresenter.onStudentResultFailure("Insertion Fail");
            mStudentInfoPresenter.dismissProgress();
        }

        db.close();

    }

    @Override
    public void studentUpdate(Context context, String sid, String sname, String saddress, String sphone) {


        con = context;

        db = ProjectUtilityClass.databaseConnection(con);


        mStudentInfoPresenter.showProgress();

        ContentValues conV = new ContentValues();
        conV.put("Stu_Id", Integer.parseInt(sid));
        conV.put("Stu_Name",sname);
        conV.put("Stu_Address",saddress);
        conV.put("Stu_No", Integer.parseInt(sphone));

        String where = "Stu_Id = "+sid;

        int newrow =db.update(MyOpenHelper.TABLE_NAME, conV, where, null);
        if(newrow == 0)
        {
            mStudentInfoPresenter.onStudentResultFailure("Data is not updated");
            mStudentInfoPresenter.dismissProgress();

        }
        else
        {
            mStudentInfoPresenter.onStudentResultSuccess("Data is updated");
            mStudentInfoPresenter.dismissProgress();
        }

        db.close();








    }

    @Override
    public void studentDelete(Context context, String sid) {

        con = context;

        db = ProjectUtilityClass.databaseConnection(con);


        mStudentInfoPresenter.showProgress();

        ContentValues conV = new ContentValues();
        conV.put("Stu_Id", Integer.parseInt(sid));

        String where = "Stu_Id = "+sid;

        int newrow = db.delete(MyOpenHelper.TABLE_NAME, where, null);
        if(newrow == 0)
        {
            mStudentInfoPresenter.onStudentResultFailure("Data is not Deleted");
            mStudentInfoPresenter.dismissProgress();

        }
        else
        {
            mStudentInfoPresenter.onStudentResultSuccess("Data is Deleted");
            mStudentInfoPresenter.dismissProgress();
        }

        db.close();





    }

    @Override
    public List<String> studentSelect(Context context) {

        con = context;

        db = ProjectUtilityClass.databaseConnection(con);

        mStudentInfoPresenter.showProgress();

        item_InFo.clear();
        Cursor c = db.rawQuery("SELECT * FROM Student",null);

        if(c!= null)
        {
            if(c.moveToFirst())
            {
                do
                {
                    String sid = c.getString(c.getColumnIndex("Stu_Id"));
                    String sname = c.getString(c.getColumnIndex("Stu_Name"));
                    String saddress =c.getString(c.getColumnIndex("Stu_Address"));
                    String sno= c.getString(c.getColumnIndex("Stu_No"));
                    item_InFo.add(sid+"--"+sname+"--"+saddress+"--"+sno);
                }
                while(c.moveToNext());


                mStudentInfoPresenter.onStudentResultSuccess("Getting all the record");
            }
        }
        else
        {
            mStudentInfoPresenter.onStudentResultFailure("Table has no contain");
        }


        mStudentInfoPresenter.dismissProgress();

        c.close();


        return item_InFo ;

    }
}
