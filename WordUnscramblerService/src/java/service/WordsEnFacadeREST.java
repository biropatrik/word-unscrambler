/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.WordsEn;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
@Path("entity.wordsen")
public class WordsEnFacadeREST extends AbstractFacade<WordsEn> {

    @PersistenceContext(unitName = "WordUnscramblerServicePU")
    private EntityManager em;

    public WordsEnFacadeREST() {
        super(WordsEn.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(WordsEn entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, WordsEn entity) {
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
    public WordsEn find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<WordsEn> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<WordsEn> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
        ArrayList<String> words = (ArrayList<String>) em.createNamedQuery("WordsEn.findByLength")
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

    @GET
    @Path("getTenRandomWords")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<String> getTenRandomWords(){
        ArrayList<String> words = (ArrayList<String>) em.createNamedQuery("WordsEn.findByLength")
                                    .setParameter("length", 4)
                                    .getResultList();
        ArrayList<String> randomWords = new ArrayList<>();
        
        Random rnd = new Random();
        for(int i=0; i<10; i++){
            randomWords.add(words.get(rnd.nextInt(words.size())));
        }
        
        return randomWords;
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
