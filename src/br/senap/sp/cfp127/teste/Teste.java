package br.senap.sp.cfp127.teste;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import br.senap.sp.cfp127.utils.Data;
import br.senap.sp.cfp127.view.FrmCliente;
import br.senap.sp.cfp127.view.FrmFuncionario;

public class Teste {

	public static void main(String[] args) {
		
		//Mudando o look (tema) da aplicação
		LookAndFeelInfo[] info = UIManager.getInstalledLookAndFeels();
		
		try {
			UIManager.setLookAndFeel(info[3].getClassName());
		} catch (Exception erro){
			erro.printStackTrace();
		}

		//FrmFuncionario funcionario = new FrmFuncionario();
		FrmCliente janela = new FrmCliente();
		
	}

}