package de.saarpit.optibas;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startResultActivity(View v){
        SharedPreferences sharedPref = getApplication().getSharedPreferences("main", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        EditText textFieldName = (EditText)findViewById(R.id.textFieldName);
        editor.putString(getString(R.string.activity_main_textFieldName),
                String.valueOf(textFieldName.getText()));
        editor.commit();

        startActivity(new Intent(MainActivity.this, GraphActivity.class));
    }
}
