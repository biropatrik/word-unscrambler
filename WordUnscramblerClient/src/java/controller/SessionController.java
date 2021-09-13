/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Patrik
 */
public class SessionController {
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                        .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                        .getExternalContext().getRequest();
    }

    public static String getLanguage() {
        HttpSession session = getSession();
        if (session != null && session.getAttribute("language") != null)
            return session.getAttribute("language").toString();
        else if(session != null){
            setLanguage("HU");
            return session.getAttribute("language").toString();
        }else
            return null;
    }
    
    public static void setLanguage(String language){
        HttpSession session = getSession();
        session.setAttribute("language", language);
    }
    
        public static String getCurrentPage() {
        HttpSession session = getSession();
        if (session != null && session.getAttribute("page") != null)
            return session.getAttribute("page").toString();
        else if(session != null){
            setCurrentPage("wordUnscrambler");
            return session.getAttribute("page").toString();
        }else
            return null;
    }
    
    public static void setCurrentPage(String page){
        HttpSession session = getSession();
        session.setAttribute("page", page);
    }
}
