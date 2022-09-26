package filterview;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.util.LangUtils;

import model.Lista;

@ManagedBean(name = "dtFilterView")
@ViewScoped
public class FilterView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Lista> lista;

	private boolean globalFilterOnly;

	@PostConstruct
	public void init() {
		globalFilterOnly = false;
	}


	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (LangUtils.isBlank(filterText)) {
			return true;
		}
		
		 int filterInt = getInteger(filterText);

		Lista listar = (Lista) value;
		return listar.getData().toLowerCase().contains(filterText) || listar.getId_lista() < filterInt;
	}
	
	public void toggleGlobalFilter() {
		setGlobalFilterOnly(!isGlobalFilterOnly());
	}
	
	
	private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }

	public List<Lista> getLista() {
		return lista;
	}

	public boolean isGlobalFilterOnly() {
		return globalFilterOnly;
	}

	public void setGlobalFilterOnly(boolean globalFilterOnly) {
		this.globalFilterOnly = globalFilterOnly;
	}

	public void setLista(List<Lista> lista) {
		this.lista = lista;
	}
	

}