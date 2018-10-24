package de.saarpit.optibas.data.user;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "USER")
public class User {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "USER_ID")
    private int mUid;

    @NonNull
    @ColumnInfo(name = "USER_NAME")
    private String mName;

    @ColumnInfo(name = "USER_WEIGHT")
    private int mWeight;

    @NonNull
    @ColumnInfo(name = "USER_DAILYINSULIN")
    private double mDailyInsulin;

    @NonNull
    @ColumnInfo(name = "USER_BASALQUOTA")
    private int mBasalQuota;

    public User(@NonNull String name, int weight, double dailyInsulin, int basalQuota){
        this.mName = name;
        this.mWeight = weight;
        this.mDailyInsulin = dailyInsulin;
        this.mBasalQuota = basalQuota;
    }

    public void setUid(int uid) {this.mUid = uid;}
    public void setName(String name) {this.mName = name;}
    public void setWeight(int weight) {this.mWeight = weight;}
    public void setDailyInsulin(double dailyInsulin) {this.mDailyInsulin = dailyInsulin;}
    public void setBasalQuota(int basalQuota) {this.mBasalQuota = basalQuota;}

    public int getUid() {return this.mUid;}
    public String getName(){return this.mName;}
    public int getWeight(){return this.mWeight;}
    public double getDailyInsulin(){return this.mDailyInsulin;}
    public int getBasalQuota(){return this.mBasalQuota;}

}
