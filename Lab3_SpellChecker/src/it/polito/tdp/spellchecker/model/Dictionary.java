package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
   private List<String> dizionario;
   
   public Dictionary(String language){
	   this.dizionario=new ArrayList<>();
	   this.loadDictionary(language);
   }
	
	
	public void loadDictionary(String language) {
		try {
			 FileReader fr = new FileReader("rsc/"+language+".txt");
			BufferedReader br = new BufferedReader(fr);
			 String word;
			 while ((word = br.readLine()) != null) {
			dizionario.add(word);
			 }
			br.close();

			 } catch (IOException e){
			 System.out.println("Errore nella lettura del file");
			}
	}
	
	public boolean checkWord (String parola){
		boolean risultato = false;
		for(String s : dizionario){
			if(parola.compareTo(s)==0){
				risultato = true;
				break;
			}
		}
		return risultato;
	}

	public List<RichWord> spellCheckText (List<String> inputTextList){
		List<RichWord> temp = new ArrayList<>();
		for(String s : inputTextList){
			temp.add(new RichWord(s,this.checkWord(s)));
		}
		return temp;
	}



}
