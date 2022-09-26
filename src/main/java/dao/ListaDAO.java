package dao;

import java.util.ArrayList;
import java.util.List;

import model.Lista;

public class ListaDAO extends DAOGeneric<Lista>{

	public List<Lista> orderBy() {
		
		List<Lista> lista= new ArrayList<Lista>();
		lista = (List<Lista>) entityManager.createQuery("select*from lista order by importanciastring");
		
		return lista;
	}
}
