package de.saarpit.optibas.data.user;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository mRepository;
    private LiveData<List<User>> mAllUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);

        mRepository = new UserRepository(application);
        mAllUsers = mRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() { return mAllUsers; }

    public void insert(User user) { mRepository.insert(user);}
}
