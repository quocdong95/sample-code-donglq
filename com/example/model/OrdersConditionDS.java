package com.example.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Restacking order search condition
 */
@Getter
@Setter
public class OrdersConditionDS {
    /**
     * list of restacking order status
     */
    private List<String> statusValues;

}
