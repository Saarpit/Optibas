package de.saarpit.optibas.data.user;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("DELETE FROM USER")
    void deleteAll();

    @Query("SELECT * from USER ORDER BY USER_NAME ASC")
    LiveData<List<User>> getAllUsers();
}
