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

public class PaternityLeave extends Activity implements View.OnClickListener {
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
        setContentView(R.layout.pleave);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        mailid = (EditText)findViewById(R.id.mailid);
        duedate = (EditText)findViewById(R.id.due);
        duedate.setInputType(InputType.TYPE_NULL);
        duedate.requestFocus();
        ename = (EditText)findViewById(R.id.name);
        /*ecause = (EditText)findViewById(R.id.ecause);
        days = (EditText)findViewById(R.id.days);
        from = (EditText)findViewById(R.id.from);
        from.setInputType(InputType.TYPE_NULL);
        from.requestFocus();*/
        sign = (EditText)findViewById(R.id.signature);
        //redate = (EditText)findViewById(R.id.ret);

        // redate.setInputType(InputType.TYPE_NULL);

        obtn = (Button)findViewById(R.id.osbtn);
        setDateTimeField();
        /*from.setOnClickListener(this);
        redate.setOnClickListener(this);*/
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
        //frm = from.getText().toString();
        /*if (TextUtils.isEmpty(frm)){
            from.setError("Enter from date");
        }*/
        si = sign.getText().toString();
        if (TextUtils.isEmpty(si)){
            sign.setError("Enter signature");
        }
        //rdt = redate.getText().toString();
        /*if(TextUtils.isEmpty(rdt)){
            redate.setError("Enter return date");
        }*/
        due = duedate.getText().toString();
        if(view == from) {
            fromDatePickerDialog.show();
        }
        /*if(view == redate) {
            toDatePickerDialog.show();
        }*/
        if(view == duedate){
            dueDatePickerDialog.show();
        }
        String emailAdd[] = {mail};
        String message = "Dear " + name + ", \n\n"+
                "My [wife/partner] is expecting a baby and I will have joint responsibility for the upbringing of the child.\n" +
                "\n" +
                "I’m applying to take time off work to support my partner and care for our child. The expected date of birth of our baby is "+due+"\n" +
                "\n" +
                "I’d like to start my paternity leave the day the baby is born, whenever this occurs, and to receive my paternity pay from this date.\n" +
                "\n" +
                "I understand that if I’m at work when the baby arrives, my leave and pay will start the day after. I would like to take two weeks’ leave and pay.\n" +
                "\n" +
                "I hope this is all satisfactory and I look forward to hearing back from you with confirmation of the above."
                + "\n Yours sincerely,\n" + " " + si;
        if ( !mail.isEmpty() && !name.isEmpty() && !due.isEmpty()  && !si.isEmpty() ) {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAdd);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Paternity Leave Application");
            emailIntent.setType("plain/text");
            emailIntent.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(emailIntent);

        }
    }
    private void setDateTimeField() {
        /*.setOnClickListener(this);
        redate.setOnClickListener(this);*/

        Calendar newCalendar = Calendar.getInstance();
        /*fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

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

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));*/
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