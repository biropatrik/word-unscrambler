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
@ManagedBean(name = "associationChainController", eager = true)
@SessionScoped
public class AssociationChainController {
    private WordsClient wordsClient;
    private ArrayList<String> originAssociationWords = new ArrayList<>();
    private ArrayList<String> inputAssociationWords = new ArrayList<>();
    private int associationChainButtonState = -1;
    private String associationChainButtonName = "Új játék!";
    private int points;
    
    public ArrayList<String> getInputAssociationWords() {
        return inputAssociationWords;
    }

    public void setInputAssociationWords(ArrayList<String> inputAssociationWords) {
        this.inputAssociationWords = inputAssociationWords;
    }

    public String getAssociationChainButtonName() {
        return associationChainButtonName;
    }

    public int getAssociationChainButtonState() {
        return associationChainButtonState;
    }

    public ArrayList<String> getOriginAssociationWords() {
        return originAssociationWords;
    }
    
    public String getPoints(){
        return "Elértpont: " + points;
    }
    
    public void runAssociationChainGame(){
        associationChainButtonState++;
        if(associationChainButtonState > 2)
            associationChainButtonState = 0;
        
        switch(associationChainButtonState){
            case 0:
                points = 0;
                getTenRandomWords();
                associationChainButtonName = "START";
                break;
            case 1:
                startAssociationChainGame();
                associationChainButtonName = "Ellenőrzés";
                break;
            case 2:
                checkAssociationChainGame();
                associationChainButtonName = "Új játék!";
                break;
        }
    }

    private void getTenRandomWords(){
        originAssociationWords = new ArrayList<>();
        inputAssociationWords = new ArrayList<>();
        wordsClient = getWordsClientBySelectedLanguage();
        originAssociationWords = wordsClient.getTenRandomWords_JSON(ArrayList.class);
        wordsClient.close();
        inputAssociationWords.addAll(originAssociationWords);
    }
    
    private void startAssociationChainGame(){
        inputAssociationWords.clear();
        for(int i=0; i<originAssociationWords.size(); i++){
            inputAssociationWords.add(new String());
        }
    }
    
    private void checkAssociationChainGame(){
        for(int i=0; i<inputAssociationWords.size(); i++){
            if(inputAssociationWords.get(i).equals(originAssociationWords.get(i)))
                points++;
        }
    }
    
    public boolean checkAssociationWords(int index){
        return (index < inputAssociationWords.size() && originAssociationWords.size() == inputAssociationWords.size()) 
                && (inputAssociationWords.get(index).equals(originAssociationWords.get(index)));
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
