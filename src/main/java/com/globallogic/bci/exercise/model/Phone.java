package com.globallogic.bci.exercise.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "PHONES")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long number;
	private int citycode;
	private String countrycode;
}
