//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.05 at 10:50:17 PM GMT 
//


package com.emiage2018s1.notes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AddNoteDetails" type="{http://emiage2018s1.com/notes}AddNoteDetails"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "addNoteDetails"
})
@XmlRootElement(name = "AddNoteDetailsRequest")
public class AddNoteDetailsRequest {

    @XmlElement(name = "AddNoteDetails", required = true)
    protected AddNoteDetails addNoteDetails;

    /**
     * Gets the value of the addNoteDetails property.
     * 
     * @return
     *     possible object is
     *     {@link AddNoteDetails }
     *     
     */
    public AddNoteDetails getAddNoteDetails() {
        return addNoteDetails;
    }

    /**
     * Sets the value of the addNoteDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddNoteDetails }
     *     
     */
    public void setAddNoteDetails(AddNoteDetails value) {
        this.addNoteDetails = value;
    }

}
