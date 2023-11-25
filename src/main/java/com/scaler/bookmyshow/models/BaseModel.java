package com.scaler.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass

// MappedSuperclass  says: is a mapping this base model class need to map this class with all the model classes
// //which are extending this class. this all the attributes should be created as column in the model
// tables which are extending this class.

@EntityListeners(AuditingEntityListener.class)
public class BaseModel {
    // this class will contain the common attribute to all the mnodels.

    @Id  // primary key column
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // auto increment
    private long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedAt;

    @LastModifiedBy
    private String lastModifiedBy;
}
