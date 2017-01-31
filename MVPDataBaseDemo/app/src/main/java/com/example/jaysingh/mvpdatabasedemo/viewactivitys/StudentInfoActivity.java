package com.example.jaysingh.mvpdatabasedemo.viewactivitys;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jaysingh.mvpdatabasedemo.Presenter.StudentInfoPresenter;
import com.example.jaysingh.mvpdatabasedemo.Presenter.StudentInfoPresenterImpl;
import com.example.jaysingh.mvpdatabasedemo.R;
import com.example.jaysingh.mvpdatabasedemo.model.StudentInfoView;
import com.example.jaysingh.mvpdatabasedemo.utility.ProjectUtilityClass;

import java.util.List;

public class StudentInfoActivity extends AppCompatActivity implements StudentInfoView {


    EditText edtStduentId,edtStduentName,edtStduentAddress,edtStduentPhone;
    ListView litStudentAllRecord;
    ProgressDialog mProgressDialog;

    StudentInfoPresenter mStudentInfoPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        edtStduentId =(EditText)findViewById(R.id.edt_Stu_Id);
        edtStduentName=(EditText)findViewById(R.id.edt_Stu_Name);
        edtStduentAddress =(EditText)findViewById(R.id.edt_Stu_Address);
        edtStduentPhone=(EditText)findViewById(R.id.edt_Stu_No);

        litStudentAllRecord = (ListView) findViewById(R.id.list1);

        mStudentInfoPresenter = new StudentInfoPresenterImpl(this);


    }


    public void clickinsert(View v)
    {

        mStudentInfoPresenter.studentInsert(
                this,edtStduentId.getText().toString(),
                edtStduentName.getText().toString(),
                edtStduentAddress.getText().toString(),
                edtStduentPhone.getText().toString()
                );

    }


    public void clickdelete(View v)
    {
        mStudentInfoPresenter.studentDelete(
                this,edtStduentId.getText().toString()
        );
    }


    public void clickdisplay(View v)
    {
        List<String> names = mStudentInfoPresenter.studentSelect(this);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        litStudentAllRecord.setAdapter(aa);

    }
    public void clickupdate(View v)
    {
        mStudentInfoPresenter.studentUpdate(
                this,edtStduentId.getText().toString(),
                edtStduentName.getText().toString(),
                edtStduentAddress.getText().toString(),
                edtStduentPhone.getText().toString()
        );

    }






    @Override
    public void showProgress() {

        mProgressDialog = ProjectUtilityClass.progressDialogForAll(this);
        mProgressDialog.show();

    }

    @Override
    public void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onStudentResultSuccess(String successMsg) {
        ProjectUtilityClass.showToast(this, successMsg);
        ProjectUtilityClass.clearFields(edtStduentId, edtStduentName,edtStduentAddress,edtStduentPhone);

    }

    @Override
    public void onStudentResultFailure(String errorMsg) {
        ProjectUtilityClass.showToast(this, errorMsg);
    }
}
