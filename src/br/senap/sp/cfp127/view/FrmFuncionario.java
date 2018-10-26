package br.senap.sp.cfp127.view;
	
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.IconView;

import com.healthmarketscience.jackcess.Cursor;

import br.senap.sp.cfp127.dao.FuncionarioDAO;
import br.senap.sp.cfp127.dbutil.Conexao;
import br.senap.sp.cfp127.modelo.Funcionario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
	
public class FrmFuncionario extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtCidade;
	private JTextField txtPeso;
	private JTable tableFuncionario;
	
	public FrmFuncionario() {
		super("Academia");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 360, 356, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String colunas[] = {"Código", "Nome do Funcionário"}; // CABEÇALHO DAS COLUNAS
		
		FuncionarioDAO dao = new FuncionarioDAO();
		int linhas = dao.getFuncionarios().size();
		String dados[][] = new String[linhas][2];
		
		for(int i = 0; i < linhas; i++) {
			dados[i][0] = String.valueOf(dao.getFuncionarios().get(i).getCodigo());
			dados[i][1] = dao.getFuncionarios().get(i).getNome();
		}
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 330, 350);
		contentPane.add(tabbedPane);
		
		JPanel panelTabela = new JPanel();
		tabbedPane.addTab("Lista", null, panelTabela, null);
		panelTabela.setLayout(new BoxLayout(panelTabela, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTabela.add(scrollPane);
		
		tableFuncionario = new JTable(dados, colunas);
		tableFuncionario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int linha = tableFuncionario.getSelectedRow();
				txtCodigo.setText(tableFuncionario.getValueAt(linha, 0).toString());
				criarFuncionario("consultar");
				tabbedPane.setSelectedIndex(1);
			}
		});
		
		scrollPane.setViewportView(tableFuncionario);
		
		JPanel panelDetalhes = new JPanel();
		tabbedPane.addTab("Dados", null, panelDetalhes, null);
		panelDetalhes.setBorder(new TitledBorder(null, "Detalhes do funcionario:", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panelDetalhes.setLayout(null);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setBounds(13, 52, 46, 14);
		panelDetalhes.add(lblCodigo);
		
		JLabel label_2 = new JLabel("Nome:");
		label_2.setBounds(13, 80, 46, 14);
		panelDetalhes.add(label_2);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(57, 49, 46, 20);
		panelDetalhes.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setBounds(57, 77, 220, 20);
		panelDetalhes.add(txtNome);
		txtNome.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(57, 105, 220, 20);
		panelDetalhes.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(57, 136, 178, 20);
		panelDetalhes.add(txtCidade);
		txtCidade.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(10, 108, 46, 14);
		panelDetalhes.add(lblEmail);
		
		JLabel lblPeso = new JLabel("Cidade:");
		lblPeso.setBounds(10, 139, 46, 14);
		panelDetalhes.add(lblPeso);
		
		JButton btnBuscar = new JButton("Consultar");
		btnBuscar.setBounds(114, 48, 89, 23);
		panelDetalhes.add(btnBuscar);
		
		JLabel lblPeso_1 = new JLabel("Peso:");
		lblPeso_1.setBounds(13, 170, 46, 14);
		panelDetalhes.add(lblPeso_1);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(57, 167, 46, 20);
		panelDetalhes.add(txtPeso);
		txtPeso.setColumns(10);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setBounds(13, 207, 82, 46);
		panelDetalhes.add(btnNovo);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(95, 207, 79, 46);
		panelDetalhes.add(btnAtualizar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(175, 207, 72, 46);
		panelDetalhes.add(btnExcluir);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(247, 207, 79, 46);
		panelDetalhes.add(btnSalvar);
		btnSalvar.setEnabled(false);
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarFuncionario("gravar");
				limpaCampos();
				if (btnNovo.getText().equals("Novo")) {
					btnSalvar.setEnabled(true);
					txtCodigo.setEnabled(false);
					limpaCampos();
					txtNome.requestFocus();
					txtCodigo.setEnabled(false);
					btnNovo.setText("Cancelar");
					btnBuscar.setEnabled(false);
					btnAtualizar.setEnabled(false);
					btnExcluir.setEnabled(false);
				} else {
					btnSalvar.setEnabled(false);
					btnNovo.setText("Novo");
					limpaCampos();
					txtCodigo.setEnabled(true);
					txtCodigo.requestFocus();
					btnBuscar.setEnabled(true);
					btnAtualizar.setEnabled(true);
					btnExcluir.setEnabled(true);
				}
			}
		});
		
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int resposta = JOptionPane.showConfirmDialog(null, "Confirma apagar do(a) \n" + txtNome.getText() + "?", "Excluir funcionario", JOptionPane.YES_NO_OPTION);
				
				if (resposta == 0) {
					criarFuncionario("excluir");
				} else {
					
				}
			}
		});
		
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarFuncionario("atualizar");
			}
		});
		
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (btnNovo.getText().equals("Novo")) {
					btnSalvar.setEnabled(true);
					txtCodigo.setEnabled(false);
					limpaCampos();
					txtNome.requestFocus();
					txtCodigo.setEnabled(false);
					btnNovo.setText("Cancelar");
					btnBuscar.setEnabled(false);
					btnAtualizar.setEnabled(false);
					btnExcluir.setEnabled(false);
				} else {
					btnSalvar.setEnabled(false);
					btnNovo.setText("Novo");
					limpaCampos();
					txtCodigo.setEnabled(true);
					txtCodigo.requestFocus();
					btnBuscar.setEnabled(true);
					btnAtualizar.setEnabled(true);
					btnExcluir.setEnabled(true);
				}
				
			}
		});
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				criarFuncionario("consultar");
			}
		});
		
		setVisible(true);
	}
	private void criarFuncionario(String operacao) {
		Funcionario funcionario = new Funcionario();
		funcionario.setCidade(txtCidade.getText());
		funcionario.setNome(txtNome.getText());
		funcionario.setEmail(txtEmail.getText());
		
		FuncionarioDAO dao = new FuncionarioDAO(funcionario);
		
		if(operacao.equals("gravar")) {
			if(dao.gravar()) {
				limpaCampos();
			}
		} else if (operacao.equals("atualizar")) {
			funcionario.setPeso(Integer.parseInt(txtPeso.getText()));
			funcionario.setCodigo(Integer.parseInt(txtCodigo.getText()));
			dao.atualizar();
			limpaCampos();
		} else if(operacao.equals("excluir")){
			funcionario.setPeso(Integer.parseInt(txtPeso.getText()));
			funcionario.setCodigo(Integer.parseInt(txtCodigo.getText()));
			dao.excluir();
			limpaCampos();
		} else if(operacao.equals("consultar")){
			funcionario = dao.getFuncionario(Integer.parseInt(txtCodigo.getText()));
			limpaCampos();
			txtCodigo.setText(String.valueOf(funcionario.getCodigo()));
			txtNome.setText(funcionario.getNome());
			txtEmail.setText(funcionario.getEmail());
			txtCidade.setText(funcionario.getCidade());
			txtPeso.setText(String.valueOf(funcionario.getPeso()));
		} else {
			
		}
	}
	private void limpaCampos() {
		txtNome.setText("");
		txtCidade.setText("");
		txtEmail.setText("");
		txtPeso.setText("");
		txtCodigo.setText("");
	}
}	
	
	
	