package net.enesimran.TWIDDA.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PASSWORDS")
public class Password {
    
    @Id
    @Column(name = "USERID")
    private String userid;


    @Column(name = "HASH")
    private String hash;


    public String getUserid() {
        return userid;
    }


    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getHash() {
        return hash;
    }


    public void setHash(String hash) {
        this.hash = hash;
    }

}
