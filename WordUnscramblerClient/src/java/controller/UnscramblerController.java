/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
    private String letters;
    private ArrayList<String> foundWords = new ArrayList<>();
    private WordsHuClient wordsHuClient;

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }
    
    public void searchWords(){
        wordsHuClient = new WordsHuClient();
        this.foundWords = wordsHuClient.wordUnscrambler_JSON(ArrayList.class, this.letters);
        wordsHuClient.close();
        this.letters = "";
    }

    public ArrayList<String> getFoundWords() {
        return foundWords;
    }
}
