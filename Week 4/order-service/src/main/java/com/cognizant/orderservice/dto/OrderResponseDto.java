package com.cognizant.orderservice.dto;

import com.cognizant.orderservice.entity.Order;

public class OrderResponseDto {
    private Order order;
    private UserDto user;

    public OrderResponseDto() {}

    public OrderResponseDto(Order order, UserDto user) {
        this.order = order;
        this.user = user;
    }

    public Order order() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
