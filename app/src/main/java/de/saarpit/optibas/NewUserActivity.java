package de.saarpit.optibas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewUserActivity extends AppCompatActivity {

    public static final String EXTRA_FULLNAME = "de.saarpit.optibas.NewUserActivity.FULLNAME";
    public static final String EXTRA_BIRTHDAY = "de.saarpit.optibas.NewUserActivity.BIRTHDAY";
    public static final String EXTRA_INSULIN = "de.saarpit.optibas.NewUserActivity.INSULIN";
    public static final String EXTRA_BASALRELATIVE = "de.saarpit.optibas.NewUserActivity.BASALRELATIVE";
    public static final String EXTRA_WEIGHT = "de.saarpit.optibas.NewUserActivity.WEIGHT";
    public static final String EXTRA_WAKEUPTIME = "de.saarpit.optibas.NewUserActivity.WAKEUPTIME";

    private EditText mEditUserName;
    private EditText mEditUserBirthday;
    private EditText mEditUserInsulin;
    private EditText mEditUserBasalRelative;
    private EditText mEditUserWeight;
    private EditText mEditUserWakeupTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        mEditUserName = findViewById(R.id.textFieldName);
        mEditUserBirthday = findViewById(R.id.dateFieldBirthday);
        mEditUserInsulin = findViewById(R.id.numberDecimalFieldInsulin);
        mEditUserBasalRelative = findViewById(R.id.numberDecimalFieldBasalRelative);
        mEditUserWeight = findViewById(R.id.numberFieldWeight);
        mEditUserWakeupTime = findViewById(R.id.timeFieldWakeupTime);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                // TODO: Leere Felder abfangen und noch eine Meldung geben
                if (TextUtils.isEmpty(mEditUserName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String fullName = mEditUserName.getText().toString();
                    replyIntent.putExtra(EXTRA_FULLNAME, fullName);

                    String birthday = mEditUserBirthday.getText().toString();
                    replyIntent.putExtra(EXTRA_BIRTHDAY, birthday);

                    String insulin = mEditUserInsulin.getText().toString();
                    replyIntent.putExtra(EXTRA_INSULIN, insulin);

                    String basalRelative = mEditUserBasalRelative.getText().toString();
                    replyIntent.putExtra(EXTRA_BASALRELATIVE, basalRelative);

                    String weight = mEditUserWeight.getText().toString();
                    replyIntent.putExtra(EXTRA_WEIGHT, weight);

                    String wakeupTime = mEditUserWakeupTime.getText().toString();
                    replyIntent.putExtra(EXTRA_WAKEUPTIME, wakeupTime);

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
