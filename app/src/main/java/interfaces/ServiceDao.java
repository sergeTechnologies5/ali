package interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;
import models.Services;

@Dao
public interface ServiceDao {
    @Insert
    void insert(Services user);

    @Update
    void update(Services user);

    @Delete
    void delete(Services user);

    @Query("DELETE FROM user_table")
    void deleteAllUsers();

    @Query("SELECT * FROM services_table")
    LiveData<List<Services >> getAllUsers();


}
