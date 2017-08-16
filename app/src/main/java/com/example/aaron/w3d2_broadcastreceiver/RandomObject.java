package com.example.aaron.w3d2_broadcastreceiver;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aaron on 8/16/17.
 */

public class RandomObject implements Parcelable{
    String imageLocation;
    String dataOne;
    String dataTwo;
    String dataThree;

    protected RandomObject(Parcel in) {
        imageLocation = in.readString();
        dataOne = in.readString();
        dataTwo = in.readString();
        dataThree = in.readString();
    }

    public static final Creator<RandomObject> CREATOR = new Creator<RandomObject>() {
        @Override
        public RandomObject createFromParcel(Parcel in) {
            return new RandomObject(in);
        }

        @Override
        public RandomObject[] newArray(int size) {
            return new RandomObject[size];
        }
    };

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getDataOne() {
        return dataOne;
    }

    public void setDataOne(String dataOne) {
        this.dataOne = dataOne;
    }

    public String getDataTwo() {
        return dataTwo;
    }

    public void setDataTwo(String dataTwo) {
        this.dataTwo = dataTwo;
    }

    public String getDataThree() {
        return dataThree;
    }

    public void setDataThree(String dataThree) {
        this.dataThree = dataThree;
    }

    public RandomObject(String imageLocation, String dataOne, String dataTwo, String dataThree) {

        this.imageLocation = imageLocation;
        this.dataOne = dataOne;
        this.dataTwo = dataTwo;
        this.dataThree = dataThree;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageLocation);
        parcel.writeString(dataOne);
        parcel.writeString(dataTwo);
        parcel.writeString(dataThree);
    }
}
