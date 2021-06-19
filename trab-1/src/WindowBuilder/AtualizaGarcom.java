package WindowBuilder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.GarcomDAO;
import model.Garcom;

public class AtualizaGarcom {

	JFrame frame;
	private static Garcom garcom;
	private GarcomDAO gerenciaGarcom = new GarcomDAO();
	private JTextField inputNome;
	private JTextField inputCPF;
	private JComboBox<String> inputGenero = new JComboBox<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtualizaGarcom window = new AtualizaGarcom(garcom);
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
	public AtualizaGarcom(Garcom garcom) {
		initialize(garcom);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Garcom garcom) {
		frame = new JFrame("Atualizar dados de "+garcom.getNome());
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		AtualizaGarcom.garcom = garcom;
	
		JPanel form = new JPanel();
		
		JPanel NomeInputBox = new JPanel(new BorderLayout());
		JLabel NomeLabelInputBox = new JLabel("Nome");
		inputNome = new JTextField();
		inputNome.setColumns(30);
		inputNome.setToolTipText("Nome do funcionario");
		inputNome.setText(garcom.getNome());
		NomeInputBox.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		NomeInputBox.add(NomeLabelInputBox, BorderLayout.WEST);
		NomeInputBox.add(inputNome, BorderLayout.EAST);
		
		JPanel CPFInputBox = new JPanel();
		JLabel CPFLabelInputBox = new JLabel("CPF");
		inputCPF = new JTextField();
		inputCPF.setColumns(20);
		inputCPF.setToolTipText("CPF do funcionario");
		inputCPF.setText(garcom.getCpf());
		CPFInputBox.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		CPFInputBox.add(CPFLabelInputBox);
		CPFInputBox.add(inputCPF);
		
		inputGenero.addItem("masculino");
		inputGenero.addItem("feminino");
		inputGenero.setSelectedItem(garcom.getGenero());
		
		form.add(NomeInputBox);
		form.add(CPFInputBox);
		form.add(inputGenero);
		
		JButton atualizaGarcom = new JButton("Salvar alterações");
		atualizaGarcom.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){
	    	  if(inputNome.getText().contentEquals("") || inputCPF.getText().contentEquals("")) {
	    		  JOptionPane.showMessageDialog(null, "Porfavor, preencha todos os dados antes de continuar.");
	    		  return;
	    	  }
		      try {
		    	  garcom.setNome(inputNome.getText());
		    	  garcom.setCpf(inputCPF.getText());
		    	  garcom.setGenero((String) inputGenero.getSelectedItem());
		    	  boolean updated = gerenciaGarcom.atualizarGarcom(garcom);
		    	  if (updated) {
		    		  JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!");
		    		  frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		    		  frame.setVisible(false);
		    	  }
		    	  else JOptionPane.showMessageDialog(null, "Erro ao atualizar dados do garçon!");
		      } catch (Exception e1) {
		    	  e1.printStackTrace();
		      }
	      }
	    });
		
		frame.getContentPane().add(form, BorderLayout.CENTER);
		frame.getContentPane().add(atualizaGarcom, BorderLayout.SOUTH);
	}

}
