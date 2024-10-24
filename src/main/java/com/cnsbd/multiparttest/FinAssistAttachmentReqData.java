package com.cnsbd.multiparttest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinAssistAttachmentReqData {
    private MultipartFile demandLetter;
    private MultipartFile passport;
    private MultipartFile embassyCertificate;
    private MultipartFile inheritanceCertificate;
    private MultipartFile deathCertificate;
    private MultipartFile airwaysBill;
    private MultipartFile powerOfAttorney;

}
