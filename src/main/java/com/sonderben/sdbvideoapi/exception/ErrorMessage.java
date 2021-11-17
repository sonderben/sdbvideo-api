package com.sonderben.sdbvideoapi.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
public class ErrorMessage {
    ZonedDateTime timestamp;
    int status;
    private String error;
    private String path;

    public ErrorMessage(String error,String path,int status) {
        timestamp= ZonedDateTime.now(ZoneId.of("Z"));
        this.error=error;
        this.path=path;
        this.status=status;
    }
}
