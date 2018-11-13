package de.saarpit.optibas;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import de.saarpit.optibas.data.user.User;
import de.saarpit.optibas.data.user.UserViewModel;
import de.saarpit.optibas.view.user.UserListAdapter;


/*
Medtronic MINIMED 640G
    0,025 U für 0,025 bis 0,975 U/h
    0,05 U für 1,00 bis 9,95 U/h
    0,1 U für Raten von 10,0 U/h und mehr

Roche Accu Chek Insight
    0,01 (von 0,02 U/h bis 5,00 U/h)
    und 0,1 (von 5,00 U/h bis 25,0 U/h)

Roche Accu Chek Combo
    0,01 U/h (bis 1,00 U/h),
    0,05 U/h (bis 10,0 U/h) sowie
    0,1 U/h (bis 50,0 U/h)

Ypsomed mylife YpsoPump
    Range 0.00U/h to 2.00U/h: increment 0.05U/h
    Range 2.00U/h to 15.0U/h: increment 0.1U/h
    Range 15.0U/h to 40.0U/h: increment 0.2U/h

Insulet Omnipod
    Bereich: 0,05 E/h bis maximale Basalrate in Schritten von 0,05 E/h. Max = 30u

Dana RS
    Basalrate 0, 0,04 - 16 u/h
    Min. Basal 0,04
    min. Zunahme 0,01 u

Dana R
    0, 0,04~16,0 u/h
    24 Einstellungen
    0,04 u/h
    0,01 Einheiten

Dana IIS
    0, 0,04~16,0 u/h
    24 Einstellungen
    0,04 u/h
    0,01 Einheiten
 */
public class UserListActivity extends AppCompatActivity {

    public static final int NEW_USER_ACTIVITY_REQUEST_CODE = 1;

    private UserViewModel mUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

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
                    data.getIntExtra(NewUserActivity.EXTRA_BASALRELATIVE, 0),
                    data.getStringExtra(NewUserActivity.EXTRA_BIRTHDAY),
                    data.getStringExtra(NewUserActivity.EXTRA_WAKEUPTIME));
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
