//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.27 at 09:08:35 PM MESZ 
//


package org.collada._2008._03.colladaschema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 * 			The asset element defines asset management information regarding its parent element.
 * 			
 * 
 * <p>Java class for asset_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="asset_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contributor" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="author_email" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;pattern value="[A-Za-z0-9!#-'\*\+\-/=\?\^_`\{-~]+(\.[A-Za-z0-9!#-'\*\+\-/=\?\^_`\{-~]+)*@[A-Za-z0-9!#-'\*\+\-/=\?\^_`\{-~]+(\.[A-Za-z0-9!#-'\*\+\-/=\?\^_`\{-~]+)*"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="author_website" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *                   &lt;element name="authoring_tool" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="comments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="copyright" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="source_data" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="coverage" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="geographic_location" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                             &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                             &lt;element name="altitude">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>float">
 *                                     &lt;attribute name="mode" type="{http://www.collada.org/2008/03/COLLADASchema}altitude_mode_enum" default="relativeToGround" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="created" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="keywords" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="modified" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="revision" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unit" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="meter" type="{http://www.collada.org/2008/03/COLLADASchema}float_type" default="1.0" />
 *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" default="meter" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="up_axis" type="{http://www.collada.org/2008/03/COLLADASchema}up_axis_enum" minOccurs="0"/>
 *         &lt;element name="extra" type="{http://www.collada.org/2008/03/COLLADASchema}extra_type" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "asset_type", propOrder = {
    "contributor",
    "coverage",
    "created",
    "keywords",
    "modified",
    "revision",
    "subject",
    "title",
    "unit",
    "upAxis",
    "extra"
})
public class AssetType {

    protected List<AssetType.Contributor> contributor;
    protected AssetType.Coverage coverage;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar created;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String keywords;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar modified;
    protected String revision;
    protected String subject;
    protected String title;
    protected AssetType.Unit unit;
    @XmlElement(name = "up_axis", defaultValue = "Y_UP")
    protected UpAxisEnum upAxis;
    protected List<ExtraType> extra;

    /**
     * Gets the value of the contributor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contributor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContributor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssetType.Contributor }
     * 
     * 
     */
    public List<AssetType.Contributor> getContributor() {
        if (contributor == null) {
            contributor = new ArrayList<AssetType.Contributor>();
        }
        return this.contributor;
    }

    /**
     * Gets the value of the coverage property.
     * 
     * @return
     *     possible object is
     *     {@link AssetType.Coverage }
     *     
     */
    public AssetType.Coverage getCoverage() {
        return coverage;
    }

    /**
     * Sets the value of the coverage property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetType.Coverage }
     *     
     */
    public void setCoverage(AssetType.Coverage value) {
        this.coverage = value;
    }

    /**
     * Gets the value of the created property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreated() {
        return created;
    }

    /**
     * Sets the value of the created property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreated(XMLGregorianCalendar value) {
        this.created = value;
    }

    /**
     * Gets the value of the keywords property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * Sets the value of the keywords property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeywords(String value) {
        this.keywords = value;
    }

    /**
     * Gets the value of the modified property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getModified() {
        return modified;
    }

    /**
     * Sets the value of the modified property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setModified(XMLGregorianCalendar value) {
        this.modified = value;
    }

    /**
     * Gets the value of the revision property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevision() {
        return revision;
    }

    /**
     * Sets the value of the revision property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevision(String value) {
        this.revision = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link AssetType.Unit }
     *     
     */
    public AssetType.Unit getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetType.Unit }
     *     
     */
    public void setUnit(AssetType.Unit value) {
        this.unit = value;
    }

    /**
     * Gets the value of the upAxis property.
     * 
     * @return
     *     possible object is
     *     {@link UpAxisEnum }
     *     
     */
    public UpAxisEnum getUpAxis() {
        return upAxis;
    }

    /**
     * Sets the value of the upAxis property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpAxisEnum }
     *     
     */
    public void setUpAxis(UpAxisEnum value) {
        this.upAxis = value;
    }

    /**
     * Gets the value of the extra property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extra property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtra().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtraType }
     * 
     * 
     */
    public List<ExtraType> getExtra() {
        if (extra == null) {
            extra = new ArrayList<ExtraType>();
        }
        return this.extra;
    }


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
     *         &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="author_email" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;pattern value="[A-Za-z0-9!#-'\*\+\-/=\?\^_`\{-~]+(\.[A-Za-z0-9!#-'\*\+\-/=\?\^_`\{-~]+)*@[A-Za-z0-9!#-'\*\+\-/=\?\^_`\{-~]+(\.[A-Za-z0-9!#-'\*\+\-/=\?\^_`\{-~]+)*"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="author_website" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
     *         &lt;element name="authoring_tool" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="comments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="copyright" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="source_data" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
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
        "author",
        "authorEmail",
        "authorWebsite",
        "authoringTool",
        "comments",
        "copyright",
        "sourceData"
    })
    public static class Contributor {

        protected String author;
        @XmlElement(name = "author_email")
        protected String authorEmail;
        @XmlElement(name = "author_website")
        @XmlSchemaType(name = "anyURI")
        protected String authorWebsite;
        @XmlElement(name = "authoring_tool")
        protected String authoringTool;
        protected String comments;
        protected String copyright;
        @XmlElement(name = "source_data")
        @XmlSchemaType(name = "anyURI")
        protected String sourceData;

        /**
         * Gets the value of the author property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAuthor() {
            return author;
        }

        /**
         * Sets the value of the author property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAuthor(String value) {
            this.author = value;
        }

        /**
         * Gets the value of the authorEmail property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAuthorEmail() {
            return authorEmail;
        }

        /**
         * Sets the value of the authorEmail property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAuthorEmail(String value) {
            this.authorEmail = value;
        }

        /**
         * Gets the value of the authorWebsite property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAuthorWebsite() {
            return authorWebsite;
        }

        /**
         * Sets the value of the authorWebsite property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAuthorWebsite(String value) {
            this.authorWebsite = value;
        }

        /**
         * Gets the value of the authoringTool property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAuthoringTool() {
            return authoringTool;
        }

        /**
         * Sets the value of the authoringTool property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAuthoringTool(String value) {
            this.authoringTool = value;
        }

        /**
         * Gets the value of the comments property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getComments() {
            return comments;
        }

        /**
         * Sets the value of the comments property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setComments(String value) {
            this.comments = value;
        }

        /**
         * Gets the value of the copyright property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCopyright() {
            return copyright;
        }

        /**
         * Sets the value of the copyright property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCopyright(String value) {
            this.copyright = value;
        }

        /**
         * Gets the value of the sourceData property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSourceData() {
            return sourceData;
        }

        /**
         * Sets the value of the sourceData property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSourceData(String value) {
            this.sourceData = value;
        }

    }


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
     *         &lt;element name="geographic_location" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                   &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                   &lt;element name="altitude">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>float">
     *                           &lt;attribute name="mode" type="{http://www.collada.org/2008/03/COLLADASchema}altitude_mode_enum" default="relativeToGround" />
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "geographicLocation"
    })
    public static class Coverage {

        @XmlElement(name = "geographic_location")
        protected AssetType.Coverage.GeographicLocation geographicLocation;

        /**
         * Gets the value of the geographicLocation property.
         * 
         * @return
         *     possible object is
         *     {@link AssetType.Coverage.GeographicLocation }
         *     
         */
        public AssetType.Coverage.GeographicLocation getGeographicLocation() {
            return geographicLocation;
        }

        /**
         * Sets the value of the geographicLocation property.
         * 
         * @param value
         *     allowed object is
         *     {@link AssetType.Coverage.GeographicLocation }
         *     
         */
        public void setGeographicLocation(AssetType.Coverage.GeographicLocation value) {
            this.geographicLocation = value;
        }


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
         *         &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *         &lt;element name="altitude">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>float">
         *                 &lt;attribute name="mode" type="{http://www.collada.org/2008/03/COLLADASchema}altitude_mode_enum" default="relativeToGround" />
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
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
            "longitude",
            "latitude",
            "altitude"
        })
        public static class GeographicLocation {

            protected float longitude;
            protected float latitude;
            @XmlElement(required = true)
            protected AssetType.Coverage.GeographicLocation.Altitude altitude;

            /**
             * Gets the value of the longitude property.
             * 
             */
            public float getLongitude() {
                return longitude;
            }

            /**
             * Sets the value of the longitude property.
             * 
             */
            public void setLongitude(float value) {
                this.longitude = value;
            }

            /**
             * Gets the value of the latitude property.
             * 
             */
            public float getLatitude() {
                return latitude;
            }

            /**
             * Sets the value of the latitude property.
             * 
             */
            public void setLatitude(float value) {
                this.latitude = value;
            }

            /**
             * Gets the value of the altitude property.
             * 
             * @return
             *     possible object is
             *     {@link AssetType.Coverage.GeographicLocation.Altitude }
             *     
             */
            public AssetType.Coverage.GeographicLocation.Altitude getAltitude() {
                return altitude;
            }

            /**
             * Sets the value of the altitude property.
             * 
             * @param value
             *     allowed object is
             *     {@link AssetType.Coverage.GeographicLocation.Altitude }
             *     
             */
            public void setAltitude(AssetType.Coverage.GeographicLocation.Altitude value) {
                this.altitude = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;simpleContent>
             *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>float">
             *       &lt;attribute name="mode" type="{http://www.collada.org/2008/03/COLLADASchema}altitude_mode_enum" default="relativeToGround" />
             *     &lt;/extension>
             *   &lt;/simpleContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class Altitude {

                @XmlValue
                protected float value;
                @XmlAttribute
                protected AltitudeModeEnum mode;

                /**
                 * Gets the value of the value property.
                 * 
                 */
                public float getValue() {
                    return value;
                }

                /**
                 * Sets the value of the value property.
                 * 
                 */
                public void setValue(float value) {
                    this.value = value;
                }

                /**
                 * Gets the value of the mode property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link AltitudeModeEnum }
                 *     
                 */
                public AltitudeModeEnum getMode() {
                    if (mode == null) {
                        return AltitudeModeEnum.RELATIVE_TO_GROUND;
                    } else {
                        return mode;
                    }
                }

                /**
                 * Sets the value of the mode property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link AltitudeModeEnum }
                 *     
                 */
                public void setMode(AltitudeModeEnum value) {
                    this.mode = value;
                }

            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="meter" type="{http://www.collada.org/2008/03/COLLADASchema}float_type" default="1.0" />
     *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" default="meter" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Unit {

        @XmlAttribute
        protected Double meter;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlSchemaType(name = "NMTOKEN")
        protected String name;

        /**
         * Gets the value of the meter property.
         * 
         * @return
         *     possible object is
         *     {@link Double }
         *     
         */
        public double getMeter() {
            if (meter == null) {
                return  1.0D;
            } else {
                return meter;
            }
        }

        /**
         * Sets the value of the meter property.
         * 
         * @param value
         *     allowed object is
         *     {@link Double }
         *     
         */
        public void setMeter(Double value) {
            this.meter = value;
        }

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            if (name == null) {
                return "meter";
            } else {
                return name;
            }
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

    }

}