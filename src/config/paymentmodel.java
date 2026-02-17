package config;

import java.util.Date;

public class paymentmodel {
    private int payment_id;
    private int bill_id;
    private double amount_paid;
    private Date payment_date;
    private String payment_method;

    public paymentmodel() {
    }

    public paymentmodel(int payment_id, int bill_id, double amount_paid, Date payment_date, String payment_method) {
        this.payment_id = payment_id;
        this.bill_id = bill_id;
        this.amount_paid = amount_paid;
        this.payment_date = payment_date;
        this.payment_method = payment_method;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public double getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(double amount_paid) {
        this.amount_paid = amount_paid;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
}

