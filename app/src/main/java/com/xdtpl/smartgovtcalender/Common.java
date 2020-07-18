package com.xdtpl.smartgovtcalender;

import com.xdtpl.Remote.IpService;
import com.xdtpl.Remote.RetrofitClient;
//import com.example.admin.demoapp.Remote.IpService;
//import com.example.admin.demoapp.Remote.RetrofitClient;

public class Common {
    private static final String BASE_URL = "http://paytmchecksum.smartins.co.in";

    public static IpService getIpService(){
        return RetrofitClient.getClient(BASE_URL).create(IpService.class);
    }
}
