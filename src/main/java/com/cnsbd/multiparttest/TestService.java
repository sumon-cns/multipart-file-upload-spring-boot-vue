package com.cnsbd.multiparttest;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    public void saveUpdate() {
        String impl = """
                <template>
                  <div>
                    <h1>Upload Receiver Info</h1>
                    <form @submit.prevent="submitForm">
                      <div>
                        <label for="receiverName">Receiver Name:</label>
                        <input type="text" v-model="form.receiverName" required/>
                      </div>
                
                      <div>
                        <label for="relationId">Relation ID:</label>
                        <input type="number" v-model="form.relationId" required/>
                      </div>
                
                      <div>
                        <label for="phoneNo">Mobile Number:</label>
                        <input type="text" v-model="form.phoneNo" required/>
                      </div>
                
                      <div>
                        <label for="address">Address:</label>
                        <input type="text" v-model="form.address" required/>
                      </div>
                
                      <div>
                        <label for="districtId">Bank District ID:</label>
                        <input type="number" v-model="form.districtId" required/>
                      </div>
                
                      <div>
                        <label for="bankId">Bank ID:</label>
                        <input type="number" v-model="form.bankId" required/>
                      </div>
                
                      <div>
                        <label for="branchId">Branch ID:</label>
                        <input type="number" v-model="form.branchId" required/>
                      </div>
                
                      <div>
                        <label for="bankAccount">Bank Account:</label>
                        <input type="text" v-model="form.bankAccount" required/>
                      </div>
                
                      <div>
                        <label for="amount">Amount:</label>
                        <input type="number" v-model="form.amount" required/>
                      </div>
                
                      <div>
                        <label for="nid">National ID:</label>
                        <input type="number" v-model="form.nid" required/>
                      </div>
                
                      <div>
                        <label for="nids">NID Files:</label>
                        <input type="file" multiple @change="(e) => handleFileChange('nids', e)"/>
                      </div>
                
                      <div>
                        <label for="photos">Photo Files:</label>
                        <input type="file" multiple @change="(e) => handleFileChange('photos', e)"/>
                      </div>
                
                      <div>
                        <label for="investigationReport">Investigation Report:</label>
                        <input type="file" @change="(e) => handleFileChange('investigationReport', e)"/>
                      </div>
                
                      <div>
                        <label for="inheritanceCert">Inheritance Certificate:</label>
                        <input type="file" @change="(e) => handleFileChange('inheritanceCert', e)"/>
                      </div>
                
                      <div>
                        <label for="authLetter">Authorization Letter:</label>
                        <input type="file" @change="(e) => handleFileChange('authLetter', e)"/>
                      </div>
                
                      <div>
                        <label for="nocCert">NOC Certificate:</label>
                        <input type="file" @change="(e) => handleFileChange('nocCert', e)"/>
                      </div>
                
                      <div>
                        <label for="other">Other Documents:</label>
                        <input type="file" @change="(e) => handleFileChange('other', e)"/>
                      </div>
                
                      <button type="submit">Submit</button>
                    </form>
                  </div>
                </template>
                
                <script setup>
                import {reactive} from 'vue';
                import axios from 'axios';
                
                const form = reactive({
                  receiverName: '',
                  relationId: null,
                  phoneNo: '',
                  address: '',
                  districtId: null,
                  bankId: null,
                  branchId: null,
                  bankAccount: '',
                  amount: null,
                  nid: null,
                  nids: [],
                  photos: [],
                  investigationReport: null,
                  inheritanceCert: null,
                  authLetter: null,
                  nocCert: null,
                  other: null,
                });
                
                const handleFileChange = (field, event) => {
                  if (field === 'nids' || field === 'photos') {
                    form[field] = Array.from(event.target.files);
                  } else {
                    form[field] = event.target.files.length > 0 ? event.target.files[0] : null;
                  }
                };
                
                const submitForm = async () => {
                  const formData = new FormData();
                
                  // Add reqDatas as a JSON string
                  const reqDatas = [
                    {
                      receiverName: form.receiverName,
                      relationId: form.relationId,
                      phoneNo: form.phoneNo,
                      address: form.address,
                      districtId: form.districtId,
                      bankId: form.bankId,
                      branchId: form.branchId,
                      bankAccount: form.bankAccount,
                      amount: form.amount,
                      nid: form.nid,
                    },
                    // Add more receiver info objects as needed
                  ];
                
                  formData.append('reqDatas', new Blob([JSON.stringify(reqDatas)], {type: 'application/json'}));
                  formData.append('faWageEarnerId', new Blob([JSON.stringify('12')], {type: 'application/json'}));
                  // Add file uploads
                  form.nids.forEach(file => formData.append('nid', file));
                  form.photos.forEach(file => formData.append('photo', file));
                
                  if (form.investigationReport) {
                    formData.append('investigation-report', form.investigationReport);
                  }
                  if (form.inheritanceCert) {
                    formData.append('inheritance-cert', form.inheritanceCert);
                  }
                  if (form.authLetter) {
                    formData.append('auth-letter', form.authLetter);
                  }
                  if (form.nocCert) {
                    formData.append('noc-cert', form.nocCert);
                  }
                  if (form.other) {
                    formData.append('other', form.other);
                  }
                
                  try {
                    const response = await axios.post(
                        'http://localhost:9021/raise-fa/api/v1/receiver-info', formData, {
                          headers: {
                            'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyVHlwZUlkIjoxLCJsb2dpbklkIjoiQURNSU4xMDEiLCJtb2R1bGVBdXRoWW4iOiJOIiwidXNlcklkIjoyNDA0MDQwMDAxLCJlbWFpbCI6ImFkbWluQHRlc3QuY29tIiwic3ViIjoiYWRtaW4xMDEiLCJpYXQiOjE3Mjk0MDQ5MTUsImV4cCI6MTcyOTQ0MDkxNX0.pdwjMr7yx4ys60sOPKGRFB2_LNf4aCiYpI79UCoFAMDZG3XcThDyS08T4zulSgGk7bEtv91s2TJ37mtb3WhTCQ'
                          },
                        });
                    console.log('Response:', response.data);
                  } catch (error) {
                    console.error('Error uploading data:', error);
                  }
                };
                </script>
                
                <style>
                /* Add your styles here */
                </style>
                
                """;

        String impl1 = """
                <template>
                  <div>
                    <form @submit.prevent="submitForm">
                      <!-- Wage Earner -->
                      <div>
                        <label for="faWageEarnerId">Wage Earner ID</label>
                        <input v-model="finAssistReqData.faWageEarnerId" type="number" id="faWageEarnerId" required />
                      </div>
                
                      <!-- Airport Nothi No -->
                      <div>
                        <label for="airportNothiNo">Airport Nothi No</label>
                        <input v-model="finAssistReqData.burialRef.airportNothiNo" type="number" id="airportNothiNo" required />
                      </div>
                
                      <!-- Registration Type -->
                      <div>
                        <label for="registrationType">Registration Type</label>
                        <input v-model="finAssistReqData.burialRef.registrationType" type="text" id="registrationType" required />
                      </div>
                
                      <!-- File No -->
                      <div>
                        <label for="fileNo">File No</label>
                        <input v-model="finAssistReqData.burialRef.fileNo" type="number" id="fileNo" required />
                      </div>
                
                      <!-- E-Nothi No -->
                      <div>
                        <label for="eNothiNo">E-Nothi No</label>
                        <input v-model="finAssistReqData.burialRef.eNothiNo" type="number" id="eNothiNo" required />
                      </div>
                
                      <!-- Upload Demand Letter -->
                      <div>
                        <label for="demandLetter">Upload Demand Letter</label>
                        <input type="file" id="demandLetter" @change="handleFileUpload($event, 'demandLetter')" />
                      </div>
                
                      <!-- Upload Passport -->
                      <div>
                        <label for="passport">Upload Passport</label>
                        <input type="file" id="passport" @change="handleFileUpload($event, 'passport')" />
                      </div>
                
                      <!-- Upload Embassy Certificate -->
                      <div>
                        <label for="embassyCertificate">Upload Embassy Certificate</label>
                        <input type="file" id="embassyCertificate" @change="handleFileUpload($event, 'embassyCertificate')" />
                      </div>
                
                      <!-- Upload Inheritance Certificate -->
                      <div>
                        <label for="inheritanceCertificate">Upload Inheritance Certificate</label>
                        <input type="file" id="inheritanceCertificate" @change="handleFileUpload($event, 'inheritanceCertificate')" />
                      </div>
                
                      <!-- Upload Death Certificate -->
                      <div>
                        <label for="deathCertificate">Upload Death Certificate</label>
                        <input type="file" id="deathCertificate" @change="handleFileUpload($event, 'deathCertificate')" />
                      </div>
                
                      <!-- Upload Airways Bill -->
                      <div>
                        <label for="airwaysBill">Upload Airways Bill</label>
                        <input type="file" id="airwaysBill" @change="handleFileUpload($event, 'airwaysBill')" />
                      </div>
                
                      <!-- Upload Power of Attorney -->
                      <div>
                        <label for="powerOfAttorney">Upload Power of Attorney</label>
                        <input type="file" id="powerOfAttorney" @change="handleFileUpload($event, 'powerOfAttorney')" />
                      </div>
                
                      <button type="submit">Submit</button>
                    </form>
                  </div>
                </template>
                
                <script setup>
                import { reactive, ref } from 'vue';
                import axios from 'axios';
                
                // Reactive object to hold form data
                const finAssistReqData = reactive({
                  faWageEarnerId: null,
                  burialRef: {
                    airportNothiNo: null,
                    registrationType: '',
                    fileNo: null,
                    eNothiNo: null,
                  }
                });
                
                // Files object to hold file inputs
                const attachmentData = reactive({
                  demandLetter: null,
                  passport: null,
                  embassyCertificate: null,
                  inheritanceCertificate: null,
                  deathCertificate: null,
                  airwaysBill: null,
                  powerOfAttorney: null
                });
                
                // Handle file input and store the file in the reactive object
                const handleFileUpload = (event, fileType) => {
                  const file = event.target.files[0];
                  if (file) {
                    attachmentData[fileType] = file;
                  }
                };
                
                // Submit form and upload data
                const submitForm = async () => {
                  try {
                    const formData = new FormData();
                
                    // Append the request data object (serialized as JSON)
                    formData.append('reqData', new Blob([JSON.stringify(finAssistReqData)], {
                      type: 'application/json',
                    }));
                
                    // Append each file if it exists
                    Object.keys(attachmentData).forEach((key) => {
                      if (attachmentData[key]) {
                        formData.append(key, attachmentData[key]);
                      }
                    });
                
                    // Make the API request
                    const response = await axios.post('/save-update', formData, {
                      headers: {
                        'Content-Type': 'multipart/form-data',
                      },
                    });
                
                    // Handle the response
                    console.log('Response:', response.data);
                  } catch (error) {
                    console.error('Error:', error);
                  }
                };
                </script>
                
                """;
    }

    public void test() {
        String impl = """
                                <template>
                                  <form @submit.prevent="submitForm">
                                    <div>
                                      <label for="receiverName">Receiver Name:</label>
                                      <input v-model="form.receiverName" type="text" id="receiverName" required/>
                                    </div>
                
                                    <div>
                                      <label for="relationId">Relation ID:</label>
                                      <input v-model="form.relationId" type="number" id="relationId" required/>
                                    </div>
                
                                    <div>
                                      <label for="phoneNo">Mobile Number:</label>
                                      <input v-model="form.phoneNo" type="text" id="phoneNo" required/>
                                    </div>
                
                                    <div>
                                      <label for="address">Address:</label>
                                      <input v-model="form.address" type="text" id="address" required/>
                                    </div>
                
                                    <div>
                                      <label for="districtId">Bank District ID:</label>
                                      <input v-model="form.districtId" type="number" id="districtId" required/>
                                    </div>
                
                                    <div>
                                      <label for="bankId">Bank ID:</label>
                                      <input v-model="form.bankId" type="number" id="bankId" required/>
                                    </div>
                
                                    <div>
                                      <label for="branchId">Branch ID:</label>
                                      <input v-model="form.branchId" type="number" id="branchId" required/>
                                    </div>
                
                                    <div>
                                      <label for="bankAccount">Bank Account:</label>
                                      <input v-model="form.bankAccount" type="text" id="bankAccount" required/>
                                    </div>
                
                                    <div>
                                      <label for="amount">Amount:</label>
                                      <input v-model="form.amount" type="number" id="amount" required/>
                                    </div>
                
                                    <div>
                                      <label for="nid">National ID:</label>
                                      <input v-model="form.nid" type="number" id="nid" required/>
                                    </div>
                
                                    <div>
                                      <label for="faWageEarnerId">Wage Earner ID:</label>
                                      <input v-model="form.faWageEarnerId" type="number" id="faWageEarnerId" required/>
                                    </div>
                
                                    <div>
                                      <label for="nids">NID Files:</label>
                                      <input type="file" @change="handleFileChange('nids', $event)" multiple/>
                                    </div>
                
                                    <div>
                                      <label for="photos">Photos:</label>
                                      <input type="file" @change="handleFileChange('photos', $event)" multiple/>
                                    </div>
                
                                    <div>
                                      <label for="investigationReport">Investigation Report:</label>
                                      <input type="file" @change="handleFileChange('investigationReport', $event)"/>
                                    </div>
                
                                    <div>
                                      <label for="inheritanceCert">Inheritance Certificate:</label>
                                      <input type="file" @change="handleFileChange('inheritanceCert', $event)"/>
                                    </div>
                
                                    <div>
                                      <label for="authLetter">Authorization Letter:</label>
                                      <input type="file" @change="handleFileChange('authLetter', $event)"/>
                                    </div>
                
                                    <div>
                                      <label for="nocCert">NOC Certificate:</label>
                                      <input type="file" @change="handleFileChange('nocCert', $event)"/>
                                    </div>
                
                                    <div>
                                      <label for="other">Other Files:</label>
                                      <input type="file" @change="handleFileChange('other', $event)"/>
                                    </div>
                
                                    <button type="submit">Submit</button>
                                  </form>
                                </template>
                
                                <script setup>
                                import {reactive} from 'vue';
                                import axios from 'axios';
                
                                const form = reactive({
                                  receiverName: '',
                                  relationId: null,
                                  phoneNo: '',
                                  address: '',
                                  districtId: null,
                                  bankId: null,
                                  branchId: null,
                                  bankAccount: '',
                                  amount: null,
                                  nid: null,
                                  faWageEarnerId: null,
                                  nids: [],
                                  photos: [],
                                  investigationReport: null,
                                  inheritanceCert: null,
                                  authLetter: null,
                                  nocCert: null,
                                  other: null,
                                });
                
                                const handleFileChange = (field, event) => {
                                  if (field === 'nids') {
                                    form.nids = Array.from(event.target.files);
                                  } else if (field === 'photos') {
                                    form.photos = Array.from(event.target.files);
                                  } else {
                                    form[field] = event.target.files[0];
                                  }
                                };
                
                //                const submitForm1 = async () => {
                //                  const formData = new FormData();
                //                
                //                  // Prepare reqDatas as an array of objects
                //                  const reqDatas = [
                //                    {
                //                      receiverName: form.receiverName,
                //                      relationId: form.relationId,
                //                      phoneNo: form.phoneNo,
                //                      address: form.address,
                //                      districtId: form.districtId,
                //                      bankId: form.bankId,
                //                      branchId: form.branchId,
                //                      bankAccount: form.bankAccount,
                //                      amount: form.amount,
                //                      nid: form.nid,
                //                    },
                //                    // You can add more receiver objects here if needed
                //                  ];
                //                
                //                  // Append to FormData
                //                  formData.append('reqDatas', new Blob([JSON.stringify(reqDatas)], {type: 'application/json'}));
                //                  formData.append('faWageEarnerId', form.faWageEarnerId); // Wage earner ID
                //                
                //                  // Handle file uploads
                //                  form.nids.forEach(file => formData.append('nids', file));
                //                  form.photos.forEach(file => formData.append('photos', file));
                //                
                //                  if (form.investigationReport) {
                //                    formData.append('investigationReport', form.investigationReport);
                //                  }
                //                  if (form.inheritanceCert) {
                //                    formData.append('inheritanceCert', form.inheritanceCert);
                //                  }
                //                  if (form.authLetter) {
                //                    formData.append('authLetter', form.authLetter);
                //                  }
                //                  if (form.nocCert) {
                //                    formData.append('nocCert', form.nocCert);
                //                  }
                //                  if (form.other) {
                //                    formData.append('other', form.other);
                //                  }
                //                
                //                  try {
                //                    const response = await axios.post(
                //                        'http://localhost:8080/financial-assistance/test', formData);
                //                    console.log('Response:', response.data);
                //                  } catch (error) {
                //                    console.error('Error uploading data:', error);
                //                  }
                //                };
                
                                const submitForm = async () => {
                                  const formData = new FormData();
                
                                  // Prepare reqDatas as an array of objects
                                  const reqDatas = [
                                    {
                                      receiverName: form.receiverName,
                                      relationId: form.relationId,
                                      phoneNo: form.phoneNo,
                                      address: form.address,
                                      districtId: form.districtId,
                                      bankId: form.bankId,
                                      branchId: form.branchId,
                                      bankAccount: form.bankAccount,
                                      amount: form.amount,
                                      nid: form.nid,
                                    },
                                    // You can add more receiver objects here if needed
                                  ];
                
                                  // Append each reqDatas field
                                  reqDatas.forEach((data, index) => {
                                    formData.append(`reqDatas[${index}].receiverName`, data.receiverName);
                                    formData.append(`reqDatas[${index}].relationId`, data.relationId);
                                    formData.append(`reqDatas[${index}].phoneNo`, data.phoneNo);
                                    formData.append(`reqDatas[${index}].address`, data.address);
                                    formData.append(`reqDatas[${index}].districtId`, data.districtId);
                                    formData.append(`reqDatas[${index}].bankId`, data.bankId);
                                    formData.append(`reqDatas[${index}].branchId`, data.branchId);
                                    formData.append(`reqDatas[${index}].bankAccount`, data.bankAccount);
                                    formData.append(`reqDatas[${index}].amount`, data.amount);
                                    formData.append(`reqDatas[${index}].nid`, data.nid);
                                  });
                
                                  // Append the other fields
                                  formData.append('faWageEarnerId', form.faWageEarnerId); // Wage earner ID
                
                                  // Handle file uploads
                                  form.nids.forEach(file => formData.append('nids', file));
                                  form.photos.forEach(file => formData.append('photos', file));
                
                                  if (form.investigationReport) {
                                    formData.append('investigationReport', form.investigationReport);
                                  }
                                  if (form.inheritanceCert) {
                                    formData.append('inheritanceCert', form.inheritanceCert);
                                  }
                                  if (form.authLetter) {
                                    formData.append('authLetter', form.authLetter);
                                  }
                                  if (form.nocCert) {
                                    formData.append('nocCert', form.nocCert);
                                  }
                                  if (form.other) {
                                    formData.append('other', form.other);
                                  }
                
                                  try {
                                    const response = await axios.post(
                                        'http://localhost:8080/financial-assistance/test', formData);
                                    console.log('Response:', response.data);
                                  } catch (error) {
                                    console.error('Error uploading data:', error);
                                  }
                                };
                
                                </script>
                
                                <style scoped>
                                /* Add your styles here */
                                </style>
                
                """;
    }

    public void testV1() {
        String impl = """
                <template>
                  <form @submit.prevent="submitForm">
                    <input v-model="form.faWageEarnerId" type="number" placeholder="Wage Earner ID" required/>
                
                    <div v-for="(receiver, index) in form.receivers" :key="index">
                      <input v-model="receiver.receiverName" placeholder="Receiver Name" required/>
                      <input v-model="receiver.phoneNo" placeholder="Phone Number" required/>
                      <input v-model="receiver.address" placeholder="Address" required/>
                      <input v-model="receiver.districtId" type="number" placeholder="District ID" required/>
                      <input v-model="receiver.bankId" type="number" placeholder="Bank ID" required/>
                      <input v-model="receiver.branchId" type="number" placeholder="Branch ID" required/>
                      <input v-model="receiver.bankAccount" placeholder="Bank Account" required/>
                      <input v-model="receiver.amount" type="number" placeholder="Amount" required/>
                      <input v-model="receiver.nid" type="number" placeholder="National ID" required/>
                      <input type="file" @change="onFileChange(receiver, 'nidFile')"/>
                      <input type="file" @change="onFileChange(receiver, 'photoFile')"/>
                    </div>
                
                    <input type="file" @change="onFileChange(null, 'investigationReport')"/>
                    <input type="file" @change="onFileChange(null, 'inheritanceCert')"/>
                    <input type="file" @change="onFileChange(null, 'authLetter')"/>
                    <input type="file" @change="onFileChange(null, 'nocCert')"/>
                    <input type="file" @change="onFileChange(null, 'other')"/>
                
                    <button type="submit">Submit</button>
                  </form>
                </template>
                
                <script setup>
                import {ref} from 'vue';
                import axios from 'axios';
                
                const form = ref({
                  faWageEarnerId: null,
                  receivers: [
                    {
                      receiverName: '',
                      phoneNo: '',
                      address: '',
                      districtId: null,
                      bankId: null,
                      branchId: null,
                      bankAccount: '',
                      amount: null,
                      nid: null,
                      nidFile: null,
                      photoFile: null,
                    },
                  ],
                  investigationReport: null,
                  inheritanceCert: null,
                  authLetter: null,
                  nocCert: null,
                  other: null,
                });
                
                const onFileChange = (receiver, fileType) => {
                  const input = event.target;
                  if (input.files.length > 0) {
                    const file = input.files[0];
                    if (receiver) {
                      receiver[fileType] = file;
                    } else {
                      form.value[fileType] = file;
                    }
                  }
                };
                
                const submitForm = async () => {
                  const formData = new FormData();
                
                  // Populate FormData
                  Object.entries(form.value).forEach(([key, value]) => {
                    if (Array.isArray(value)) {
                      value.forEach((receiver, index) => {
                        Object.entries(receiver).forEach(([subKey, subValue]) => {
                          if (subKey.endsWith('File')) {
                            formData.append(`receivers[${index}].${subKey}`, subValue);
                          } else {
                            formData.append(`receivers[${index}].${subKey}`, subValue);
                          }
                        });
                      });
                    } else {
                      formData.append(key, value); // This should include faWageEarnerId
                    }
                  });
                
                
                  try {
                    const response = await axios.post(
                        'http://localhost:9021/raise-fa/api/v1/receiver-info', formData, {
                          headers: {
                            'Content-Type': 'multipart/form-data',
                            'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyVHlwZUlkIjo3LCJsb2dpbklkIjoiREVNTzAxIiwibW9kdWxlQXV0aFluIjoiTiIsInVzZXJJZCI6MjQwOTAwNDAxLCJlbWFpbCI6Im1tQHlhaG9vLmNvbSIsInN1YiI6ImRlbW8wMSIsImlhdCI6MTcyOTc1ODE3MCwiZXhwIjoxNzI5Nzk0MTcwfQ.7zhnojOeupj_vs6eq9kFh_-t8juo3Zz4fjz2jg_fKTAVB88c6tq_Qfvg-qK38NpAs_VGMEBRwU1ncALztK_y5Q'
                          },
                        });
                    console.log('Response:', response.data);
                  } catch (error) {
                    console.error('Error uploading data:', error);
                  }
                };
                </script>
                
                """;
    }
}
