package com.example.thosiba.sedtu.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.thosiba.sedtu.R;
import com.example.thosiba.sedtu.modelos.ServerRequest;
import com.example.thosiba.sedtu.modelos.ServerResponse;
import com.example.thosiba.sedtu.modelos.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by thosiba on 08/11/2016.
 */

public class RegistroFragment extends Fragment implements View.OnClickListener{

    private AppCompatButton btn_register;
    private EditText et_email, et_name, et_apellidoP, et_apellidoM;
    private EditText et_password, et_calle, et_colonia, et_municipio, et_estado;
    private TextView tv_login;
    private ProgressBar progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);
        initViews(view);
        return view;
    }

    public void initViews(View view){
        btn_register = (AppCompatButton) view.findViewById(R.id.btn_register);
        tv_login = (TextView) view.findViewById(R.id.tv_login);
        et_name = (EditText) view.findViewById(R.id.et_name);
        et_apellidoP = (EditText) view.findViewById(R.id.et_apellidoP);
        et_apellidoM = (EditText) view.findViewById(R.id.et_apellidoM);
        et_email = (EditText) view.findViewById(R.id.et_email);
        et_password = (EditText) view.findViewById(R.id.et_password);
        et_calle = (EditText) view.findViewById(R.id.et_calle);
        et_colonia = (EditText) view.findViewById(R.id.et_colonia);
        et_municipio = (EditText) view.findViewById(R.id.et_municipio);
        et_estado = (EditText) view.findViewById(R.id.et_estado);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        btn_register.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_login:
                goToLogin();
                break;

            case R.id.btn_register:

                String nombre = et_name.getText().toString();
                String apellidoP = et_apellidoP.getText().toString();
                String apellidoM = et_apellidoM.getText().toString();
                String calle = et_calle.getText().toString();
                String colonia = et_colonia.getText().toString();
                String municipio = et_municipio.getText().toString();
                String estado = et_estado.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                if(!nombre.isEmpty() && !email.isEmpty() && !password.isEmpty() && !apellidoP.isEmpty()
                        && !apellidoM.isEmpty() && !calle.isEmpty() && !colonia.isEmpty()
                        && !municipio.isEmpty() && !estado.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    registerProcess(nombre, apellidoP, apellidoM, email, password, calle, colonia,
                     municipio, estado);

                } else {

                    Snackbar.make(getView(), "Ninguna campo de estar vacio", Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void registerProcess(String nombre, String apellidoP, String apellidoM, String email,
                                String password, String calle, String colonia,
                                String municipio, String estado){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellidoPaterno(apellidoP);
        usuario.setApellidoMaterno(apellidoM);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setCalle(calle);
        usuario.setColonia(colonia);
        usuario.setMunicipio(municipio);
        usuario.setEstado(estado);

        System.out.println(usuario);

        ServerRequest request = new ServerRequest();
        request.setOperation(Constantes.REGISTER_OPERATION);
        request.setUsuario(usuario);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                System.out.println(call);
                ServerResponse resp = response.body();
                Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();
                tv_login.setText(resp.getMessage().toString());
                progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                progress.setVisibility(View.INVISIBLE);
                Log.d(Constantes.TAG, "fallido");
                Snackbar.make(getView(), "hola" + t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();

            }
        });
    }

    private void goToLogin(){
        Fragment login = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,login);
        ft.commit();
    }
}
