package de.saarpit.optibas;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;

import java.util.List;

import de.saarpit.optibas.data.user.User;
import de.saarpit.optibas.data.user.UserViewModel;
import de.saarpit.optibas.view.user.UserListAdapter;

import static de.saarpit.optibas.MainActivity.NEW_WORD_ACTIVITY_REQUEST_CODE;

public class UserListActivity extends AppCompatActivity {

    public static final int NEW_USER_ACTIVITY_REQUEST_CODE = 1;

    private UserViewModel mUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        final UserListAdapter adapter = new UserListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        mUserViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable final List<User> users) {
                // Update the cached copy of the words in the adapter.
                adapter.setUsers(users);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent intent = new Intent(UserListActivity.this, NewUserActivity.class);
                startActivityForResult(intent, NEW_USER_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_USER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            User user = new User(data.getStringExtra(NewUserActivity.EXTRA_FULLNAME),
                    data.getIntExtra(NewUserActivity.EXTRA_WEIGHT, 0) ,
                    data.getDoubleExtra(NewUserActivity.EXTRA_INSULIN, 0) ,
                    data.getIntExtra(NewUserActivity.EXTRA_BASALRELATIVE, 0));
            mUserViewModel.insert(user);
        } else {
            // TODO: Hier muss noch eine Überprüfung der Formularfelder hin
            Toast.makeText(
                    getApplicationContext(),
                    "Nicht gespeichert", //R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

}
