//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.27 at 09:08:35 PM MESZ 
//


package org.collada._2008._03.colladaschema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for gles_texenv_mode_enum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="gles_texenv_mode_enum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="REPLACE"/>
 *     &lt;enumeration value="MODULATE"/>
 *     &lt;enumeration value="DECAL"/>
 *     &lt;enumeration value="BLEND"/>
 *     &lt;enumeration value="ADD"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "gles_texenv_mode_enum")
@XmlEnum
public enum GlesTexenvModeEnum {

    REPLACE,
    MODULATE,
    DECAL,
    BLEND,
    ADD;

    public String value() {
        return name();
    }

    public static GlesTexenvModeEnum fromValue(String v) {
        return valueOf(v);
    }

}