package com.github.skomaromi.tasky.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Task {
    @PrimaryKey(autoGenerate = true) private int mId;
    @ColumnInfo(name = "title") private String mTitle;
    @ColumnInfo(name = "description") private String mDescription;
    @ColumnInfo(name = "priority") private String mPriority;

    public Task(String title, String description, String priority) {
        mTitle = title;
        mDescription = description;
        mPriority = priority;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) { this.mId = mId; }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getPriority() {
        return mPriority;
    }

    public void setPriority(String mPriority) {
        this.mPriority = mPriority;
    }

    @Override
    public String toString() {
        return "Book{" +
                "mTitle='" + mTitle + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mPriority='" + mPriority + '\'' +
                '}';

    }
}
