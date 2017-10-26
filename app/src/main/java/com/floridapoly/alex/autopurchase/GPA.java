package com.floridapoly.alex.autopurchase;

public class GPA {

    private double mGrade;
    private int mCredits;
    private String mCourseName;

    public void setCredits(int credits) {
        mCredits = credits;
    }

    public double getCredits() {
        return mCredits;
    }

    public void setCourseName(String courseName) {
        mCourseName = courseName;
    }

    public String getCourseName() {
        return mCourseName;
    }

    public void setGrade(String grade) {
        if (grade.equals ("A"))
            mGrade = 4.00;
        else if (grade.equals ("A-"))
            mGrade = 3.67;
        else if (grade.equals ("B+"))
            mGrade = 3.33;
        else if (grade.equals ("B"))
            mGrade = 3.00;
        else if (grade.equals ("B-"))
            mGrade = 2.67;
        else if (grade.equals ("C+"))
            mGrade = 2.33;
        else if (grade.equals ("C"))
            mGrade = 2.00;
        else if (grade.equals ("C-"))
            mGrade = 1.67;
        else if (grade.equals ("D+"))
            mGrade = 1.33;
        else if (grade.equals ("D"))
            mGrade = 1.00;
        else if (grade.equals ("D-"))
            mGrade = 0.67;
        else if (grade.equals ("F"))
            mGrade = 0.00;
        else if (grade.equals ("WF"))
            mGrade = 0.00;
        else
            mGrade = 0.00;
    }

    public double getGrade() {return mGrade;}

    public double totalGradePoints() {return mCredits * mGrade;}

    // need to get total credit hours
    public int totalCreditHours() {return mCredits;}

    public double totalGPA() {return totalGradePoints() / totalCreditHours();}

}