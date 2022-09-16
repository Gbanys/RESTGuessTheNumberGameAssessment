package com.example.demo;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int gameId;
	private String answer;
	private boolean status;
	
	@OneToMany(mappedBy="roundId", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Round> rounds;
	
	public boolean getStatus() {
		return this.status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}

