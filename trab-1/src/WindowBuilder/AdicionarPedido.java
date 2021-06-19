package WindowBuilder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.CardapioDAO;
import dao.PedidoDAO;

import model.Mesa;
import model.Opcao;
import model.Pedido;

public class AdicionarPedido {

	JFrame frame;
	private static Mesa mesa;
	public ArrayList<Opcao> cardapio;
	private JComboBox<String> inputPedido = new JComboBox<String>();
	private CardapioDAO gerenciaCardapio = new CardapioDAO();
	private PedidoDAO gerenciaPedidos = new PedidoDAO();
	private JTextField inputQuantidade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdicionarPedido window = new AdicionarPedido(mesa);
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
	public AdicionarPedido(Mesa mesa) {
		initialize(mesa);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Mesa mesa) {
		frame = new JFrame("Adicionar Novo pedido");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		cardapio = gerenciaCardapio.listarCardapio();
		for(int i=0;i<cardapio.size();i++) {
			inputPedido.addItem(cardapio.get(i).getNome());
		}
		
		JPanel CPFInputBox = new JPanel();
		JLabel CPFLabelInputBox = new JLabel("Quantidade");
		inputQuantidade = new JTextField();
		inputQuantidade.setColumns(20);
		inputQuantidade.setToolTipText("Quantidade");
		CPFInputBox.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		CPFInputBox.add(CPFLabelInputBox);
		CPFInputBox.add(inputQuantidade);
		
		JPanel CENTER = new JPanel(new FlowLayout());
		CENTER.add(inputPedido);
		JPanel SOUTH = new JPanel(new GridLayout(0,1));
		
		JButton finalizarPedido = new JButton("Finalizar Pedido");
		finalizarPedido.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){
	    	  if(inputQuantidade.getText().contentEquals("") || Integer.valueOf(inputQuantidade.getText()) < 0 ) {
	    		  JOptionPane.showMessageDialog(null, "Porfavor, preencha os dados corretamente  antes de continuar.");
	    		  return;
	    	  }
	    	  
		      try {
		    	   
		    	  int index = -1;
		    	  for (int i=0; i<cardapio.size() && index == -1;i++) {
		    		  Opcao opcao = cardapio.get(i);
		    		  if(inputPedido.getSelectedItem().equals(opcao.getNome()) == true ) {
		    			  index = i;
		    		  }
		    	  }
		    	  
		    	  Opcao opcao = cardapio.get(index);
		    	  Pedido pedido = new Pedido(opcao.getId(),Integer.valueOf(inputQuantidade.getText()));
		    	  pedido.toString();
		    	  boolean add = gerenciaPedidos.adicionarPedido(mesa, pedido);
		    	  if (add) {
		    		  JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso!");
		    		  frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		    		  frame.setVisible(false);
		    	  }
		    	  else JOptionPane.showMessageDialog(null, "Erro ao fazer novo pedido!");
		      } catch (Exception e1) {
		    	  e1.printStackTrace();
		      }
	      }
	    });
		SOUTH.add(finalizarPedido);
		
		frame.getContentPane().add(CPFInputBox, BorderLayout.NORTH);
		frame.getContentPane().add(CENTER, BorderLayout.CENTER);
		frame.getContentPane().add(SOUTH, BorderLayout.SOUTH);
	}

}
