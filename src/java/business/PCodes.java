/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author raefo
 */
@Entity
@Table(name="tblCodes")
public class PCodes {
    
    @Id
    @Column(name="TransCd")
    private String transCode;
    
    @Column(name="TransType")
    private String transType;
    
    @Column(name="TransDesc")
    private String transDesc;
    
    public PCodes() {
        transCode = "";
        transType = "";
        transDesc = "";
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
     * @return the transDesc
     */
    public String getTransDesc() {
        return transDesc;
    }

    /**
     * @param transDesc the transDesc to set
     */
    public void setTransDesc(String transDesc) {
        this.transDesc = transDesc;
    }
}
