package com.codecool.restauratio.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Accessing another user is forbidden.")
public class AccessForbiddenException extends Exception {
}
