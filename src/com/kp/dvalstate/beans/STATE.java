//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.05.07 at 05:47:48 PM CDT 
//


package com.kp.dvalstate.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="PATH" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="DIM" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="SOURCEID" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "STATE")
public class STATE {

    @XmlAttribute(name = "ID", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String id;
    @XmlAttribute(name = "PATH", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String path;
    @XmlAttribute(name = "DIM", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String dim;
    @XmlAttribute(name = "SOURCEID", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String sourceid;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the path property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPATH() {
        return path;
    }

    /**
     * Sets the value of the path property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPATH(String value) {
        this.path = value;
    }

    /**
     * Gets the value of the dim property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDIM() {
        return dim;
    }

    /**
     * Sets the value of the dim property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDIM(String value) {
        this.dim = value;
    }

    /**
     * Gets the value of the sourceid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSOURCEID() {
        return sourceid;
    }

    /**
     * Sets the value of the sourceid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSOURCEID(String value) {
        this.sourceid = value;
    }

}
