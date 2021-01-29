package com.example.fhirexample.resourceprovider.tryOutClasses;

import java.util.*;

import ca.uhn.fhir.jpa.provider.r5.*;
import ca.uhn.fhir.jpa.searchparam.SearchParameterMap;
import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.model.api.annotation.*;
import org.hl7.fhir.r5.model.*;
import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.param.*;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.api.SummaryEnum;
import ca.uhn.fhir.rest.api.SearchTotalModeEnum;

public class AllergyIntoleranceResourceProvider2 extends
        JpaResourceProviderR5<AllergyIntolerance>
        {

        @Override
        public Class<AllergyIntolerance> getResourceType() {
                return AllergyIntolerance.class;
        }

        @Search(allowUnknownParams=true)
       public ca.uhn.fhir.rest.api.server.IBundleProvider search(
                        javax.servlet.http.HttpServletRequest theServletRequest,
                        javax.servlet.http.HttpServletResponse theServletResponse,

                        ca.uhn.fhir.rest.api.server.RequestDetails theRequestDetails,

                        @Description(shortDefinition="Search the contents of the resource's data using a filter")
                        @OptionalParam(name=ca.uhn.fhir.rest.api.Constants.PARAM_FILTER)
                        StringAndListParam theFtFilter,

                        @Description(shortDefinition="Search the contents of the resource's data using a fulltext search")
                        @OptionalParam(name=ca.uhn.fhir.rest.api.Constants.PARAM_CONTENT)
                        StringAndListParam theFtContent,

                        @Description(shortDefinition="Search the contents of the resource's narrative using a fulltext search")
                        @OptionalParam(name=ca.uhn.fhir.rest.api.Constants.PARAM_TEXT)
                        StringAndListParam theFtText,

                        @Description(shortDefinition="Search for resources which have the given tag")
                        @OptionalParam(name=ca.uhn.fhir.rest.api.Constants.PARAM_TAG)
                        TokenAndListParam theSearchForTag,

                        @Description(shortDefinition="Search for resources which have the given security labels")
                        @OptionalParam(name=ca.uhn.fhir.rest.api.Constants.PARAM_SECURITY)
                        TokenAndListParam theSearchForSecurity,

                        @Description(shortDefinition="Search for resources which have the given profile")
                        @OptionalParam(name=ca.uhn.fhir.rest.api.Constants.PARAM_PROFILE)
                        UriAndListParam theSearchForProfile,

                        @Description(shortDefinition="Search for resources which have the given source value (Resource.meta.source)")
                        @OptionalParam(name=ca.uhn.fhir.rest.api.Constants.PARAM_SOURCE)
                        UriAndListParam theSearchForSource,

                        @Description(shortDefinition="Return resources linked to by the given target")
                        @OptionalParam(name="_has")
                        HasAndListParam theHas,


                        @Description(shortDefinition="The ID of the resource")
                        @OptionalParam(name="_id")
                        TokenAndListParam the_id,

                        @Description(shortDefinition="The language of the resource")
                        @OptionalParam(name="_language")
                        StringAndListParam the_language,

                        @Description(shortDefinition="Search on the narrative of the resource")
                        @OptionalParam(name="_text")
                        StringAndListParam the_text,

                        @Description(shortDefinition="Source of the information about the allergy")
                        @OptionalParam(name="asserter", targetTypes={  } )
                        ReferenceAndListParam theAsserter,

                        @Description(shortDefinition="food | medication | environment | biologic")
                        @OptionalParam(name="category")
                        TokenAndListParam theCategory,

                        @Description(shortDefinition="active | inactive | resolved")
                        @OptionalParam(name="clinical-status")
                        TokenAndListParam theClinical_status,

                        @Description(shortDefinition="Multiple Resources: * [AllergyIntolerance](allergyintolerance.html): Code that identifies the allergy or intolerance* [Condition](condition.html): Code for the condition* [DeviceRequest](devicerequest.html): Code for what is being requested/ordered* [DiagnosticReport](diagnosticreport.html): The code for the report, as opposed to codes for the atomic results, which are the names on the observation resource referred to from the result* [FamilyMemberHistory](familymemberhistory.html): A search by a condition code* [List](list.html): What the purpose of this list is* [Medication](medication.html): Returns medications for a specific code* [MedicationAdministration](medicationadministration.html): Return administrations of this medication code* [MedicationDispense](medicationdispense.html): Returns dispenses of this medicine code* [MedicationRequest](medicationrequest.html): Return prescriptions of this medication code* [MedicationUsage](medicationusage.html): Return statements of this medication code* [Observation](observation.html): The code of the observation type* [Procedure](procedure.html): A code to identify a  procedure* [ServiceRequest](servicerequest.html): What is being requested/ordered")
                        @OptionalParam(name="code")
                        TokenAndListParam theCode,

                        @Description(shortDefinition="low | high | unable-to-assess")
                        @OptionalParam(name="criticality")
                        TokenAndListParam theCriticality,

                        @Description(shortDefinition="Multiple Resources: * [AllergyIntolerance](allergyintolerance.html): Date first version of the resource instance was recorded* [CarePlan](careplan.html): Time period plan covers* [CareTeam](careteam.html): A date within the coverage time period.* [ClinicalImpression](clinicalimpression.html): When the assessment was documented* [Composition](composition.html): Composition editing time* [Consent](consent.html): When consent was agreed to* [DiagnosticReport](diagnosticreport.html): The clinically relevant time of the report* [Encounter](encounter.html): A date within the period the Encounter lasted* [EpisodeOfCare](episodeofcare.html): The provided date search value falls within the episode of care's period* [FamilyMemberHistory](familymemberhistory.html): When history was recorded or last updated* [Flag](flag.html): Time period when flag is active* [Immunization](immunization.html): Vaccination  (non)-Administration Date* [List](list.html): When the list was prepared* [Observation](observation.html): Obtained date/time. If the obtained element is a period, a date that falls in the period* [Procedure](procedure.html): When the procedure occurred or is occurring* [RiskAssessment](riskassessment.html): When was assessment made?* [SupplyRequest](supplyrequest.html): When the request was made")
                        @OptionalParam(name="date")
                        DateRangeParam theDate,

                        @Description(shortDefinition="Multiple Resources: * [AllergyIntolerance](allergyintolerance.html): External ids for this item* [CarePlan](careplan.html): External Ids for this plan* [CareTeam](careteam.html): External Ids for this team* [Composition](composition.html): Version-independent identifier for the Composition* [Condition](condition.html): A unique identifier of the condition record* [Consent](consent.html): Identifier for this record (external references)* [DetectedIssue](detectedissue.html): Unique id for the detected issue* [DeviceRequest](devicerequest.html): Business identifier for request/order* [DiagnosticReport](diagnosticreport.html): An identifier for the report* [DocumentManifest](documentmanifest.html): Unique Identifier for the set of documents* [DocumentReference](documentreference.html): Identifier of the attachment binary* [Encounter](encounter.html): Identifier(s) by which this encounter is known* [EpisodeOfCare](episodeofcare.html): Business Identifier(s) relevant for this EpisodeOfCare* [FamilyMemberHistory](familymemberhistory.html): A search by a record identifier* [Goal](goal.html): External Ids for this goal* [ImagingStudy](imagingstudy.html): Identifiers for the Study, such as DICOM Study Instance UID and Accession number* [Immunization](immunization.html): Business identifier* [List](list.html): Business identifier* [MedicationAdministration](medicationadministration.html): Return administrations with this external identifier* [MedicationDispense](medicationdispense.html): Returns dispenses with this external identifier* [MedicationRequest](medicationrequest.html): Return prescriptions with this external identifier* [MedicationUsage](medicationusage.html): Return statements with this external identifier* [NutritionOrder](nutritionorder.html): Return nutrition orders with this external identifier* [Observation](observation.html): The unique id for a particular observation* [Procedure](procedure.html): A unique identifier for a procedure* [RiskAssessment](riskassessment.html): Unique identifier for the assessment* [ServiceRequest](servicerequest.html): Identifiers assigned to this order* [SupplyDelivery](supplydelivery.html): External identifier* [SupplyRequest](supplyrequest.html): Business Identifier for SupplyRequest* [VisionPrescription](visionprescription.html): Return prescriptions with this external identifier")
                        @OptionalParam(name="identifier")
                        TokenAndListParam theIdentifier,

                        @Description(shortDefinition="Date(/time) of last known occurrence of a reaction")
                        @OptionalParam(name="last-date")
                        DateRangeParam theLast_date,

                        @Description(shortDefinition="Clinical symptoms/signs associated with the Event")
                        @OptionalParam(name="manifestation")
                        TokenAndListParam theManifestation,

                        @Description(shortDefinition="Multiple Resources: * [AllergyIntolerance](allergyintolerance.html): Who the sensitivity is for* [CarePlan](careplan.html): Who the care plan is for* [CareTeam](careteam.html): Who care team is for* [ClinicalImpression](clinicalimpression.html): Patient or group assessed* [Composition](composition.html): Who and/or what the composition is about* [Condition](condition.html): Who has the condition?* [Consent](consent.html): Who the consent applies to* [DetectedIssue](detectedissue.html): Associated patient* [DeviceRequest](devicerequest.html): Individual the service is ordered for* [DeviceUseStatement](deviceusestatement.html): Search by subject - a patient* [DiagnosticReport](diagnosticreport.html): The subject of the report if a patient* [DocumentManifest](documentmanifest.html): The subject of the set of documents* [DocumentReference](documentreference.html): Who/what is the subject of the document* [Encounter](encounter.html): The patient or group present at the encounter* [EpisodeOfCare](episodeofcare.html): The patient who is the focus of this episode of care* [FamilyMemberHistory](familymemberhistory.html): The identity of a subject to list family member history items for* [Flag](flag.html): The identity of a subject to list flags for* [Goal](goal.html): Who this goal is intended for* [ImagingStudy](imagingstudy.html): Who the study is about* [Immunization](immunization.html): The patient for the vaccination record* [List](list.html): If all resources have the same subject* [MedicationAdministration](medicationadministration.html): The identity of a patient to list administrations  for* [MedicationDispense](medicationdispense.html): The identity of a patient to list dispenses  for* [MedicationRequest](medicationrequest.html): Returns prescriptions for a specific patient* [MedicationUsage](medicationusage.html): Returns statements for a specific patient.* [NutritionOrder](nutritionorder.html): The identity of the person who requires the diet, formula or nutritional supplement* [Observation](observation.html): The subject that the observation is about (if patient)* [Procedure](procedure.html): Search by subject - a patient* [RiskAssessment](riskassessment.html): Who/what does assessment apply to?* [ServiceRequest](servicerequest.html): Search by subject - a patient* [SupplyDelivery](supplydelivery.html): Patient for whom the item is supplied* [VisionPrescription](visionprescription.html): The identity of a patient to list dispenses for")
                        @OptionalParam(name="patient", targetTypes={  } )
                        ReferenceAndListParam thePatient,

                        @Description(shortDefinition="Who recorded the sensitivity")
                        @OptionalParam(name="recorder", targetTypes={  } )
                        ReferenceAndListParam theRecorder,

                        @Description(shortDefinition="How the subject was exposed to the substance")
                        @OptionalParam(name="route")
                        TokenAndListParam theRoute,

                        @Description(shortDefinition="mild | moderate | severe (of event as a whole)")
                        @OptionalParam(name="severity")
                        TokenAndListParam theSeverity,

                        @Description(shortDefinition="Multiple Resources: * [AllergyIntolerance](allergyintolerance.html): allergy | intolerance - Underlying mechanism (if known)* [Composition](composition.html): Kind of composition (LOINC if possible)* [DocumentManifest](documentmanifest.html): Kind of document set* [DocumentReference](documentreference.html): Kind of document (LOINC if possible)* [Encounter](encounter.html): Specific type of encounter* [EpisodeOfCare](episodeofcare.html): Type/class  - e.g. specialist referral, disease management")
                        @OptionalParam(name="type")
                        TokenAndListParam theType,

                        @Description(shortDefinition="unconfirmed | presumed | confirmed | refuted | entered-in-error")
                        @OptionalParam(name="verification-status")
                        TokenAndListParam theVerification_status,

                        @RawParam
                        Map<String, List<String>> theAdditionalRawParams,

                        @IncludeParam(reverse=true)
                        Set<Include> theRevIncludes,
                        @Description(shortDefinition="Only return resources which were last updated as specified by the given range")
                        @OptionalParam(name="_lastUpdated")
                        DateRangeParam theLastUpdated,

                        @IncludeParam(allow= {
                                "AllergyIntolerance:asserter",
                                "AllergyIntolerance:patient",
                                "AllergyIntolerance:recorder",
                                "*"
                        })
                        Set<Include> theIncludes,

                        @Sort
                        SortSpec theSort,

                        @ca.uhn.fhir.rest.annotation.Count
                        Integer theCount,

                        @ca.uhn.fhir.rest.annotation.Offset
                        Integer theOffset,

                        SummaryEnum theSummaryMode,

                        SearchTotalModeEnum theSearchTotalMode

                        ) {
                startRequest(theServletRequest);
                try {
                        SearchParameterMap paramMap = new SearchParameterMap();
                        paramMap.add(ca.uhn.fhir.rest.api.Constants.PARAM_FILTER, theFtFilter);
                        paramMap.add(ca.uhn.fhir.rest.api.Constants.PARAM_CONTENT, theFtContent);
                        paramMap.add(ca.uhn.fhir.rest.api.Constants.PARAM_TEXT, theFtText);
                        paramMap.add(ca.uhn.fhir.rest.api.Constants.PARAM_TAG, theSearchForTag);
                        paramMap.add(ca.uhn.fhir.rest.api.Constants.PARAM_SECURITY, theSearchForSecurity);
                        paramMap.add(ca.uhn.fhir.rest.api.Constants.PARAM_PROFILE, theSearchForProfile);
                        paramMap.add(ca.uhn.fhir.rest.api.Constants.PARAM_SOURCE, theSearchForSource);
                        paramMap.add("_has", theHas);
                        paramMap.add("_id", the_id);
                        paramMap.add("_language", the_language);
                        paramMap.add("_text", the_text);
                        paramMap.add("asserter", theAsserter);
                        paramMap.add("category", theCategory);
                        paramMap.add("clinical-status", theClinical_status);
                        paramMap.add("code", theCode);
                        paramMap.add("criticality", theCriticality);
                        paramMap.add("date", theDate);
                        paramMap.add("identifier", theIdentifier);
                        paramMap.add("last-date", theLast_date);
                        paramMap.add("manifestation", theManifestation);
                        paramMap.add("patient", thePatient);
                        paramMap.add("recorder", theRecorder);
                        paramMap.add("route", theRoute);
                        paramMap.add("severity", theSeverity);
                        paramMap.add("type", theType);
                        paramMap.add("verification-status", theVerification_status);
                        paramMap.setRevIncludes(theRevIncludes);
                        paramMap.setLastUpdated(theLastUpdated);
                        paramMap.setIncludes(theIncludes);
                        paramMap.setSort(theSort);
                        paramMap.setCount(theCount);
                        paramMap.setOffset(theOffset);
                        paramMap.setSummaryMode(theSummaryMode);
                        paramMap.setSearchTotalMode(theSearchTotalMode);

                        getDao().translateRawParameters(theAdditionalRawParams, paramMap);

                        ca.uhn.fhir.rest.api.server.IBundleProvider retVal = getDao().search(paramMap, theRequestDetails, theServletResponse);
                        return retVal;
                } finally {
                        endRequest(theServletRequest);
                }
        }

}