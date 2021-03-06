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

package gov.hhs.fha.nhinc.taskmanager;

import gov.hhs.fha.nhinc.taskmanager.model.TaskServiceRef;
import gov.hhs.fha.nhinc.taskmanager.model.TaskType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Handle Create Lab Order task events.  Forward to mirth.
 *
 * @author cmatser
 */
public class SlotRequestHandler {

    /** Failure code. */
    public static final String RESPONSE_FAILURE = "-1";

    /** Logging. */
    private static Log log = LogFactory.getLog(SlotRequestHandler.class);

    /**
     * Process task.
     */
    public void handleMessage(String taskTicket, TaskServiceRef serviceRef, TaskMessage taskMessage, TaskType task) {
        SlotRequestMessage apptMessage = null;

        log.debug("Handling schedule appointment task.");

        if (taskMessage instanceof SlotRequestMessage) {
            apptMessage = (SlotRequestMessage) taskMessage;
        }

        if (apptMessage == null) {
            log.error("Error, invalid schedule appointment task for ticket: " + taskTicket);
            return;
        }

        //Same interfaces as for orders
//        try { // This code block invokes the Orders:acceptMessage operation on web service
//            org.mirth.orders._Proxy7_Stub stub =
//                (org.mirth.orders._Proxy7_Stub)
//                    new org.mirth.orders._Proxy7Service_Impl().getPort(
//                        org.mirth.orders._Proxy7.class);
//            stub._setProperty(
//                org.mirth.orders._Proxy7_Stub.ENDPOINT_ADDRESS_PROPERTY,
//                serviceRef.getLocation());
//            stub.acceptMessage(apptMessage.getSlotRequest());
//
//            log.debug("Appointment request sent to mirth, ticket: " + taskTicket);
//        } catch(Exception e) {
//            log.error("Error sending appointment request to mirth, ticket: " + taskTicket, e);
//        }

    }

}