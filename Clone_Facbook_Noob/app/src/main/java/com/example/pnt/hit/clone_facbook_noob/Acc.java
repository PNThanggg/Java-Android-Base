package com.example.pnt.hit.clone_facbook_noob;

import android.os.Parcel;
import android.os.Parcelable;

public class Acc implements Parcelable{
    String email, pass;

    public Acc(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public Acc() {
    }

    protected Acc(Parcel in) {
        email = in.readString();
        pass = in.readString();
    }

    public static final Creator<Acc> CREATOR = new Creator<Acc>() {
        @Override
        public Acc createFromParcel(Parcel in) {
            return new Acc(in);
        }

        @Override
        public Acc[] newArray(int size) {
            return new Acc[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(pass);
    }
}
