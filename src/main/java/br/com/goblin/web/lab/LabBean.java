/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.goblin.web.lab;

import br.com.goblin.domain.account.AccountPayable;
import javax.faces.bean.ManagedBean;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author aeloy
 */
@ManagedBean
public class LabBean {
    
    @Getter @Setter
    private AccountPayable payable;

    public LabBean() {}
    
    public String execute() {
        System.out.println(payable);
        return "home";
    }
}
