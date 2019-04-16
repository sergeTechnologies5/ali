
package repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.widget.Toast;
import java.util.List;
import database.Db;
import interfaces.ServiceDao;
import interfaces.UserDao;
import models.Services;
import retrofit.ApiClient;
import retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ServiceRepo {
    private ServiceDao noteDao;

    //livedata fromdb
    private LiveData<List<Services>> allliveservices;


    private MutableLiveData<List<Services>> heroList;
    Application application;
    List<Services> allservices;
    public ApiService apiInterface;

    public ServiceRepo(final Application application) {
        Db database = Db.getInstance(application);
        noteDao = database.ServiceDao();
        apiInterface = ApiClient.getService();


        //
        Call<List<Services>> call = apiInterface.getServices();
        call.enqueue(new Callback<List<Services>>() {
            @Override
            public void onResponse(Call<List<Services>> call, Response<List<Services>> response) {

                if (heroList == null) {
                    heroList = new MutableLiveData<>();
                    //we will load it asynchronously from server in this method
                    heroList.setValue(allservices);
                }else {
                    heroList.setValue(response.body());
                }



                //Toast.makeText(application, response.body().get(0).getServiceno(),Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<List<Services>> call, Throwable t) {
                //Toast.makeText(application, t.getMessage(),Toast.LENGTH_LONG).show();
            }

        });

        this.application = application;

    }

    public LiveData<List<Services>> getLiveServices()
    {
        return allliveservices;
    }

    public List<Services> getServies() {

        return allservices;
    }

    public void getServices(){


    }

    public LiveData<List<Services>> getHeroes() {
        //if the list is null
        if (heroList == null) {
            heroList = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
            heroList.setValue(allservices);
        }

        //finally we will return the list
        return heroList;
    }


}
