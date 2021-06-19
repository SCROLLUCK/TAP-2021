package WindowBuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
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

import dao.MesaDAO;
import dao.PedidoDAO;
import model.Mesa;
import model.Pedido;

public class AtualizaMesa {

	JFrame frame;
	private static Mesa mesa;
	private JTextField inputNumeroClientes;
	private ArrayList<Pedido> pedidos;
	private MesaDAO gerenciaMesas = new MesaDAO();
	private PedidoDAO gerenciaPedidos = new PedidoDAO();
	private JPanel pedidosList = new JPanel();
	private JComboBox<String> inputOcupada = new JComboBox<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtualizaMesa window = new AtualizaMesa(mesa);
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
	public AtualizaMesa(Mesa mesa) {
		initialize(mesa);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void updatePedidos() {
		
		pedidos = gerenciaPedidos.listarPedidos(mesa);
		pedidosList.setBackground(Color.LIGHT_GRAY);
		pedidosList.removeAll();
		
		for(int i=0; i < pedidos.size(); i++) {
			
			Pedido pedido = pedidos.get(i);
			
			JPanel pedidoInfo = new JPanel(new GridLayout(0, 1));
			
			pedidoInfo.setBounds(10, 35*i, 100, 30);	
			JLabel pedidoNome = new JLabel("Pedido: "+pedido.getNome());
			Double total = Math.round(pedido.getPreco()*pedido.getQuantidade()*100.0)/100.0;
			JLabel peidoPreco = new JLabel("Preço total: "+total);
			JLabel pedidoQuantidade = new JLabel("Quantidade: "+pedido.getQuantidade());
			JLabel pedidoStatus = new JLabel("Status: "+pedido.getStatus());
			pedidoStatus.setFont(new Font("Dialog", Font.ITALIC, 12)); 
			if(pedido.getStatus().equals("pendente")) { 
				pedidoStatus.setForeground(Color.orange);
			}else if(pedido.getStatus().equals("entregue")) { 
				pedidoStatus.setForeground(Color.blue);
			}
			
			pedidoInfo.add(pedidoNome);
			pedidoInfo.add(peidoPreco);
			pedidoInfo.add(pedidoQuantidade);
			pedidoInfo.add(pedidoStatus);
			
			JButton entregarPedido = new JButton("Entregar");
			entregarPedido.setToolTipText("Entregar "+pedido.getNome());
			entregarPedido.setBounds(0, 0, 50, 100);
			entregarPedido.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent e){

		    	  if (gerenciaPedidos.entregarPedido(pedido)) {
		    		  JOptionPane.showMessageDialog(null, "Pedido atualizado com sucesso!");
		    		  updatePedidos();
		    	  }
		    	  else JOptionPane.showMessageDialog(null, "Erro ao tentar atualizar este pedido. Porfavor, tente novamente.");

		      }
		    });
			
			JButton removerPedido = new JButton("X");
			removerPedido.setToolTipText("Remover "+pedido.getNome());
			removerPedido.setBackground(Color.red);
			removerPedido.setForeground(Color.white);
			removerPedido.setBounds(0, 0, 50, 100);
			removerPedido.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent e){

		    	  boolean remove = gerenciaPedidos.removerPedido(mesa,pedido);
		    	  if (remove) {
		    		  JOptionPane.showMessageDialog(null, "Pedido removido com sucesso!");
		    		  updatePedidos();
		    	  }
		    	  else JOptionPane.showMessageDialog(null, "Erro ao tentar remover este pedido. Porfavor, tente novamente.");

		      }
		    });
			
			JPanel pedidoBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pedidoBox.add(pedidoInfo);
			if(pedido.getStatus().equals("pendente")) pedidoBox.add(entregarPedido);
			if(pedido.getStatus().equals("pendente")) pedidoBox.add(removerPedido);
			pedidosList.add(pedidoBox);
		
		}
		
		pedidosList.repaint();
		pedidosList.revalidate();
	}
	
	private void initialize(Mesa mesa) {
		frame = new JFrame("Informações sobre a mesa: "+mesa.getId());
		frame.setBounds(100, 100, 800, 400);
		frame.setBackground(Color.DARK_GRAY);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		AtualizaMesa.mesa = mesa;
		
		JPanel form = new JPanel();
		form.setBackground(Color.LIGHT_GRAY);
		
		JPanel NumeroInputBox = new JPanel(new BorderLayout());
		JLabel NumeroLabelInputBox = new JLabel("Numero de Clientes");
		inputNumeroClientes = new JTextField();
		inputNumeroClientes.setColumns(30);
		inputNumeroClientes.setToolTipText("Numero de Clientes");
		inputNumeroClientes.setText(String.valueOf(mesa.getNumeroClientes()));
		NumeroInputBox.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		NumeroInputBox.add(NumeroLabelInputBox, BorderLayout.WEST);
		NumeroInputBox.add(inputNumeroClientes, BorderLayout.EAST);
		
		inputOcupada.addItem("ocupada");
		inputOcupada.addItem("disponível");
		String status = "disponível";
		if(mesa.getOcupada()) { status = "ocupada"; } 
		inputOcupada.setSelectedItem(status);
		
		form.add(NumeroInputBox);
		form.add(inputOcupada);
		
		updatePedidos();
		
		JButton adicionarPedido = new JButton("Adicionar Pedido");
		adicionarPedido.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AdicionarPedido adicionarPedido = new AdicionarPedido(mesa);
				adicionarPedido.frame.setLocationByPlatform(true);
				adicionarPedido.frame.setVisible(true);
				adicionarPedido.frame.addWindowListener(new java.awt.event.WindowAdapter() {
				    @Override
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				        updatePedidos();
				    }
				});
		    }
		});
		
		
		
		JButton atualizarMesa = new JButton("Salvar alterações");
		atualizarMesa.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){
	    	  
	    	  if(inputNumeroClientes.getText().contentEquals("") || Integer.valueOf(inputNumeroClientes.getText()) < 0 ) {
	    		  JOptionPane.showMessageDialog(null, "Porfavor, preencha os dados corretamente  antes de continuar.");
	    		  return;
	    	  }
	    
		      try {
		    	  mesa.setNumeroClientes(Integer.valueOf(inputNumeroClientes.getText()));
		    	  boolean status = false;
		    	  if(inputOcupada.getSelectedItem().equals("ocupada")) { status = true; }
		    	  mesa.setOcupada(status);
		    	  boolean updated = gerenciaMesas.atualizarMesa(mesa);
	
		    	  if (updated) {
		    		  JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!");
		    		  frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		    		  frame.setVisible(false);
		    		  updatePedidos();
		    	  }
		    	  else JOptionPane.showMessageDialog(null, "Erro ao atualizar dados desta mesa. Por favor, tente novamente.");
		      } catch (Exception e1) {
		    	  e1.printStackTrace();
		      }
	      }
	    });
		
		JButton fecharConta = new JButton("Fechar Conta");
		fecharConta.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){
	  
	    	  FinalizarConta finalizarConta = new FinalizarConta(mesa);
	    	  finalizarConta.frame.setLocationByPlatform(true);
	    	  finalizarConta.frame.setVisible(true);
	    	  finalizarConta.frame.addWindowListener(new java.awt.event.WindowAdapter() {
	    		  @Override
	    		  public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	    			  frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		    		  frame.setVisible(false);
	    			  updatePedidos();
	    		  }
	    	  });
		      
	      }
	    });
		
		JPanel CENTER = new JPanel(new GridLayout(0,1));
		CENTER.setBackground(Color.DARK_GRAY);
		CENTER.add(form);
		CENTER.add(pedidosList);
		JPanel SOUTH = new JPanel(new FlowLayout());
		SOUTH.add(adicionarPedido);
		SOUTH.add(atualizarMesa);
		if(mesa.getOcupada() && pedidos.size() > 0) SOUTH.add(fecharConta);
		frame.getContentPane().add(CENTER, BorderLayout.CENTER);
		frame.getContentPane().add(SOUTH, BorderLayout.SOUTH);
	}

}
