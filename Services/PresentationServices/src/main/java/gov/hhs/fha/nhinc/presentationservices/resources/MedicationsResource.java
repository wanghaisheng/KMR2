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

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.Client;
import org.restlet.Context;

import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Protocol;
import org.restlet.data.Reference;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.data.Status;

import org.restlet.resource.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.StringRepresentation;
import org.restlet.resource.Variant;


/**
 *
 * @author markpitman
 */
public class MedicationsResource extends BaseResource
{

    private String patientId;
    private String toDate = "20110630";
    private String fromDate = "20090101";
    private String apiKey;
    private String jsonRequestString = "";
    private int version = 2;

    public MedicationsResource(Context context, Request request,
        Response response)
    {
        super(context, request, response);

        try
        {
            String query = request.getResourceRef().getQueryAsForm()
                .getQueryString();

            //      System.out.println("query: "+query);
            if (checkApiCaller(query) != true)
            {
                getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);

                return;
            }

        }
        catch (Exception e)
        {
            getResponse().setStatus(Status.SERVER_ERROR_INTERNAL, e);

            return;
        }


        // This representation has only one type of representation.
        getVariants().add(new Variant(MediaType.APPLICATION_JSON));

        String apikey = request.getResourceRef().getQueryAsForm().getFirstValue(
                "apiKey");

        if (apikey != null)
        {
            this.apiKey = apikey;
        }

        patientId = this.getParameter(request, "patientId", "");
        fromDate = this.getDateParameter(request, "fromDate", "Patient", "Medications", "Begin");
        toDate = this.getDateParameter(request, "toDate", "Patient", "Medications", "End");

        jsonRequestString =
            "{getMedicationFacts:{criteria:{queryId:\"20100411011010\",\"senderId\":\"Adapter Assembly Service\",\"interactionId\":\"QUPC_IN043100UV\",\"triggerEventCode\":\"QUPC_TE043100UV0\",\"patientId\":\"" +
            patientId +
            "\",\"careProvisionCode\":\"HISTMEDLIST\",\"careRecordStartTimePeriod\":\"" +
            fromDate + "\",\"careRecordEndTimePeriod\":\"" + toDate + "\"}}}";
    }

    /**
     * Returns a full representation for a given variant.
     */
    @Override public Representation represent(Variant variant)
        throws ResourceException
    {

        String endPoint;

        if (version == 1)
        {
            jsonRequestString =
                "{getMedicationFacts:{criteria:{queryId:\"20100411011010\",\"senderId\":\"Adapter Assembly Service\",\"interactionId\":\"QUPC_IN043100UV\",\"triggerEventCode\":\"QUPC_TE043100UV0\",\"patientId\":\"" +
                patientId +
                "\",\"careProvisionCode\":\"HISTMEDLIST\",\"careRecordStartTimePeriod\":\"" +
                fromDate + "\",\"careRecordEndTimePeriod\":\"" + toDate +
                "\"}}}";

            endPoint = this.getProperty("JSONPojoFactServiceEndpoint");

        }
        else
        {
            jsonRequestString =
                "{\"getMedicationFacts\":{\"criteria\":{\"queryId\":\"20100411011010\",\"senderId\":\"Adapter Assembly Service\",\"interactionId\":\"QUPC_IN043100UV\",\"triggerEventCode\":\"QUPC_TE043100UV01\","+
                "\"careRecordPayload\":{\"patientId\":\"" + patientId +
                "\",\"careProvisionCode\":\"HISTMEDLIST\",\"careRecordStartTimePeriod\":\"" +
                fromDate + "\",\"careRecordEndTimePeriod\":\"" + toDate +
                "\"}}}}";

            endPoint = this.getProperty("JSONPojoFactServiceEndpointV2");

        }

        try
        {
            Client client = new Client(Protocol.HTTP);
            client.setConnectTimeout(10);

            Request request = new Request(Method.POST, new Reference(endPoint),
                    new StringRepresentation(jsonRequestString));
            Response response = client.handle(request);

            if (response.getStatus().isSuccess())
            {
                String rep = response.getEntity().getText();
                JSONObject jo = new JSONObject(rep);
                Object omf = jo.get("medicationFact");
                if (!(omf instanceof JSONArray))
                {
                    JSONArray joa = new JSONArray();
                    joa.put(omf);
                    jo.remove("medicationFact");
                    jo.put("medicationFact", joa);
                    rep = jo.toString();
                }
                Representation representation = new StringRepresentation(
                        rep,
                        MediaType.APPLICATION_JSON);

                return representation;
            }
            else
            {
                throw new ResourceException(Status.SERVER_ERROR_INTERNAL,
                    "Error during call to JSONPojoFactServiceEndpoint, Status = " +
                    response.getStatus().getDescription());
            }
        }
        catch (ResourceException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            Logger.getLogger(AllergiesResource.class.getName()).log(
                Level.SEVERE, null, e);
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL,
                "Error calling JSONPojoFactServiceEndpoint", e);
        }


    }
}