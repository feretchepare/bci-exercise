package com.globallogic.bci.exercise.model.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;

import org.springframework.stereotype.Component;

import com.globallogic.bci.exercise.model.Phone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class LoginResponse {
	private Long id;
	private LocalDateTime created;
	private LocalDateTime lastLogin;
	private String token;
	private Boolean isActive;
	private String name;
	private String email;
	private String password;
	private List<Phone> phones;

	public String getCreated() {
		if (this.created != null) {
			return created.format(getDateFormatter());
		}
		return null;
	}

	public String getLastLogin() {
		if (this.lastLogin != null) {
			return this.lastLogin.format(getDateFormatter());
		}
		return null;
	}

	private DateTimeFormatter getDateFormatter() {
		return new DateTimeFormatterBuilder().appendText(ChronoField.MONTH_OF_YEAR).appendLiteral(" ")
				.appendValue(ChronoField.DAY_OF_MONTH).appendLiteral(", ").appendValue(ChronoField.YEAR, 4)
				.appendLiteral(" ").appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral(":")
				.appendValue(ChronoField.MINUTE_OF_HOUR, 2).appendLiteral(":")
				.appendValue(ChronoField.SECOND_OF_MINUTE, 2).appendLiteral(" ").appendText(ChronoField.AMPM_OF_DAY)
				.toFormatter();
	}

}
