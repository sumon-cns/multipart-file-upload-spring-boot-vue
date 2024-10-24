package com.cnsbd.multiparttest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FaBurialRefReqData {
    private Long faBurialRefId;

    @NotNull(message = "AIRPORT NOTHI NO IS REQUIRED!")
    private Long airportNothiNo;

    @NotBlank(message = "REGISTRATION TYPE IS REQUIRED!")
    private String registrationType;

    @NotNull(message = "FILE NO IS REQUIRED!")
    private Long fileNo;

    @NotNull(message = "E-NOTHI NO IS REQUIRED!")
    private Long eNothiNo;

    private String refPassportNo;

    private Long applicationRefId;

    private String notRecReason;
}
