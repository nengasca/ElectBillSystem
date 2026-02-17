package config;

import java.sql.Date;

public class billsmodel {
    private int b_id;
    private String account_number;
    private String bill_month;
    private int kwh_used;
    private double amount_due;
    private Date due_date;
    private String status;

    public billsmodel(int b_id, String account_number, String bill_month, int kwh_used, double amount_due, Date due_date, String status) {
        this.b_id = b_id;
        this.account_number = account_number;
        this.bill_month = bill_month;
        this.kwh_used = kwh_used;
        this.amount_due = amount_due;
        this.due_date = due_date;
        this.status = status;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getBill_month() {
        return bill_month;
    }

    public void setBill_month(String bill_month) {
        this.bill_month = bill_month;
    }

    public int getKwh_used() {
        return kwh_used;
    }

    public void setKwh_used(int kwh_used) {
        this.kwh_used = kwh_used;
    }

    public double getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(double amount_due) {
        this.amount_due = amount_due;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

