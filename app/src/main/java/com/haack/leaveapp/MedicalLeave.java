package com.haack.leaveapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by User on 2/3/2017.
 */

public class MedicalLeave extends Activity implements View.OnClickListener {
    EditText mailid,ename,ecause,days,from,sign,redate,worker;
    String mail,name,cause,day,frm,si,rdt,cworker;
    Button obtn;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;
    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medleave);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        mailid = (EditText)findViewById(R.id.mailid);
        ename = (EditText)findViewById(R.id.name);
        ecause = (EditText)findViewById(R.id.ecause);
        worker = (EditText)findViewById(R.id.cname);
        days = (EditText)findViewById(R.id.days);
        from = (EditText)findViewById(R.id.from);
        from.setInputType(InputType.TYPE_NULL);
        from.requestFocus();
        sign = (EditText)findViewById(R.id.sign);
        redate = (EditText)findViewById(R.id.redate);
        redate.setInputType(InputType.TYPE_NULL);
        obtn = (Button)findViewById(R.id.osbtn);
        setDateTimeField();
        from.setOnClickListener(this);
        redate.setOnClickListener(this);
        obtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        mail = mailid.getText().toString();
        if(TextUtils.isEmpty(mail)){
            mailid.setError("Enter mail id");
        }
        name = ename.getText().toString();
        if(TextUtils.isEmpty(name)){
            ename.setError("Enter Receiver name");
        }
        cworker = worker.getText().toString();
        if(TextUtils.isEmpty(name)){
            ename.setError("Enter Co-Worker name");
        }
        cause = ecause.getText().toString();
        if(TextUtils.isEmpty(cause)){
            ecause.setError("Enter a cause");
        }
        day = days.getText().toString();
        if(TextUtils.isEmpty(day)){
            days.setError("Enter number of days");
        }
        frm = from.getText().toString();
        /*if (TextUtils.isEmpty(frm)){
            from.setError("Enter from date");
        }*/
        si = sign.getText().toString();
        if (TextUtils.isEmpty(si)){
            sign.setError("Enter signature");
        }
        rdt = redate.getText().toString();
        /*if(TextUtils.isEmpty(rdt)){
            redate.setError("Enter return date");
        }*/
        if(view == from) {
            fromDatePickerDialog.show();
        }
        if(view == redate) {
            toDatePickerDialog.show();
        }
        String emailAdd[] = {mail};
        String message = "Dear " + name + ", \n\n"
                + "I would like to bring to your kind attention that my medical reports have detected " + cause
                + " I have been advised by my doctor to be on bed rest for " + day + " day(s). I am enclosing my medical reports for your reference."
                + "\n\n" + "I am writing this letter to officially inform about my illness and request for medical leave."
                +"I have handed over my work to "+cworker+" who will be keep my work updated in my absence."
                + " I would kindly request you to grant me leave w.e.f " + frm + ". I would be reporting back on " + rdt
                + "\n\n Thanks and Regards "
                + "\n" + " " + si;
        if ( !mail.isEmpty() && !name.isEmpty() && !cause.isEmpty() && !day.isEmpty() && !cworker.isEmpty() && !frm.isEmpty() && !si.isEmpty() && !rdt.isEmpty() ) {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAdd);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Leave Application");
            emailIntent.setType("plain/text");
            emailIntent.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(emailIntent);

        }
    }
    private void setDateTimeField() {
        /*.setOnClickListener(this);
        redate.setOnClickListener(this);*/

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @TargetApi(Build.VERSION_CODES.N)
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                from.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @TargetApi(Build.VERSION_CODES.N)
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                redate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}