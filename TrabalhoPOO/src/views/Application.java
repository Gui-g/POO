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
import persistencia.BemDAO;
import persistencia.ContrachequeDAO;
import persistencia.ContribuinteDAO;
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
import javax.swing.table.TableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

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
	private JSpinner spinnerValorBem;
	private JButton btnCancelarBem;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup2 = new ButtonGroup();
	private final ButtonGroup buttonGroup3 = new ButtonGroup();
	private final ButtonGroup buttonGroup4 = new ButtonGroup();
	private Sistema sistema = new Sistema();
	private JTextField txtNomePJ;
	private JTextField txtEnderecoPJ;
	private JTable tblPJ;
	private JTextField txtFiltroPJ;
	private JTextField txtNomeDep;
	private JTextField txtEndDep;
	private JTable tblDep;
	private JTable tblDocs;
	private JTextField txtNomeBem;
	private JTextField txtTipoBem;
	private JTable tblBens;
	private JComboBox<String> comboFiltroCpfBem;
	private JComboBox<String> comboCpfBens;
	private JRadioButton rdbtnBens;
	private JComboBox<String> comboCpfConsulta;
	private JRadioButton rdbtnDespesas;
	private JRadioButton rdbtnReceitas;
	private JRadioButton rdbtnPessoaJuridica;
	private JRadioButton rdbtnContracheques;
	private JRadioButton rdbtnNotasFiscais;
	private JComboBox<String> comboCnpjConsulta;
	
	private int ID;
	private JTextField txtTotal;
	private JTable tblConsulta;
	private JTextField txtTotalCnpj;
	private JTable tblConsultaCnpj;
	
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
		lblNome.setBounds(80, 28, 97, 16);
		panel.add(lblNome);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setBounds(80, 82, 97, 16);
		panel.add(lblEndereo);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(80, 128, 97, 16);
		panel.add(lblCpf);
		
		JLabel lblIdade = new JLabel("Idade");
		lblIdade.setBounds(80, 175, 96, 16);
		panel.add(lblIdade);
		
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setBounds(257, 25, 310, 22);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		txtEndereco = new JTextField();
		txtEndereco.setEnabled(false);
		txtEndereco.setBounds(257, 79, 310, 22);
		panel.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		txtCpf = new JFormattedTextField(criarMask("###.###.###-##"));
		txtCpf.setEnabled(false);
		txtCpf.setBounds(257, 125, 310, 22);
		panel.add(txtCpf);
		
		spinnerIdade = new JSpinner();
		spinnerIdade.setEnabled(false);
		spinnerIdade.setBounds(257, 172, 310, 22);
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
					
					try {
						sistema.inserirContribuinte(contribuinte);
					} catch (Exception e) {
						infoBox("CPF Já cadastrado", "ERROR");
					}
					
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
					txtCpf.setEnabled(true);
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
		lblContaBancria.setBounds(80, 227, 97, 16);
		panel.add(lblContaBancria);
		
		txtContaBancaria = new JFormattedTextField(criarMask("#####-#"));
		txtContaBancaria.setEnabled(false);
		txtContaBancaria.setBounds(257, 224, 310, 22);
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
					txtCpf.setEnabled(false);
				}
			}
		});
		btnModificar.setBounds(300, 287, 97, 25);
		panel_1.add(btnModificar);
		
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
		
		JPanel panel_10 = new JPanel();
		tabbedPane_1.addTab("Consultar", null, panel_10, null);
		panel_10.setLayout(null);
		
		JLabel lblCpf_3 = new JLabel("CPF ");
		lblCpf_3.setBounds(126, 40, 46, 14);
		panel_10.add(lblCpf_3);
		
		comboCpfConsulta = new JComboBox<String>();
		comboCpfConsulta.setBounds(182, 37, 298, 20);
		panel_10.add(comboCpfConsulta);
		
		JRadioButton rdbtnContribuinte = new JRadioButton("Contribuinte");
		buttonGroup3.add(rdbtnContribuinte);
		rdbtnContribuinte.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TableModel model = new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"CPF", "Nome", "Idade", "Endere\u00E7o", "Conta Banc\u00E1ria"
						}
				);
				
				tblConsulta.setModel(model);
			}
		});
		rdbtnContribuinte.setBounds(120, 64, 102, 23);
		panel_10.add(rdbtnContribuinte);
		
		JRadioButton rdbtnDependentes = new JRadioButton("Dependentes");
		buttonGroup3.add(rdbtnDependentes);
		rdbtnDependentes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TableModel model = new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"CPF", "Nome", "Idade", "Endere\u00E7o"
						}
				);
				
				tblConsulta.setModel(model);
			}
		});
		rdbtnDependentes.setBounds(224, 64, 109, 23);
		panel_10.add(rdbtnDependentes);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(107, 94, 490, 171);
		panel_10.add(scrollPane_5);
		
		tblConsulta = new JTable();
		tblConsulta.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CPF", "Nome", "Idade", "Endere\u00E7o", "Conta Banc\u00E1ria"
			}
		));
		scrollPane_5.setViewportView(tblConsulta);
		
		txtTotal = new JTextField();
		txtTotal.setEnabled(true);
		txtTotal.setEditable(false);
		txtTotal.setBounds(299, 276, 115, 20);
		panel_10.add(txtTotal);
		txtTotal.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(245, 279, 46, 14);
		panel_10.add(lblTotal);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(rdbtnContribuinte.isSelected()) {
					String cpf = ContribuinteDAO.getInstance().selectAll().get(comboCpfConsulta.getSelectedIndex()).getCpf();
					ArrayList<Contribuinte> lista = ContribuinteDAO.getInstance().selectCpf(cpf);
					atualizaTabelaConsultaContribuinte(lista);
				}
				else if(rdbtnDependentes.isSelected()) {
					ID = sistema.getContribuintes().get(comboCpfConsulta.getSelectedIndex()).getId();
					ArrayList<Dependente> lista = sistema.buscaDependente(ID);
					atualizaTabelaConsultaDependente(lista);
				}
				else if(rdbtnBens.isSelected()) {
					ID = sistema.getContribuintes().get(comboCpfConsulta.getSelectedIndex()).getId();
					ArrayList<Bem> lista = sistema.buscaBem(ID);
					atualizaTabelaConsultaBens(lista);
					
					txtTotal.setText(String.valueOf(BemDAO.getInstance().soma(ID)));
				}
				else if(rdbtnReceitas.isSelected()) {
					ID = sistema.getContribuintes().get(comboCpfConsulta.getSelectedIndex()).getId();
					ArrayList<Contracheque> lista = sistema.buscaContracheque(ID);
					atualizaTabelaConsultaCheques(lista);
					
					try {
						txtTotal.setText(String.valueOf(ContrachequeDAO.getInstance().soma(ID)));
					} catch (Exception e1) {
						infoBox("Nenhum contracheque cadastrado", "ERROR");
					}
				}
				else if(rdbtnDespesas.isSelected()) {
					ID = sistema.getContribuintes().get(comboCpfConsulta.getSelectedIndex()).getId();
					ArrayList<NotaFiscal> lista = sistema.buscaNotaFiscal(ID);
					atualizaTabelaNotaFiscal(lista);
					
					try {
						txtTotal.setText(String.valueOf(NotaFiscalDAO.getInstance().soma(ID)));
					} catch (Exception e1) {
						infoBox("Nenhuma nota fiscal cadastrada", "ERROR");
					}
				}
			}
		});
		btnConsultar.setBounds(508, 36, 89, 23);
		panel_10.add(btnConsultar);
		
		rdbtnBens = new JRadioButton("Bens");
		rdbtnBens.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TableModel model = new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Nome", "Tipo", "Valor"
						}
				);
				
				tblConsulta.setModel(model);
			}
		});
		buttonGroup3.add(rdbtnBens);
		rdbtnBens.setBounds(335, 64, 74, 23);
		panel_10.add(rdbtnBens);
		
		rdbtnReceitas = new JRadioButton("Receitas");
		buttonGroup3.add(rdbtnReceitas);
		rdbtnReceitas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TableModel model = new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Num Protocolo", "Valor"
						}
				);
				
				tblConsulta.setModel(model);
			}
		});
		rdbtnReceitas.setBounds(411, 64, 95, 23);
		panel_10.add(rdbtnReceitas);
		
		rdbtnDespesas = new JRadioButton("Despesas");
		buttonGroup3.add(rdbtnDespesas);
		rdbtnDespesas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TableModel model = new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Num Protocolo", "Valor"
						}
				);
				
				tblConsulta.setModel(model);
			}
		});
		rdbtnDespesas.setBounds(508, 64, 109, 23);
		panel_10.add(rdbtnDespesas);
		
		JTabbedPane tabbedPane_4 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Dependente", null, tabbedPane_4, null);
		
		JPanel panel_6 = new JPanel();
		tabbedPane_4.addTab("Cadastrar", null, panel_6, null);
		panel_6.setLayout(null);
		
		JLabel lblNome_1 = new JLabel("Nome");
		lblNome_1.setBounds(81, 94, 46, 14);
		panel_6.add(lblNome_1);
		
		txtNomeDep = new JTextField();
		txtNomeDep.setEnabled(false);
		txtNomeDep.setBounds(257, 90, 310, 22);
		panel_6.add(txtNomeDep);
		txtNomeDep.setColumns(10);
		
		JLabel lblEndereo_2 = new JLabel("Endere\u00E7o");
		lblEndereo_2.setBounds(81, 136, 89, 14);
		panel_6.add(lblEndereo_2);
		
		JLabel lblCpf_2 = new JLabel("CPF");
		lblCpf_2.setBounds(81, 175, 46, 14);
		panel_6.add(lblCpf_2);
		
		JLabel lblIdade_1 = new JLabel("Idade");
		lblIdade_1.setBounds(81, 215, 46, 14);
		panel_6.add(lblIdade_1);
		
		JLabel lblResponsvel = new JLabel("CPF Contribuinte");
		lblResponsvel.setBounds(81, 43, 108, 14);
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
					dependente.setIdade((int) spinnerIdadeDep.getValue()); 
			
					try {
						sistema.inserirDependente(dependente, sistema.getContribuintes().get(comboCpfDep.getSelectedIndex()).getId());
					} catch (Exception e) {
						infoBox("CPF já cadastrado", "ERROR");
					}
					
					trocarEstadoCamposDep(false);
					btnInserirDep.setText("Inserir");
					btnCancelarDep.setVisible(false);
					limparCamposDep();
				}
				else {
					Dependente dependente = new Dependente();
					dependente.setId(ID);
					dependente.setNome(txtNomeDep.getText());
					dependente.setEndereco(txtEndDep.getText());
					dependente.setCpf(txtCpfDep.getText().replaceAll("[.,-]",""));
					dependente.setIdade((int) spinnerIdadeDep.getValue()); 
		
					sistema.atualizarDependente(dependente);
					
					trocarEstadoCamposDep(false);
					btnInserirDep.setText("Inserir");
					btnCancelarDep.setVisible(false);
					comboCpfDep.setEnabled(true);
					txtCpfDep.setEnabled(true);
					limparCamposDep();
				}
			}
		});
		btnInserirDep.setBounds(170, 278, 89, 23);
		panel_6.add(btnInserirDep);
		
		btnCancelarDep = new JButton("Cancelar");
		btnCancelarDep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnInserirDep.setText("Inserir");
				btnCancelarDep.setVisible(false);
				trocarEstadoCamposDep(false);
				limparCamposDep();
			}
		});
		btnCancelarDep.setVisible(false);
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
				ID = sistema.getContribuintes().get(comboFiltroDep.getSelectedIndex()).getId();
				ArrayList<Dependente> lista = sistema.buscaDependente(ID);
				atualizaTabelaDep(lista);
			}
		});
		btnSelecionarDep.setBounds(419, 26, 107, 23);
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
		btnModificarDep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tblDep.getSelectedRow() != -1) {
					Dependente dependente = new Dependente();
					dependente.setId((int) tblDep.getValueAt(tblDep.getSelectedRow(), 0));
					dependente.setNome((String) tblDep.getValueAt(tblDep.getSelectedRow(), 1));
					dependente.setEndereco((String) tblDep.getValueAt(tblDep.getSelectedRow(), 2));
					dependente.setCpf((String) tblDep.getValueAt(tblDep.getSelectedRow(), 3));
					dependente.setIdade((int) tblDep.getValueAt(tblDep.getSelectedRow(), 4));
					ID = dependente.getId();
					
					tabbedPane_4.setSelectedIndex(0);
					trocarEstadoCamposDep(true);
					btnInserirDep.setText("Atualizar");
					btnCancelarDep.setVisible(true);
					comboCpfDep.setEnabled(false);
					
					txtNomeDep.setText(dependente.getNome());
					txtEndDep.setText(dependente.getEndereco());
					txtCpfDep.setText(dependente.getCpf());
					spinnerIdadeDep.setValue(dependente.getIdade());
					txtCpfDep.setEnabled(false);
				}
			}
		});
		btnModificarDep.setBounds(180, 277, 89, 23);
		panel_7.add(btnModificarDep);
		
		JButton btnDeletarDep = new JButton("Deletar");
		btnDeletarDep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tblDep.getSelectedRow() != -1) {
					System.out.println((int) tblDep.getValueAt(tblDep.getSelectedRow(), 0));
					ID = (int) tblDep.getValueAt(tblDep.getSelectedRow(), 0);
					sistema.deletarDependente(ID);
					ID = sistema.getContribuintes().get(comboFiltroDep.getSelectedIndex()).getId();
					ArrayList<Dependente> lista = sistema.buscaDependente(ID);
					atualizaTabelaDep(lista);
				}
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
		
					try {
						sistema.inserirPessoaJuridica(pj);
					} catch (Exception e) {
						infoBox("CNPJ já cadastrado", "ERROR");
					}
					
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
					txtCnpj.setEnabled(true);
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
					spinnerNumFunc.setValue(pj.getNumFuncionarios());
					txtCnpj.setEnabled(false);
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
		
		JPanel panel_11 = new JPanel();
		tabbedPane_3.addTab("Consultar", null, panel_11, null);
		panel_11.setLayout(null);
		
		JLabel lblCnpj_2 = new JLabel("CNPJ");
		lblCnpj_2.setBounds(111, 48, 46, 14);
		panel_11.add(lblCnpj_2);
		
		comboCnpjConsulta = new JComboBox<String>();
		comboCnpjConsulta.setBounds(167, 45, 298, 20);
		panel_11.add(comboCnpjConsulta);
		
		rdbtnPessoaJuridica = new JRadioButton("Pessoa Juridica");
		buttonGroup4.add(rdbtnPessoaJuridica);
		rdbtnPessoaJuridica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TableModel model = new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Nome", "Endere\u00E7o", "Num Funcion\u00E1rios"
						}
				);
				
				tblConsultaCnpj.setModel(model);
			}
		});
		rdbtnPessoaJuridica.setBounds(121, 72, 138, 23);
		panel_11.add(rdbtnPessoaJuridica);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(92, 102, 490, 171);
		panel_11.add(scrollPane_6);
		
		tblConsultaCnpj = new JTable();
		tblConsultaCnpj.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Endere\u00E7o", "Num Funcion\u00E1rios"
			}
		));
		tblConsultaCnpj.getColumnModel().getColumn(2).setPreferredWidth(96);
		scrollPane_6.setViewportView(tblConsultaCnpj);
		
		txtTotalCnpj = new JTextField();
		txtTotalCnpj.setEnabled(true);
		txtTotalCnpj.setEditable(false);
		txtTotalCnpj.setColumns(10);
		txtTotalCnpj.setBounds(284, 284, 115, 20);
		panel_11.add(txtTotalCnpj);
		
		JLabel label_1 = new JLabel("Total:");
		label_1.setBounds(230, 287, 46, 14);
		panel_11.add(label_1);
		
		JButton btnConsultaCnpj = new JButton("Consultar");
		btnConsultaCnpj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(rdbtnPessoaJuridica.isSelected()) {
					String cnpj = sistema.getPjs().get(comboCnpjConsulta.getSelectedIndex()).getCnpj();
					ArrayList<PessoaJuridica> pjs = PessoaJuridicaDAO.getInstance().selectCnpj(cnpj);
					atualizaTabelaConsultaCnpj(pjs);
				}
				else if(rdbtnContracheques.isSelected()) {
					ID = sistema.getPjs().get(comboCnpjConsulta.getSelectedIndex()).getId();
					ArrayList<Contracheque> lista = sistema.buscaContrachequePJ(ID);
					atualizaTabelaConsultaContracheque(lista);
					
					try {
						txtTotalCnpj.setText(String.valueOf(ContrachequeDAO.getInstance().somaPj(ID)));
					} catch (Exception e) {
						infoBox("Nenhum contracheque cadastrado", "ERROR");
					}
				}
				else if(rdbtnNotasFiscais.isSelected()) {
					ID = sistema.getPjs().get(comboCnpjConsulta.getSelectedIndex()).getId();
					ArrayList<NotaFiscal> lista = sistema.buscaNotaFiscalPj(ID);
					atualizaTabelaConsultaNotaFiscal(lista);
					
					try {
						txtTotalCnpj.setText(String.valueOf(NotaFiscalDAO.getInstance().somaPj(ID)));
					} catch (Exception e) {
						infoBox("Nenhuma nota fiscal cadastrada", "ERROR");
					}
				}
			}
			
		});
		btnConsultaCnpj.setBounds(493, 44, 89, 23);
		panel_11.add(btnConsultaCnpj);
		
		rdbtnContracheques = new JRadioButton("Contracheques");
		buttonGroup4.add(rdbtnContracheques);
		rdbtnContracheques.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TableModel model = new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Num Protocolo", "Valor"
						}
				);
				
				tblConsultaCnpj.setModel(model);
			}
		});
		rdbtnContracheques.setBounds(276, 72, 138, 23);
		panel_11.add(rdbtnContracheques);
		
		rdbtnNotasFiscais = new JRadioButton("Notas Fiscais");
		buttonGroup4.add(rdbtnNotasFiscais);
		rdbtnNotasFiscais.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TableModel model = new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Num Protocolo", "Valor"
						}
				);
				
				tblConsultaCnpj.setModel(model);
			}
		});
		rdbtnNotasFiscais.setBounds(429, 72, 109, 23);
		panel_11.add(rdbtnNotasFiscais);
		
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(tabbedPane_2.getSelectedIndex() == 1)
					limpaTabela(tblDocs);
			}
		});
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
						
						sistema.insereNotaFiscal(nota, id1, id2);
						
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
						
						sistema.insereContracheque(cheque, id1, id2);
						
						trocarEstadoCamposDoc(false);
						btnInserirDocumento.setText("Inserir");
						btnCancelarDocumento.setVisible(false);
						limparCamposDoc();
					}
				}
			}
		});
		btnInserirDocumento.setBounds(246, 226, 97, 25);
		panel_2.add(btnInserirDocumento);
		
		btnCancelarDocumento = new JButton("Cancelar");
		btnCancelarDocumento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnInserirDocumento.setText("Inserir");
				btnCancelarDocumento.setVisible(false);
				rdbtnNota.setSelected(false);
				rdbtnContracheque.setSelected(false);
				trocarEstadoCamposDoc(false);
				limparCamposDoc();
			}
		});
		btnCancelarDocumento.setBounds(475, 226, 97, 25);
		panel_2.add(btnCancelarDocumento);
		
		JPanel panel_3 = new JPanel();
		tabbedPane_2.addTab("Modificar", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblCpfContribuinte_1 = new JLabel("CPF Contribuinte");
		lblCpfContribuinte_1.setBounds(100, 31, 107, 14);
		panel_3.add(lblCpfContribuinte_1);
		
		comboFiltroCpfDoc = new JComboBox<String>();
		comboFiltroCpfDoc.setBounds(217, 28, 235, 20);
		panel_3.add(comboFiltroCpfDoc);
		
		JRadioButton rdbtnNotaFiscalFiltro = new JRadioButton("Nota Fiscal");
		buttonGroup2.add(rdbtnNotaFiscalFiltro);
		rdbtnNotaFiscalFiltro.setBounds(583, 7, 109, 23);
		panel_3.add(rdbtnNotaFiscalFiltro);
		
		JRadioButton rdbtnContrachequeFiltro = new JRadioButton("Contracheque");
		buttonGroup2.add(rdbtnContrachequeFiltro);
		rdbtnContrachequeFiltro.setBounds(583, 31, 109, 23);
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
		
		JButton btnDeletarDoc = new JButton("Deletar");
		btnDeletarDoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tblDocs.getSelectedRow() != -1) {
					if(rdbtnNotaFiscalFiltro.isSelected()) {
						ID = (int) tblDocs.getValueAt(tblDocs.getSelectedRow(), 0);
						sistema.deletaNotaFiscal(ID);
						ID = sistema.getContribuintes().get(comboFiltroCpfDoc.getSelectedIndex()).getId();
						ArrayList<NotaFiscal> lista = sistema.buscaNotaFiscal(ID);
						atualizaTabelaNotas(lista);
					}
					else if (rdbtnContrachequeFiltro.isSelected()) {
						ID = (int) tblDocs.getValueAt(tblDocs.getSelectedRow(), 0);
						sistema.deletaContracheque(ID);
						ID = sistema.getContribuintes().get(comboFiltroCpfDoc.getSelectedIndex()).getId();
						ArrayList<Contracheque> lista = sistema.buscaContracheque(ID);
						atualizaTabelaCheques(lista);
					}
				}
			}
		});
		btnDeletarDoc.setBounds(304, 288, 89, 23);
		panel_3.add(btnDeletarDoc);
		
		JButton btnSelecionarDoc = new JButton("Selecionar");
		btnSelecionarDoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				limpaTabela(tblDocs);
				if(rdbtnNotaFiscalFiltro.isSelected()) {
					ID = sistema.getContribuintes().get(comboFiltroCpfDoc.getSelectedIndex()).getId();
					ArrayList<NotaFiscal> lista = sistema.buscaNotaFiscal(ID);
					atualizaTabelaNotas(lista);
				}
				else if(rdbtnContrachequeFiltro.isSelected()) {
					ID = sistema.getContribuintes().get(comboFiltroCpfDoc.getSelectedIndex()).getId();
					ArrayList<Contracheque> lista = sistema.buscaContracheque(ID);
					atualizaTabelaCheques(lista);
				}	
			}
		});
		btnSelecionarDoc.setBounds(462, 27, 115, 23);
		panel_3.add(btnSelecionarDoc);
		
		JTabbedPane tabbedPane_5 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Bens", null, tabbedPane_5, null);
		
		JPanel panel_8 = new JPanel();
		tabbedPane_5.addTab("Cadastrar", null, panel_8, null);
		panel_8.setLayout(null);
		
		JLabel lblCpfContribuinte_2 = new JLabel("CPF Contribuinte");
		lblCpfContribuinte_2.setBounds(115, 43, 133, 14);
		panel_8.add(lblCpfContribuinte_2);
		
		JLabel lblNome_2 = new JLabel("Nome");
		lblNome_2.setBounds(115, 94, 46, 14);
		panel_8.add(lblNome_2);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(115, 152, 46, 14);
		panel_8.add(lblTipo);
		
		JLabel lblValor_1 = new JLabel("Valor");
		lblValor_1.setBounds(115, 212, 46, 14);
		panel_8.add(lblValor_1);
		
		comboCpfBens = new JComboBox<String>();
		comboCpfBens.setEnabled(false);
		comboCpfBens.setBounds(258, 40, 310, 20);
		panel_8.add(comboCpfBens);
		
		txtNomeBem = new JTextField();
		txtNomeBem.setEnabled(false);
		txtNomeBem.setBounds(258, 91, 310, 20);
		panel_8.add(txtNomeBem);
		txtNomeBem.setColumns(10);
		
		txtTipoBem = new JTextField();
		txtTipoBem.setEnabled(false);
		txtTipoBem.setBounds(258, 149, 310, 20);
		panel_8.add(txtTipoBem);
		txtTipoBem.setColumns(10);
		
		spinnerValorBem = new JSpinner();
		spinnerValorBem.setEnabled(false);
		spinnerValorBem.setBounds(258, 209, 310, 20);
		panel_8.add(spinnerValorBem);
		
		JButton btnInserirBem = new JButton("Inserir");
		btnInserirBem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnInserirBem.getText().toLowerCase().equals("inserir")) {
					trocarEstadoCamposBem(true);
					btnInserirBem.setText("Salvar");
					btnCancelarBem.setVisible(true);
				}
				else if(btnInserirBem.getText().toLowerCase().equals("salvar")){
					Bem bem = new Bem();
					bem.setNome(txtNomeBem.getText());
					bem.setTipo(txtTipoBem.getText());
					bem.setValor((float) ((int) spinnerValorBem.getValue())); 
			
					sistema.inserirBem(bem, sistema.getContribuintes().get(comboCpfBens.getSelectedIndex()).getId());
					
					trocarEstadoCamposBem(false);
					btnInserirBem.setText("Inserir");
					btnCancelarBem.setVisible(false);
					limparCamposBem();
				}
			}
		});
		btnInserirBem.setBounds(170, 278, 89, 23);
		panel_8.add(btnInserirBem);
		
		btnCancelarBem = new JButton("Cancelar");
		btnCancelarBem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnInserirBem.setText("Inserir");
				btnInserirBem.setVisible(false);
				trocarEstadoCamposBem(false);
				limparCamposBem();
			}
		});
		btnCancelarBem.setVisible(false);
		btnCancelarBem.setBounds(415, 278, 89, 23);
		panel_8.add(btnCancelarBem);
		
		JPanel panel_9 = new JPanel();
		tabbedPane_5.addTab("Modificar", null, panel_9, null);
		panel_9.setLayout(null);
		
		JLabel lblCpfContribuinte_3 = new JLabel("CPF Contribuinte");
		lblCpfContribuinte_3.setBounds(97, 30, 125, 14);
		panel_9.add(lblCpfContribuinte_3);
		
		comboFiltroCpfBem = new JComboBox<String>();
		comboFiltroCpfBem.setBounds(232, 26, 214, 22);
		panel_9.add(comboFiltroCpfBem);
		
		JButton btnSelecionarBem = new JButton("Selecionar");
		btnSelecionarBem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ID = sistema.getContribuintes().get(comboFiltroCpfBem.getSelectedIndex()).getId();
				ArrayList<Bem> lista = sistema.buscaBem(ID);
				atualizaTabelaBens(lista);
			}
		});
		btnSelecionarBem.setBounds(456, 26, 104, 23);
		panel_9.add(btnSelecionarBem);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(64, 67, 546, 178);
		panel_9.add(scrollPane_4);
		
		tblBens = new JTable();
		tblBens.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Tipo", "Valor"
			}
		));
		scrollPane_4.setViewportView(tblBens);
		
		JButton btnDeletarBem = new JButton("Deletar");
		btnDeletarBem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tblBens.getSelectedRow() != -1) {
					ID = (int) tblBens.getValueAt(tblBens.getSelectedRow(), 0);
					sistema.deletarBem(ID);
					ID = sistema.getContribuintes().get(comboFiltroCpfBem.getSelectedIndex()).getId();
					ArrayList<Bem> lista = sistema.buscaBem(ID);
					atualizaTabelaBens(lista);
				}
			}
		});
		btnDeletarBem.setBounds(295, 280, 89, 23);
		panel_9.add(btnDeletarBem);
		
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
		tabbedPane_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				limpaCombo();
				if(tabbedPane_1.getSelectedIndex() == 2) {;
					for (Contribuinte contr : sistema.getContribuintes()) {
						comboCpfConsulta.addItem(contr.getCpf());
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
					comboFiltroCpfBem.addItem(contr.getCpf());
					comboCpfBens.addItem(contr.getCpf());
					comboCpfConsulta.addItem(contr.getCpf());
				}
				for (PessoaJuridica pj : sistema.getPjs()) {
					comboCnpj.addItem(pj.getCnpj());
					comboCnpjConsulta.addItem(pj.getCnpj());
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
		tabbedPane_5.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				limpaCombo();
				if(tabbedPane_5.getSelectedIndex() == 0) {
					for (Contribuinte contr : sistema.getContribuintes()) {
						comboCpfBens.addItem(contr.getCpf());
					}
				}
				if(tabbedPane_5.getSelectedIndex() == 1) {
					for (Contribuinte contr : sistema.getContribuintes()) {
						comboFiltroCpfBem.addItem(contr.getCpf());
					}
				}
			}
		});
		tabbedPane_3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				limpaCombo();
				if(tabbedPane_3.getSelectedIndex() == 2) {
					for (PessoaJuridica pj : sistema.getPjs()) {
						comboCnpjConsulta.addItem(pj.getCnpj());
					}
				}
			}
		});
		
		
	}
	
	public void limpaCombo() {
		comboCnpj.removeAllItems();
		comboCpf.removeAllItems();
		comboCpfDep.removeAllItems();
		comboFiltroCpfDoc.removeAllItems();
		comboFiltroDep.removeAllItems();
		comboFiltroCpfBem.removeAllItems();
		comboCpfBens.removeAllItems();
		comboCpfConsulta.removeAllItems();
		comboCnpjConsulta.removeAllItems();
	}
	
	public void limpaTabela(JTable table) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
	}
	
	public void carregaTabela() {
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
	
	public void carregaTabelaPJ() {
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
	
	public void atualizaTabela(ArrayList<Contribuinte> contribuintes) {
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
	
	public void atualizaTabelaDep(ArrayList<Dependente> dependentes) {
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
	
	public void atualizaTabelaNotas(ArrayList<NotaFiscal> documentos) {
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
	
	public void atualizaTabelaCheques(ArrayList<Contracheque> documentos) {
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
	
	public void atualizaTabelaPJ(ArrayList<PessoaJuridica> pjs) {
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
	
	public void atualizaTabelaBens(ArrayList<Bem> bens) {
		limpaTabela(tblBens);
		String linha[] = new String[] {"", "", ""};
		try {
			DefaultTableModel dadosBens = (DefaultTableModel) tblBens.getModel();
			
			int pos = -1;
			for(Bem bem : bens) {
				pos++;
				dadosBens.addRow(linha);
				dadosBens.setValueAt(bem.getId(), pos, 0);
				dadosBens.setValueAt(bem.getNome(), pos, 1);
				dadosBens.setValueAt(bem.getTipo(), pos, 2);
				dadosBens.setValueAt(bem.getValor(), pos, 3);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erros: " + e.getMessage(), "Erro:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void atualizaTabelaConsultaContribuinte(ArrayList<Contribuinte> contribuintes) {
		limpaTabela(tblConsulta);
		String linha[] = new String[] {"", "", "", "", ""};
		try {
			DefaultTableModel dadosConsulta = (DefaultTableModel) tblConsulta.getModel();
			
			int pos = -1;
			for(Contribuinte contribuinte : contribuintes) {
				pos++;
				dadosConsulta.addRow(linha);
				dadosConsulta.setValueAt(contribuinte.getNome(), pos, 1);
				dadosConsulta.setValueAt(contribuinte.getEndereco(), pos, 3);
				dadosConsulta.setValueAt(contribuinte.getCpf(), pos, 0);
				dadosConsulta.setValueAt(contribuinte.getIdade(), pos, 2);
				dadosConsulta.setValueAt(contribuinte.getContaBancaria(), pos, 4);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erros: " + e.getMessage(), "Erro:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void atualizaTabelaConsultaDependente(ArrayList<Dependente> dependentes) {
		limpaTabela(tblConsulta);
		String linha[] = new String[] {"", "", "", ""};
		try {
			DefaultTableModel dadosConsulta = (DefaultTableModel) tblConsulta.getModel();
			
			int pos = -1;
			for(Dependente dependente : dependentes) {
				pos++;
				dadosConsulta.addRow(linha);
				dadosConsulta.setValueAt(dependente.getNome(), pos, 1);
				dadosConsulta.setValueAt(dependente.getEndereco(), pos, 3);
				dadosConsulta.setValueAt(dependente.getCpf(), pos, 0);
				dadosConsulta.setValueAt(dependente.getIdade(), pos, 2);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erros: " + e.getMessage(), "Erro:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void atualizaTabelaConsultaBens(ArrayList<Bem> bens) {
		limpaTabela(tblConsulta);
		String linha[] = new String[] {"", "", ""};
		try {
			DefaultTableModel dadosConsulta = (DefaultTableModel) tblConsulta.getModel();
			
			int pos = -1;
			for(Bem bem : bens) {
				pos++;
				dadosConsulta.addRow(linha);
				dadosConsulta.setValueAt(bem.getNome(), pos, 0);
				dadosConsulta.setValueAt(bem.getTipo(), pos, 1);
				dadosConsulta.setValueAt(bem.getValor(), pos, 2);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erros: " + e.getMessage(), "Erro:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void atualizaTabelaConsultaCheques(ArrayList<Contracheque> cheques) {
		limpaTabela(tblConsulta);
		String linha[] = new String[] {"", "", ""};
		try {
			DefaultTableModel dadosConsulta = (DefaultTableModel) tblConsulta.getModel();
			
			int pos = -1;
			for(Contracheque cheque : cheques) {
				pos++;
				dadosConsulta.addRow(linha);
				dadosConsulta.setValueAt(cheque.getNumProtocolo(), pos, 0);
				dadosConsulta.setValueAt(cheque.getValor(), pos, 1);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erros: " + e.getMessage(), "Erro:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void atualizaTabelaNotaFiscal(ArrayList<NotaFiscal> notas) {
		limpaTabela(tblConsulta);
		String linha[] = new String[] {"", "", ""};
		try {
			DefaultTableModel dadosConsulta = (DefaultTableModel) tblConsulta.getModel();
			
			int pos = -1;
			for(NotaFiscal nota : notas) {
				pos++;
				dadosConsulta.addRow(linha);
				dadosConsulta.setValueAt(nota.getNumProtocolo(), pos, 0);
				dadosConsulta.setValueAt(nota.getValor(), pos, 1);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erros: " + e.getMessage(), "Erro:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void atualizaTabelaConsultaCnpj(ArrayList<PessoaJuridica> pjs) {
		limpaTabela(tblConsultaCnpj);
		String linha[] = new String[] {"", "", ""};
		try {
			DefaultTableModel dadosConsulta = (DefaultTableModel) tblConsultaCnpj.getModel();
			
			int pos = -1;
			for(PessoaJuridica pj : pjs) {
				pos++;
				dadosConsulta.addRow(linha);
				dadosConsulta.setValueAt(pj.getNomePJ(), pos, 0);
				dadosConsulta.setValueAt(pj.getEndereco(), pos, 1);
				dadosConsulta.setValueAt(pj.getNumFuncionarios(), pos, 2);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erros: " + e.getMessage(), "Erro:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void atualizaTabelaConsultaContracheque(ArrayList<Contracheque> cheques) {
		limpaTabela(tblConsultaCnpj);
		String linha[] = new String[] {"", ""};
		try {
			DefaultTableModel dadosConsulta = (DefaultTableModel) tblConsultaCnpj.getModel();
			
			int pos = -1;
			for(Contracheque cheque : cheques) {
				pos++;
				dadosConsulta.addRow(linha);
				dadosConsulta.setValueAt(cheque.getNumProtocolo(), pos, 0);
				dadosConsulta.setValueAt(cheque.getValor(), pos, 1);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erros: " + e.getMessage(), "Erro:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void atualizaTabelaConsultaNotaFiscal(ArrayList<NotaFiscal> notas) {
		limpaTabela(tblConsultaCnpj);
		String linha[] = new String[] {"", ""};
		
		try {
			DefaultTableModel dadosConsulta = (DefaultTableModel) tblConsultaCnpj.getModel();
			
			int pos = -1;
			for(NotaFiscal nota : notas) {
				pos++;
				dadosConsulta.addRow(linha);
				dadosConsulta.setValueAt(nota.getNumProtocolo(), pos, 0);
				dadosConsulta.setValueAt(nota.getValor(), pos, 1);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erros: " + e.getMessage(), "Erro:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void trocarEstadoCampos(boolean estado) {
		txtNome.setEnabled(estado);
		txtEndereco.setEnabled(estado);
		txtCpf.setEnabled(estado);
		spinnerIdade.setEnabled(estado);
		txtContaBancaria.setEnabled(estado);
	}
	
	public void limparCampos() {
		txtNome.setText("");
		txtEndereco.setText("");
		txtCpf.setText("");
		spinnerIdade.setValue(0);
		txtContaBancaria.setText("");
	}
	
	public void trocarEstadoCamposDep(boolean estado) {
		comboCpfDep.setEnabled(estado);
		txtNomeDep.setEnabled(estado);
		txtEndDep.setEnabled(estado);
		txtCpfDep.setEnabled(estado);
		spinnerIdadeDep.setEnabled(estado);
	}
	
	public void limparCamposDep() {
		comboCpfDep.setSelectedIndex(0);
		txtNomeDep.setText("");
		txtEndDep.setText("");
		txtCpfDep.setText("");
		spinnerIdadeDep.setValue(0);
	}
	
	public void trocarEstadoCamposDoc(boolean estado) {
		txtNumProt.setEnabled(estado);
		spinnerValor.setEnabled(estado);
		comboCpf.setEnabled(estado);
		comboCnpj.setEnabled(estado);
	}
	
	public void limparCamposDoc() {
		txtNumProt.setText("");
		spinnerValor.setValue(0);
		comboCpf.setSelectedIndex(0);
		comboCnpj.setSelectedIndex(0);
	}
	
	public void trocarEstadoCamposPJ(boolean estado) {
		txtNomePJ.setEnabled(estado);
		txtEnderecoPJ.setEnabled(estado);
		txtCnpj.setEnabled(estado);
		spinnerNumFunc.setEnabled(estado);
	}
	
	public void limparCamposPJ() {
		txtNomePJ.setText("");
		txtEnderecoPJ.setText("");
		txtCnpj.setText("");
		spinnerNumFunc.setValue(0);
	}
	
	public void trocarEstadoCamposBem(boolean estado) {
		comboCpfBens.setEnabled(estado);
		txtNomeBem.setEnabled(estado);
		txtTipoBem.setEnabled(estado);
		spinnerValorBem.setEnabled(estado);
	}
	
	public void limparCamposBem() {
		comboCpfBens.setSelectedIndex(0);
		txtNomeBem.setText("");
		txtTipoBem.setText("");
		spinnerValorBem.setValue(0);
	}


	public MaskFormatter criarMask(String string) {
		MaskFormatter mf = null;
		try {
			mf = new MaskFormatter(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mf;
	}
	
	public void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}