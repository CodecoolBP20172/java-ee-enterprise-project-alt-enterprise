package com.codecool.restauratio.models;

import java.util.List;

public class Order extends Request{
    private int id;
    private String adress;
    private List<Food> foodList;
}
