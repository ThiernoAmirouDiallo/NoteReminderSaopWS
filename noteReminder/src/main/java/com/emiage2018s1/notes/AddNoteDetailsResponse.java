//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.06 at 09:45:37 PM GMT 
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
 *         &lt;element name="NoteDetails" type="{http://emiage2018s1.com/notes}NoteDetails"/>
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
    "noteDetails"
})
@XmlRootElement(name = "AddNoteDetailsResponse")
public class AddNoteDetailsResponse {

    @XmlElement(name = "NoteDetails", required = true)
    protected NoteDetails noteDetails;

    /**
     * Gets the value of the noteDetails property.
     * 
     * @return
     *     possible object is
     *     {@link NoteDetails }
     *     
     */
    public NoteDetails getNoteDetails() {
        return noteDetails;
    }

    /**
     * Sets the value of the noteDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link NoteDetails }
     *     
     */
    public void setNoteDetails(NoteDetails value) {
        this.noteDetails = value;
    }

}
