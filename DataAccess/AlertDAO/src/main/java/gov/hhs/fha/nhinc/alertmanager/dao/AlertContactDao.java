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

package gov.hhs.fha.nhinc.alertmanager.dao;

import gov.hhs.fha.nhinc.alertmanager.model.AlertContact;
import gov.hhs.fha.nhinc.alertmanager.util.HibernateUtil;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author jharby
 */
public class AlertContactDao {
    
    ObjectDao<AlertContact> objectDao = new ObjectDao();

    Log log = LogFactory.getLog(AlertContactDao.class);

    /**
     * Save a record to the AlertContact table in the database.
     * Insert if id is null. Update otherwise.
     *
     * @param alertContact AlertContact object to save.
     */
    public void save(AlertContact alertContact) {
        log.debug("Performing alertContact save");

        try {
            objectDao.save(alertContact);
        }
        catch (Throwable t) {
            log.error("Failure during object save.", t);
        }

        log.debug("Completed alertContact save");
    }

    /**
     * Retrieves all alertContacts
     *
     * @return All alertContact records
     */
    @SuppressWarnings("unchecked")
    public List<AlertContact> findAll() {
        List<AlertContact> alertContact = null;

        log.debug("Performing retrieve of all AlertContacts");

        try {
            alertContact = objectDao.findAll(AlertContact.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findAll", t);
        }

        log.debug("Completed AlertContact retrieve all");

        return alertContact;
    }


        /**
     * Retrieves all alertContacts
     *
     * @return All alertContact records
     */
    @SuppressWarnings("unchecked")
    public List<AlertContact> findAllByProvId(String providerId) {
        List<AlertContact> alertContacts = null;

        log.debug("Performing retrieve of all AlertContacts");

        try {
            alertContacts = findProviders(providerId);
        }
        catch (Throwable t) {
            log.error("Failure during object findAll", t);
        }

        log.debug("Completed AlertContact retrieve all");

        return alertContacts;
    }

    private List<AlertContact> findProviders(String providerId) throws Exception {

        List<AlertContact> entities = null;
        Session sess = null;
        try
        {
            SessionFactory fact = HibernateUtil.getSessionFactory();
            if (fact != null)
            {
                sess = fact.openSession();
                if (sess != null)
                {
                    Criteria criteria = sess.createCriteria(AlertContact.class);
                    criteria.add( Restrictions.eq("providerId", providerId ) );
                    entities = criteria.list();
                }
                else
                {
                    throw new Exception("Failed to obtain a session from the sessionFactory");
                }
            }
            else
            {
                throw new Exception("Session factory was null");
            }
        }
        finally
        {
            if (sess != null)
            {
                try
                {
                    sess.close();
                }
                catch (Throwable t)
                {
                    throw new Exception("Failed to close session: " + t.getMessage(), t);
                }
            }
        }
        return entities;
    }

}
