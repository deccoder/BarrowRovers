package declanbrophy.barrowrovers;


import android.widget.EditText;

import java.io.Serializable;

public class Players implements Serializable {

    private String name,email,squadNumber,pinNumber;
    private String id;

    public Players(String pName, String eAddress, String sNumber, String pNumber) {
    }



    public Players() {
    }

    public Players(String name, String email, String squadNumber, String pinNumber, String id) {
        this.name = name;
        this.email = email;
        this.squadNumber = squadNumber;
        this.pinNumber = pinNumber;
        this.id = id;
    }

    public String getpName() {
        return name;
    }

    public void setpName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEAddress(String email) {
        this.email = email;
    }

    public String getSNumber() {
        return squadNumber;
    }

    public void setSNumber(String squadNumber) {
        this.squadNumber = squadNumber;
    }

    public String getPNumber() {
        return pinNumber;
    }

    public void setPNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
