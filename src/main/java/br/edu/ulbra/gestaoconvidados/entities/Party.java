package br.edu.ulbra.gestaoconvidados.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Party {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private User owner;
	
	@Column(nullable = false)
	private String name;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dateTime;

	@Column(nullable = false)
	private String address;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Guest> guests;

	public Party() {}
	
	public Party(Long id) {
		this.id = id;
	}

	public Party(Long id, User owner, Date dateTime, String address, List<Guest> guests, String name) {
		this.id = id;
		this.owner = owner;
		this.dateTime = dateTime;
		this.address = address;
		this.guests = guests;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Guest> getGuests() {
		return guests;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public void setGuests(List<Guest> guests) {
		this.guests = guests;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
