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
	private boolean status;
	@Column(nullable = false)
	private String statusString = "";
	@Column(nullable = false)
	private int[] importancia = { 0 };
	@Column(nullable = false)
	private String importanciaString = "";

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getStatusString() {
		if (status != false) {
			statusString = "Concluído";
		} else {
			statusString = "Andamento";
		}
		return statusString;
	}

	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}

	public int[] getImportancia() {
		return importancia;
	}

	public void setImportancia(int[] importancia) {
		this.importancia = importancia;
	}

	public String getImportanciaString() {
		if (importancia[0] == 1) {
			importanciaString = "Baixa";
		}
		if (importancia[0] == 2) {
			importanciaString = "Média";
		}
		if (importancia[0] == 3) {
			importanciaString = "Alta";
		}
		
		return importanciaString;
	}

	public void setImportanciaString(String importanciaString) {
		this.importanciaString = importanciaString;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
