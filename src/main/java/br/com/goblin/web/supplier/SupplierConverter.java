package br.com.goblin.web.supplier;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.goblin.domain.supplier.Supplier;
import br.com.goblin.persistence.JpaUtil;

@FacesConverter(forClass=Supplier.class)
public class SupplierConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value)
			throws ConverterException {
		
		if (value != null) {
			EntityManager em = JpaUtil.getEntityManager();
			return em.find(Supplier.class, Long.valueOf(value));
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj)
			throws ConverterException {
		
		if (obj instanceof Supplier) {
			Supplier supplier = (Supplier) obj;
			return supplier.getId().toString();
		}
		
		return null;
	}

}
