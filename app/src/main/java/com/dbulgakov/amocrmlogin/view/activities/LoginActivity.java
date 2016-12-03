package com.dbulgakov.amocrmlogin.view.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.dbulgakov.amocrmlogin.R;
import com.dbulgakov.amocrmlogin.other.di.view.DaggerViewComponent;
import com.dbulgakov.amocrmlogin.other.di.view.ViewComponent;
import com.dbulgakov.amocrmlogin.other.di.view.ViewDynamicModule;
import com.dbulgakov.amocrmlogin.presenter.LoginPresenter;
import com.dbulgakov.amocrmlogin.view.LoginView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private ProgressDialog progressDialog;
    private ViewComponent viewComponent;

    @Inject
    protected LoginPresenter loginPresenter;

    @BindView(R.id.user_login)
    EditText userEmailTextInput;

    @BindView(R.id.user_password)
    EditText userPasswordTextInput;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initProgressBar();

        if (viewComponent == null) {
            viewComponent = DaggerViewComponent.builder()
                    .viewDynamicModule(new ViewDynamicModule(this))
                    .build();
        }
        viewComponent.inject(this);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_button)
    public void onLoginButtonClick(){
        if (validateUserInput()) {
            loginPresenter.onLoginButtonClick(userEmailTextInput.getText().toString(),
                    userPasswordTextInput.getText().toString());
        }
    }


    @Override
    public void startMainActivity() {
        Log.d("we are", "here");
    }

    @Override
    public void startProgressBar() {
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    @Override
    public void stopProgressBar() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(Throwable throwable) {

    }

    public boolean validateUserInput(){
        boolean successfulValidation = true;

        if (!loginPresenter.isEmailValid(userEmailTextInput.getText().toString())) {
            successfulValidation = false;
            userEmailTextInput.setError(getString(R.string.wrong_email_input_error));
        }

        if (!loginPresenter.isPasswordValid(userPasswordTextInput.getText().toString())) {
            successfulValidation = false;
            userPasswordTextInput.setError(getString(R.string.wrong_password_input_error));
        }

        return successfulValidation;
    }



    private void initProgressBar() {
        progressDialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setMessage(getString(R.string.login_progressbar_message));
        progressDialog.setIndeterminate(true);
    }
}
