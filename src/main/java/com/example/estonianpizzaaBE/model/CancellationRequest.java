package com.example.estonianpizzaaBE.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.estonianpizzaaBE.model.order.Order;

@Entity
@Table(name = "orderCancellations")
public class CancellationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "cancellationRequest")
    private Order order;
    @Column(name="approved")
    private Boolean approved;

    public Boolean isApproved() {
        return this.approved;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getApproved() {
        return this.approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public CancellationRequest() {
        this.approved = false;
    }

    public CancellationRequest(Boolean approved) {
        this.approved = approved;
    }

}
