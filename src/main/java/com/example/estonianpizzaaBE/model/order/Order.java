package com.example.estonianpizzaaBE.model.order;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.estonianpizzaaBE.model.CancellationRequest;
import com.example.estonianpizzaaBE.model.MenuItem;


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    @Column(name = "customerId")
    private long customerId;
    @Column(name = "startDate")
    private Instant startDate;
    @Column(name = "endDate")
    private Instant endDate;
    @Column(name = "status")
    private OrderStatus status;
    @Column(name = "type")
    private OrderType type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cancellation_id", referencedColumnName = "id")
    private CancellationRequest cancellationRequest;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    public Order(long orderId,
            Instant startDate,
            Instant endDate,
            OrderStatus status,
            OrderType type,
            CancellationRequest cancellationRequest,
            List<CartItem> cartItems) {
        this.orderId = orderId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.type = type;
        this.cancellationRequest = cancellationRequest;
        this.cartItems = cartItems;
    }

    public Order(OrderStatus status,
            OrderType type,
            List<MenuItem> shoppingCart,
            long customerId
    ) {
        this.customerId = customerId;
        this.startDate = Instant.now();
        this.endDate = null;
        this.status = status;
        this.type = type;
        this.cancellationRequest = null;
        this.cartItems = new ArrayList<CartItem>();
        for (MenuItem item : shoppingCart) 
        {
            CartItem cartItem = new CartItem(item, this);
            this.cartItems.add(cartItem);
        }
    }

    public long getOrderId() {
        return this.orderId;
    }

    public Instant getStartDate() {
        return this.startDate;
    }

    public Instant getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderType getType() {
        return this.type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    // public long getCustomerId() {
    // return this.customerId;
    // }

    // public void setCustomerId(long customerId) {
    // this.customerId = customerId;
    // }

    public CancellationRequest getCancellationRequest() {
        return this.cancellationRequest;
    }

    public void setCancellationRequest(CancellationRequest cancellationRequest) {
        this.cancellationRequest = cancellationRequest;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }
    
    public List<CartItem> getCartItems() {
        return this.cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }


    public long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }


    public Order() {
        this.startDate = Instant.now();
        this.endDate = null;
        this.status = null;
        this.type = null;
        this.cancellationRequest = null;
    }
    
}
