package com.lykj.myapplication.retrofits;


import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * author：LiShan
 * Creation time：2017/1/9 0009
 */

public class Service {
    private static Service instance;
    private Service() {
    }
    public static  Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public void postSS() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();
//        GitHubService service = retrofit.create(GitHubService.class);
//        Call<> repos = service.listRepos("octocat");
    }
    public interface ApiService {
        //GET为GET请求,标明请求地址为BASEURI+/users/{user}
        //{user}会被getFeed方法的参数代替
        //JsonBean为自己设置返回类型,通过.addConverterFactory(GsonConverterFactory.create())
        //利用gson自动完成数据转换
//        @GET("users/{user}")
//        Call<JsonBean> getFeed(@Path("user")String user);
    }
}
