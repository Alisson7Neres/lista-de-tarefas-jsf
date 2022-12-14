package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OrderBy;

import com.sun.istack.NotNull;

@Entity
//consulta JPQL (Java Persistence Query Language
@NamedQueries({ @NamedQuery(name = "Usuario.todos", query = "select u from Usuario u"),
		@NamedQuery(name = "Usuario.buscaPorNome", query = "select u from Usuario u where" + " u.nome = :nome") })
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_usuario;

	@NotNull
	private String nome;

	@NotNull
	private String email;

	@Column(nullable = false)
	private String senha;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY) // Relacionamento de um para muitos
	private List<Telefone> telefoneUsers = new ArrayList<Telefone>();

	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER) // Relacionamento de um para muitos
	@OrderBy(clause = "status")
	private List<Lista> listaUsers = new ArrayList<Lista>();

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public List<Telefone> getTelefoneUsers() {
		return telefoneUsers;
	}

	public void setTelefoneUsers(List<Telefone> telefoneUsers) {
		this.telefoneUsers = telefoneUsers;
	}

	public List<Lista> getListaUsers() {
		return listaUsers;
	}

	public void setListaUsers(List<Lista> listaUsers) {
		this.listaUsers = listaUsers;
	}

}
