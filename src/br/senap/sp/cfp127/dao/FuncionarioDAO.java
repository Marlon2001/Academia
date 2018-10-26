package br.senap.sp.cfp127.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senap.sp.cfp127.dbutil.Conexao;
import br.senap.sp.cfp127.modelo.Funcionario;
import br.senap.sp.cfp127.view.FrmFuncionario;

public class FuncionarioDAO {
	
	private Funcionario funcionario;
	
	public FuncionarioDAO(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public FuncionarioDAO() {
		
	}
	
	public Funcionario getFuncionario(int codigo) {
		try {
			String consulta = "SELECT * FROM funcionario WHERE codigo = ?";
			
			PreparedStatement stm = Conexao.getConexao().prepareStatement(consulta);
			try {
				stm.setInt(1, codigo);
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, "Entre com o codigo do cliente!", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			ResultSet rs = stm.executeQuery();
			
			if (rs.next()){
				funcionario = new Funcionario();
				this.funcionario.setNome(rs.getString("nome"));
				this.funcionario.setEmail(rs.getString("email"));
				this.funcionario.setCidade(rs.getString("cidade"));
				this.funcionario.setPeso(rs.getInt("peso"));
				this.funcionario.setCodigo(rs.getInt("codigo"));
				
			}else {
				JOptionPane.showMessageDialog(null, "Registro " + codigo + " não encontrado.");
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return funcionario;
	}
	
	public ArrayList<Funcionario> getFuncionarios() {
		
		//ArrayList(Coleção) é uma classe ue gerencia vetores permitindo não especificar tamanho e tornando a array dinamica
		ArrayList<Funcionario> funcionarios =  new ArrayList<>();
		
		try {
			String consulta = "SELECT * FROM funcionario ORDER BY nome";
			
			PreparedStatement stm = Conexao.getConexao().prepareStatement(consulta);
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()){
				funcionario = new Funcionario();
				this.funcionario.setNome(rs.getString("nome"));
				this.funcionario.setEmail(rs.getString("email"));
				this.funcionario.setCidade(rs.getString("cidade"));
				this.funcionario.setPeso(rs.getInt("peso"));
				this.funcionario.setCodigo(rs.getInt("codigo"));
				funcionarios.add(funcionario);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return funcionarios;
	}
	
	public boolean gravar() {
		try {
			String insert = "INSERT INTO funcionario (nome, email, cidade, peso) "
					+ "VALUES(?, ?, ?, ?)";
			
			PreparedStatement stm = Conexao.getConexao().prepareStatement(insert);
			stm.setString(1, funcionario.getNome());
			stm.setString(2, funcionario.getEmail());
			stm.setString(3, funcionario.getCidade());
			stm.setInt(4, funcionario.getPeso());
			
			if(stm.execute()) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro na gravação!");
				return false;
			} else {
				JOptionPane.showMessageDialog(null, "Funcionário cadastrado com SUCESSO!");
				return true;
			}
			
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro na gravação!");
			System.out.println(erro.getMessage());
			return false;
		}
	}
	
	public void atualizar() {
		try {
			String update = "UPDATE funcionario SET nome=?,"
					+ " email=?,"
					+ " cidade=?,"
					+ " peso=? WHERE codigo=?";
			
			PreparedStatement stm = Conexao.getConexao().prepareStatement(update);
			stm.setString(1, funcionario.getNome());
			stm.setString(2, funcionario.getEmail());
			stm.setString(3, funcionario.getCidade());
			stm.setInt(4, funcionario.getPeso());
			stm.setInt(5, funcionario.getCodigo());
			
			
			if(stm.execute()) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro na gravação!");
			} else {
				JOptionPane.showMessageDialog(null, "Funcionário atualizado com SUCESSO!");
			}
			
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro para atualizar!");
			System.out.println(erro.getMessage());
		}
	}
	
	public void excluir() {
		try {
			String delete = "DELETE FROM funcionario "
					+ " WHERE codigo=?";
			
			PreparedStatement stm = Conexao.getConexao().prepareStatement(delete);
			stm.setInt(1, funcionario.getCodigo());
			
			
			if(stm.execute()) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro para excluir!");
			} else {
				JOptionPane.showMessageDialog(null, "Funcionário excluido com SUCESSO!");
			}
			
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro para exluir!");
			System.out.println(erro.getMessage());
		}
	}
	
}
