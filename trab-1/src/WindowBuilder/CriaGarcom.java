package WindowBuilder;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;

import dao.GarcomDAO;
import model.Garcom;

public class CriaGarcom {

	JFrame frame;
	private JTextField inputNome;
	private JTextField inputCPF;
	private GarcomDAO gerenciaGarcom = new GarcomDAO();
	private JComboBox<String> inputGenero = new JComboBox<String>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CriaGarcom window = new CriaGarcom();
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
	public CriaGarcom() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Cadastro de Garçons");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel titulo = new JLabel("Cadastro de garçons");
		JPanel form = new JPanel();
		
		JPanel NomeInputBox = new JPanel(new BorderLayout());
		JLabel NomeLabelInputBox = new JLabel("Nome");
		inputNome = new JTextField();
		inputNome.setColumns(30);
		inputNome.setToolTipText("Nome do funcionario");
		NomeInputBox.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		NomeInputBox.add(NomeLabelInputBox, BorderLayout.WEST);
		NomeInputBox.add(inputNome, BorderLayout.EAST);
		
		JPanel CPFInputBox = new JPanel();
		JLabel CPFLabelInputBox = new JLabel("CPF");
		inputCPF = new JTextField();
		inputCPF.setColumns(20);
		inputCPF.setToolTipText("CPF do funcionario");
		CPFInputBox.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		CPFInputBox.add(CPFLabelInputBox);
		CPFInputBox.add(inputCPF);
		
		inputGenero.addItem("masculino");
		inputGenero.addItem("feminino");
		
		form.add(NomeInputBox);
		form.add(CPFInputBox);
		form.add(inputGenero);
		
		JButton cadastraGarcom = new JButton("Cadastrar Garçom");
		cadastraGarcom.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){
	    	  if(inputNome.getText().contentEquals("") || inputCPF.getText().contentEquals("")) {
	    		  JOptionPane.showMessageDialog(null, "Porfavor, preencha todos os dados antes de continuar.");
	    		  return;
	    	  }
		      try {
		    	  
		    	  Garcom novoGarcom = new Garcom(inputNome.getText(),inputCPF.getText(),(String) inputGenero.getSelectedItem() ,new Date());
		    	  if (gerenciaGarcom.adicionarGarcom(novoGarcom)) {
		    		  JOptionPane.showMessageDialog(null, "Garçom cadastrado com sucesso!");
		    		  frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		    		  frame.setVisible(false);
		    	  }
		    	  else JOptionPane.showMessageDialog(null, "Erro ao cadastrar novo Garçom!");
		      } catch (Exception e1) {
		    	  e1.printStackTrace();
		      }
	      }
	    });
		
		frame.getContentPane().add(titulo, BorderLayout.NORTH);
		frame.getContentPane().add(form, BorderLayout.CENTER);
		frame.getContentPane().add(cadastraGarcom, BorderLayout.SOUTH);
	}

}
