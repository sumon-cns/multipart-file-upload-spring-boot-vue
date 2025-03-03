package com.cnsbd.multiparttest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/financial-assistance")
@RequiredArgsConstructor
public class FinancialAssistanceController {

    private final TestService testService;

    @PostMapping(value = "/save-update", consumes = "multipart/form-data")
    public ResponseEntity<?> saveAndUpdateFinancialAssistApplication(
            @RequestPart("reqData") @Valid FinAssistReqData reqData,
            @ModelAttribute @Valid FinAssistAttachmentReqData attachmentData
    ) throws IOException {

        testService.saveUpdate();
        System.out.println("files uploaded");
        System.out.println(reqData);
        System.out.println(attachmentData);
        return new ResponseEntity<>("Financial Assistance Application saved successfully", HttpStatus.OK);
    }


    @PostMapping(value = "/test")
    public ResponseEntity<?> saveReceiverInfo(@ModelAttribute @Valid FinancialAssistanceRequest request) throws IOException {
        // Now you can access all fields through the request object
        List<FaReceiverInfoReqData> reqDatas = request.getReqDatas();
        Long faWageEarnerId = request.getFaWageEarnerId();
        List<MultipartFile> nids = request.getNids();
        List<MultipartFile> photos = request.getPhotos();
        MultipartFile investigationReport = request.getInvestigationReport();
        MultipartFile inheritanceCert = request.getInheritanceCert();
        MultipartFile authLetter = request.getAuthLetter();
        MultipartFile nocCert = request.getNocCert();
        MultipartFile other = request.getOther();

        testService.test();
        // Your processing logic here
        System.out.println("OK");
        return ResponseEntity.ok("Success");
    }


    @PostMapping(value = "/param-test")
    public ResponseEntity<?> attachmentSaveUpdate(@RequestParam("encApplicationId") String encApplicationId,
                                                  @RequestParam("file") MultipartFile file,
                                                  @RequestParam("docId") Long docTypeId) {
        return ResponseEntity.ok(testService.requestParamTest(encApplicationId, file, docTypeId));
    }

    @PostMapping(value = "/file-inside-object")
    public ResponseEntity<?> fileInsideObject(@Valid @ModelAttribute RequestWithFile request) {
        return ResponseEntity.ok(testService.fileInsideObjectTest(request));
    }
}
