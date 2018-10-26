package br.senap.sp.cfp127.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import br.senap.sp.cfp127.cliente.Cliente;
import br.senap.sp.cfp127.dbutil.Conexao;
import br.senap.sp.cfp127.modelo.Funcionario;
import br.senap.sp.cfp127.utils.Data;

public class ClienteDAO {
	
	Cliente cliente;
	
	public ClienteDAO(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public ClienteDAO() {
		
	}
	
	public Cliente getCliente(int codigo) {
		try {
			String consulta = "SELECT * FROM cliente WHERE codigo=?";
			
			PreparedStatement stm = Conexao.getConexao().prepareStatement(consulta);
			stm.setInt(1, codigo);
			ResultSet rs = stm.executeQuery();
			
			if (rs.next()){
				cliente = new Cliente();
				this.cliente.setNome(rs.getString("nome"));
				this.cliente.setPeso(rs.getInt("peso"));
				this.cliente.setAltura(rs.getDouble("altura"));
				this.cliente.setNivelAtividade(rs.getInt("nivelAtividade")+1);
				this.cliente.setLogradouro(rs.getString("logradouro"));
				this.cliente.setSexo(rs.getString("sexo").charAt(0));
				this.cliente.setBairro(rs.getString("bairro"));
				this.cliente.setCidade(rs.getString("cidade"));
				this.cliente.setTelefone(rs.getString("telefone"));
				this.cliente.setEmail(rs.getString("email"));
				this.cliente.setDtNascimento(rs.getDate("dataNascimento"));
				this.cliente.setIdade(Data.calcularIdade(new Date(), cliente.getDtNascimento()));
			}else {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cliente;
	}
	
	public ArrayList<Cliente> getClientes() {
		ArrayList<Cliente> clientes =  new ArrayList<>();
		
		try {
			String consulta = "SELECT * FROM cliente ORDER BY codigo";
			
			PreparedStatement stm = Conexao.getConexao().prepareStatement(consulta);
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()){
				cliente = new Cliente();
				this.cliente.setNome(rs.getString("nome"));
				//this.cliente.setIdade(rs.getInt("dataNascimento"));
				//this.cliente.setPeso(rs.getInt("peso"));
				//this.cliente.setAltura(rs.getDouble("altura"));
				//this.cliente.setSexo(rs.getString("sexo").charAt(0));
				//this.cliente.setNivelAtividade(rs.getInt("nivelAtividade"));
				//this.cliente.setLogradouro(rs.getString("logradouro"));
				//this.cliente.setBairro(rs.getString("bairro"));
				//this.cliente.setCidade(rs.getString("cidade"));
				//this.cliente.setTelefone(rs.getString("telefone"));
				//this.cliente.setDtNascimento((rs.getDate("dataNascimento")));
				//this.cliente.setIdade(Data.calcularIdade(new Date(), cliente.getDtNascimento()));
				this.cliente.setEmail(rs.getString("email"));
				this.cliente.setCodigo(rs.getInt("codigo"));
				
				clientes.add(cliente);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	public boolean gravar() {
		try {
			String insert = "INSERT INTO cliente (nome, peso, altura,"
					+ " sexo, nivelAtividade, logradouro,"
					+ " bairro, telefone, email, cidade,"
					+ " dataNascimento) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement stm = Conexao.getConexao().prepareStatement(insert);
			stm.setString(1, cliente.getNome());
			stm.setDouble(2, cliente.getPeso());
			stm.setDouble(3, cliente.getAltura());
			stm.setString(4, String.valueOf(cliente.getSexo()));
			stm.setInt(5, cliente.getIdade());
			stm.setString(6, cliente.getLogradouro());
			stm.setString(7, cliente.getBairro());
			stm.setString(8, cliente.getTelefone());
			stm.setString(9, cliente.getEmail());
			stm.setString(10, cliente.getCidade());
			stm.setString(11, cliente.getDtNasc());
			
			if(stm.execute()) {
				return false;
			} else {
				return true;
			}
			
		} catch (Exception erro) {
			erro.printStackTrace();
			return false;
		}
	}
	
	public boolean atualizar(int codigo) {
		try {
			String update = "UPDATE cliente SET nome=?, peso=?, altura=?,"
					+ " email=?, sexo=?, nivelAtividade=?,"
					+ "	logradouro=?, bairro=?, telefone=?,"
					+ " cidade=?, dataNascimento=? WHERE codigo=?";
			
			PreparedStatement stm = Conexao.getConexao().prepareStatement(update);
			stm.setString(1, cliente.getNome());
			stm.setDouble(2, cliente.getPeso());
			stm.setDouble(3, cliente.getAltura());
			stm.setString(4, cliente.getEmail());
			stm.setString(5, String.valueOf(cliente.getSexo()));
			stm.setInt(6, cliente.getNivelAtividade());
			stm.setString(7, cliente.getLogradouro());
			stm.setString(8, cliente.getBairro());
			stm.setString(9, cliente.getTelefone());
			stm.setString(10, cliente.getCidade());
			stm.setString(11, cliente.getDtNasc());
			stm.setInt(12, codigo);
			
			if(stm.execute()) {
				return false;
			} else {
				return true;
			}
			
		} catch (Exception erro) {
			System.out.println(erro.getMessage());
			return false;
		}
	}
	
	public boolean excluir(int codigo) {
		try {
			String delete = "DELETE FROM cliente "
					+ " WHERE codigo=?";
			
			PreparedStatement stm = Conexao.getConexao().prepareStatement(delete);
			stm.setInt(1, codigo);
			
			if(stm.execute()) {
				return false;
			} else {
				return true;
			}
			
		} catch (Exception erro) {
			System.out.println(erro.getMessage());
			return false;
		}
	}
}
