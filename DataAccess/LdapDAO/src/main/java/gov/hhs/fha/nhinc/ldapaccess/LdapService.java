/*
 * TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION
 * Copyright (c) 2008, Nationwide Health Information Network (NHIN) Connect. All rights reserved.
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * - Neither the name of the NHIN Connect Project nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *    END OF TERMS AND CONDITIONS
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.ldapaccess;

import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * LDAP Service object creates DAO instances for user.
 *
 * @author cmatser
 */
public class LdapService {

    public static BusinessUnitDAO getBusinessUnitDAO() {
        String propLoc = PropertyAccessor.getPropertyFileLocation();
        Resource resource = new FileSystemResource(propLoc + "kmrBusinessUnits.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        return (BusinessUnitDAO) factory.getBean("ldapBusinessUnit");
    }

    public static ContactDAO getContactDAO() {
        String propLoc = PropertyAccessor.getPropertyFileLocation();
        Resource resource = new FileSystemResource(propLoc + "kmrContacts.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        return (ContactDAO) factory.getBean("ldapContact");
    }

    public static ContactDAO getContactDAO(String propertiesDir) {
        Resource resource = new FileSystemResource(propertiesDir + "/kmrContacts.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        return (ContactDAO) factory.getBean("ldapContact");
    }

    public static EquipmentDAO getEquipmentDAO() {
        Resource resource = new FileSystemResource(PropertyAccessor.getPropertyFileLocation() + "kmrEquipment.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        return (EquipmentDAO) factory.getBean("ldapEquipment");
    }

    public static LocationDAO getLocationDAO() {
        Resource resource = new FileSystemResource(PropertyAccessor.getPropertyFileLocation() + "kmrLocations.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        return (LocationDAO) factory.getBean("ldapLocation");
    }

    public static RoleDAO getRoleDAO() {
        Resource resource = new FileSystemResource(PropertyAccessor.getPropertyFileLocation() + "kmrRoles.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        return (RoleDAO) factory.getBean("ldapRole");
    }
}