package com.example.fhirexample.resourceprovider;

import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import ca.uhn.fhir.rest.server.exceptions.ResourceVersionConflictException;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r5.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class AllergyIntoleranceResourceProvider implements IResourceProvider {

    long nextID = 1;

    //Basic constructor
    public AllergyIntoleranceResourceProvider() {
    }

    //Constructor to create and add AI to list
    public AllergyIntoleranceResourceProvider(Reference reference) {
        long resourceID = nextID++;

        AllergyIntolerance allergyIntolerance = new AllergyIntolerance();
        allergyIntolerance.setId(Long.toString(resourceID));
        allergyIntolerance.setPatient(reference);
        allergyintolerancemap.put(Long.toString(resourceID), allergyIntolerance);
    }

    //A map to hold all Allergies/Intolerances
    private Map<String, AllergyIntolerance> allergyintolerancemap = new HashMap<String, AllergyIntolerance>();


    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return AllergyIntolerance.class;
    }

    //Search Allergies/Intolerances by Patient
    @Search
    public List<AllergyIntolerance> search(@RequiredParam(name = AllergyIntolerance.SP_PATIENT) StringParam stringParam) {
        List<AllergyIntolerance> allergiesIntolerances;
        allergiesIntolerances = this.searchByPatient(stringParam.getValue());
        return allergiesIntolerances;
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

    //Basic CREATE-method
    @Create
    public MethodOutcome createAllergyIntolerance(
            @ResourceParam AllergyIntolerance ai,
            @ConditionalUrlParam Patient patient) {
        if (patient != null) {
            ai.setPatient(ai.getPatient());
            return new MethodOutcome();
        } else {
            return new MethodOutcome();
        }
    }

    @Update
    public MethodOutcome updateAllergyIntolerance (@IdParam IdType ID, AllergyIntolerance AI) {
        String resourceID = ID.getIdPart();
        String versionID = ID.getVersionIdPart();
        String currentVersion = "1";

        if (!versionID.equals(currentVersion)) {
            throw new ResourceVersionConflictException("Expected version " + currentVersion);
        }

        return new MethodOutcome();
    }

    //Specific search-method for AllergyIntolerance
    private List<AllergyIntolerance> searchByPatient(String patient) {
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
        this.allergyintolerancemap.put("1", allergyIntolerance);
        for (int i = 2; i < 5; i++) {
            allergyIntolerance = new AllergyIntolerance();
            allergyIntolerance.setId(Integer.toString(i));
            allergyIntolerance.addChild("Milk Intolerance");
            allergyIntolerance.addCategory(AllergyIntolerance.AllergyIntoleranceCategory.FOOD);
        }
    }
}