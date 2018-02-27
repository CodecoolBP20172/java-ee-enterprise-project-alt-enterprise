package com.codecool.restauratio.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ConnectToDBFailed extends Exception {
    public ConnectToDBFailed(String s){
        super(s);
    }
}
