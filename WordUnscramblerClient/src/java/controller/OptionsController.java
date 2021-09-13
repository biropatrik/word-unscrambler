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
@ManagedBean(name = "optionsController", eager = true)
@SessionScoped
public class OptionsController {
    
    public String getCurrentLanguage() {
        return SessionController.getLanguage();
    }

    public void setCurrentLanguage(String currentLanguage) {
        SessionController.setLanguage(currentLanguage);
    }
}
