package com.example.gatewayservice.exceptionhandle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorCus  {

    private LocalDateTime timestamp;
   private String message;


}
