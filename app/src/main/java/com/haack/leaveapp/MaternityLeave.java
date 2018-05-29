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

public class MaternityLeave extends Activity implements View.OnClickListener {
    EditText mailid,ename,ecause,days,from,sign,redate,duedate;
    String mail,name,cause,day,frm,si,rdt,due;
    Button obtn;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog,dueDatePickerDialog;

    private SimpleDateFormat dateFormatter;
    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mleave);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        mailid = (EditText)findViewById(R.id.mailid);
        ename = (EditText)findViewById(R.id.name);
        ecause = (EditText)findViewById(R.id.ecause);
        days = (EditText)findViewById(R.id.days);
        from = (EditText)findViewById(R.id.from);
        from.setInputType(InputType.TYPE_NULL);
        from.requestFocus();
        sign = (EditText)findViewById(R.id.signature);
        duedate = (EditText)findViewById(R.id.due);
        duedate.setInputType(InputType.TYPE_NULL);
        duedate.requestFocus();

        redate = (EditText)findViewById(R.id.ret);

        redate.setInputType(InputType.TYPE_NULL);

        obtn = (Button)findViewById(R.id.osbtn);
        setDateTimeField();
        from.setOnClickListener(this);
        redate.setOnClickListener(this);
        duedate.setOnClickListener(this);
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
       /* cause = ecause.getText().toString();
        if(TextUtils.isEmpty(cause)){
            ecause.setError("Enter a cause");
        }*/
       /* day = days.getText().toString();
        if(TextUtils.isEmpty(day)){
            days.setError("Enter number of days");
        }*/
        frm = from.getText().toString();
        /*if (TextUtils.isEmpty(frm)){
            from.setError("Enter from date");
        }*/
        due = duedate.getText().toString();
        rdt = redate.getText().toString();
        /*if(TextUtils.isEmpty(rdt)){
            redate.setError("Enter return date");
        }*/
        si = sign.getText().toString();
        if (TextUtils.isEmpty(si)){
            sign.setError("Enter signature");
        }


        if(view == from) {
            fromDatePickerDialog.show();
        }
        if(view == redate) {
            toDatePickerDialog.show();
        }
        if(view == duedate){
            dueDatePickerDialog.show();
        }
        String emailAdd[] = {mail};
        String message = "Dear " + name + ", \n\n"+
        "I write this letter to inform you that I am nearing my baby due date and my doctor has given "+due+" as my due date for baby delivery."
                +"He also advised me to be under his supervision as soon as possible"+
                "So, I want to inform you that Iam in need of leave starting from "+frm+" . " +
                "In addition I want to inform you that " +
                "I have my full annual leave allowance and cover my maternity leave under this allowance."
                +"If wish to join office soon, I will notify you about it and if I want to extend my leave period I will notify you as well. I kindly request to approve my leave and notify me as soon as possible.\n" +
                "\n" +
                "\n Yours sincerely,\n" + " " + si;
        if ( !mail.isEmpty() && !name.isEmpty() && !due.isEmpty() && !frm.isEmpty() && !si.isEmpty() && !rdt.isEmpty() ) {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAdd);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Maternity Leave Application");
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
        dueDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @TargetApi(Build.VERSION_CODES.N)
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                duedate.setText(dateFormatter.format(newDate.getTime()));
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