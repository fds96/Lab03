package it.polito.tdp.spellchecker.controller;


import java.util.*;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {

	
	private Dictionary dizionario;
	
	List<String> listaDaCorreggere = new LinkedList<String>();

    @FXML
    private TextArea txtInsert;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtResult;

    @FXML
    private ComboBox<String> menuLingua;
    @FXML
    private Label txtContains;

    @FXML
    private Button btnClearText;

    @FXML
    private Label TxtSeconds;

    @FXML
    void doClearText(ActionEvent event) {
    txtResult.clear();
    }
    
	@FXML
    void doSpellCheck(ActionEvent event) {
		if(menuLingua.getValue()==null){
			txtResult.appendText("Selezionare una lingua!\n");
		}
		else{
		dizionario= new Dictionary (menuLingua.getValue());
		String testo = txtInsert.getText();
		//testo.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]]\\", "");
    	testo.trim().toLowerCase();
    	StringTokenizer st = new StringTokenizer(testo, " ");
		while (st.hasMoreTokens()) {
			listaDaCorreggere.add(st.nextToken());
		}    	
		long l1 = System.nanoTime();
		List<RichWord> lista = dizionario.spellCheckText(listaDaCorreggere);
		long l2 = System.nanoTime();
		int err=0;
		for(RichWord w : lista){
			if(!w.isCorretta()) {
				err++;
				txtResult.appendText(w.getParola()+"\n");
			}
		}
		txtContains.setText("The text contains " + err + " errors!");
		TxtSeconds.setText("Spell check completed in " + (l2 - l1) / 1E9 + " seconds");
		listaDaCorreggere.clear();
		lista.clear();
		}
    }
	
	@FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtInsert != null : "fx:id=\"txtInsert\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtContains != null : "fx:id=\"txtContains\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert menuLingua != null : "fx:id=\"menuLingua\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert TxtSeconds != null : "fx:id=\"TxtSeconds\" was not injected: check your FXML file 'SpellChecker.fxml'.";

        menuLingua.getItems().addAll("English", "Italian");
        
        
    }
}
