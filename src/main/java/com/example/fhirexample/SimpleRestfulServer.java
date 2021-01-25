package com.example.fhirexample;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.RestfulServer;
import com.example.fhirexample.resourceprovider.AllergyIntoleranceResourceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/*")
public class SimpleRestfulServer extends RestfulServer{
    //Initialize
    @Override
    protected void initialize() throws ServletException {
        //create a context for the appropriate version
        //Release 5 is already available so I considered it's worth the try
        setFhirContext(FhirContext.forR5());

        registerProvider(new AllergyIntoleranceResourceProvider());
    }
}
