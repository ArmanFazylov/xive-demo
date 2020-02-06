package com.xive.demo.entity;



import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


@Entity
@Table(name = "authority")
@Data
@EqualsAndHashCode(callSuper = true)
public class Authority extends BaseEntity {
	@Enumerated(EnumType.STRING)
	private AuthorityType name;
}

