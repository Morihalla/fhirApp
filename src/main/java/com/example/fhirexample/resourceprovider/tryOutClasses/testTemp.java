//package com.example.fhirexample.resourceprovider;
//
//
///* #%L
// *
// * HAPI FHIR JPA Server
// * %%
// * Copyright (C) 2014 - 2021 Smile CDR, Inc.
// * %%
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// * #L%
// */
//import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
//import ca.uhn.fhir.jpa.model.util.JpaConstants;
//import ca.uhn.fhir.model.api.IResource;
//import ca.uhn.fhir.model.dstu2.composite.MetaDt;
//import ca.uhn.fhir.model.dstu2.resource.Parameters;
//import ca.uhn.fhir.model.primitive.BooleanDt;
//import ca.uhn.fhir.model.primitive.IdDt;
//import ca.uhn.fhir.model.primitive.IntegerDt;
//import ca.uhn.fhir.rest.annotation.ConditionalUrlParam;
//import ca.uhn.fhir.rest.annotation.Create;
//import ca.uhn.fhir.rest.annotation.Delete;
//import ca.uhn.fhir.rest.annotation.IdParam;
//import ca.uhn.fhir.rest.annotation.Operation;
//import ca.uhn.fhir.rest.annotation.OperationParam;
//import ca.uhn.fhir.rest.annotation.ResourceParam;
//import ca.uhn.fhir.rest.annotation.Update;
//import ca.uhn.fhir.rest.annotation.Validate;
//import ca.uhn.fhir.rest.api.EncodingEnum;
//import ca.uhn.fhir.rest.api.MethodOutcome;
//import ca.uhn.fhir.rest.api.ValidationModeEnum;
//import ca.uhn.fhir.rest.api.server.RequestDetails;
//import ca.uhn.fhir.rest.server.exceptions.InvalidRequestException;
//import org.hl7.fhir.instance.model.api.IIdType;
//
//import javax.servlet.http.HttpServletRequest;
//
//import static ca.uhn.fhir.jpa.model.util.JpaConstants.OPERATION_EXPUNGE_PARAM_EXPUNGE_DELETED_RESOURCES;
//import static ca.uhn.fhir.jpa.model.util.JpaConstants.OPERATION_EXPUNGE_PARAM_EXPUNGE_PREVIOUS_VERSIONS;
//import static ca.uhn.fhir.jpa.model.util.JpaConstants.OPERATION_EXPUNGE_PARAM_LIMIT;
//import static ca.uhn.fhir.jpa.model.util.JpaConstants.OPERATION_META;
//import static ca.uhn.fhir.jpa.model.util.JpaConstants.OPERATION_META_ADD;
//import static ca.uhn.fhir.jpa.model.util.JpaConstants.OPERATION_META_DELETE;
//
//public class testTemp <T extends IResource> extends BaseJpaResourceProvider<T> {
//057
//        058        public JpaResourceProviderDstu2() {
//            059                // nothing
//            060        }
//061
//        062        public JpaResourceProviderDstu2(IFhirResourceDao<T> theDao) {
//            063                super(theDao);
//            064        }
//065
//        066        @Create
//067        public MethodOutcome create(HttpServletRequest theRequest, @ResourceParam T theResource, @ConditionalUrlParam String theConditional, RequestDetails theRequestDetails) {
//            068                startRequest(theRequest);
//            069                try {
//                070                        if (theConditional != null) {
//                    071                                return getDao().create(theResource, theConditional, theRequestDetails);
//                    072                        } else {
//                    073                                return getDao().create(theResource, theRequestDetails);
//                    074                        }
//                075                } finally {
//                076                        endRequest(theRequest);
//                077                }
//            078        }
//079
//        080        @Delete()
//081        public MethodOutcome delete(HttpServletRequest theRequest, @IdParam IdDt theResource, @ConditionalUrlParam(supportsMultiple = true) String theConditional, RequestDetails theRequestDetails) {
//            082                startRequest(theRequest);
//            083                try {
//                084                        if (theConditional != null) {
//                    085                                return getDao().deleteByUrl(theConditional, theRequestDetails);
//                    086                        } else {
//                    087                                return getDao().delete(theResource, theRequestDetails);
//                    088                        }
//                089                } finally {
//                090                        endRequest(theRequest);
//                091                }
//            092        }
//093
//        094        @Operation(name = JpaConstants.OPERATION_EXPUNGE, idempotent = false, returnParameters = {
//                095                @OperationParam(name = JpaConstants.OPERATION_EXPUNGE_OUT_PARAM_EXPUNGE_COUNT, type = IntegerDt.class)
//                096        })
//097        public Parameters expunge(
//098                @IdParam IIdType theIdParam,
//099                @OperationParam(name = OPERATION_EXPUNGE_PARAM_LIMIT) IntegerDt theLimit,
//100                @OperationParam(name = OPERATION_EXPUNGE_PARAM_EXPUNGE_DELETED_RESOURCES) BooleanDt theExpungeDeletedResources,
//101                @OperationParam(name = OPERATION_EXPUNGE_PARAM_EXPUNGE_PREVIOUS_VERSIONS) BooleanDt theExpungeOldVersions,
//102                RequestDetails theRequest) {
//            103                org.hl7.fhir.r4.model.Parameters retVal = super.doExpunge(theIdParam, theLimit, theExpungeDeletedResources, theExpungeOldVersions, null, theRequest);
//            104                return JpaSystemProviderDstu2.toExpungeResponse(retVal);
//            105        }
//106
//        107        @Operation(name = JpaConstants.OPERATION_EXPUNGE, idempotent = false, returnParameters = {
//                108                @OperationParam(name = JpaConstants.OPERATION_EXPUNGE_OUT_PARAM_EXPUNGE_COUNT, type = IntegerDt.class)
//                109        })
//110        public Parameters expunge(
//111                @OperationParam(name = OPERATION_EXPUNGE_PARAM_LIMIT) IntegerDt theLimit,
//112                @OperationParam(name = OPERATION_EXPUNGE_PARAM_EXPUNGE_DELETED_RESOURCES) BooleanDt theExpungeDeletedResources,
//113                @OperationParam(name = OPERATION_EXPUNGE_PARAM_EXPUNGE_PREVIOUS_VERSIONS) BooleanDt theExpungeOldVersions,
//114                RequestDetails theRequest) {
//            115                org.hl7.fhir.r4.model.Parameters retVal = super.doExpunge(null, theLimit, theExpungeDeletedResources, theExpungeOldVersions, null, theRequest);
//            116                return JpaSystemProviderDstu2.toExpungeResponse(retVal);
//            117        }
//118
//        119        @Operation(name = OPERATION_META, idempotent = true, returnParameters = {
//                120                @OperationParam(name = "return", type = MetaDt.class)
//                121        })
//122        public Parameters meta(RequestDetails theRequestDetails) {
//            123                Parameters parameters = new Parameters();
//            124                MetaDt metaGetOperation = getDao().metaGetOperation(MetaDt.class, theRequestDetails);
//            125                parameters.addParameter().setName("return").setValue(metaGetOperation);
//            126                return parameters;
//            127        }
//128
//        129        @Operation(name = OPERATION_META, idempotent = true, returnParameters = {
//                130                @OperationParam(name = "return", type = MetaDt.class)
//                131        })
//132        public Parameters meta(@IdParam IdDt theId, RequestDetails theRequestDetails) {
//            133                Parameters parameters = new Parameters();
//            134                MetaDt metaGetOperation = getDao().metaGetOperation(MetaDt.class, theId, theRequestDetails);
//            135                parameters.addParameter().setName("return").setValue(metaGetOperation);
//            136                return parameters;
//            137        }
//138
//        139        @Operation(name = OPERATION_META_ADD, idempotent = true, returnParameters = {
//                140                @OperationParam(name = "return", type = MetaDt.class)
//                141        })
//142        public Parameters metaAdd(@IdParam IdDt theId, @OperationParam(name = "meta") MetaDt theMeta, RequestDetails theRequestDetails) {
//            143                if (theMeta == null) {
//                144                        throw new InvalidRequestException("Input contains no parameter with name 'meta'");
//                145                }
//            146                Parameters parameters = new Parameters();
//            147                MetaDt metaAddOperation = getDao().metaAddOperation(theId, theMeta, theRequestDetails);
//            148                parameters.addParameter().setName("return").setValue(metaAddOperation);
//            149                return parameters;
//            150        }
//151
//        152        @Operation(name = OPERATION_META_DELETE, idempotent = true, returnParameters = {
//                153                @OperationParam(name = "return", type = MetaDt.class)
//                154        })
//155        public Parameters metaDelete(@IdParam IdDt theId, @OperationParam(name = "meta") MetaDt theMeta, RequestDetails theRequestDetails) {
//            156                if (theMeta == null) {
//                157                        throw new InvalidRequestException("Input contains no parameter with name 'meta'");
//                158                }
//            159                Parameters parameters = new Parameters();
//            160                parameters.addParameter().setName("return").setValue(getDao().metaDeleteOperation(theId, theMeta, theRequestDetails));
//            161                return parameters;
//            162        }
//163
//        164        @Update
//165        public MethodOutcome update(HttpServletRequest theRequest, @ResourceParam T theResource, @IdParam IdDt theId, @ConditionalUrlParam String theConditional, RequestDetails theRequestDetails) {
//            166                startRequest(theRequest);
//            167                try {
//                168                        if (theConditional != null) {
//                    169                                return getDao().update(theResource, theConditional, theRequestDetails);
//                    170                        } else {
//                    171                                theResource.setId(theId);
//                    172                                return getDao().update(theResource, theRequestDetails);
//                    173                        }
//                174                } finally {
//                175                        endRequest(theRequest);
//                176                }
//            177        }
//178
//        179        @Validate
//180        public MethodOutcome validate(@ResourceParam T theResource, @ResourceParam String theRawResource, @ResourceParam EncodingEnum theEncoding, @Validate.Mode ValidationModeEnum theMode,
//181                                                                                        @Validate.Profile String theProfile, RequestDetails theRequestDetails) {
//            182                return validate(theResource, null, theRawResource, theEncoding, theMode, theProfile, theRequestDetails);
//            183        }
//184
//        185        @Validate
//186        public MethodOutcome validate(@ResourceParam T theResource, @IdParam IdDt theId, @ResourceParam String theRawResource, @ResourceParam EncodingEnum theEncoding, @Validate.Mode ValidationModeEnum theMode,
//187                                                                                        @Validate.Profile String theProfile, RequestDetails theRequestDetails) {
//            188                return getDao().validate(theResource, theId, theRawResource, theEncoding, theMode, theProfile, theRequestDetails);
//            189        }
//190
//        191}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
