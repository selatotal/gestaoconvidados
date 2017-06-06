package br.edu.ulbra.gestaoconvidados.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Guest {

	@EmbeddedId
	private GuestPK id;

	@Column(nullable = false)
	private Boolean confirmed = Boolean.FALSE;

	public Guest() {

	}

	public GuestPK getId() {
		return id;
	}

	public void setId(GuestPK id) {
		this.id = id;
	}

	public Boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Guest(GuestPK id, Boolean confirmed) {
		this.id = id;
		this.confirmed = confirmed;
	}

}
