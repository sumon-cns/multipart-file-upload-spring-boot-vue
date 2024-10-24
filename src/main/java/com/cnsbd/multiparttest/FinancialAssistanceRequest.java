package com.cnsbd.multiparttest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialAssistanceRequest {

    @Valid
    private List<FaReceiverInfoReqData> reqDatas; // List of receiver info

    private Long faWageEarnerId; // Wage earner ID

    private List<MultipartFile> nids; // NID files
    private List<MultipartFile> photos; // Photo files
    private MultipartFile investigationReport; // Investigation report
    private MultipartFile inheritanceCert; // Inheritance certificate
    private MultipartFile authLetter; // Authorization letter
    private MultipartFile nocCert; // NOC certificate
    private MultipartFile other; // Other files
}
