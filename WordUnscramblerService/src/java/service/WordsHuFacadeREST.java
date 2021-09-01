/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.WordsHu;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Patrik
 */
@Stateless
@Path("entity.wordshu")
public class WordsHuFacadeREST extends AbstractFacade<WordsHu> {

    @PersistenceContext(unitName = "WordUnscramblerServicePU")
    private EntityManager em;

    public WordsHuFacadeREST() {
        super(WordsHu.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(WordsHu entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, WordsHu entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public WordsHu find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<WordsHu> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<WordsHu> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("wordUnscrambler/{letters}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<String> wordUnscrambler(@PathParam("letters") String letters) {
        ArrayList<String> words = (ArrayList<String>) em.createNamedQuery("WordsHu.findByLength")
                                    .setParameter("length", 4)
                                    .getResultList();
        ArrayList<String> foundWords = new ArrayList<>();
        
        if(letters == null || words == null)
            return foundWords;
        
        words.forEach((word) -> {
            char[] charsOfWord = word.toCharArray();
            int numberOfMatches = word.length();
            
            for(int i=0; i<letters.length(); i++){
                for(int j=0; j<charsOfWord.length; j++){
                    
                    if(letters.charAt(i) == charsOfWord[j]){
                        numberOfMatches--;
                        charsOfWord[j] = '_';
                        break;
                    }
                }
            }
            if (numberOfMatches == 0) {
                foundWords.add(word);
            }
        });
        return foundWords;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
