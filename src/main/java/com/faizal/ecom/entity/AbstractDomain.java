package com.faizal.ecom.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;

@Data
@Getter
@Setter
@MappedSuperclass
public class AbstractDomain implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDate;

    @Column(updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifiedDate;

    private Status status;

    @PrePersist
    public void setCreatedDate () {
        this.createdDate = Calendar.getInstance(TimeZone.getDefault());
        this.modifiedDate = Calendar.getInstance(TimeZone.getDefault());
    }

    @PreUpdate
    public void setModifiedDate () {
        this.modifiedDate = Calendar.getInstance(TimeZone.getDefault());
    }

}
