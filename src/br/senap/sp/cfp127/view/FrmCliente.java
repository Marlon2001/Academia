package br.senap.sp.cfp127.view;

import java.awt.Color;

import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;

import org.w3c.dom.events.MouseEvent;

import br.senap.sp.cfp127.cliente.*;
import br.senap.sp.cfp127.dao.ClienteDAO;
import br.senap.sp.cfp127.dao.FuncionarioDAO;
import br.senap.sp.cfp127.modelo.Funcionario;
import br.senap.sp.cfp127.utils.Data;

import java.awt.ScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class FrmCliente extends JFrame {
	
	private JPanel painelTitulo, painelDados;
	private JLabel lblTitulo, lblIcone, lblNome, lblPeso,
	lblAltura, lblIdade, lblNivelAtividade, lblImcResult,lblFcmResult, lblTmbResult,lblLembrete;
	private JTextField txtNome, txtPeso, txtAltura, txtIdade;
	private ImageIcon IconeCalculadora, iconeTitulo;
	private Color cinza = new Color(210, 210, 210), azul = new Color(0, 0, 255), vermelho = new Color(255,0,0);
	private Font minhaFonte = new Font("Arial", 1, 20);
	private TitledBorder bordaPainelDados, bordaPainelResultado;
	private JRadioButton radioM, radioF;
	private JComboBox comboAtividades;
	private JTable tableCliente;
	private JPanel painelEndereco;
	private JTextField txtLogradouro, txtBairro, txtCidade, txtTelefone, txtEmail;
	private JTabbedPane tabbedPane;
	private JScrollPane scrollPane;
	private int x = 1;
	
	public FrmCliente() {
		setTitle("Cadastro de Cliente");
		setSize(501, 499);
		setResizable(false);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//*** IMAGENS DO FORMULÁRIO
		iconeTitulo = new ImageIcon(FrmCliente.class.getResource("/br/senap/sp/cfp127/imagens/img32.png"));
		
		//*** LABELS DO PAINEL TITULO
		lblTitulo = new JLabel("Academia Boa Forma");
		lblTitulo.setBounds(70, 10, 300, 30);
		lblTitulo.setFont(minhaFonte);
		lblTitulo.setForeground(azul);
		
		lblIcone = new JLabel(iconeTitulo);
		lblIcone.setBounds(0, 0, 50, 50);
		
		//*** PAINEL TITULO
		painelTitulo = new JPanel();
		painelTitulo.setLayout(null);
		painelTitulo.add(lblTitulo);
		painelTitulo.add(lblIcone);
		painelTitulo.setBackground(cinza);
		painelTitulo.setBounds(0, 0, 495, 50);

		bordaPainelDados = new TitledBorder("Dados do cliente!");
		
		ButtonGroup grupoRadio = new ButtonGroup();
		
		//Criando a Combo BOX usando um vetor
		String[] atividades = {"Sedentário", "Levemente Ativo", "Moderadamente Ativo", "Bastante Ativo", "Muito ativo"};
		IconeCalculadora = new ImageIcon(FrmCliente.class.getResource("/br/senap/sp/cfp127/imagens/imgIcon16.png"));
		bordaPainelResultado = new TitledBorder("Resultados!");
		bordaPainelDados.setTitleColor(azul);
		bordaPainelResultado.setTitleColor(azul);
		
		getContentPane().add(painelTitulo);
		
		lblLembrete = new JLabel("");
		lblLembrete.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		lblLembrete.setForeground(Color.BLUE);
		lblLembrete.setBounds(329, 11, 150, 29);
		painelTitulo.add(lblLembrete);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane.setBounds(10, 64, 479, 397);
		getContentPane().add(tabbedPane);
		
		JPanel painelClientes = new JPanel();
		tabbedPane.addTab("Lista de clientes", null, painelClientes, null);
		
		painelClientes.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), null, TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_2.setBounds(10, 0, 476, 358);
		painelClientes.add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 21, 409, 194);
		panel_2.add(scrollPane);
		
		String colunas[] = {"ID", "Nome", "Email"};
		updateTable();
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(10, 226, 442, 118);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnCriar = new JButton("");
		
		btnCriar.setToolTipText("Adicionar um novo cliente.");
		btnCriar.setIcon(new ImageIcon(FrmCliente.class.getResource("/br/senap/sp/cfp127/imagens/if_add_user_678158 (1).png")));
		btnCriar.setBounds(10, 38, 82, 53);
		panel_3.add(btnCriar);
		
		JButton btnEditar = new JButton("");
		btnEditar.setToolTipText("Editar um cliente.");
		btnEditar.setIcon(new ImageIcon(FrmCliente.class.getResource("/br/senap/sp/cfp127/imagens/if_compose_1055085.png")));
		btnEditar.setBounds(102, 38, 82, 53);
		panel_3.add(btnEditar);
		
		JButton btnExcluir = new JButton("");
		btnExcluir.setToolTipText("Deletar um cliente.");
		btnExcluir.setIcon(new ImageIcon(FrmCliente.class.getResource("/br/senap/sp/cfp127/imagens/if_Close_1891023 (1).png")));
		
		btnExcluir.setBounds(194, 38, 75, 53);
		panel_3.add(btnExcluir);
		
		JButton btnSair = new JButton("");
		btnSair.setToolTipText("Sair.");
		
		btnSair.setIcon(new ImageIcon(FrmCliente.class.getResource("/br/senap/sp/cfp127/imagens/if_simpline_43_2305619.png")));
		btnSair.setBounds(357, 38, 75, 53);
		panel_3.add(btnSair);
		
		JPanel painelInformacoes = new JPanel();
		tabbedPane.addTab("Dados do Cliente", null, painelInformacoes, null);
		painelInformacoes.setLayout(null);
		
		//*** PAINEL DADOS
		painelDados = new JPanel();
		painelDados.setBounds(10, 11, 445, 148);
		painelInformacoes.add(painelDados);
		painelDados.setLayout(null);
		painelDados.setBorder(new TitledBorder(null, "Dados do cliente:", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		
		lblNome = new JLabel("Nome: ");
		lblNome.setBounds(10, 20, 50, 30);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 51, 211, 22);
		
		lblPeso = new JLabel("Peso: ");
		lblPeso.setBounds(10, 82, 41, 30);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(10, 105, 51, 22);
		
		lblAltura = new JLabel("Altura: ");
		lblAltura.setBounds(71, 82, 50, 30);
		
		txtAltura = new JTextField();
		txtAltura.setBounds(71, 105, 50, 22);
		
		lblIdade = new JLabel("Data Nasc:");
		lblIdade.setBounds(240, 20, 69, 30);
		
		txtIdade = new JTextField();
		txtIdade.setBounds(241, 51, 68, 22);
		
		lblNivelAtividade = new JLabel("Nível de atividade: ");
		lblNivelAtividade.setBounds(131, 82, 150, 30);
		
		comboAtividades = new JComboBox(atividades);
		comboAtividades.setBounds(131, 105, 178, 20);
		
		painelDados.add(comboAtividades);
		painelDados.add(lblNivelAtividade);
		painelDados.add(txtAltura);
		painelDados.add(txtIdade);
		painelDados.add(lblNome);
		painelDados.add(txtNome);
		painelDados.add(lblPeso);
		painelDados.add(lblAltura);
		painelDados.add(lblIdade);
		painelDados.add(txtPeso);
		
		JPanel painelSexo = new JPanel();
		painelSexo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sexo:", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		painelSexo.setBounds(326, 29, 106, 108);
		painelDados.add(painelSexo);
		painelSexo.setLayout(null);
		
		radioM = new JRadioButton("Masculino");
		radioM.setBounds(10, 61, 86, 40);
		painelSexo.add(radioM);
		grupoRadio.add(radioM);
		
		radioF = new JRadioButton("Feminino");
		radioF.setBounds(10, 19, 86, 40);
		painelSexo.add(radioF);
		
		grupoRadio.add(radioF);
		
		painelEndereco = new JPanel();
		painelEndereco.setBorder(new TitledBorder(null, "Endere\u00E7o do cliente:", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		painelEndereco.setBounds(10, 164, 315, 130);
		painelInformacoes.add(painelEndereco);
		painelEndereco.setLayout(null);
		
		JLabel lblLogradouro = new JLabel("Logradouro:");
		lblLogradouro.setBounds(10, 31, 65, 14);
		painelEndereco.add(lblLogradouro);
		
		txtLogradouro = new JTextField();
		txtLogradouro.setBounds(10, 49, 149, 22);
		painelEndereco.add(txtLogradouro);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(168, 31, 65, 14);
		painelEndereco.add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(169, 49, 130, 22);
		painelEndereco.add(txtBairro);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(10, 80, 65, 14);
		painelEndereco.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(10, 98, 98, 22);
		painelEndereco.add(txtCidade);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(118, 98, 78, 22);
		painelEndereco.add(txtTelefone);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(118, 80, 65, 14);
		painelEndereco.add(lblTelefone);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(211, 98, 88, 22);
		painelEndereco.add(txtEmail);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(211, 80, 65, 14);
		painelEndereco.add(lblEmail);
		
		JPanel painelResultados = new JPanel();
		painelResultados.setBorder(new TitledBorder(null, "Resultados:", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		painelResultados.setBounds(335, 164, 120, 130);
		painelInformacoes.add(painelResultados);
		painelResultados.setLayout(null);
		
		JLabel lblTmb = new JLabel("TMB:");
		lblTmb.setBounds(10, 26, 46, 14);
		painelResultados.add(lblTmb);
		
		JLabel lblFcm = new JLabel("FCM:");
		lblFcm.setBounds(10, 56, 46, 14);
		painelResultados.add(lblFcm);
		
		JLabel lblImc = new JLabel("IMC:");
		lblImc.setBounds(10, 85, 46, 14);
		painelResultados.add(lblImc);
		
		lblTmbResult = new JLabel("");
		lblTmbResult.setBounds(40, 26, 46, 14);
		painelResultados.add(lblTmbResult);
		
		lblFcmResult = new JLabel("");
		lblFcmResult.setBounds(40, 56, 46, 14);
		painelResultados.add(lblFcmResult);
		
		lblImcResult = new JLabel("");
		lblImcResult.setBounds(40, 85, 46, 14);
		painelResultados.add(lblImcResult);
		
		JButton btnGravar = new JButton("Gravar.");
		btnGravar.setIcon(new ImageIcon(FrmCliente.class.getResource("/br/senap/sp/cfp127/imagens/if_floppy_285657.png")));
		btnGravar.setBounds(335, 297, 120, 57);
		painelInformacoes.add(btnGravar);
		
		JButton btnCancelar = new JButton("Cancelar...");
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(20, 297, 120, 57);
		painelInformacoes.add(btnCancelar);
		
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(1);
				tabbedPane.setEnabled(false);
				btnCancelar.setEnabled(true);
				txtNome.requestFocus();
				grupoRadio.clearSelection();
				tableCliente.getSelectionModel().clearSelection();
				limpaCampos();
				lblLembrete.setText("Novo cliente...");
			
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setEnabled(true);
				btnCancelar.setEnabled(false);
				tabbedPane.setSelectedIndex(0);
				tableCliente.getSelectionModel().clearSelection();
				lblLembrete.setText("");
				limpaCampos();
			}
		});
		
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = tableCliente.getSelectedRow();
				if(linha == -1) {
					JOptionPane.showMessageDialog(FrmCliente.this, "Seleciona um cliente na lista para excluir.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				}else {
					int x = JOptionPane.showConfirmDialog(FrmCliente.this, "Tem certeza que deseja excluir um cliente?", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					if(x == 0) {
						criarCliente("excluir");
						tableCliente.getSelectionModel().clearSelection();
						updateTable();
					}
				}
			}
		});
		
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = -1, codigo;
				try {
					linha = tableCliente.getSelectedRow();
					codigo = Integer.parseInt(tableCliente.getValueAt(linha, 0).toString());
				}catch (Exception erro) {
					
				}
				if(linha >= 0) {
					int linha2 = tableCliente.getSelectedRow();
					int codigo2 = Integer.parseInt(tableCliente.getValueAt(linha2, 0).toString());
					consultarCliente(codigo2);
					tabbedPane.setSelectedIndex(1);
					x = 2;
					btnCancelar.setEnabled(true);
					tabbedPane.setEnabled(false);
					lblLembrete.setText("Editando cliente...");
				}else {
					JOptionPane.showMessageDialog(FrmCliente.this, "Seleciona um cliente na lista para editar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
				
		btnGravar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(x==1) {
					criarCliente("gravar");
					tabbedPane.setEnabled(true);
					btnCancelar.setEnabled(false);
					radioF.setSelected(false);
					radioM.setSelected(false);
					limpaCampos();
					updateTable();
					grupoRadio.clearSelection();
					lblLembrete.setText("");
				}else if(x==2) {
					criarCliente("atualizar");
					tabbedPane.setEnabled(true);
					btnCancelar.setEnabled(false);
					radioF.setSelected(false);
					radioM.setSelected(false);
					limpaCampos();
					updateTable();
					grupoRadio.clearSelection();
					lblLembrete.setText("");
					x = 1;
				}
			}
		});
		
		setVisible(true);
	}
	
	
	private void criarCliente(String operacao) {
		Cliente cliente = new Cliente();
		cliente.setNome(txtNome.getText());
		cliente.setDtNasc(Data.converterParaAccess(txtIdade.getText()));
		cliente.setPeso(Double.parseDouble(txtPeso.getText()));
		cliente.setAltura(Double.parseDouble(txtAltura.getText()));
		cliente.setNivelAtividade(comboAtividades.getSelectedIndex());
		cliente.setBairro(txtBairro.getText());
		cliente.setCidade(txtCidade.getText());
		cliente.setTelefone(txtTelefone.getText());
		cliente.setEmail(txtEmail.getText());
		cliente.setLogradouro(txtLogradouro.getText());
		
		if(radioF.isSelected()) {
			cliente.setSexo('F');
		}else if(radioM.isSelected()) {
			cliente.setSexo('M');
		}else {
			
		}
		
		ClienteDAO dao = new ClienteDAO(cliente);
		
		if(operacao.equals("gravar")) {
			if(dao.gravar()) {
				JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso.");
				limpaCampos();
			}else{
				JOptionPane.showMessageDialog(null, "Cliente não cadastrado com sucesso.");
			}
		}else if(operacao.equals("atualizar")) {
			int linha = tableCliente.getSelectedRow();
			int codigo = Integer.parseInt(tableCliente.getValueAt(linha, 0).toString());
			if(dao.atualizar(codigo)) {
				JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso.");
				limpaCampos();
			}else {
				JOptionPane.showMessageDialog(null, "Cliente não atualizado.");
			}
		}else if(operacao.equals("excluir")) {
			int linha = tableCliente.getSelectedRow();
			int codigo = Integer.parseInt(tableCliente.getValueAt(linha, 0).toString());
			if(dao.excluir(codigo)) {
				JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso.");
			}else {
				JOptionPane.showMessageDialog(null, "Cliente não excluido.");
			}
		}else {
			
		}
	}
	
	public void consultarCliente(int codigo) {
		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = dao.getCliente(codigo);
		limpaCampos();
		txtNome.setText(cliente.getNome());
		txtPeso.setText(String.valueOf(cliente.getPeso()));
		txtAltura.setText(String.valueOf(cliente.getAltura()));
		
		if(cliente.getNivelAtividade() == 1) {
			comboAtividades.setSelectedIndex(0);
		}else if(cliente.getNivelAtividade() == 2) {
			comboAtividades.setSelectedIndex(1);
		}else if(cliente.getNivelAtividade() == 3) {
			comboAtividades.setSelectedIndex(2);
		}else if(cliente.getNivelAtividade() == 4) {
			comboAtividades.setSelectedIndex(3);
		}else if(cliente.getNivelAtividade() == 5) {
			comboAtividades.setSelectedIndex(4);
		}else {
			
		}
		
		if(String.valueOf(cliente.getSexo()).equals("F")) {
			radioF.setSelected(true);
		}else if(String.valueOf(cliente.getSexo()).equals("M")) {
			radioM.setSelected(true);
		}else {
			
		}
		
		txtLogradouro.setText(cliente.getLogradouro());
		txtBairro.setText(cliente.getBairro());
		txtCidade.setText(cliente.getCidade());
		txtTelefone.setText(cliente.getTelefone());
		txtEmail.setText(cliente.getEmail());
		txtIdade.setText(Data.converterParaPortugues((cliente.getDtNascimento())));
		lblImcResult.setToolTipText(cliente.getImc());
		lblTmbResult.setText(cliente.getTmb());
		lblFcmResult.setText(cliente.getFmc());
	}
	
	private void limpaCampos() {
		txtNome.setText("");
		txtIdade.setText("");
		txtPeso.setText("");
		txtAltura.setText("");
		comboAtividades.setSelectedIndex(0);
		txtLogradouro.setText("");
		txtBairro.setText("");
		txtCidade.setText("");
		txtTelefone.setText("");
		txtEmail.setText("");
		lblFcmResult.setText("");
		lblImcResult.setText("");
		lblTmbResult.setText("");
	}
	
	private void updateTable() {
		String colunas[] = {"ID", "Nome", "Email"};
		ClienteDAO dao = new ClienteDAO();
		int linhas = dao.getClientes().size();
		String dados[][] = new String[linhas][3];
		
		for(int i = 0; i < linhas; i++) {
			dados[i][0] = String.valueOf(dao.getClientes().get(i).getCodigo());
			dados[i][1] = dao.getClientes().get(i).getNome();
			dados[i][2] = dao.getClientes().get(i).getEmail();
		}
		
		tableCliente = new JTable(dados, colunas);
		tableCliente.setDefaultEditor(Object.class, null);
		
		tableCliente.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				Cliente cliente = new Cliente();
				if (e.getClickCount() == 2) {
					tabbedPane.setSelectedIndex(1);
					lblLembrete.setText("Editando cliente...");					
				}
				int linha = tableCliente.getSelectedRow();
				String codigo = tableCliente.getValueAt(linha, 0).toString();
				consultarCliente(Integer.parseInt(codigo));
				x = 2;
			}
		});
		
		scrollPane.setViewportView(tableCliente);
		
	}
}
