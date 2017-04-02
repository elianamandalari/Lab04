package it.polito.tdp.lab04.controller;

import java.util.*;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	private Model model;
	List<Corso> corsi = new LinkedList<Corso>();
    
	@FXML
	private ComboBox<String> comboCorso;

	@FXML
	private Button btnCercaIscrittiCorso;

	@FXML
	private Button btnCercaCorsi;

	@FXML
	private Button btnCercaNome;

	@FXML
	private TextArea txtResult;

	@FXML
	private Button btnIscrivi;

	@FXML
	private TextField txtMatricola;

	@FXML
	private Button btnReset;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCognome;

	public void setModel(Model model) {
    this.model=model;

  
	comboCorso.getItems().addAll(model.getCorsi());
	
    
	}
	
	@FXML
	void doReset(ActionEvent event) {
     txtNome.clear();
     txtCognome.clear();
     txtMatricola.clear();
     txtResult.clear();
     
	}

	@FXML
	void doCercaNome(ActionEvent event) {
		int matricola=Integer.parseInt(txtMatricola.getText());
		Studente s=model.cercaStudente(matricola);
		if(s!=null)
		{
			txtNome.setText(s.getNome());
			txtCognome.setText(s.getCognome());
		}
		else
			txtResult.appendText("Matricola non trovata");
		
    	}
    		
	  
	

	@FXML
	void doCercaIscrittiCorso(ActionEvent event) {
		String nomeCorso=comboCorso.getValue();
	    
		if(nomeCorso.length()>1){
			for(Studente s: model.studentiIscrittiAiCorsi(nomeCorso)){
	    		txtResult.appendText(+s.getMatricola()+"      "+s.getNome()+"      "+s.getCognome()+"\n");
	    	}
		}else{
			txtResult.setText("seleziona un corso");
		}
	}

	@FXML
	void doCercaCorsi(ActionEvent event) {
		int matricola=Integer.parseInt(txtMatricola.getText());
		Studente s=model.cercaStudente(matricola);
		String nomeCorso=comboCorso.getValue();

		boolean trovato=false;
        if(s==null)
        	txtResult.setText("La matricola non è presente nel database");
        else
        {  
        	for(Corso c:model.cercaCorsi(matricola))
        	 txtResult.appendText(c.getCodins()+" "+c.getNome()+"\n");
        }
        
      
        	for(Corso c:model.cercaCorsi(matricola))
        		if(c.getNome().equals(nomeCorso))
        			trovato=true;
        	
        	if(trovato==true)
        		txtResult.setText("Studente già iscritto al corso "+nomeCorso);
        	else 
        		txtResult.setText("Studente non iscritto al corso "+nomeCorso);
        	       
        	
        
	}

	@FXML
	void doIscrivi(ActionEvent event) {
		try{
			String nomeCorso=comboCorso.getValue();
            int matricola=Integer.parseInt(txtMatricola.getText());
	    	Studente s=model.cercaStudente(matricola);
	    	if(s!=null && nomeCorso!=null){
	    		if(!model.isIscritto(comboCorso.getValue(),s.getMatricola()))
	    				{
	    				if(model.iscriviStudente(comboCorso.getValue(), s))
	    					txtResult.appendText("Studente appena iscritto al corso! \n");
	    				}
	    		else
	    			txtResult.appendText("Studnete già iscritto! \n");
	    	}
	    	
	    	}catch(NumberFormatException e){
	    		txtResult.appendText("Caratteri non validi nella matricola !\n");
	    	}

    }

	

	

	@FXML
	void initialize() {
		assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	  
	  
	}

}
