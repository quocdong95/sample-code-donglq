package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.entity.Order;
import com.example.model.IOptionDS;
import com.example.model.ProcessingStatus;
import com.example.model.OrdersConditionDS;
import com.example.model.OrdersDS;
import com.example.repo.OrderRepository;
import com.example.repo.StationRepository;
import com.example.repo.UserRepository;
import com.example.utils.MessageUtils;

/**
 * OrderServiceImpl
 */
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * Message Source.
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * Order Repository
     */
    @Autowired
    private OrderRepository ordersRepo;

    /**
     * User repository
     */
    @Autowired
    private UserRepository userRepo;

    /**
     * Station repository
     */
    @Autowired
    private StationRepository stationRepo;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrdersDS> getOrders(OrdersConditionDS condition) {
        List<OrdersDS> results = new ArrayList<>();

        // Create Map between value in db and label displayed on GUI for: status, station and creator
        Map<String, String> statusMap = new HashMap<String, String>();
        for (ProcessingStatus status : ProcessingStatus.FOR_ORDER) {
            statusMap.put(status.getKey(), MessageUtils.getMessage(messageSource, status.getLabel()));
        }
        Map<Integer, String> userMap = new HashMap<>();
        List<IOptionDS> allUser = userRepo.getAllUserOptions();
        for (IOptionDS user : allUser) {
            userMap.put(Integer.parseInt(user.getValue()), user.getLabel());
        }
        Map<Integer, String> stationMap = new HashMap<>();
        List<IOptionDS> allStation = stationRepo.getAllStationOptions();
        for (IOptionDS station : allStation) {
            stationMap.put(Integer.parseInt(station.getValue()), station.getLabel());
        }

        //  Call repository to handle searching
        List<Order> orders = null;
        if (CollectionUtils.isEmpty(condition.getStatusValues())) {
            orders = ordersRepo.findTop10000ByOrderByCreatedDateDesc();
        } else {
            orders = ordersRepo.findTop10000ByStatusInOrderByCreatedDateDesc(condition.getStatusValues());
        }

        // Set label for: status, station and creator
        if (!CollectionUtils.isEmpty(orders)) {
            for (Order order : orders) {
                order.setStatusLabel(statusMap.get(order.getStatus().getKey()));
                order.setStationLabel(stationMap.get(order.getStationId()));
                order.setCreatorLabel(userMap.get(order.getCreator()));

                results.add(new OrdersDS(order));
            }
        }

        return results;
    }


}
