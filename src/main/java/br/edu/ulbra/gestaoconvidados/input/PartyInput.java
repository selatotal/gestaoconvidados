package br.edu.ulbra.gestaoconvidados.input;

import java.util.Date;

public class PartyInput {

	private Long id;
	private Long userId;
	private Date dateTime;
	private String address;
	private String name;
	
	public PartyInput() {}
	
	public PartyInput(Long id) {
		super();
		this.id = id;
	}
	
	public PartyInput(Long id, Long userId, Date dateTime, String address, String name) {
		super();
		this.id = id;
		this.userId = userId;
		this.dateTime = dateTime;
		this.address = address;
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
