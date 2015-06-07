/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.goblin.web.payable;

import br.com.goblin.domain.account.AccountPayable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import static br.com.goblin.persistence.JpaUtil.getEntityManager;

/**
 * Converter for AccountPayable
 * @author aeloy
 */
@FacesConverter(forClass = AccountPayable.class)
public class AccountPayableConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        
        if (value != null) {
            Long id = Long.valueOf(value);
            try {
                AccountPayable payable = getEntityManager().find(AccountPayable.class, id);
                return payable;
            } catch (Exception e) {
                return null;
            }
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        
        if (o instanceof AccountPayable) {
            AccountPayable payable = (AccountPayable) o;
            return payable.getId().toString();
        }
        
        return null;
    }
    
}
