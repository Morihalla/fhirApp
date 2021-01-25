package com.example.fhirExample.ResourceProvider;

import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r5.model.AllergyIntolerance;
import org.hl7.fhir.r5.model.IdType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AllergyIntoleranceResourceProvider implements IResourceProvider {

    //Constructor
    public AllergyIntoleranceResourceProvider(){}

    //A map to hold all Allergies/Intolerances
    private Map<String, AllergyIntolerance> allergyintolerancemap = new HashMap<String,AllergyIntolerance>();

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return AllergyIntolerance.class;
    }

    //Search Allergies/Intolerances by Patient
    @Search
    public List<AllergyIntolerance> search(@RequiredParam(name = AllergyIntolerance.SP_PATIENT) StringParam stringParam) {
        List<AllergyIntolerance> allergieIntolerances = new ArrayList<AllergyIntolerance>();
        allergieIntolerances = this.searchByPatient(stringParam.getValue());
        return allergieIntolerances;
    }

    //Simple READ-method
    @Read
    public AllergyIntolerance read(@IdParam IdType ID) {
        loadMockAIs();
        AllergyIntolerance allergyIntolerance = allergyintolerancemap.get(ID.getId());
        if (allergyIntolerance == null) {
            throw new ResourceNotFoundException(ID);
        }
        return allergyIntolerance;
    }

    //Specific search-method for AllergyIntolerance
    private List<AllergyIntolerance> searchByPatient (String patient) {
        List<AllergyIntolerance> allergyIntoleranceList;
        loadMockAIs();
        allergyIntoleranceList = allergyintolerancemap.values()
                .stream()
                .filter(next ->
                        patient.toLowerCase().equals(next.getPatient().getId().toLowerCase()))
                .collect(Collectors.toList());
        return allergyIntoleranceList;
    }

    //Create AllergyItolerance to test the app
    private void loadMockAIs() {
        AllergyIntolerance allergyIntolerance = new AllergyIntolerance();
        allergyIntolerance.setId("1");
        allergyIntolerance.addChild("Perfume");
        allergyIntolerance.addCategory(AllergyIntolerance.AllergyIntoleranceCategory.ENVIRONMENT);
        this.allergyintolerancemap.put("1",allergyIntolerance);
        for (int i = 2; i < 5; i++) {
            allergyIntolerance = new AllergyIntolerance();
            allergyIntolerance.setId(Integer.toString(i));
            allergyIntolerance.addChild("Milk Intolerance");
            allergyIntolerance.addCategory(AllergyIntolerance.AllergyIntoleranceCategory.FOOD);
        }
    }
}
