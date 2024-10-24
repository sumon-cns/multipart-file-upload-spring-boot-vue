package com.cnsbd.multiparttest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinAssistReqData {

    @NotNull(message = "WAGE EARNER CAN NOT BE NULL")
    private Long faWageEarnerId;

    @NotNull(message = "BURIAL REF DATA IS REQUIRED!")
    private FaBurialRefReqData burialRef;
}
