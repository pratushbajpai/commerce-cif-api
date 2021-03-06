/*******************************************************************************
 *
 *    Copyright 2018 Adobe. All rights reserved.
 *    This file is licensed to you under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License. You may obtain a copy
 *    of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software distributed under
 *    the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR REPRESENTATIONS
 *    OF ANY KIND, either express or implied. See the License for the specific language
 *    governing permissions and limitations under the License.
 *
 ******************************************************************************/

package com.adobe.commerce.cif.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.adobe.commerce.cif.model.health.StatusReport;
import com.adobe.commerce.cif.model.health.HealthResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static com.adobe.commerce.cif.api.Constants.HTTP_OK;
import static com.adobe.commerce.cif.api.Constants.HTTP_OK_MESSAGE;
import static com.adobe.commerce.cif.api.Constants.HTTP_SERVICE_UNAVAILABLE;
import static com.adobe.commerce.cif.api.Constants.HTTP_SERVICE_UNAVAILABLE_MESSAGE;

@Path("/_health")
@Api(value = "/_health")
@Produces(MediaType.APPLICATION_JSON)
public interface HealthStatusApi {

    @GET
    @Path("/")
    @ApiOperation(
        value = "Returns the health status.",
        notes =
            "This endpoint is used to check if underlying scope(s) are healthy." +
            "This is an internal API used for monitoring and must not be used by any storefront." +
            "The endpoint must not return any sensitive information in the response body like IPs or detailed error messages, but instead write them to internal logs." +
            "If all scopes are healthy the implementation should return HTTP Status-Code 200, otherwise it should return HTTP Status-Code 503."
    )
    @ApiResponses(value = {
        @ApiResponse(code = HTTP_OK, message = HTTP_OK_MESSAGE),
        @ApiResponse(code = HTTP_SERVICE_UNAVAILABLE, message = HTTP_SERVICE_UNAVAILABLE_MESSAGE)
    })
    HealthResponse<StatusReport> getHealth(
        @ApiParam(value = "Optional parameter that specifies the scope(s) of the health check. A scope can be a service like Inventory, PIM or Order")
        @QueryParam(value = "scope")
        String scope
    );
}
