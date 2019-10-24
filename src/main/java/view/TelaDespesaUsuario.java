package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ControladoraDespesa;
import controller.ControladoraUsuario;
import model.dao.DespesaDAO;

import model.vo.DespesaVO;
import model.vo.UsuarioVO;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class TelaDespesaUsuario {

	private JFrame frame;
	private JTable tblDespesa;
	private String[] columns = {"#", "Id.Usuário", "Categoria", "Descrição", "Valor", "Dt. Pagamento"};
	ControladoraUsuario conUsuario;
	
	

	Locale ptBr = new Locale("pt", "BR");
	NumberFormat formato = NumberFormat.getCurrencyInstance(ptBr);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDespesaUsuario window = new TelaDespesaUsuario();
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
	public TelaDespesaUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Despesas");
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[13.00][][grow][][][][grow][18.00]", "[16.00][][2.00][][8.00][249.00,grow][]"));
		
		JLabel lblNewLabel = new JLabel("Usu\u00E1rio:");
		frame.getContentPane().add(lblNewLabel, "cell 1 1,alignx trailing");
		



		ArrayList<UsuarioVO> usuarios = new ArrayList();
		conUsuario = new ControladoraUsuario();
		
		usuarios = conUsuario.consultarTodosUsuariosController();
		JComboBox<UsuarioVO> cbUsuario = new JComboBox();
		cbUsuario.addItem(null);
		for (UsuarioVO user: usuarios) {
			cbUsuario.addItem(user);			
		}
		

		frame.getContentPane().add(cbUsuario, "cell 2 1,growx");
		
		JLabel lblNewLabel_1 = new JLabel("Categoria:");
		frame.getContentPane().add(lblNewLabel_1, "cell 5 1");
		
		JComboBox cbCategoria = new JComboBox();
		frame.getContentPane().add(cbCategoria, "flowx,cell 6 1,growx");
		ArrayList<DespesaVO> categorias = new ArrayList();
		ControladoraDespesa conDespesa = new ControladoraDespesa();
		categorias = conDespesa.consultarTodasCategorias();
		
		cbCategoria.addItem("");
		for(DespesaVO desp: categorias) {
			cbCategoria.addItem(desp.getCategoria());
		}
		
		
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparTela();
				UsuarioVO  usuarioSelecionado = (UsuarioVO) cbUsuario.getSelectedItem();
			//	System.out.println(usuarioSelecionado.getIdUsuario());
				UsuarioVO idUsuario = (UsuarioVO) cbUsuario.getSelectedItem();
				String categoriaDespesa = String.valueOf(cbCategoria.getSelectedItem());
				
				ControladoraDespesa conDespesa = new ControladoraDespesa();
				ArrayList<DespesaVO> despesasTabela = conDespesa.consultarDespesas(idUsuario, categoriaDespesa);
				atualizarTabelaUsuario(despesasTabela);
				
			}
		});
		frame.getContentPane().add(btnBuscar, "cell 2 3 5 1,growx");
		
		tblDespesa = new JTable();
		tblDespesa.setModel(new DefaultTableModel(
				new Object[][] {columns}, columns));
		tblDespesa.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		frame.getContentPane().add(tblDespesa, "cell 1 5 6 1,grow");
	}

	protected void atualizarTabelaUsuario(ArrayList<DespesaVO> despesasTabela) {
		DefaultTableModel modelo = (DefaultTableModel) tblDespesa.getModel();

		for (DespesaVO des : despesasTabela) {
			String[] novaLinha = new String[6];
			novaLinha[0] = String.valueOf(des.getId());
			novaLinha[1] = String.valueOf(des.getIdUsuario());
			novaLinha[2] = des.getCategoria();
			novaLinha[3] = des.getDescricao();
			novaLinha[4] = formato.format(des.getValor());
			novaLinha[5] = mostrarDataAtualFormatada(des.getDataPagamento());
			
			modelo.addRow(novaLinha);
			
		}
	}

	private void limparTela() {
		tblDespesa.setModel(new DefaultTableModel(
				new Object[][] {columns}, columns));
	}
	
	public String mostrarDataAtualFormatada(LocalDate dataAtual) {
		DateTimeFormatter formatadorDeData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
		String dataFormatada = dataAtual.format(formatadorDeData);

		return dataFormatada;
	}
}
