package com.xdtpl.Remote;

import com.xdtpl.Model.Ip;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IpService {
    @POST("/GenerateChecksum.aspx")
    @FormUrlEncoded
    Call<Ip> getIp(@FieldMap Map<String, String> params);
}
