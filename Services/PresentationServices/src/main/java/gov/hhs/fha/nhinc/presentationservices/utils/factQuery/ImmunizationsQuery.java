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
package gov.hhs.fha.nhinc.presentationservices.utils.factQuery;

import gov.hhs.fha.nhinc.kmr.util.CommonUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author nhin
 */
public class ImmunizationsQuery {
    
    private String classname = "\n"+this.getClass().getName()+":";
    private static ImmunizationsQuery instance = null;
    
    public static ImmunizationsQuery getInstance() {
        synchronized (ImmunizationsQuery.class) {
            if (instance == null) {
                instance = new ImmunizationsQuery();
            }
        }
        return instance;
    }
    
    public String createResponse(Map args) throws FileNotFoundException{
        
        String domain = (String) args.get("domain");
        String responseType = (String) args.get("responseType");
        String code = (String) args.get("code");
        String codeSystemCode = (String) args.get("codeSystemCode");
        String sectionId = (String) args.get("sectionId");

        //--------- DBG ONLY - REMOVE -----------------
        String itemId = (String) args.get("itemId");
        if (responseType.equalsIgnoreCase("detail"))
            return this.getStubbedXMLDetailData(itemId);
        else
            return this.getStubbedXMLListData();
        //--------- DBG ONLY - REMOVE -----------------



        //TODO - Integrate DSA Fact service call.
        //1. Call DSA to request this domain facts, for this patientId.
        //2. Transform DSA FAct response xml --> GUI Fact xml.
        //3. parse GUI Fact xml --> GUI Fact xml
        //
        //4. If (responseType == list)
        //      GUI Fact xml --> GUI List xml for this domain
        //      GUI List xml --> GUI json reponse.
        //   Else
        //      GUI Fact xml --> GUI Detail xml per domain, itemId //NOTE: IS THERE UNIQUE ID FOR EACH fact entry?
        //      GUI Detail xml --> GUI json reponse.


    }
        
    //TBD: Map Facts from DSA
    //
    private String mapFactsXML() {
        return this.classname+"KMR Fact mapping TBD";
    }
    
    public String getStubbedXMLDetailData(String itemId) throws FileNotFoundException {
        
        String id = itemId;
        if (CommonUtil.strNullorEmpty(itemId)) {
System.out.println(classname+":No itemId given. DEFAULTING to 10");
            id = "10";
        }
        
        String filename = "/home/nhin/Properties/facts/data/getPatientData-Immunizations_Detail_"+id+".json";
        System.out.println("PULLING detial STATIC DATA:  "+filename);
        
        String text = new Scanner( new File(filename) ).useDelimiter("\\A").next();
        return text;        
    }
    
    public String getStubbedXMLListData() throws FileNotFoundException {
        String filename = "/home/nhin/Properties/facts/data/getPatientData-Immunizations_List.json";
        System.out.println("PULLING list STATIC DATA:  "+filename);

        String text = new Scanner( new File(filename) ).useDelimiter("\\A").next();
        return text;
        

    }


    
    
}
