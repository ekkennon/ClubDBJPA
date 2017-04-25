/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author ekk
 */
@Entity
@Table (name="tblMembers")
public class Member {
    
    @Id
    @Column (name="MemID")
    private String memID;
    
    @Column (name="MemDt")
    @Temporal (TemporalType.DATE)
    private Date memDate;
    
    @Column (name="FirstName")
    private String fname;
    
    @Column (name="LastName")
    private String lname;
    
    @Column (name="MiddleName")
    private String midName;
    
    @Column (name="Status")
    private String status;
    
    @Column (name="Password")
    private long password;
    
    @Column (name="YTD_Total")
    private double ytdtotal;
    
    @Column (name="YTD_Total_Dt")
    @Temporal (TemporalType.DATE)
    private Date ytdtotaldt;
    
    @Transient
    private long pwAttempt;
    
    public Member() {
        memID = "";
        memDate = null;
        fname = "";
        lname = "";
        midName = "";
        status = "";
        password = -1;
        pwAttempt = 0;
    }

    /**
     * @return the memID
     */
    public String getMemID() {
        return memID;
    }

    /**
     * @param memID the memID to set
     */
    public void setMemID(String memID) {
        this.memID = memID;
    }

    /**
     * @param memDate the memDate to set
     */
    public void setMemDate(Date memDate) {
        this.memDate = memDate;
    }
    
    /**
     * @return the memDate
     */
    public Date getMemDate() {
        return memDate;
    }

    public String getMemDateS() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        return sdf.format(memDate);
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the midName
     */
    public String getMidName() {
        return midName;
    }

    /**
     * @param midName the midName to set
     */
    public void setMidName(String midName) {
        this.midName = midName;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the password
     */
    public long getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(long password) {
        this.password = password;
    }
    
    /**
    * @param attempt the attempt to set
    */
    public void setPwAttempt(long attempt) {
        this.pwAttempt = attempt;
    }
    
    public boolean isAuthenticated() {
        if (this.password > 0) {
            if (this.password == this.pwAttempt) {
                return true;
            }
        }
        return false;
    }
}
