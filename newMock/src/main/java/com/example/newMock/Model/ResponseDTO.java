package com.example.newMock.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {
    private String rqUID;
    private String clientId;
    private String account;
    private String currency;
    private String balance;
    private String maxLimit;
}
