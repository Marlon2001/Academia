package br.senap.sp.cfp127.dbutil;

import java.sql.*;

public class Conexao {
	
	private static Connection conexao;
	
	public static Connection getConexao() {
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			conexao = DriverManager.getConnection("jdbc:ucanaccess://Z:/git/Academia.accdb");
		} catch(Exception erro) {
			erro.printStackTrace();
		}
		
		return conexao;
	}
}
