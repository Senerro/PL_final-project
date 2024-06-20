package com.example.newMock.Model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.NonNullFields;

@Data
@Builder
public class RequestDTO {
    private String rqUID;
    private String clientId;
    private String account;
    private String openDate;
    private String closeDate;
}
