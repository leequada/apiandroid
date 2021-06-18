package com.example.demoapi.API;

public class API_Implement {
    private static String sv_url = "http://10.0.2.2:8081/api/";

    public static DataService getService() {
        return API_Retrofit.getClient(sv_url).create(DataService.class);
    }
}
