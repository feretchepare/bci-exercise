package com.globallogic.bci.exercise.model.response;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class ErrorResponseData {
	private Date timestamp;
	private int codigo;
	private String detail;

	public ErrorResponseData(int codigo, String detail) {
		this.timestamp = new Timestamp(System.currentTimeMillis());
		this.codigo = codigo;
		this.detail = detail;
	}
}
