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

import java.util.List;
import java.util.Map;

/**
 *
 * @author jharby
 */
public class ParameterValidator {

    private Map<String, Object> fieldMap;

    public ParameterValidator(Map fieldMap) {
        this.fieldMap = fieldMap;
    }

    public String validateMissingOrEmpty() {
        if (fieldMap.isEmpty()) {
            return "No fields in map to validate";
        }
        StringBuilder failures = new StringBuilder();
        for (String name : fieldMap.keySet()) {
            if (fieldMap.get(name) == null) {
                failures.append(name + " ");
                continue;
            }
            if (fieldMap.get(name) instanceof java.lang.String) {
                String value = (String) fieldMap.get(name);
                if (value.isEmpty()) {
                    failures.append(name + " ");
                }
            }
            else if(fieldMap.get(name) instanceof java.util.List) {
                List value = (List) fieldMap.get(name);
                if (value == null) {
                    failures.append(name + " ");
                }
            }
        }
        return failures.toString();
    }
}
