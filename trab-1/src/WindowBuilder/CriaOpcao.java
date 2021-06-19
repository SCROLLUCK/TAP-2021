package WindowBuilder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.CardapioDAO;
import model.Garcom;
import model.Opcao;

public class CriaOpcao {

	JFrame frame;
	private CardapioDAO gerenciaOpcao = new CardapioDAO();
	private JTextField inputNome;
	private JTextField inputPreco;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CriaOpcao window = new CriaOpcao();
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
	public CriaOpcao() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Cadastrar nova opção no cardápio");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
		JPanel form = new JPanel();
		
		JPanel NomeInputBox = new JPanel(new BorderLayout());
		JLabel NomeLabelInputBox = new JLabel("Nome");
		inputNome = new JTextField();
		inputNome.setColumns(30);
		inputNome.setToolTipText("Nome do produto");
		
		NomeInputBox.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		NomeInputBox.add(NomeLabelInputBox, BorderLayout.WEST);
		NomeInputBox.add(inputNome, BorderLayout.EAST);
		
		JPanel PrecoInputBox = new JPanel();
		JLabel PrecoLabelInputBox = new JLabel("Preço do produto");
		inputPreco = new JTextField();
		inputPreco.setColumns(10);
		inputPreco.setToolTipText("Preço do produto");

		PrecoInputBox.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		PrecoInputBox.add(PrecoLabelInputBox);
		PrecoInputBox.add(inputPreco);
		
		
		form.add(NomeInputBox);
		form.add(PrecoInputBox);
		
		JButton criaOpcao = new JButton("Cadastrar Opção");
		criaOpcao.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){
	    	  
	    	  if(inputNome.getText().contentEquals("") || inputPreco.getText().contentEquals("")) {
	    		  JOptionPane.showMessageDialog(null, "Porfavor, preencha todos os dados antes de continuar.");
	    		  return;
	    	  }
	    	  
		      try {
		    	  Opcao opcao = new Opcao(-1,inputNome.getText(), Float.parseFloat(inputPreco.getText()));
		    	  if (gerenciaOpcao.adicionarOpcao(opcao)) {
		    		  JOptionPane.showMessageDialog(null, "Opção cadastrada com sucesso!");
		    		  frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		    		  frame.setVisible(false);
		    	  }
		    	  else JOptionPane.showMessageDialog(null, "Erro ao tentar cadastrar esta opção!");
		      } catch (Exception e1) {
		    	  e1.printStackTrace();
		      }
		      
	      }
	    });
		

		frame.getContentPane().add(form, BorderLayout.CENTER);
		frame.getContentPane().add(criaOpcao, BorderLayout.SOUTH);
	}

}
