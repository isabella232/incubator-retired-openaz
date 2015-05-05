/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */

/*
 *                        AT&T - PROPRIETARY
 *          THIS FILE CONTAINS PROPRIETARY INFORMATION OF
 *        AT&T AND IS NOT TO BE DISCLOSED OR USED EXCEPT IN
 *             ACCORDANCE WITH APPLICABLE AGREEMENTS.
 *
 *          Copyright (c) 2014 AT&T Knowledge Ventures
 *              Unpublished and Not for Publication
 *                     All Rights Reserved
 */
package com.att.research.xacml.api.pap;

import java.util.Set;

import com.att.research.xacml.std.pap.StdPDPStatus;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/*
 * The following allows us to use Jackson to convert sub-types of this type into JSON and back to objects.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "PDPStatusType")
@JsonSubTypes({
    @Type(value = StdPDPStatus.class, name = "StdPDPStatus")
})
public interface PDPStatus {

    public enum Status {
        UP_TO_DATE,
        OUT_OF_SYNCH,
        LOAD_ERRORS,
        UPDATING_CONFIGURATION,
        LAST_UPDATE_FAILED,
        UNKNOWN,
        NO_SUCH_HOST,
        CANNOT_CONNECT
    }

    public Status getStatus();

    public Set<String> getLoadErrors();

    public Set<String> getLoadWarnings();

    public Set<PDPPolicy> getLoadedPolicies();

    public Set<PDPPolicy> getLoadedRootPolicies();

    public Set<PDPPolicy> getFailedPolicies();

    public boolean policiesOK();

    public Set<PDPPIPConfig> getLoadedPipConfigs();

    public Set<PDPPIPConfig> getFailedPipConfigs();

    public boolean pipConfigOK();

    public boolean isOk();

}
