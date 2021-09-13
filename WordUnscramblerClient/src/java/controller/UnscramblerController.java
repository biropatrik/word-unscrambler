/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import client.WordsClient;
import client.WordsEnClient;
import client.WordsHuClient;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Patrik
 */
@ManagedBean(name="unscramblerController", eager = true)
@SessionScoped
public class UnscramblerController {
    private WordsClient wordsClient;
    private String letters;
    private ArrayList<String> foundWords = new ArrayList<>();

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }
    
    public ArrayList<String> getFoundWords() {
        return foundWords;
    }
    
    public void searchWordsFromLetters(){
        wordsClient = getWordsClientBySelectedLanguage();
        this.foundWords = wordsClient.wordUnscrambler_JSON(ArrayList.class, this.letters);
        wordsClient.close();
        this.letters = "";
    }
    
    private WordsClient getWordsClientBySelectedLanguage(){
        WordsClient client = new WordsHuClient(); //Default
        
        switch(SessionController.getLanguage()){
            case "EN":
                client = new WordsEnClient();
                break;
        }
        
        return client;
    }
}
