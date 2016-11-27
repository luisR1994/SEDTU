package com.example.thosiba.sedtu.servicios;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thosiba.sedtu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SolicitarServicioFragment extends Fragment implements View.OnClickListener{


    AppCompatButton btn_est_fecha, btn_est_hora, btn_solicitar;
    TextView tv_fecha, tv_hora;
    public SolicitarServicioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_solicitar_servicio, container, false);
        initViews(view);
        return view;
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

    }
}
