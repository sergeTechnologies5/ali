package models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "services_table")
public class Services {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("fee")
    int fee;
    @SerializedName("servicetitle")
    String servicetitle;
    @SerializedName("serviceno")
    String serviceno;
    @SerializedName("description")
    String description;
    @SerializedName("date_created")
    String date_created;

    public Services(int fee, String servicetitle, String serviceno, String description, String date_created) {
        this.fee = fee;
        this.servicetitle = servicetitle;
        this.serviceno = serviceno;
        this.description = description;
        this.date_created = date_created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getServicetitle() {
        return servicetitle;
    }

    public void setServicetitle(String servicetitle) {
        this.servicetitle = servicetitle;
    }

    public String getServiceno() {
        return serviceno;
    }

    public void setServiceno(String serviceno) {
        this.serviceno = serviceno;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }
}
