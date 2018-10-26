package br.senap.sp.cfp127.cliente;

import java.nio.channels.NetworkChannel;
import java.sql.Date;
import java.text.DecimalFormat;

import br.senap.sp.cfp127.view.FrmCliente;

public class Cliente {

	// Minha classe cliente
	private int codigo;
	private String nome;
	private int idade;
	
	private double peso;
	private double altura;
	private char sexo;
	private int nivelAtividade;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String telefone;
	private String email;
	
	private String imc;
	private String tmb;
	private String fmc;
	
	private String dtNasc;
	private Date dtNascimento;
	private DecimalFormat df;
	
	public Cliente(String nome) {
		this.nome = nome;
	}
	
	public Cliente() {
		
	}
	public String getDtNasc() {
		return dtNasc;
	}
	
	public void setDtNasc(String dtNasc) {
		this.dtNasc = dtNasc;
	}
	public int getCodigo() {
		return codigo;
	}
	
	public Date getDtNascimento() {
		return dtNascimento;
	}
	
	public void setDtNascimento(Date dt) {
		this.dtNascimento = dt;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Cliente(String nome, double peso, double altura) {
		this.nome = nome;
		this.peso = peso;
		this.altura = altura;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getNivelAtividade() {
		return nivelAtividade;
	}

	public void setNivelAtividade(int nivelAtividade) {
		this.nivelAtividade = nivelAtividade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImc() {
		df = new DecimalFormat("0.00");
		double imc = this.peso / Math.pow(this.altura, 2);
	
		if (imc < 16.19) {
			this.imc = "Seu IMC é " + df.format(imc) + ". Você está muito abaixo do peso!\n"
					+ " Riscos de Queda de cabelo, infetilidade" + " ausência menstrual. ";
		} else if (imc < 18.5) {
			this.imc = "Seu IMC é " + df.format(imc) + ". Você está baixo do peso!\n" + " Riscos de fadiga, stress, ansiedade.";
		} else if (imc < 25) {
			this.imc = "Seu IMC é " + df.format(imc) + ". Peso normal\n" + " Menor risco de doenças cardíacas e vasculares ";
		} else if (imc < 30) {
			this.imc = "Seu IMC é " + df.format(imc) + ". Você está acima do peso.\n"
					+ " Riscos de fadiga, má circulação, varizes. ";
		} else if (imc < 35) {
			this.imc = "Seu IMC é " + df.format(imc) + ". Obesidade Grau I.\n"
					+ " Riscos de diabetes, angina, infarto, aterosclerose. ";
		} else if (imc < 40) {
			this.imc = "Seu IMC é " + df.format(imc) + ". Obesidade Grau II.\n" + " Riscos de apneia do sono, falta de ar. ";
		} else if (imc > 40) {
			this.imc = "Seu IMC é " + df.format(imc) + ". Obesidade Grau III.\n"
					+ "Riscos de refluxo, dificuldade para se mover, escaras, diabetes, infarto, AVC.";
		} else {
			this.imc = "Não foi possível determinar o seu IMC.";
		}

		return this.imc;
	}

	public String getTmb() {
		df = new DecimalFormat("0.00");
		double tmb;

		if (this.sexo == 'M') {
			tmb = 66 + (13.7 * this.peso) + (5 * this.altura * 100) - (6.8 * this.idade);
		} else {
			tmb = 665 + (9.6 * this.peso) + (1.8 * this.altura * 100) - (4.7 * this.idade);
		}

		if (nivelAtividade == 1) {
			this.tmb = "" + (tmb * 1.20);
		} else if (nivelAtividade == 2) {
			this.tmb = "" + (tmb * 1.37);
		} else if (nivelAtividade == 3) {
			this.tmb = "" + (tmb * 1.55);
		} else if (nivelAtividade == 4) {
			this.tmb = "" + (tmb * 1.72);
		} else if (nivelAtividade == 5) {
			this.tmb = "" + (tmb * 1.90);
		} else {
			this.tmb = "Informe o seu nivel de atividade.";
		}

		return this.tmb;
	}

	public String getFmc() {

		if (this.sexo == 'M') {
			this.fmc = ""+((210 - (0.5 * this.idade)) - (this.peso * 0.01) + 4);
		} else if (this.sexo == 'F') {
			this.fmc = ""+((210 - (0.5 * this.idade)) - (this.peso * 0.01));
		} else {
			this.fmc = "Não foi possivel\ncalcular seu FMC.";
		}

		return this.fmc;
	}

}
