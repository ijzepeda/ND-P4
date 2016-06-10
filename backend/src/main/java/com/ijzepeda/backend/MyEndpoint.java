/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.ijzepeda.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;
import com.ijzepeda.JavaLibJokes;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.ijzepeda.com",
                ownerName = "backend.ijzepeda.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "tellJoke")
    public MyBean tellJoke(@Named("name") String passedJoke) {
        MyBean response = new MyBean();
        response.setData("[on GCM Endpoint.tellJoke]"+passedJoke);

        return response;
    }
    /**
     * Retrieves the joke from the java library
     * */
    @ApiMethod(name = "retrieveJoke")
    public MyBean retrieveJoke() {
        MyBean response = new MyBean();
        response.setData("[on GCM Endpoint.retrievesJoke]"+new JavaLibJokes().getJoke());

        return response;
    }
}
