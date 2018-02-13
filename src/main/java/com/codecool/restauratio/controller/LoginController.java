package com.codecool.restauratio.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

    public static ModelAndView renderLogin(Request request, Response response, Boolean isLogin) {

        Map params = new HashMap<>();
        params.put( "login", isLogin );

        return new ModelAndView( params, "login" );
    }

    public static ModelAndView renderRegister(Request request, Response response, Boolean isRegister) {
        Map params = new HashMap<>();
        params.put( "register", isRegister );

        return new ModelAndView( params, "login" );
    }



}
