package com.floridapoly.alex.autopurchase;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

/**
 * Created by Alex on 10/10/17.
 */

public class MainActivity extends Activity {
    // THE GPA OBJECT CONTAINS THE INFORMATION ABOUT THE GPA
    GPA mGPA;

    // THE DATA TO BE PASSED TO THE GPA SUMMARY ACTIVITY
    String creditHours;
    String totalGPA;
    String gradePoints;

    // LAYOUT INPUT REFERENCES
    private EditText courseET1;
    private EditText courseET2;
    private EditText courseET3;
    private EditText courseET4;
    private Spinner creditSpinner1;
    private Spinner creditSpinner2;
    private Spinner creditSpinner3;
    private Spinner creditSpinner4;
    private Spinner gradeSpinner1;
    private Spinner gradeSpinner2;
    private Spinner gradeSpinner3;
    private Spinner gradeSpinner4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        //ESTABLISH REFERENCES TO EDITABLE TEXT FIELDS AND RADIO BUTTON
        courseET1 = (EditText) findViewById(R.id.editText1);
        courseET2 = (EditText) findViewById(R.id.editText2);
        courseET3 = (EditText) findViewById(R.id.editText3);
        courseET4 = (EditText) findViewById(R.id.editText4);

        creditSpinner1 = (Spinner) findViewById(R.id.spinner1);
        creditSpinner2 = (Spinner) findViewById(R.id.spinner2);
        creditSpinner3 = (Spinner) findViewById(R.id.spinner3);
        creditSpinner4 = (Spinner) findViewById(R.id.spinner4);

        gradeSpinner1 = (Spinner) findViewById(R.id.spinner5);
        gradeSpinner2 = (Spinner) findViewById(R.id.spinner6);
        gradeSpinner3 = (Spinner) findViewById(R.id.spinner7);
        gradeSpinner4 = (Spinner) findViewById(R.id.spinner8);


        //CREATE AN AUTOMOBILE OBJECT TO STORE AUTO DATA
        mGPA = new GPA();
    }

    private void collectGPAInputData() {
        // TASK 1: SET THE COURSE NAME
        mGPA.setCourseName1 ((String) String.valueOf(courseET1.getText()
                .toString()));

        mGPA.setCourseName2 ((String) String.valueOf(courseET2.getText()
                .toString()));

        mGPA.setCourseName3 ((String) String.valueOf(courseET3.getText()
                .toString()));

        mGPA.setCourseName4 ((String) String.valueOf(courseET4.getText()
                .toString()));

        //TASK 2: SET THE CREDIT HOURS
        mGPA.setCredits((int)
                Integer.valueOf(creditSpinner1.getText()
                        .toString()));

        //TASK 3 SET THE GRADES
        Integer radioId = loanTermRG.getCheckedRadioButtonId();
        RadioButton term = (RadioButton) findViewById(radioId);
        mGPA.setLoanTerm(term.getText().toString());
    }

    private void buildLoanReport() {
        // TASK 1: CONSTRUCT THE MONTHLY PAYMENT
        Resources res = getResources();
        monthlyPayment = res.getString(R.string.report_line1)
                + String.format("%.02f", mGPA.monthlyPayment());


        // TASK 2: CONSTRUCT THE LOAN REPORT
        loanReport = res.getString(R.string.report_line6)
                + String.format("%10.02f", mGPA.getPrice());
        loanReport += res.getString(R.string.report_line7)
                + String.format("%10.02f", mGPA.getDownPayment());

        loanReport += res.getString(R.string.report_line9)
                + String.format("%18.02f", mGPA.taxAmount());
        loanReport += res.getString(R.string.report_line10)
                + String.format("%18.02f", mGPA.totalCost());
        loanReport += res.getString(R.string.report_line11)
                + String.format("%12.02f", mGPA.borrowedAmount());
        loanReport += res.getString(R.string.report_line12)
                + String.format("%12.02f", mGPA.interestAmount());

        loanReport += "\n\n" + res.getString(R.string.report_line8) + " " + mGPA.getLoanTerm() + " years.";

        loanReport += "\n\n" + res.getString(R.string.report_line2);
        loanReport += res.getString(R.string.report_line3);
        loanReport += res.getString(R.string.report_line4);
        loanReport += res.getString(R.string.report_line5);

    }

    public void activateLoanSummary(View view) {
        //TASK 1: BUILD A LOAN REPORT FROM THE INPUT DATA
        collectAutoInputData();
        buildLoanReport();

        //TASK 2: CREATE AN INTENT TO DISPLAY THE LOAN SUMMARY ACTIVITY
        Intent launchReport = new Intent(this, GPASummaryActivity.class);

        //TASK 3: PASS THE LOAN SUMMARY ACTIVITY TWO PIECES OF DATA:
        //     THE LOAN REPORT CONTAINING LOAN DETAILS
        //     THE MONTHLY PAYMENT
        launchReport.putExtra("LoanReport", loanReport);
        launchReport.putExtra("MonthlyPayment", monthlyPayment);

        //TASK 4: START THE LOAN ACTIVITY
        startActivity(launchReport);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu;
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}