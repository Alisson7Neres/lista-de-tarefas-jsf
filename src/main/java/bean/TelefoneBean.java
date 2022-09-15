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

	private Usuario usuario = new Usuario();
	private UsuarioDAO<Usuario> usuarioDao = new UsuarioDAO<Usuario>();

	private TelefoneDAO<Telefone> dao = new TelefoneDAO<Telefone>();

	private Telefone telefone = new Telefone();

	@PostConstruct
	public void init() throws IOException {
		String coduser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("codigouser");
		usuario = usuarioDao.pesquisar(Integer.parseInt(coduser), Usuario.class);
		if (coduser == null) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("bem-vindo.jsf");
		}
	}
	
	public String salvar() {
		telefone.setUsuario(usuario);
	
		dao.salvar(telefone);
		telefone = new Telefone();
		usuario = usuarioDao.pesquisar(usuario.getId_usuario(), Usuario.class);
		return "";
	}
	
	public void novo() {
		telefone = new Telefone();
	}

	public void deletarPorId() {
		dao.deletarPorId(telefone);
		usuario = usuarioDao.pesquisar(usuario.getId_usuario(), Usuario.class);
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDAO<Usuario> getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDAO<Usuario> usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

}
