package com.xive.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Arman on Feb  06, 2020
 * Feel free to email me: arman.fazylov@gmail.com
 */

@Entity
@Table(name = "book")
@Data
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity {
    @NotNull
    @Size(min = 1)
    @Column(name = "title", nullable = false)
    private String title;

    @Size(min = 1, max = 2048)
    @Column(name = "description", length = 2048)
    private String description;

    @Column(name = "first_50_paragraphs", columnDefinition="text")
    private String first50Paragraphs;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;
}
