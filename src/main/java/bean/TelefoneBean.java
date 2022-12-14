package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.TelefoneDAO;
import dao.UsuarioDAO;

import java.io.IOException;

import javax.annotation.PostConstruct;
import model.Telefone;
import model.Usuario;

@ManagedBean(name = "telefoneBean")
@ViewScoped
public class TelefoneBean {

	private Usuario user = new Usuario();
	private UsuarioDAO<Usuario> usuarioDao = new UsuarioDAO<Usuario>();

	private TelefoneDAO<Telefone> dao = new TelefoneDAO<Telefone>();

	private Telefone telefone = new Telefone();

	@PostConstruct
	public void init() {
		// A variável coduser(coidigouser) vai fazer regerência ao id do usuário
		String coduser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("codigouser");
		// Se a variável for nula, irá retornar a página inicial do sistema
		if (coduser == null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
			} catch (IOException e) {
				e.printStackTrace();
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("error.jsf");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else { // Se id for diferente de nulo, irá pesquisar o id pelo usuário
			user = usuarioDao.pesquisar(Integer.parseInt(coduser), Usuario.class);
		}
	}

	public String salvar() throws IOException {
		telefone.setUsuario(user);

		dao.salvar(telefone);
		telefone = new Telefone();
		user = usuarioDao.pesquisar(user.getId_usuario(), Usuario.class);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Telefone salvo!", "salvo"));
		return "";
	}

	public void novo() {
		telefone = new Telefone();
	}

	public void deletarPorId() {
		dao.deletarPorId(telefone);
		user = usuarioDao.pesquisar(user.getId_usuario(), Usuario.class);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "Telefone excluído!", "excluído"));
		if (true) {
			telefone = new Telefone();
		}
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public TelefoneDAO<Telefone> getDao() {
		return dao;
	}

	public void setDao(TelefoneDAO<Telefone> dao) {
		this.dao = dao;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public UsuarioDAO<Usuario> getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDAO<Usuario> usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

}
