package com.codecool.restauratio.customException;

public class ConnectToDBFailed extends Exception {
    public ConnectToDBFailed(String s){
        super(s);
    }
}
