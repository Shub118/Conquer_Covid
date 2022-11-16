package com.covid.Model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
@Entity
public class CurrentUserSession {
	
	@Id
	private Integer UserId;
	private String uuid;
	private LocalDateTime timeStamp;
	private String Type;
}
