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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author raefo
 */

@Entity
@Table(name="tblpurchases")
public class Purchase {
    
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long pid;
    
    @Column(name="MemId")
    private String memid;
    
    @Column(name="PurchaseDt")
    @Temporal(TemporalType.DATE)
    private Date purchaseDate;
    
    @Column(name="TransType")
    private String transType;
    
    @Column(name="TransCd")
    private String transCode;
    
    @Column(name="Amount")
    private double amt;
    
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="TransCd", insertable=false, updatable=false)
    private PCodes pcodes;
    
    public Purchase() {
        memid= "";
        purchaseDate = null;
        transType = "";
        transCode = "";
        amt = 0;
        pid = 0;
    }

    /**
     * @return the pid
     */
    public long getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(long pid) {
        this.pid = pid;
    }

    /**
     * @return the memid
     */
    public String getMemid() {
        return memid;
    }

    /**
     * @param memid the memid to set
     */
    public void setMemid(String memid) {
        this.memid = memid;
    }

    /**
     * @return the purchaseDate
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }
    
    public String getPurchaseDateS() {
        return new SimpleDateFormat("MM-dd-yyyy").format(purchaseDate);
    }

    /**
     * @param purchaseDate the purchaseDate to set
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * @return the transType
     */
    public String getTransType() {
        return transType;
    }

    /**
     * @param transType the transType to set
     */
    public void setTransType(String transType) {
        this.transType = transType;
    }

    /**
     * @return the transCode
     */
    public String getTransCode() {
        return transCode;
    }

    /**
     * @param transCode the transCode to set
     */
    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    /**
     * @return the amt
     */
    public double getAmt() {
        return amt;
    }

    /**
     * @param amt the amt to set
     */
    public void setAmt(double amt) {
        this.amt = amt;
    }
    
    public String getTransDesc() {
        return pcodes.getTransDesc();
    }
}
