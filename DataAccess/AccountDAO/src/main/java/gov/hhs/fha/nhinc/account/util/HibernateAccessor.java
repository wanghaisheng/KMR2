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

package gov.hhs.fha.nhinc.account.util;
import java.io.File;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *
 * @author dunnek
 */
public class HibernateAccessor
{
    private static Log log = LogFactory.getLog(HibernateAccessor.class);
    private static final String CRLF = System.getProperty("line.separator");
    private static String m_sPropertyFileDir = "";
    private static boolean m_bFailedToLoadEnvVar = false;

    static
    {
        String sValue = PropertyAccessor.getPropertyFileLocation();
        if ((sValue != null) && (sValue.length() > 0))
        {
            // Set it up so that we always have a "/" at the end - in case
            //------------------------------------------------------------
            if ((sValue.endsWith("/")) || (sValue.endsWith("\\")))
            {
                m_sPropertyFileDir = sValue;
            }
            else
            {
                String sFileSeparator = System.getProperty("file.separator");
                m_sPropertyFileDir = sValue + sFileSeparator;
            }
        }
        else
        {
              m_sPropertyFileDir = "/home/nhin/Properties/";
//            log.error("Failed to load Hibernate Directory");
//            m_bFailedToLoadEnvVar = true;
        }
    }
    public static File getHibernateFile(String hibernateFileName)
           throws PropertyAccessException
    {
        String sFileSeparator = System.getProperty("file.separator");
        checkEnvVarSet();

        File result = new File(m_sPropertyFileDir + "hibernate" +
                sFileSeparator + hibernateFileName );

        if(result == null)
        {
            throw new PropertyAccessException("Unable to locate " +
                    hibernateFileName);
        }
        return result;
    }
    private static boolean checkEnvVarSet()
        throws PropertyAccessException
    {
        if (m_bFailedToLoadEnvVar)
        {
            throw new PropertyAccessException("Failed to load Hibernate Directory");
        }

        return true;            // We only get here if the env variable was loaded.
    }
}
