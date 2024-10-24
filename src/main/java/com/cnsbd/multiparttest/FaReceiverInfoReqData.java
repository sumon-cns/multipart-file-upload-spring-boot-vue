package com.cnsbd.multiparttest;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FaReceiverInfoReqData {

    private Long faRecInfoId;
    @NotEmpty(message = "NAME CAN NOT BE EMPTY!")
    private String receiverName;
    @NotNull(message = "RELATION CAN NOT BE NULL!")
    private Long relationId;
    @NotEmpty(message = "MOBILE NUMBER CAN NOT BE EMPTY!")
    private String phoneNo;
    @NotEmpty(message = "ADDRESS CAN NOT BE EMPTY!")
    private String address;
    @NotNull(message = "BANK DISTRICT CAN NOT BE NULL!")
    private Long districtId;
    @NotNull(message = "BANK CAN NOT BE NULL!")
    private Long bankId;
    @NotNull(message = "BANK DISTRICT CAN NOT BE NULL!")
    private Long branchId;
    @NotEmpty(message = "BANK ACCOUNT CAN NOT BE EMPTY!")
    private String bankAccount;
    @NotNull(message = "AMOUNT CANNOT BE NULL!")
    private Double amount;
    @NotNull(message = "NATIONAL ID CAN NOT BE EMPTY!")
    private Long nid;

    private MultipartFile photo; //optional
}
