package com.bezkoder.springjwt.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class StandardErrorResponse {

    private String code;
    private String message;

}
