package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

@Entity
//consulta JPQL (Java Persistence Query Language
@NamedQueries({
	@NamedQuery(name = "Usuario.todos", query = "select u from Usuario u"),
	@NamedQuery(name = "Usuario.buscaPorNome", query = "select u from Usuario u where" +
	 " u.nome = :nome")
})
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_usuario;

	@NotNull
	private String nome;

	@NotNull
	private String email;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER) // Relacionamento de um para muitos
	private List<Telefone> telefoneUsers = new ArrayList<Telefone>();

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
	
	public List<Telefone> getTelefoneUsers() {
		return telefoneUsers;
	}
	
	public void setTelefoneUsers(List<Telefone> telefoneUsers) {
		this.telefoneUsers = telefoneUsers;
	}

}
