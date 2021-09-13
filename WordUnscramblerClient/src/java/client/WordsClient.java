/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Patrik
 */
public abstract class WordsClient {
    public abstract <T> T wordUnscrambler_XML(Class<T> responseType, String letters);
    public abstract <T> T wordUnscrambler_JSON(Class<T> responseType, String letters);
    public abstract <T> T getTenRandomWords_XML(Class<T> responseType);
    public abstract <T> T getTenRandomWords_JSON(Class<T> responseType);
    public abstract String countREST();
    public abstract void edit_XML(Object requestEntity, String id);
    public abstract void edit_JSON(Object requestEntity, String id);
    public abstract <T> T find_XML(Class<T> responseType, String id);
    public abstract <T> T find_JSON(Class<T> responseType, String id);
    public abstract <T> T findRange_XML(Class<T> responseType, String from, String to);
    public abstract <T> T findRange_JSON(Class<T> responseType, String from, String to);
    public abstract void create_XML(Object requestEntity);
    public abstract void create_JSON(Object requestEntity);
    public abstract <T> T findAll_XML(Class<T> responseType);
    public abstract <T> T findAll_JSON(Class<T> responseType);
    public abstract void remove(String id);
    public abstract void close();
}
