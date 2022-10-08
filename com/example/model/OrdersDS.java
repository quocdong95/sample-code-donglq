package com.example.model;

import java.io.Serializable;
import java.util.Date;

import com.example.entity.Order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * OrdersDS
 */
@Getter
@Setter
@NoArgsConstructor
public class OrdersDS implements Serializable {

    /**
     * Restacking order id
     */
    private Long id;
    /**
     * Restacking order number
     */
    private String orderNo;
    /**
     * Restacking order status
     */
    private String status;
    /**
     * Station name
     */
    private String stationName;
    /**
     * Creator name
     */
    private String creator;
    /**
     * Created date
     */
    private Date createdDate;

    /**
     * Constructor
     *
     * @param order Restacking order entity
     */
    public OrdersDS(Order order) {
        super();
        this.id = order.getId();
        this.orderNo = order.getOrderNo();
        this.status = order.getStatusLabel();
        this.stationName = order.getStationLabel();
        this.creator = order.getCreatorLabel();
        this.createdDate = order.getCreatedDate();
    }

}
