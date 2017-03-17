package com.ling.jibonetposa.tools;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by cuiqiang on 2017/3/16.
 */

public interface IRetrofitHttp<T> {

    void onResponse(Call<T> call, Response<T> response);

    void onFailure(Call<T> call, Throwable t);
}
