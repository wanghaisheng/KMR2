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
 * @author jharby
 * 
 * Teets for insert and update alert. Keep ignored unless necessary because
 * they change data in the db.
 */
package gov.hhs.fha.nhinc.displaydataaggregator;

import gov.hhs.fha.nhinc.aggregator.DisplayDataAggregatorPortType;
import javax.xml.ws.BindingProvider;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jharby
 */
public class UpdateAlertTest {

    public UpdateAlertTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testInsertAlert() {
        DisplayDataAggregatorPortType port = getPort();
//        InsertAlertRequestType irequest = new InsertAlertRequestType();
//        irequest.setAlertOriginator("TESTORIGINATOR2");
//        irequest.setAtId("3");
//        irequest.setDescription("BLACKJACK!");
//        irequest.setPayload("TEST PAYLOAD INSERT ALERT - I'M ALL IN");
//        irequest.setProviderId("1");
//        irequest.setPtUnitNumber("99990071");
//        irequest.setTicketId(3);
//        irequest.setTicketUniqueId("9990071");
//        irequest.setTimestamp(new Date().toString());
//        irequest.setType("ALERT");
//        InsertAlertResponseType iresponse = port.insertAlert(irequest);
//        System.out.println("ALERT INSERT RESPONSE IS: " + iresponse.getStatus());
    }

    @Test
    public void testUpdateAlert() {
//        DisplayDataAggregatorPortType port = getPort();
//        UpdateAlertRequestType request = new UpdateAlertRequestType();
//        request.setTicketId(3);
//        request.setPayload("UPDATE PAYLOAD " + new Date());
//        request.setTimestamp("2011-04-21 15:30:00");
//        request.setDescription("UPDATE DESCRIPTION");
//        UpdateAlertResponseType response = port.updateAlert(request);
//        System.out.println("UPDATE ALERT RESPONSE IS: " + response.getStatus());
    }

    private DisplayDataAggregatorPortType getPort() {
        gov.hhs.fha.nhinc.aggregator.DisplayDataAggregator service =
                    new gov.hhs.fha.nhinc.aggregator.DisplayDataAggregator();
        DisplayDataAggregatorPortType port = service.getDisplayDataAggregatorPortSoap11();
        ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, 
             "http://referenceadaptor:8080/DisplayDataAggregatorWeb/DisplayDataAggregator");
            //    "http://localhost:8080/DisplayDataAggregatorWeb/DisplayDataAggregator");
        return port;
    }
}
