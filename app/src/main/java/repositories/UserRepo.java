
package repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import database.Db;
import interfaces.UserDao;
import models.Login;
import models.LoginResponse;
import models.PaymentResponse;
import models.User;
import retrofit.ApiClient;
import retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepo {
    private UserDao noteDao;
    private LiveData<List<User>> allUsers;

    private MutableLiveData<User> heroList;
    Application application;

    List<User> allusers;
    public ApiService apiInterface;

 ;

    public UserRepo(final Application application) {
        Db database = Db.getInstance(application);
        noteDao = database.userDao();
        apiInterface = ApiClient.getService();
        this.application = application;

    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public List<User> getAlljobs() {

        return allusers;
    }

    public void createAccount(User user){
        Call<PaymentResponse> call = apiInterface.createAccount(user);
        call.enqueue(new Callback<PaymentResponse>() {
            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
               // heroList.setValue(response.body());
                Toast.makeText(application, response.message(),Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {
               Toast.makeText(application, t.getMessage(),Toast.LENGTH_LONG).show();
            }

        });

    }



}
