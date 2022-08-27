package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

@Entity
public class Lista {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_lista;
	@NotNull
	private Date data;
	@NotNull
	private Date hora;
	@NotNull
	private String status;

	@ManyToOne(optional = false, fetch = FetchType.EAGER) // Relacionamento de muitos para um
	private Usuario usuario;

	public int getId_lista() {
		return id_lista;
	}

	public void setId_lista(int id_lista) {
		this.id_lista = id_lista;
	}

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
