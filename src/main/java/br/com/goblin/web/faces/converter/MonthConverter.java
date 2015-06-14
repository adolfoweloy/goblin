package br.com.goblin.web.faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.goblin.domain.general.Month;

@FacesConverter(forClass = Month.class)
public class MonthConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return Month.fromString(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		
		if (obj instanceof Month) {
			Month month = (Month) obj;
			return Integer.toString(month.getYearMonth());
		}
		
		return null;
	}

}
