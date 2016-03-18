package com.mycompany.signin;

/**
 * Created by Prakash Chourasia on 11/23/2015.
 */
public class Transactions {
    private int _id;
    private String _emailid;
    private String _amount;
    private String _category;
    private String _day;
    private String _month;
    private String _year;

    public Transactions()
    {

    }

    public Transactions(String _emailid, String _amount, String _category, String _day, String _month, String _year) {
        this._emailid = _emailid;
        this._amount = _amount;
        this._category = _category;
        this._day = _day;
        this._month = _month;
        this._year = _year;
    }

    public int get_id() {
        return _id;
    }

    public String get_emailid() {
        return _emailid;
    }

    public String get_amount() {
        return _amount;
    }

    public String get_category() {
        return _category;
    }

    public String get_day() {
        return _day;
    }

    public String get_year() {
        return _year;
    }

    public String get_month() {
        return _month;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_emailid(String _emailid) {
        this._emailid = _emailid;
    }

    public void set_amount(String _amount) {
        this._amount = _amount;
    }

    public void set_category(String _category) {
        this._category = _category;
    }

    public void set_day(String _day) {
        this._day = _day;
    }

    public void set_month(String _month) {
        this._month = _month;
    }

    public void set_year(String _year) {
        this._year = _year;
    }
}
