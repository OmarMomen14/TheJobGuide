package com.example.thejobguide.Models;

public class User {

    private String fname, lname, email, phone, password, role;

    public User() {

    }

    public User(String fname, String lname, String email, String phone, String password, String role) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
