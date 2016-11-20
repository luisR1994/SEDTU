package com.example.thosiba.sedtu.login;

import com.example.thosiba.sedtu.modelos.ServerRequest;
import com.example.thosiba.sedtu.modelos.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by thosiba on 09/11/2016.
 */

public interface RequestInterface {
    @POST("sedtu/")
    Call<ServerResponse> operation(@Body ServerRequest request);
}
