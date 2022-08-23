package model;

import java.util.Date;

import javax.persistence.Entity;

import com.sun.istack.NotNull;

@Entity
public class Lista {

	@NotNull
	private Date data;
	@NotNull
	private Date hora;
	@NotNull
	private String status;

	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	public String isStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
