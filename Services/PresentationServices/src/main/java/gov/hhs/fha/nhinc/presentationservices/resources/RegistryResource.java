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

package gov.hhs.fha.nhinc.presentationservices.resources;

import gov.hhs.fha.nhinc.kmr.util.SessionUtilities;
import gov.hhs.fha.nhinc.taskmanager.DiseaseRegistryHandler;
import gov.hhs.fha.nhinc.taskmanager.DiseaseRegistryMessage;
import gov.hhs.fha.nhinc.taskmanager.model.Specification;
import gov.hhs.fha.nhinc.taskmanager.model.TaskServiceRef;
import gov.hhs.fha.nhinc.taskmanager.model.TaskType;
import java.util.Date;
import java.util.GregorianCalendar;
import org.restlet.Context;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.resource.Representation;
import org.restlet.resource.StringRepresentation;
import org.restlet.resource.Variant;

/**
 *
 * @author jharby
 */
public class RegistryResource extends BaseResource {
        private String apiName;
    String NL = System.getProperty("line.separator");
    
    public RegistryResource(Context context, Request request, Response response) {
        super(context, request, response);
        apiName = request.getResourceRef().getQueryAsForm().getFirstValue("apiName");
        getVariants().add(new Variant(MediaType.APPLICATION_XML));
        context.getClientDispatcher();
        init(context, request, response);
    }

    @Override
    public void init(Context context, Request request, Response response) {
        SessionUtilities.setCORSHeaders(this);
        super.init(context, request, response);
    }

    @Override
    public Representation represent(Variant variant) throws org.restlet.resource.ResourceException {
        System.out.println("handleMessage");
        String taskTicket = "Test Ticket: " + new Date();
        TaskServiceRef serviceRef = new TaskServiceRef();
        DiseaseRegistryMessage taskMessage = new DiseaseRegistryMessage();
        TaskType task = new TaskType();
        DiseaseRegistryHandler instance = new DiseaseRegistryHandler();
        
        serviceRef.setName("Test service ref");
        serviceRef.setLocation("http://localhost:8080/RegistriesService/RegistriesService");
        serviceRef.setType("ALERT");
        serviceRef.setDescription("Unit test for SMS");
        serviceRef.setServiceParam1("IUV404il71");
        
        taskMessage.setTaskID("1");
        taskMessage.setDiseaseType("1");
        taskMessage.setActionRegister(true);
        GregorianCalendar cal = new GregorianCalendar(2010,1,1);        
        taskMessage.setPatientDOB(cal);
        taskMessage.setPatientName("Jane Doe");
        taskMessage.setPatientId("99990070");
       
        task.setDescription("Test task type");
        task.setName("First test");
        task.setTaskTypeId(1L);
        
        Specification spec = new Specification();
        spec.setName("First spec test");
        spec.setSpecificationId(1L);
        spec.setValue("This is a test spec value");
        spec.setTaskType(task);
        
      //  task.getSpecifications().add(spec);
        getVariants().add(new Variant(MediaType.APPLICATION_XML));
        
        instance.handleMessage(taskTicket, serviceRef, taskMessage, task);

        Representation representation =
        new StringRepresentation("Success", MediaType.APPLICATION_JSON);       
        return representation;
    }
}
