package WindowBuilder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.CardapioDAO;
import model.Opcao;

public class AtualizaOpcao {

	JFrame frame;
	private static Opcao opcao;
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
					AtualizaOpcao window = new AtualizaOpcao(opcao);
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
	public AtualizaOpcao(Opcao opcao) {
		initialize(opcao);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Opcao opcao) {
		
		frame = new JFrame("Atualizar dados de "+opcao.getNome());
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		AtualizaOpcao.opcao = opcao;
	
		JPanel form = new JPanel();
		
		JPanel NomeInputBox = new JPanel(new BorderLayout());
		JLabel NomeLabelInputBox = new JLabel("Nome");
		inputNome = new JTextField();
		inputNome.setColumns(30);
		inputNome.setToolTipText("Nome do funcionario");
		inputNome.setText(opcao.getNome());
		NomeInputBox.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		NomeInputBox.add(NomeLabelInputBox, BorderLayout.WEST);
		NomeInputBox.add(inputNome, BorderLayout.EAST);
		
		JPanel PrecoInputBox = new JPanel();
		JLabel PrecoLabelInputBox = new JLabel("Preço do produto");
		inputPreco = new JTextField();
		inputPreco.setColumns(10);
		inputPreco.setToolTipText("Preço do produto");
		inputPreco.setText(Float.toString(opcao.getPreco()));
		PrecoInputBox.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		PrecoInputBox.add(PrecoLabelInputBox);
		PrecoInputBox.add(inputPreco);
		
		
		form.add(NomeInputBox);
		form.add(PrecoInputBox);

		JButton atualizaOpcao = new JButton("Salvar alterações");
		atualizaOpcao.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){
	    	  
	    	  if(inputNome.getText().contentEquals("")) {
	    		  JOptionPane.showMessageDialog(null, "Porfavor, preencha todos os dados antes de continuar.");
	    		  return;
	    	  }
	    	  
		      try {
		    	  opcao.setNome(inputNome.getText());
		    	  opcao.setPreco(Float.parseFloat(inputPreco.getText()));
	
		    	  if (gerenciaOpcao.atualizarOpcao(opcao)) {
		    		  JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!");
		    		  frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		    		  frame.setVisible(false);
		    	  }
		    	  else JOptionPane.showMessageDialog(null, "Erro ao atualizar dados desta opção!");
		      } catch (Exception e1) {
		    	  e1.printStackTrace();
		      }
	      }
	    });
		
		frame.getContentPane().add(form, BorderLayout.CENTER);
		frame.getContentPane().add(atualizaOpcao, BorderLayout.SOUTH);
	}

}
