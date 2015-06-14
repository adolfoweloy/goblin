package br.com.goblin.web.payable;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import lombok.Getter;
import lombok.Setter;
import br.com.goblin.domain.general.Month;

@ManagedBean
public class AccountsPayableSearch implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	private Month month;
	
}
