package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import dados.*;
import negocio.Sistema;
import persistencia.ContribuinteDAO;
import persistencia.DependenteDAO;
import persistencia.NotaFiscalDAO;
import persistencia.PessoaJuridicaDAO;

import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Application {

	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JFormattedTextField txtCpf;
	private JFormattedTextField txtContaBancaria;
	private JSpinner spinnerIdade;
	private JButton btnInserir;
	private JButton btnCancelar;
	private JTable tblContribuintes;
	private JFormattedTextField txtFiltro;
	private JButton btnCancelarDocumento;
	private JComboBox<String> comboCpf;
	private JComboBox<String> comboCnpj;
	private JComboBox<String> comboCpfDep;
	private JSpinner spinnerValor;
	private JFormattedTextField txtNumProt;
	private JSpinner spinnerNumFunc;
	private JFormattedTextField txtCnpj;
	private JButton btnCancelarPJ;
	private JButton btnModificarPJ;
	private JButton btnCancelarDep;
	private JFormattedTextField txtCpfDep;
	private JSpinner spinnerIdadeDep;
	private JComboBox<String> comboFiltroDep;
	private JComboBox<String> comboFiltroCpfDoc;

	private int ID;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Sistema sistema = new Sistema();
	private JTextField txtNomePJ;
	private JTextField txtEnderecoPJ;
	private JTable tblPJ;
	private JTextField txtFiltroPJ;
	private JTextField txtNomeDep;
	private JTextField txtEndDep;
	private JTable tblDep;
	private JTable tblDocs;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 724, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(tabbedPane_1.getSelectedIndex() == 1)
					carregaTabela();
			}
		});
		tabbedPane.addTab("Contribuinte", null, tabbedPane_1, null);
		
		JPanel panel = new JPanel();
		tabbedPane_1.addTab("Cadastrar", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(75, 42, 97, 16);
		panel.add(lblNome);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setBounds(75, 79, 97, 16);
		panel.add(lblEndereo);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(75, 114, 97, 16);
		panel.add(lblCpf);
		
		JLabel lblIdade = new JLabel("Idade");
		lblIdade.setBounds(75, 155, 96, 16);
		panel.add(lblIdade);
		
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setBounds(257, 39, 310, 22);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		txtEndereco = new JTextField();
		txtEndereco.setEnabled(false);
		txtEndereco.setBounds(257, 76, 310, 22);
		panel.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		txtCpf = new JFormattedTextField(criarMask("###.###.###-##"));
		txtCpf.setEnabled(false);
		txtCpf.setBounds(257, 111, 310, 22);
		panel.add(txtCpf);
		
		spinnerIdade = new JSpinner();
		spinnerIdade.setEnabled(false);
		spinnerIdade.setBounds(255, 152, 310, 22);
		panel.add(spinnerIdade);
		
		btnInserir = new JButton("Inserir");
		btnInserir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnInserir.getText().toLowerCase().equals("inserir")) {
					trocarEstadoCampos(true);
					btnInserir.setText("Salvar");
					btnCancelar.setVisible(true);
				}
				else if(btnInserir.getText().toLowerCase().equals("salvar")){
					Contribuinte contribuinte = new Contribuinte();
					contribuinte.setNome(txtNome.getText());
					contribuinte.setEndereco(txtEndereco.getText());
					contribuinte.setCpf(txtCpf.getText().replaceAll("[.,-]",""));
					contribuinte.setIdade((int) spinnerIdade.getValue()); 
					contribuinte.setContaBancaria(Integer.parseInt(txtContaBancaria.getText().replaceAll("[.,-]","")));
					
					sistema.inserirContribuinte(contribuinte);
					
					trocarEstadoCampos(false);
					btnInserir.setText("Inserir");
					btnCancelar.setVisible(false);
					limparCampos();
				}
				else {
					Contribuinte contribuinte = new Contribuinte();
					contribuinte.setId(ID);
					contribuinte.setNome(txtNome.getText());
					contribuinte.setEndereco(txtEndereco.getText());
					contribuinte.setCpf(txtCpf.getText().replaceAll("[.,-]",""));
					contribuinte.setIdade((int) spinnerIdade.getValue()); 
					contribuinte.setContaBancaria(Integer.parseInt(txtContaBancaria.getText().replaceAll("[.,-]","")));
		
					sistema.atualizaContribuinte(contribuinte);

					trocarEstadoCampos(false);
					btnInserir.setText("Inserir");
					btnCancelar.setVisible(false);
					limparCampos();
				}
			}
		});
		btnInserir.setBounds(170, 278, 97, 25);
		panel.add(btnInserir);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnInserir.setText("Inserir");
				btnCancelar.setVisible(false);
				trocarEstadoCampos(false);
				limparCampos();
			}
		});
		btnCancelar.setBounds(415, 278, 97, 25);
		btnCancelar.setVisible(false);
		panel.add(btnCancelar);
		
		JLabel lblContaBancria = new JLabel("Conta Banc\u00E1ria");
		lblContaBancria.setBounds(75, 198, 97, 16);
		panel.add(lblContaBancria);
		
		txtContaBancaria = new JFormattedTextField(criarMask("#####-#"));
		txtContaBancaria.setEnabled(false);
		txtContaBancaria.setBounds(255, 195, 310, 22);
		panel.add(txtContaBancaria);
		
		JPanel panel_1 = new JPanel();
		tabbedPane_1.addTab("Modificar", null, panel_1, null);
		panel_1.setLayout(null);
		
		txtFiltro = new JFormattedTextField();
		txtFiltro.setBounds(229, 23, 178, 31);
		panel_1.add(txtFiltro);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String cpf = txtFiltro.getText().replaceAll("[.,-]", "");
				ArrayList<Contribuinte> lista = ContribuinteDAO.getInstance().selectCpf(cpf);
				atualizaTabela(lista);
			}
		});
		btnFiltrar.setBounds(419, 26, 97, 25);
		panel_1.add(btnFiltrar);
		
		JLabel lblCpf_1 = new JLabel("CPF");
		lblCpf_1.setBounds(180, 30, 37, 16);
		panel_1.add(lblCpf_1);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tblContribuintes.getSelectedRow() != -1) {
					Contribuinte contribuinte = new Contribuinte();
					contribuinte.setId((int) tblContribuintes.getValueAt(tblContribuintes.getSelectedRow(), 0));
					contribuinte.setNome((String) tblContribuintes.getValueAt(tblContribuintes.getSelectedRow(), 1));
					contribuinte.setEndereco((String) tblContribuintes.getValueAt(tblContribuintes.getSelectedRow(), 2));
					contribuinte.setCpf((String) tblContribuintes.getValueAt(tblContribuintes.getSelectedRow(), 3));
					contribuinte.setIdade((int) tblContribuintes.getValueAt(tblContribuintes.getSelectedRow(), 4));
					contribuinte.setContaBancaria((int) tblContribuintes.getValueAt(tblContribuintes.getSelectedRow(), 5));
					ID = contribuinte.getId();
					
					tabbedPane_1.setSelectedIndex(0);
					trocarEstadoCampos(true);
					btnInserir.setText("Atualizar");
					btnCancelar.setVisible(true);
					
					txtNome.setText(contribuinte.getNome());
					txtEndereco.setText(contribuinte.getEndereco());
					txtCpf.setText(contribuinte.getCpf());
					spinnerIdade.setValue(contribuinte.getIdade());
					txtContaBancaria.setText(String.valueOf(contribuinte.getContaBancaria()));
				}
			}
		});
		btnModificar.setBounds(180, 277, 97, 25);
		panel_1.add(btnModificar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tblContribuintes.getSelectedRow() != -1) {
					ID = (int) tblContribuintes.getValueAt(tblContribuintes.getSelectedRow(), 0);
					sistema.deletaContribuinte(ID);
					carregaTabela();
				}
			}
		});
		btnDeletar.setBounds(419, 277, 97, 25);
		panel_1.add(btnDeletar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 67, 546, 178);
		panel_1.add(scrollPane);
		
		tblContribuintes = new JTable();
		tblContribuintes.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblContribuintes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Endere\u00E7o", "CPF", "Idade", "Conta Bancaria"
			}
		));
		tblContribuintes.setBounds(64, 67, 546, 178);
		scrollPane.setViewportView(tblContribuintes);
		
		JTabbedPane tabbedPane_4 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Dependente", null, tabbedPane_4, null);
		
		JPanel panel_6 = new JPanel();
		tabbedPane_4.addTab("Cadastrar", null, panel_6, null);
		panel_6.setLayout(null);
		
		JLabel lblNome_1 = new JLabel("Nome");
		lblNome_1.setBounds(75, 94, 46, 14);
		panel_6.add(lblNome_1);
		
		txtNomeDep = new JTextField();
		txtNomeDep.setEnabled(false);
		txtNomeDep.setBounds(257, 90, 310, 22);
		panel_6.add(txtNomeDep);
		txtNomeDep.setColumns(10);
		
		JLabel lblEndereo_2 = new JLabel("Endere\u00E7o");
		lblEndereo_2.setBounds(75, 136, 46, 14);
		panel_6.add(lblEndereo_2);
		
		JLabel lblCpf_2 = new JLabel("CPF");
		lblCpf_2.setBounds(75, 175, 46, 14);
		panel_6.add(lblCpf_2);
		
		JLabel lblIdade_1 = new JLabel("Idade");
		lblIdade_1.setBounds(75, 215, 46, 14);
		panel_6.add(lblIdade_1);
		
		JLabel lblResponsvel = new JLabel("CPF Contribuinte");
		lblResponsvel.setBounds(75, 43, 108, 14);
		panel_6.add(lblResponsvel);
		
		txtEndDep = new JTextField();
		txtEndDep.setEnabled(false);
		txtEndDep.setBounds(257, 133, 310, 20);
		panel_6.add(txtEndDep);
		txtEndDep.setColumns(10);
		
		txtCpfDep = new JFormattedTextField(criarMask("###.###.###-##"));
		txtCpfDep.setEnabled(false);
		txtCpfDep.setBounds(257, 172, 310, 20);
		panel_6.add(txtCpfDep);
		
		spinnerIdadeDep = new JSpinner();
		spinnerIdadeDep.setEnabled(false);
		spinnerIdadeDep.setBounds(257, 212, 310, 20);
		panel_6.add(spinnerIdadeDep);
		
		comboCpfDep = new JComboBox<String>();
		comboCpfDep.setEnabled(false);
		comboCpfDep.setBounds(257, 40, 310, 20);
		panel_6.add(comboCpfDep);
		
		JButton btnInserirDep = new JButton("Inserir");
		btnInserirDep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnInserirDep.getText().toLowerCase().equals("inserir")) {
					trocarEstadoCamposDep(true);
					btnInserirDep.setText("Salvar");
					btnCancelarDep.setVisible(true);
				}
				else if(btnInserirDep.getText().toLowerCase().equals("salvar")){
					Dependente dependente = new Dependente();
					dependente.setNome(txtNomeDep.getText());
					dependente.setEndereco(txtEndDep.getText());
					dependente.setCpf(txtCpfDep.getText().replaceAll("[.,-]",""));
					dependente.setIdade((int) spinnerIdade.getValue()); 
			
					sistema.inserirDependente(dependente, comboCpfDep.getSelectedIndex());
					
					trocarEstadoCamposDep(false);
					btnInserirDep.setText("Inserir");
					btnCancelarDep.setVisible(false);
					limparCamposDep();
				}
				else {
					Contribuinte contribuinte = new Contribuinte();
					contribuinte.setId(ID);
					contribuinte.setNome(txtNome.getText());
					contribuinte.setEndereco(txtEndereco.getText());
					contribuinte.setCpf(txtCpf.getText().replaceAll("[.,-]",""));
					contribuinte.setIdade((int) spinnerIdade.getValue()); 
					contribuinte.setContaBancaria(Integer.parseInt(txtContaBancaria.getText().replaceAll("[.,-]","")));
		
					ContribuinteDAO cDao = ContribuinteDAO.getInstance();
					cDao.update(contribuinte);
					
					trocarEstadoCamposDep(false);
					btnInserirDep.setText("Inserir");
					btnCancelarDep.setVisible(false);
					limparCamposDep();
				}
			}
		});
		btnInserirDep.setBounds(170, 278, 89, 23);
		panel_6.add(btnInserirDep);
		
		btnCancelarDep = new JButton("Cancelar");
		btnCancelarDep.setBounds(415, 278, 89, 23);
		panel_6.add(btnCancelarDep);
		
		JPanel panel_7 = new JPanel();
		tabbedPane_4.addTab("Modificar", null, panel_7, null);
		panel_7.setLayout(null);
		
		JLabel lblCpfContribuinte = new JLabel("CPF Contribuinte");
		lblCpfContribuinte.setBounds(115, 30, 107, 14);
		panel_7.add(lblCpfContribuinte);
		
		JButton btnSelecionarDep = new JButton("Selecionar");
		btnSelecionarDep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println(comboFiltroDep.getSelectedIndex());
				ArrayList<Dependente> lista = sistema.getContribuintes().get(comboFiltroDep.getSelectedIndex()).getDependentes();
				atualizaTabelaDep(lista);
			}
		});
		btnSelecionarDep.setBounds(419, 26, 89, 23);
		panel_7.add(btnSelecionarDep);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(64, 67, 546, 178);
		panel_7.add(scrollPane_2);
		
		tblDep = new JTable();
		tblDep.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Endere\u00E7o", "CPF", "Idade"
			}
		));
		scrollPane_2.setViewportView(tblDep);
		
		JButton btnModificarDep = new JButton("Modificar");
		btnModificarDep.setBounds(180, 277, 89, 23);
		panel_7.add(btnModificarDep);
		
		JButton btnDeletarDep = new JButton("Deletar");
		btnDeletarDep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDeletarDep.setBounds(419, 277, 89, 23);
		panel_7.add(btnDeletarDep);
		
		comboFiltroDep = new JComboBox<String>();
		comboFiltroDep.setBounds(220, 26, 189, 22);
		panel_7.add(comboFiltroDep);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(tabbedPane_3.getSelectedIndex() == 1)
					carregaTabelaPJ();
			}
		});
		tabbedPane.addTab("Pessoa Juridica", null, tabbedPane_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane_3.addTab("Cadastrar", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setBounds(76, 53, 56, 16);
		panel_4.add(lblCnpj);
		
		JLabel lblNomepj = new JLabel("NomePJ");
		lblNomepj.setBounds(76, 109, 56, 16);
		panel_4.add(lblNomepj);
		
		JLabel lblEndereo_1 = new JLabel("Endere\u00E7o");
		lblEndereo_1.setBounds(76, 163, 56, 16);
		panel_4.add(lblEndereo_1);
		
		JLabel lblNmeroFuncionrios = new JLabel("N\u00FAmero Funcion\u00E1rios");
		lblNmeroFuncionrios.setBounds(76, 215, 135, 16);
		panel_4.add(lblNmeroFuncionrios);
		
		txtCnpj = new JFormattedTextField(criarMask("##.###.###/####-##"));
		txtCnpj.setEnabled(false);
		txtCnpj.setBounds(255, 50, 328, 22);
		panel_4.add(txtCnpj);
		
		txtNomePJ = new JTextField();
		txtNomePJ.setEnabled(false);
		txtNomePJ.setBounds(255, 106, 328, 22);
		panel_4.add(txtNomePJ);
		txtNomePJ.setColumns(10);
		
		txtEnderecoPJ = new JTextField();
		txtEnderecoPJ.setEnabled(false);
		txtEnderecoPJ.setBounds(255, 160, 328, 22);
		panel_4.add(txtEnderecoPJ);
		txtEnderecoPJ.setColumns(10);
		
		spinnerNumFunc = new JSpinner();
		spinnerNumFunc.setEnabled(false);
		spinnerNumFunc.setBounds(255, 212, 328, 22);
		panel_4.add(spinnerNumFunc);
		
		JButton btnInserirPJ = new JButton("Inserir");
		btnInserirPJ.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnInserirPJ.getText().toLowerCase().equals("inserir")) {
					trocarEstadoCamposPJ(true);
					btnInserirPJ.setText("Salvar");
					btnCancelarPJ.setVisible(true);
				}
				else if(btnInserirPJ.getText().toLowerCase().equals("salvar")){
					PessoaJuridica pj = new PessoaJuridica();
					pj.setNomePJ(txtNomePJ.getText());
					pj.setEndereco(txtEnderecoPJ.getText());
					pj.setCnpj(txtCnpj.getText().replaceAll("[.,-/]",""));
					pj.setNumFuncionarios((int) spinnerNumFunc.getValue());
		
					sistema.inserirPessoaJuridica(pj);
					
					trocarEstadoCamposPJ(false);
					btnInserirPJ.setText("Inserir");
					btnCancelarPJ.setVisible(false);
					limparCamposPJ();
				}
				else {
					PessoaJuridica pj = new PessoaJuridica();
					pj.setId(ID);
					pj.setNomePJ(txtNomePJ.getText());
					pj.setEndereco(txtEnderecoPJ.getText());
					pj.setCnpj(txtCnpj.getText().replaceAll("[.,/-]",""));
					pj.setNumFuncionarios((int) spinnerNumFunc.getValue()); 
		
					sistema.atualizaPessoaJuridica(pj);
					
					trocarEstadoCamposPJ(false);
					btnInserirPJ.setText("Inserir");
					btnCancelarPJ.setVisible(false);
					limparCamposPJ();
				}
			}
		});
		btnInserirPJ.setBounds(170, 278, 97, 25);
		panel_4.add(btnInserirPJ);
		
		btnCancelarPJ = new JButton("Cancelar");
		btnCancelarPJ.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnInserirPJ.setText("Inserir");
				btnCancelarPJ.setVisible(false);
				trocarEstadoCamposPJ(false);
				limparCamposPJ();
			}
		});
		btnCancelarPJ.setBounds(415, 278, 97, 25);
		btnCancelarPJ.setVisible(false);
		panel_4.add(btnCancelarPJ);
		
		JPanel panel_5 = new JPanel();
		tabbedPane_3.addTab("Modificar", null, panel_5, null);
		panel_5.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(64, 67, 546, 178);
		panel_5.add(scrollPane_1);
		
		tblPJ = new JTable();
		tblPJ.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblPJ.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "CNPJ", "Nome", "Endere\u00E7o", "Num Func"
			}
		));
		scrollPane_1.setViewportView(tblPJ);
		
		JLabel lblCnpj_1 = new JLabel("CNPJ");
		lblCnpj_1.setBounds(180, 30, 37, 16);
		panel_5.add(lblCnpj_1);
		
		txtFiltroPJ = new JTextField();
		txtFiltroPJ.setBounds(229, 23, 178, 31);
		panel_5.add(txtFiltroPJ);
		txtFiltroPJ.setColumns(10);
		
		JButton btnFiltrarPJ = new JButton("Filtrar");
		btnFiltrarPJ.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String cnpj = txtFiltroPJ.getText().replaceAll("[.,-/]", "");
				ArrayList<PessoaJuridica> lista = PessoaJuridicaDAO.getInstance().selectCnpj(cnpj);
				atualizaTabelaPJ(lista);
			}
		});
		btnFiltrarPJ.setBounds(419, 26, 97, 25);
		panel_5.add(btnFiltrarPJ);
		
		btnModificarPJ = new JButton("Modificar");
		btnModificarPJ.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tblPJ.getSelectedRow() != -1) {
					PessoaJuridica pj = new PessoaJuridica();
					pj.setId((int) tblPJ.getValueAt(tblPJ.getSelectedRow(), 0));
					pj.setCnpj((String) tblPJ.getValueAt(tblPJ.getSelectedRow(), 1));
					pj.setNomePJ((String) tblPJ.getValueAt(tblPJ.getSelectedRow(), 2));
					pj.setEndereco((String) tblPJ.getValueAt(tblPJ.getSelectedRow(), 3));
					pj.setNumFuncionarios((int) tblPJ.getValueAt(tblPJ.getSelectedRow(), 4));
					ID = pj.getId();
					
					tabbedPane_3.setSelectedIndex(0);
					trocarEstadoCamposPJ(true);
					btnInserirPJ.setText("Atualizar");
					btnCancelarPJ.setVisible(true);
					
					txtCnpj.setText(pj.getCnpj());
					txtNomePJ.setText(pj.getNomePJ());
					txtEnderecoPJ.setText(pj.getEndereco());
					spinnerNumFunc.setValue(pj.getNumFuncionarios());;
				}
			}
		});
		btnModificarPJ.setBounds(180, 277, 97, 25);
		panel_5.add(btnModificarPJ);
		
		JButton btnDeletarPJ = new JButton("Deletar");
		btnDeletarPJ.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tblPJ.getSelectedRow() != -1) {
					ID = (int) tblPJ.getValueAt(tblPJ.getSelectedRow(), 0);
					sistema.deletaPessoaJuridica(ID);
					carregaTabelaPJ();
				}
			}
		});
		btnDeletarPJ.setBounds(419, 277, 97, 25);
		panel_5.add(btnDeletarPJ);
		
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Documentos", null, tabbedPane_2, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane_2.addTab("Cadastrar", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel numProt = new JLabel("Num Protocolo");
		numProt.setBounds(35, 59, 86, 16);
		panel_2.add(numProt);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(35, 130, 56, 16);
		panel_2.add(lblValor);
		
		JLabel lblcpfnota = new JLabel("CPF");
		lblcpfnota.setBounds(376, 59, 56, 16);
		panel_2.add(lblcpfnota);
		
		JLabel lblcnpjnota = new JLabel("CNPJ");
		lblcnpjnota.setBounds(376, 130, 56, 16);
		panel_2.add(lblcnpjnota);
		
		txtNumProt = new JFormattedTextField();
		txtNumProt.setEnabled(false);
		txtNumProt.setBounds(133, 56, 209, 22);
		panel_2.add(txtNumProt);
		
		spinnerValor = new JSpinner();
		spinnerValor.setEnabled(false);
		spinnerValor.setBounds(133, 127, 209, 22);
		panel_2.add(spinnerValor);
		
		comboCpf = new JComboBox<String>();
		comboCpf.setEnabled(false);
		comboCpf.setBounds(475, 56, 178, 22);
		panel_2.add(comboCpf);
		
		comboCnpj = new JComboBox<String>();
		comboCnpj.setEnabled(false);
		comboCnpj.setBounds(475, 127, 178, 22);
		panel_2.add(comboCnpj);
		
		JRadioButton rdbtnNota = new JRadioButton("Nota Fiscal");
		buttonGroup.add(rdbtnNota);
		rdbtnNota.setBounds(42, 196, 127, 25);
		panel_2.add(rdbtnNota);
		
		JRadioButton rdbtnContracheque = new JRadioButton("Contracheque");
		buttonGroup.add(rdbtnContracheque);
		rdbtnContracheque.setBounds(42, 226, 127, 25);
		panel_2.add(rdbtnContracheque);
		
		JButton btnInserirDocumento = new JButton("Inserir");
		btnInserirDocumento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnInserirDocumento.getText().toLowerCase().equals("inserir")) {
					trocarEstadoCamposDoc(true);
					btnInserirDocumento.setText("Salvar");
					btnCancelarDocumento.setVisible(true);
				}
				else if(btnInserirDocumento.getText().toLowerCase().equals("salvar")){
					if(rdbtnNota.isSelected()) {
						NotaFiscal nota = new NotaFiscal();
						nota.setNumProtocolo(Integer.parseInt(txtNumProt.getText()));
						nota.setValor((float) ((int) spinnerValor.getValue()));
						int id1 = comboCpf.getSelectedIndex();
						int id2 = comboCnpj.getSelectedIndex();
						
						sistema.insereDocumento(nota, id1, id2);
						
						trocarEstadoCamposDoc(false);
						btnInserirDocumento.setText("Inserir");
						btnCancelarDocumento.setVisible(false);
						limparCamposDoc();
					}
					else if(rdbtnContracheque.isSelected()) {
						Contracheque cheque = new Contracheque();
						cheque.setNumProtocolo(Integer.parseInt(txtNumProt.getText()));
						cheque.setValor((float) ((int) spinnerValor.getValue()));
						int id1 = comboCpf.getSelectedIndex();
						int id2 = comboCnpj.getSelectedIndex();
						
						sistema.insereDocumento(cheque, id1, id2);
						
						trocarEstadoCamposDoc(false);
						btnInserirDocumento.setText("Inserir");
						btnCancelarDocumento.setVisible(false);
						limparCamposDoc();
					}
				}
				else {
					Contribuinte contribuinte = new Contribuinte();
					contribuinte.setId(ID);
					contribuinte.setNome(txtNome.getText());
					contribuinte.setEndereco(txtEndereco.getText());
					contribuinte.setCpf(txtCpf.getText().replaceAll("[.,-]",""));
					contribuinte.setIdade((int) spinnerIdade.getValue()); 
					contribuinte.setContaBancaria(Integer.parseInt(txtContaBancaria.getText().replaceAll("[.,-]","")));
		
					ContribuinteDAO cDao = ContribuinteDAO.getInstance();
					cDao.update(contribuinte);
					
					trocarEstadoCamposDoc(false);
					btnInserirDocumento.setText("Inserir");
					btnCancelarDocumento.setVisible(false);
					limparCamposDoc();
				}
			}
		});
		btnInserirDocumento.setBounds(246, 226, 97, 25);
		panel_2.add(btnInserirDocumento);
		
		btnCancelarDocumento = new JButton("Cancelar");
		btnCancelarDocumento.setBounds(475, 226, 97, 25);
		panel_2.add(btnCancelarDocumento);
		
		JPanel panel_3 = new JPanel();
		tabbedPane_2.addTab("Modificar", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblCpfContribuinte_1 = new JLabel("CPF Contribuinte");
		lblCpfContribuinte_1.setBounds(112, 31, 89, 14);
		panel_3.add(lblCpfContribuinte_1);
		
		comboFiltroCpfDoc = new JComboBox<String>();
		comboFiltroCpfDoc.setBounds(217, 28, 235, 20);
		panel_3.add(comboFiltroCpfDoc);
		
		JRadioButton rdbtnNotaFiscalFiltro = new JRadioButton("Nota Fiscal");
		rdbtnNotaFiscalFiltro.setBounds(562, 7, 109, 23);
		panel_3.add(rdbtnNotaFiscalFiltro);
		
		JRadioButton rdbtnContrachequeFiltro = new JRadioButton("Contracheque");
		rdbtnContrachequeFiltro.setBounds(562, 31, 109, 23);
		panel_3.add(rdbtnContrachequeFiltro);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(100, 78, 495, 173);
		panel_3.add(scrollPane_3);
		
		tblDocs = new JTable();
		tblDocs.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Num Protocolo", "Valor"
			}
		));
		scrollPane_3.setViewportView(tblDocs);
		
		JButton btnModificarDoc = new JButton("Modificar");
		btnModificarDoc.setBounds(174, 293, 89, 23);
		panel_3.add(btnModificarDoc);
		
		JButton btnDeletarDoc = new JButton("Deletar");
		btnDeletarDoc.setBounds(415, 293, 89, 23);
		panel_3.add(btnDeletarDoc);
		
		JButton btnSelecionarDoc = new JButton("Selecionar");
		btnSelecionarDoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(rdbtnNotaFiscalFiltro.isSelected()) {
					ArrayList<NotaFiscal> lista = new ArrayList<NotaFiscal>();
					for(Documento doc : sistema.getContribuintes().get(comboFiltroCpfDoc.getSelectedIndex()).getDocumentos()) {
						if(doc instanceof NotaFiscal)
							lista.add((NotaFiscal) doc);
					}
					atualizaTabelaNotas(lista);
				}
				else if(rdbtnContrachequeFiltro.isSelected()) {
					ArrayList<Contracheque> lista = new ArrayList<Contracheque>();
					for(Documento doc : sistema.getContribuintes().get(comboFiltroCpfDoc.getSelectedIndex()).getDocumentos()) {
						if(doc instanceof Contracheque)
							lista.add((Contracheque) doc);
					}
					atualizaTabelaCheques(lista);
				}	
			}
		});
		btnSelecionarDoc.setBounds(462, 27, 89, 23);
		panel_3.add(btnSelecionarDoc);
		
		JTabbedPane tabbedPane_5 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Bens", null, tabbedPane_5, null);
		
		JPanel panel_8 = new JPanel();
		tabbedPane_5.addTab("Cadastrar", null, panel_8, null);
		
		JPanel panel_9 = new JPanel();
		tabbedPane_5.addTab("Modificar", null, panel_9, null);
		
		tabbedPane_2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				limpaCombo();
				if(tabbedPane_2.getSelectedIndex() == 0) {
					for (Contribuinte contr : sistema.getContribuintes()) {
						comboCpf.addItem(contr.getCpf());
					}
					for (PessoaJuridica pj : sistema.getPjs()) {
						comboCnpj.addItem(pj.getCnpj());
					}
				}
				if(tabbedPane_2.getSelectedIndex() == 1) {;
					for (Contribuinte contr : sistema.getContribuintes()) {
						comboFiltroCpfDoc.addItem(contr.getCpf());
					}
				}
			}
		});
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				limpaCombo();
				for (Contribuinte contr : sistema.getContribuintes()) {
					comboCpf.addItem(contr.getCpf());
					comboCpfDep.addItem(contr.getCpf());
					comboFiltroDep.addItem(contr.getCpf());
					comboFiltroCpfDoc.addItem(contr.getCpf());
				}
				for (PessoaJuridica pj : sistema.getPjs()) {
					comboCnpj.addItem(pj.getCnpj());
				}
			}
		});
		tabbedPane_4.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				limpaCombo();
				if(tabbedPane_4.getSelectedIndex() == 0) {
					for (Contribuinte contr : sistema.getContribuintes()) {
						comboCpfDep.addItem(contr.getCpf());
					}
				}
				if(tabbedPane_4.getSelectedIndex() == 1) {
					for (Contribuinte contr : sistema.getContribuintes()) {
						comboFiltroDep.addItem(contr.getCpf());
					}
				}
			}
		});
		
		
	}
	
	private void limpaCombo() {
		comboCnpj.removeAllItems();
		comboCpf.removeAllItems();
		comboCpfDep.removeAllItems();
		comboFiltroCpfDoc.removeAllItems();
		comboFiltroDep.removeAllItems();
	}
	
	private void limpaTabela(JTable table) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
	}
	
	private void carregaTabela() {
		limpaTabela(tblContribuintes);
		txtFiltro.setText("");
		
		String linha[] = new String[] {"", "", "", "", "", ""};
		try {
			DefaultTableModel dadosContrib = (DefaultTableModel) tblContribuintes.getModel();
			ArrayList<Contribuinte> contribuintes = ContribuinteDAO.getInstance().selectAll();
			int pos = -1;
			for(Contribuinte contribuinte : contribuintes) {
				pos++;
				dadosContrib.addRow(linha);
				dadosContrib.setValueAt(contribuinte.getId(), pos, 0);
				dadosContrib.setValueAt(contribuinte.getNome(), pos, 1);
				dadosContrib.setValueAt(contribuinte.getEndereco(), pos, 2);
				dadosContrib.setValueAt(contribuinte.getCpf(), pos, 3);
				dadosContrib.setValueAt(contribuinte.getIdade(), pos, 4);
				dadosContrib.setValueAt(contribuinte.getContaBancaria(), pos, 5);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erros: " + e.getMessage(), "Erro:", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void carregaTabelaPJ() {
		limpaTabela(tblPJ);
		txtFiltro.setText("");
		
		String linha[] = new String[] {"", "", "", "", ""};
		try {
			DefaultTableModel dadosPJ = (DefaultTableModel) tblPJ.getModel();
			ArrayList<PessoaJuridica> pjs = PessoaJuridicaDAO.getInstance().selectAll();
			int pos = -1;
			for(PessoaJuridica pj : pjs) {
				pos++;
				dadosPJ.addRow(linha);
				dadosPJ.setValueAt(pj.getId(), pos, 0);
				dadosPJ.setValueAt(pj.getCnpj(), pos, 1);
				dadosPJ.setValueAt(pj.getNomePJ(), pos, 2);
				dadosPJ.setValueAt(pj.getEndereco(), pos, 3);
				dadosPJ.setValueAt(pj.getNumFuncionarios(), pos, 4);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erros: " + e.getMessage(), "Erro:", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void atualizaTabela(ArrayList<Contribuinte> contribuintes) {
		limpaTabela(tblContribuintes);
		String linha[] = new String[] {"", "", "", "", "", ""};
		try {
			DefaultTableModel dadosContrib = (DefaultTableModel) tblContribuintes.getModel();
			
			int pos = -1;
			for(Contribuinte contribuinte : contribuintes) {
				pos++;
				dadosContrib.addRow(linha);
				dadosContrib.setValueAt(contribuinte.getId(), pos, 0);
				dadosContrib.setValueAt(contribuinte.getNome(), pos, 1);
				dadosContrib.setValueAt(contribuinte.getEndereco(), pos, 2);
				dadosContrib.setValueAt(contribuinte.getCpf(), pos, 3);
				dadosContrib.setValueAt(contribuinte.getIdade(), pos, 4);
				dadosContrib.setValueAt(contribuinte.getContaBancaria(), pos, 5);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erros: " + e.getMessage(), "Erro:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void atualizaTabelaDep(ArrayList<Dependente> dependentes) {
		limpaTabela(tblDep);
		String linha[] = new String[] {"", "", "", "", ""};
		try {
			DefaultTableModel dadosDep = (DefaultTableModel) tblDep.getModel();
			
			int pos = -1;
			for(Dependente dependente : dependentes) {
				pos++;
				dadosDep.addRow(linha);
				dadosDep.setValueAt(dependente.getId(), pos, 0);
				dadosDep.setValueAt(dependente.getNome(), pos, 1);
				dadosDep.setValueAt(dependente.getEndereco(), pos, 2);
				dadosDep.setValueAt(dependente.getCpf(), pos, 3);
				dadosDep.setValueAt(dependente.getIdade(), pos, 4);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erros: " + e.getMessage(), "Erro:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void atualizaTabelaNotas(ArrayList<NotaFiscal> documentos) {
		limpaTabela(tblDocs);
		String linha[] = new String[] {"", "", ""};
		try {
			DefaultTableModel dadosNota = (DefaultTableModel) tblDocs.getModel();
			
			int pos = -1;
			for(NotaFiscal nota : documentos) {
				pos++;
				dadosNota.addRow(linha);
				dadosNota.setValueAt(nota.getId(), pos, 0);
				dadosNota.setValueAt(nota.getNumProtocolo(), pos, 1);
				dadosNota.setValueAt(nota.getValor(), pos, 2);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erros: " + e.getMessage(), "Erro:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void atualizaTabelaCheques(ArrayList<Contracheque> documentos) {
		limpaTabela(tblDocs);
		String linha[] = new String[] {"", "", ""};
		try {
			DefaultTableModel dadosNota = (DefaultTableModel) tblDocs.getModel();
			
			int pos = -1;
			for(Contracheque cheque : documentos) {
				pos++;
				dadosNota.addRow(linha);
				dadosNota.setValueAt(cheque.getId(), pos, 0);
				dadosNota.setValueAt(cheque.getNumProtocolo(), pos, 1);
				dadosNota.setValueAt(cheque.getValor(), pos, 2);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erros: " + e.getMessage(), "Erro:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void atualizaTabelaPJ(ArrayList<PessoaJuridica> pjs) {
		limpaTabela(tblPJ);
		String linha[] = new String[] {"", "", "", "", ""};
		try {
			DefaultTableModel dadosPJ = (DefaultTableModel) tblPJ.getModel();
			
			int pos = -1;
			for(PessoaJuridica pj : pjs) {
				pos++;
				dadosPJ.addRow(linha);
				dadosPJ.setValueAt(pj.getId(), pos, 0);
				dadosPJ.setValueAt(pj.getCnpj(), pos, 1);
				dadosPJ.setValueAt(pj.getNomePJ(), pos, 2);
				dadosPJ.setValueAt(pj.getEndereco(), pos, 3);
				dadosPJ.setValueAt(pj.getNumFuncionarios(), pos, 4);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erros: " + e.getMessage(), "Erro:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void trocarEstadoCampos(boolean estado) {
		txtNome.setEnabled(estado);
		txtEndereco.setEnabled(estado);
		txtCpf.setEnabled(estado);
		spinnerIdade.setEnabled(estado);
		txtContaBancaria.setEnabled(estado);
	}
	
	private void limparCampos() {
		txtNome.setText("");
		txtEndereco.setText("");
		txtCpf.setText("");
		spinnerIdade.setValue(0);
		txtContaBancaria.setText("");
	}
	
	private void trocarEstadoCamposDep(boolean estado) {
		comboCpfDep.setEnabled(estado);
		txtNomeDep.setEnabled(estado);
		txtEndDep.setEnabled(estado);
		txtCpfDep.setEnabled(estado);
		spinnerIdadeDep.setEnabled(estado);
	}
	
	private void limparCamposDep() {
		comboCpfDep.setSelectedIndex(0);
		txtNomeDep.setText("");
		txtEndDep.setText("");
		txtCpfDep.setText("");
		spinnerIdadeDep.setValue(0);
	}
	
	private void trocarEstadoCamposDoc(boolean estado) {
		txtNumProt.setEnabled(estado);
		spinnerValor.setEnabled(estado);
		comboCpf.setEnabled(estado);
		comboCnpj.setEnabled(estado);
	}
	
	private void limparCamposDoc() {
		txtNumProt.setText("");
		spinnerValor.setValue(0);
		comboCpf.setSelectedIndex(0);
		comboCnpj.setSelectedIndex(0);
	}
	
	private void trocarEstadoCamposPJ(boolean estado) {
		txtNomePJ.setEnabled(estado);
		txtEnderecoPJ.setEnabled(estado);
		txtCnpj.setEnabled(estado);
		spinnerNumFunc.setEnabled(estado);
	}
	
	private void limparCamposPJ() {
		txtNomePJ.setText("");
		txtEnderecoPJ.setText("");
		txtCnpj.setText("");
		spinnerNumFunc.setValue(0);
	}

	private MaskFormatter criarMask(String string) {
		MaskFormatter mf = null;
		try {
			mf = new MaskFormatter(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mf;
	}
}