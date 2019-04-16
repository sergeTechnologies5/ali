package database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import interfaces.ServiceDao;
import interfaces.UserDao;
import models.Services;
import models.User;

@Database(entities = {User.class, Services.class}, version = 1)
public abstract class Db extends RoomDatabase {

    private static Db instance;

    public abstract UserDao userDao();
    public abstract ServiceDao ServiceDao();


    public static synchronized Db getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Db.class, "krapp_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static Callback roomCallback =
            new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateServiceAsyncTask(instance).execute();
            new PopulateUserAsyncTask(instance).execute();
        }
    };


    private static class PopulateUserAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        private PopulateUserAsyncTask(Db db) {
            userDao = db.userDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insert(new User("njesh@gmail.com","njeru","nthiga", "serge", "12345"));
            return null;
        }
    }

    private static class PopulateServiceAsyncTask extends AsyncTask<Void, Void, Void> {
        private ServiceDao serviceDao;

        private PopulateServiceAsyncTask(Db db) {
            serviceDao = db.ServiceDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            serviceDao.insert(new Services(300,"Parking","3344", "Pay For Parking", "12-3-2019"));
            serviceDao.insert(new Services(800,"Shops","6785", "Pay For Shops", "12-3-2019"));
            serviceDao.insert(new Services(100,"Lands","6765", "Pay For Land", "12-3-2019"));
            serviceDao.insert(new Services(10,"Water","6678", "Pay For Water", "12-3-2019"));
            serviceDao.insert(new Services(300,"Parking","3344", "Pay For Parking", "12-3-2019"));
            serviceDao.insert(new Services(800,"Shops","6785", "Pay For Shops", "12-3-2019"));
            return null;
        }
    }


}

