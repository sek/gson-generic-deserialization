package com.stankurdziel.sample;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import lombok.Getter;

@Getter
class Response<T> {

    @SerializedName("id")
    private int id;

    @SerializedName("jsonrpc")
    private String jsonRpc;

    @SerializedName("result")
    private T result;

    public Response() {
    }

    public static Response fromJson(String json) {
        return new Gson().fromJson(json, Response.class);
    }

    public static <T> Response<T> fromJson(String json, Class<T> resultClass) {
        return new Gson().fromJson(json, responseType(resultClass));
    }

    public static <T> Type responseType(Class<T> resultClass) {
        switch (resultClass.getSimpleName()) {
            case "ResultT1":
                return new TypeToken<Response<ResultT1>>() {
                }.getType();
            case "ResultT2":
                return new TypeToken<Response<ResultT2>>() {
                }.getType();
            default:
                throw new RuntimeException("Unknown type: " + resultClass);
        }
    }
}
