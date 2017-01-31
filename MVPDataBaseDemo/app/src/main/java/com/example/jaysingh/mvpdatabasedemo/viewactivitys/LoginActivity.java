package com.example.jaysingh.mvpdatabasedemo.viewactivitys;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jaysingh.mvpdatabasedemo.Presenter.LoginPresenter;
import com.example.jaysingh.mvpdatabasedemo.Presenter.LoginPresenterImpl;
import com.example.jaysingh.mvpdatabasedemo.R;
import com.example.jaysingh.mvpdatabasedemo.model.LoginView;
import com.example.jaysingh.mvpdatabasedemo.utility.ProjectUtilityClass;

public class LoginActivity extends AppCompatActivity implements LoginView{


    LoginPresenter mLoginPresenter;
    ProgressDialog mProgressDialog;

    Button mBtnLogin;
    EditText mUserName,mUserPasswrd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserName = (EditText)findViewById(R.id.edtUserName);
        mUserPasswrd = (EditText)findViewById(R.id.edtPassword);

        mBtnLogin = (Button)findViewById(R.id.btnLogin);

        mLoginPresenter = new LoginPresenterImpl(this);


        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mLoginPresenter.validateCredentials(LoginActivity.this,
                        mUserName.getText().toString(),
                        mUserPasswrd.getText().toString());

            }
        });


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
    public void onLoginSuccess() {
        ProjectUtilityClass.showToast(this, "Login Done");
        ProjectUtilityClass.clearFields(mUserName, mUserPasswrd);
        finish();
    }


    @Override
    public void onLoginFailure(String errorMsg) {
        ProjectUtilityClass.showToast(this, errorMsg);
    }
}
