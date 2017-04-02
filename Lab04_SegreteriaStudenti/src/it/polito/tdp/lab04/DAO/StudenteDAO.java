package it.polito.tdp.lab04.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
public Studente find(int matricola) {
		
		String sql = 
				"SELECT nome,cognome,CDS,matricola " +
				"FROM studente "+
				"WHERE matricola=?" ;
		
	
		Studente result=null;
		
		try {
			Connection conn = ConnectDB.getConnection() ;
			
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setInt(1, matricola);
			
			ResultSet res = st.executeQuery() ;
			
			if(res.next()) {
				
				Studente s = new Studente(
						res.getInt("matricola"),
						res.getString("nome"),
						res.getString("cognome"),
						res.getString("CDS")
						) ;
				
		
				
				result = s ;
				
			} else {
				result = null;
			}
			
			
			return result ;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null ;
		}
		
	}

public List<Corso> getCorsi(int matricola) {
	String sql="SELECT * "+
			"FROM CORSO "+
			"WHERE codins IN  (SELECT codins "+
			                  "FROM iscrizione "+
			                  "WHERE matricola=?)";
	List<Corso> corsi = new LinkedList<Corso>();
	
	try {
		Connection conn = ConnectDB.getConnection() ;
		
		PreparedStatement st = conn.prepareStatement(sql) ;
		
		st.setInt(1, matricola);
		
		ResultSet rs = st.executeQuery() ;
		
		while(rs.next()) {
			
			Corso c=new Corso(
					rs.getString("codins"),
					rs.getInt("crediti"),
					rs.getString("nome"),
					rs.getInt("pd")
				    );
			
			corsi.add(c);
		}
		return corsi;
		
		
	} catch (SQLException e) {
		
		e.printStackTrace();
		throw new RuntimeException("Errore Db");
	}
	
}

public boolean checkIscritto(Corso corso,int matricola) {
	boolean iscritto=false;
	String  sql="SELECT * "+
			"FROM iscrizione "+
			"WHERE matricola=? and codins=?";

	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, Integer.toString(matricola));
		st.setString(2, corso.getCodins());

		ResultSet res = st.executeQuery();

		if (res.next()) 
			iscritto=true;
	
		
		

	} catch (SQLException e) {
		throw new RuntimeException("Errore Db");
	}
	return iscritto;
	
}

}
