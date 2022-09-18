package bean;

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
		String coduser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("codigouser");
		user = usuarioDao.pesquisar(Integer.parseInt(coduser), Usuario.class);
		if (coduser == null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("bem-vindo.jsf");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String salvar() {
		telefone.setUsuario(user);

		dao.salvar(telefone);
		telefone = new Telefone();
		user = usuarioDao.pesquisar(user.getId_usuario(), Usuario.class);
		return "";
	}

	public void novo() {
		telefone = new Telefone();
	}

	public void deletarPorId() {
		dao.deletarPorId(telefone);
		user = usuarioDao.pesquisar(user.getId_usuario(), Usuario.class);
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
