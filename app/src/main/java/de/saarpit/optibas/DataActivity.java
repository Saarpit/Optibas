package de.saarpit.optibas;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import de.saarpit.optibas.data.user.User;
import de.saarpit.optibas.data.user.UserViewModel;
import de.saarpit.optibas.view.user.UserListAdapter;

public class DataActivity extends AppCompatActivity {

    private final ThreadLocal<UserViewModel> mUserViewModel = new ThreadLocal<UserViewModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_values);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final UserListAdapter adapter = new UserListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUserViewModel.set(ViewModelProviders.of(this).get(UserViewModel.class));

        mUserViewModel.get().getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable final List<User> users) {
                // Update the cached copy of the words in the adapter.
                adapter.setUsers(users);
            }
        });
    }


}
