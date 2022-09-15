package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Lista {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_lista;
	@Column(nullable = false)
	private String data;
	@Column(nullable = false)
	private String hora;
	@Column(nullable = false)
	private String status;

	@ManyToOne(optional = false, fetch = FetchType.EAGER) // Relacionamento de muitos para um
	private Usuario usuario;

	public int getId_lista() {
		return id_lista;
	}

	public void setId_lista(int id_lista) {
		this.id_lista = id_lista;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getStatus() {
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
