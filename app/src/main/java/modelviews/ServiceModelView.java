package modelviews;

import android.app.Application;
        import android.arch.lifecycle.AndroidViewModel;
        import android.arch.lifecycle.LiveData;
        import android.support.annotation.NonNull;

        import java.util.List;

import models.Services;
import repositories.ServiceRepo;

public class ServiceModelView extends AndroidViewModel {

    private ServiceRepo repository;
    private LiveData<List<Services>> allNotes;


    private List<Services> alljobs;


    public ServiceModelView(@NonNull Application application) {
        super(application);

        repository = new ServiceRepo(application);
       allNotes = repository.getHeroes();

    }

    public LiveData<List<Services>> getLiveServices() {
        return allNotes;
    }

    public List<Services> getAllJobs() {
        return alljobs;
    }


}
