package de.saarpit.optibas.data.user;

import android.os.AsyncTask;

import de.saarpit.optibas.data.user.UserRoomDatabase;

public class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private final UserDao mDao;

    PopulateDbAsync(UserRoomDatabase db) {
        mDao = db.userDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
        mDao.deleteAll();
        User user = new User("Eric Peters", 75, 0, 0);
        mDao.insert(user);
        user = new User("Silke Peters", 50, 17, 50);
        mDao.insert(user);
        return null;
    }
}