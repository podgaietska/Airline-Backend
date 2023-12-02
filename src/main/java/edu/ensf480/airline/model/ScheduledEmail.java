package edu.ensf480.airline.model;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class ScheduledEmail {
    private static ScheduledEmail newsletterEmail;

    public static ScheduledEmail getNewsletterEmail(){
        if (newsletterEmail==null){newsletterEmail = new ScheduledEmail();}
        return newsletterEmail;
    }

//    private Calendar calendar;
//    private int daySpacing;
//    private Timer timer;
    private String message;
    private String subject;
    private String[] emails;

    public ScheduledEmail(){};

//    public void setTimer(int daySpacing, int dayofMonth, int hour, int minute){
//        Calendar calendar = Calendar.getInstance();
////        calendar.set(Calendar.DAY_OF_MONTH, dayofMonth);
//        calendar.set(Calendar.HOUR_OF_DAY, hour);
//        calendar.set(Calendar.MINUTE, minute);
//        calendar.set(Calendar.SECOND, 0);
//        this.daySpacing = daySpacing;
//
//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                SendEmail.sendEmail(message,subject,emails);
//            }
//        }, calendar.getTime(),86400000*daySpacing ); //Add /24 /60 if you want to send 1 every daySpacing minutes
//    }

    public void setMessage(String subject, String message){
        this.message = message;
        this.subject = subject;
    }

    public void setEmailList(String[] emails){
        this.emails = emails;
    }

    public void sendEmail(){
        SendEmail.sendEmail(message,subject,emails);
    }

    public void addEmail(String email){
        if (emails == null) {
            emails = new String[1];
            emails[0] = email;
        }
        else{
            int initialLength = emails.length;
            emails = Arrays.copyOf(emails, initialLength + 1);
            emails[initialLength] = email;
        }
    }
}
