package de.saarpit.optibas;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Calendar;

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

    private Calendar mCalendar = Calendar.getInstance();

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

        final Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                Boolean lError = false;

                // TODO: Leere Felder abfangen und noch eine Meldung geben
                if (TextUtils.isEmpty(mEditUserName.getText())) {
                    mEditUserName.setError("Bitte geben Sie einen Namen ein");
                    lError = true;
                }

                if (TextUtils.isEmpty(mEditUserBirthday.getText())) {
                    mEditUserBirthday.setError("Bitte geben Sie ein Geburtsdatum ein");
                    lError = true;
                }

                if (!lError) {
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
                    finish();
                }
            }
        });

        final Button buttonCancel = findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                setResult(RESULT_CANCELED, replyIntent);
                finish();
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateBirthdayLabel();
            }
        };

        mEditUserBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog lPicker = new DatePickerDialog(NewUserActivity.this, date, mCalendar
                        .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH));

                lPicker.getDatePicker().setMaxDate(new Date().getTime());
                lPicker.show();
            }
        });

        final TimePickerDialog.OnTimeSetListener wakeupTime = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                mCalendar.set(Calendar.MINUTE, minute);
                updateWakeUpLabel();
            }
        };

        mEditUserWakeupTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(NewUserActivity.this, wakeupTime,
                        mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE),
                        true).show();
            }
        });
    }

    private void updateBirthdayLabel() {
        String myFormat = "dd.MM.yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.GERMANY);

        mEditUserBirthday.setText(sdf.format(mCalendar.getTime()));
    }

    private void updateWakeUpLabel() {
        String myFormat = "HH:mm"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.GERMANY);

        mEditUserWakeupTime.setText(sdf.format(mCalendar.getTime()));
    }
}
