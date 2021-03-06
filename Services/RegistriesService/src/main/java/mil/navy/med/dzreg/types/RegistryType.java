
package mil.navy.med.dzreg.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegistryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegistryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="registryId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="registryDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegistryType", propOrder = {
    "registryId",
    "registryDesc"
})
@XmlSeeAlso({
    RegistryProfileType.class
})
public class RegistryType {

    protected int registryId;
    protected String registryDesc;

    /**
     * Gets the value of the registryId property.
     * 
     */
    public int getRegistryId() {
        return registryId;
    }

    /**
     * Sets the value of the registryId property.
     * 
     */
    public void setRegistryId(int value) {
        this.registryId = value;
    }

    /**
     * Gets the value of the registryDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistryDesc() {
        return registryDesc;
    }

    /**
     * Sets the value of the registryDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistryDesc(String value) {
        this.registryDesc = value;
    }

}
