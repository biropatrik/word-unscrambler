/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Patrik
 */
@ManagedBean(name = "navigationbarController", eager = true)
public class NavigationbarController {
    
    public String getPageName(){
        return SessionController.getCurrentPage();
    }
    
    public String wordUnscrambler(){
        SessionController.setCurrentPage("wordUnscrambler");
        return "word-unscrambler.xhtml?faces-redirect=true"; 
    }
    
    public String associationChain(){
        SessionController.setCurrentPage("associationChain");
        return "association-chain.xhtml?faces-redirect=true"; 
    }
    
    public String options(){
        SessionController.setCurrentPage("options");
        return "options.xhtml?faces-redirect=true";
    }
}
