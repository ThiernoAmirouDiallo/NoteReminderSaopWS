//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.02 at 04:59:18 PM GMT 
//


package com.emiage2018s1.notes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddNoteDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddNoteDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="texte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="couleur" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="echeance" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ordre" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddNoteDetails", propOrder = {
    "texte",
    "couleur",
    "echeance",
    "ordre"
})
public class AddNoteDetails {

    @XmlElement(required = true)
    protected String texte;
    @XmlElement(required = true)
    protected String couleur;
    @XmlElement(required = true)
    protected String echeance;
    protected int ordre;

    /**
     * Gets the value of the texte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTexte() {
        return texte;
    }

    /**
     * Sets the value of the texte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTexte(String value) {
        this.texte = value;
    }

    /**
     * Gets the value of the couleur property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCouleur() {
        return couleur;
    }

    /**
     * Sets the value of the couleur property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCouleur(String value) {
        this.couleur = value;
    }

    /**
     * Gets the value of the echeance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEcheance() {
        return echeance;
    }

    /**
     * Sets the value of the echeance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEcheance(String value) {
        this.echeance = value;
    }

    /**
     * Gets the value of the ordre property.
     * 
     */
    public int getOrdre() {
        return ordre;
    }

    /**
     * Sets the value of the ordre property.
     * 
     */
    public void setOrdre(int value) {
        this.ordre = value;
    }

}