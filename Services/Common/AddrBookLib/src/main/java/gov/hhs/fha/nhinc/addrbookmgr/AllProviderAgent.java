/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.addrbookmgr;

import gov.hhs.fha.nhinc.addrbook.model.AddressItem;
import gov.hhs.fha.nhinc.ldapaccess.ContactDAO;
import gov.hhs.fha.nhinc.ldapaccess.ContactDTO;
import gov.hhs.fha.nhinc.ldapaccess.LdapService;
import java.util.List;

/**
 * This address agent is intended for users who are providers as they
 * can email any other provider.
 *
 * @author cmatser
 */
public class AllProviderAgent extends AddrBookAgent {

    public static final String LOOKUP_ALL_PROVIDERS = "businessCategory=staff";

    public void run() {
        //Find the contacts for my class type
        for (AddressItem addr : startAddrs) {
            if (!CLASS_ALL_PROVIDER.equals(addr.getClassId())) {
                continue;
            }

            //Get all providers in ldap
            ContactDAO contactDAO = LdapService.getContactDAO();
            List<ContactDTO> contacts = contactDAO.findContact(LOOKUP_ALL_PROVIDERS);
            for (ContactDTO contact : contacts) {
                AddressItem newAddr = new AddressItem();
                newAddr.setAddressId(addr.getAddressId());
                newAddr.setUserId(addr.getUserId());
                newAddr.setClassId(addr.getClassId());
                newAddr.setName(contact.getDisplayName() == null ? "" : contact.getDisplayName());
                newAddr.setContactId("cn=" + contact.getCommonName());
                getAddressFinish().add(newAddr);
            }

        }
    }

}
