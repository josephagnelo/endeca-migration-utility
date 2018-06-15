//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.05.08 at 02:30:35 PM CDT 
//


package com.kp.contenttemplate.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for NavigationRecordsEditor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NavigationRecordsEditor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SortChoices" type="{http://endeca.com/schema/content-template/2008}SortChoices" minOccurs="0"/>
 *         &lt;element name="RelevanceRankingChoices" type="{http://endeca.com/schema/content-template/2008}RelevanceRankingChoices" minOccurs="0"/>
 *         &lt;element name="RecordsPerPage" type="{http://endeca.com/schema/content-template/2008}RecordsPerPage" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="propertyName" use="required" type="{http://www.w3.org/2001/XMLSchema}Name" />
 *       &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NavigationRecordsEditor", propOrder = {
    "sortChoices",
    "relevanceRankingChoices",
    "recordsPerPage"
})
public class NavigationRecordsEditor {

    @XmlElement(name = "SortChoices")
    protected SortChoices sortChoices;
    @XmlElement(name = "RelevanceRankingChoices")
    protected RelevanceRankingChoices relevanceRankingChoices;
    @XmlElement(name = "RecordsPerPage")
    protected RecordsPerPage recordsPerPage;
    @XmlAttribute(name = "propertyName", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "Name")
    protected java.lang.String propertyName;
    @XmlAttribute(name = "label")
    protected java.lang.String label;

    /**
     * Gets the value of the sortChoices property.
     * 
     * @return
     *     possible object is
     *     {@link SortChoices }
     *     
     */
    public SortChoices getSortChoices() {
        return sortChoices;
    }

    /**
     * Sets the value of the sortChoices property.
     * 
     * @param value
     *     allowed object is
     *     {@link SortChoices }
     *     
     */
    public void setSortChoices(SortChoices value) {
        this.sortChoices = value;
    }

    /**
     * Gets the value of the relevanceRankingChoices property.
     * 
     * @return
     *     possible object is
     *     {@link RelevanceRankingChoices }
     *     
     */
    public RelevanceRankingChoices getRelevanceRankingChoices() {
        return relevanceRankingChoices;
    }

    /**
     * Sets the value of the relevanceRankingChoices property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelevanceRankingChoices }
     *     
     */
    public void setRelevanceRankingChoices(RelevanceRankingChoices value) {
        this.relevanceRankingChoices = value;
    }

    /**
     * Gets the value of the recordsPerPage property.
     * 
     * @return
     *     possible object is
     *     {@link RecordsPerPage }
     *     
     */
    public RecordsPerPage getRecordsPerPage() {
        return recordsPerPage;
    }

    /**
     * Sets the value of the recordsPerPage property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordsPerPage }
     *     
     */
    public void setRecordsPerPage(RecordsPerPage value) {
        this.recordsPerPage = value;
    }

    /**
     * Gets the value of the propertyName property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getPropertyName() {
        return propertyName;
    }

    /**
     * Sets the value of the propertyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setPropertyName(java.lang.String value) {
        this.propertyName = value;
    }

    /**
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setLabel(java.lang.String value) {
        this.label = value;
    }

}
