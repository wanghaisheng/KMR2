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

package gov.hhs.fha.nhinc.presentationservices.helpers;


import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessException;
import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessor;
import java.util.Properties;


/**
 * @author  Jerry Goodnough
 */
public class PropertyHelper
{

    /** DOCUMENT ME! */
    private static PropertyHelper singleton = new PropertyHelper();


    /** DOCUMENT ME! */
    //private Properties properties = null;
    //private long lastLoad=0;
    //private long propRefresh=1000*60;

    private static String PROPERTY_FILE = "PresentationServices";
    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static PropertyHelper getPropertyHelper()
    {
        return singleton;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   s  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public String getProperty(String s)
    {

        String out;
        try
        {
            out = PropertyAccessor.getProperty(
                    PROPERTY_FILE,
                    s);
        }
        catch(PropertyAccessException e)
        {
            out ="";

        }
        return out;

    }

    /**
     * DOCUMENT ME!
     *
     * @param   s  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public float getPropertyAsFloat(String s)
    {
        return Float.parseFloat(getProperty(s));
    }

    /**
     * DOCUMENT ME!
     *
     * @param   s  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public int getPropertyAsInt(String s)
    {


        return Integer.parseInt(getProperty(s));
    }

    public boolean getPropertyAsBoolean(String s)
    {

        return Boolean.parseBoolean(getProperty(s));
    }

}
