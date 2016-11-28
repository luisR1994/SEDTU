package com.example.thosiba.sedtu.servicios;


import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.thosiba.sedtu.R;
import com.example.thosiba.sedtu.login.Constantes;
import com.example.thosiba.sedtu.login.RequestInterface;
import com.example.thosiba.sedtu.modelos.ServerRequest;
import com.example.thosiba.sedtu.modelos.ServerResponse;
import com.example.thosiba.sedtu.modelos.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SolicitarServicioFragment extends Fragment implements View.OnClickListener{

    private SharedPreferences pref;
    AppCompatButton btn_est_fecha, btn_est_hora, btn_solicitar;
    TextView tv_fecha, tv_hora;
    AlertDialog dialog;
    String format=null , minute = null;
    int hora, minuto;

    public SolicitarServicioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_solicitar_servicio, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        pref = getActivity().getSharedPreferences("user_pref", MODE_PRIVATE);
    }

    public void initViews(View view){
        btn_est_fecha = (AppCompatButton) view.findViewById(R.id.btn_est_fecha);
        btn_est_hora = (AppCompatButton) view.findViewById(R.id.btn_est_hora);
        btn_solicitar = (AppCompatButton) view.findViewById(R.id.btn_solicitar);

        tv_fecha = (TextView) view.findViewById(R.id.tv_fecha);
        tv_hora = (TextView) view.findViewById(R.id.tv_hora);

        btn_est_fecha.setOnClickListener(this);
        btn_est_hora.setOnClickListener(this);
        btn_solicitar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_est_fecha:
                showDialogFecha();
                break;
            case R.id.btn_est_hora:
                showDialogHora();
                break;
            case R.id.btn_solicitar:
                registrarServicio();
                break;
        }

    }

    public void showDialogFecha(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_get_fecha, null);
        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        builder.setView(view);
        builder.setTitle("Elegir Fecha");
        builder.setPositiveButton("Elegir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mes = datePicker.getMonth();
                int dia = datePicker.getDayOfMonth();
                int anio = datePicker.getYear();

                tv_fecha.setText(mes + "/" + dia + "/" + anio);
                dialog.dismiss();
            }
        });

    }

    public void showDialogHora(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_get_hora, null);
        final TimePicker timePicker = (TimePicker) view.findViewById(R.id.timePicker);
        builder.setView(view);
        builder.setTitle("Elegir hora");
        builder.setPositiveButton("Elegir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hora = timePicker.getCurrentHour();
                minuto = timePicker.getCurrentMinute();
                if (hora == 0) {
                    hora += 12;
                    format = "AM";
                } else if (hora == 12) {
                    format = "PM";
                } else if (hora > 12) {
                    hora -= 12;
                    format = "PM";
                } else {
                    format = "AM";
                }
                if(minuto <10)
                    minute = "0" + minuto;
                else minute = String.valueOf(minuto);

                tv_hora.setText(hora + ":" + minute + " " + format);
                dialog.dismiss();
            }
        });
    }

    public void registrarServicio(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Usuario usuario = new Usuario();
        usuario.setEmail(pref.getString(Constantes.EMAIL,""));
        usuario.setHora(tv_hora.getText().toString());
        usuario.setFecha(tv_fecha.getText().toString());
        ServerRequest request = new ServerRequest();
        request.setOperation(Constantes.REGISTER_SERVICE);
        request.setUsuario(usuario);
        Call<ServerResponse> response = requestInterface.operation(request);
        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                if(resp.getResult().equals(Constantes.SUCCESS)){
                    Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();

                }else {
                    Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.d(Constantes.TAG,"failed");
                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();

            }
        });
    }
}
