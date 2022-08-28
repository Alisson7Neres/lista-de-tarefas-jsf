package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.sun.istack.NotNull;

@Entity
//consulta JPQL (Java Persistence Query Language
@NamedQueries({
	@NamedQuery(name = "Usuario.porId", query = "select u from Usuario u where" + " u.id_usuario = :id_usuario")
})
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_usuario;

	@NotNull
	private String nome;

	@NotNull
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

}
