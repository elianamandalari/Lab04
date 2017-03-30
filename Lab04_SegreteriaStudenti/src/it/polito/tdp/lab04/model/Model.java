package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
    private List<Corso> corsi;
 
   
 
    
   
   
    
    public List<String> getCorsi(){
    	 CorsoDAO dao=new CorsoDAO();
    	 corsi=dao.getTuttiICorsi();
    	List<String> corsiTemp=new LinkedList<String>();
    	String s ="";
   corsiTemp.add(s);
   for(Corso c:corsi)
	  corsiTemp.add(c.getNome());
   
    	return corsiTemp;
    }
	
    public Studente cercaStudente(int matricola){
    	StudenteDAO dao=new StudenteDAO();
    	return dao.find(matricola);
    }

	public List<Studente> studentiIscrittiAiCorsi(String nomeCorso) {
		

		CorsoDAO dao= new CorsoDAO();
		
		for(Corso c : corsi){
			if(c.getNome().equals(nomeCorso)){
				return dao.getStudentiIscrittiAlCorso(c);
		
			}
		}
		return null;
	}
}
