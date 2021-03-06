package com.example.thosiba.sedtu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.thosiba.sedtu.login.Constantes;
import com.example.thosiba.sedtu.login.LoginFragment;
import com.example.thosiba.sedtu.login.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences("user_pref", MODE_PRIVATE);
        initFragment();
    }
    private void initFragment() {
        Fragment fragmento = new LoginFragment();
        if(pref.getBoolean(Constantes.IS_LOGGED_IN, false)){
            Intent intent = new Intent(getApplicationContext(), FuncionActivity.class);
            startActivity(intent);
        } else {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_frame, fragmento);
            ft.commit();

        }
    }
}
