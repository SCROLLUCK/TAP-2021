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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dao.MesaDAO;
import dao.PedidoDAO;
import model.Mesa;
import model.Pedido;
import net.miginfocom.swing.MigLayout;

public class FinalizarConta {

	JFrame frame;
	static Mesa mesa;
	private ArrayList<Pedido> pedidos;
	private MesaDAO gerenciaMesas = new MesaDAO();
	private PedidoDAO gerenciaPedidos = new PedidoDAO();
	private JPanel pedidosList = new JPanel();
	private JPanel pagamentosIndividuais = new JPanel(new GridLayout(0,1));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinalizarConta window = new FinalizarConta(mesa);
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
	public FinalizarConta(Mesa mesa) {
		initialize(mesa);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void updateValorIndividual() {
		pagamentosIndividuais.setBackground(Color.LIGHT_GRAY);
		pagamentosIndividuais.removeAll();
		
		for(int i=0; i< mesa.getNumeroClientes(); i++) {
			Double total = 0.0;
			for(int j=0; j<pedidos.size();j++) {
				if((i+1) == pedidos.get(j).getNumeroCliente()) {
					total += pedidos.get(j).getPreco() * pedidos.get(j).getQuantidade();
				}
			}
			total = Math.round(total*100.0)/100.0;
			JLabel ClienteLabel = new JLabel(" Cliente Número "+(i+1)+", Total: R$ "+total);
			pagamentosIndividuais.add(ClienteLabel);
		}
		
		pagamentosIndividuais.repaint();
		pagamentosIndividuais.revalidate();
	}
	
	public void updatePedidos() {
		
		pedidos = gerenciaPedidos.listarPedidos(mesa);
		pedidosList.setBackground(Color.LIGHT_GRAY);
		pedidosList.removeAll();
		
		for(int i=0; i < pedidos.size(); i++) {
			
			Pedido pedido = pedidos.get(i);
			
			JPanel pedidoInfo = new JPanel(new GridLayout(0, 1));
			
			pedidoInfo.setBounds(10, 35*i, 100, 30);	
			JLabel pedidoNome = new JLabel("Pedido: "+pedido.getNome());
			JLabel peidoPreco = new JLabel("Preço total: "+pedido.getPreco()*pedido.getQuantidade());
			JLabel pedidoQuantidade = new JLabel("Quantidade: "+pedido.getQuantidade());
			JLabel pedidoStatus = new JLabel("Status: "+pedido.getStatus());
			pedidoStatus.setFont(new Font("Dialog", Font.ITALIC, 12)); 
			if(pedido.getStatus().equals("pendente")) { 
				pedidoStatus.setForeground(Color.orange);
			}else if(pedido.getStatus().equals("entregue")) { 
				pedidoStatus.setForeground(Color.blue);
			}
			JPanel BoxNumeroCliente = new JPanel(new FlowLayout());
			JLabel labelCliente = new JLabel("Numero do Cliente");
			
			JTextField numeroCliente = new JTextField(3);
			numeroCliente.setText(Integer.toString(pedido.getNumeroCliente()));
			numeroCliente.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) { warn(); }
			  public void removeUpdate(DocumentEvent e) { warn(); }
			  public void insertUpdate(DocumentEvent e) { warn(); }

			  public void warn() {
				 if(numeroCliente.getText().equals("") == false) {
					 if (Integer.parseInt(numeroCliente.getText())<=0 || (Integer.parseInt(numeroCliente.getText())>mesa.getNumeroClientes())){
						 numeroCliente.setText("0");
						 return;
					 }
					 pedido.setNumeroCliente(Integer.parseInt(numeroCliente.getText()));
					 updateValorIndividual();
				 }else {
					 pedido.setNumeroCliente(0);
					 updateValorIndividual();
				 }
			  }
			});
			
			BoxNumeroCliente.add(labelCliente);
			BoxNumeroCliente.add(numeroCliente);
			
			pedidoInfo.add(pedidoNome);
			pedidoInfo.add(peidoPreco);
			pedidoInfo.add(pedidoQuantidade);
			pedidoInfo.add(pedidoStatus);
			pedidoInfo.add(labelCliente);
			pedidoInfo.add(numeroCliente);
			
			JPanel pedidoBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pedidoBox.add(pedidoInfo);
			pedidosList.add(pedidoBox);
		
		}
		
		pedidosList.repaint();
		pedidosList.revalidate();
	}

	private void initialize(Mesa mesa) {
		frame = new JFrame("Fechamento de conta da mesa: "+mesa.getId());
		frame.setBounds(100, 100, 800, 350);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		FinalizarConta.mesa = mesa;		
		updatePedidos();
		updateValorIndividual();
		
		JButton finalizarPagamento = new JButton("Finalizar Pagamento");
		finalizarPagamento.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){
	    	  
	    	 
	    
		      try {
		    	  
		    	  for(int i=0; i<pedidos.size(); i++) {
		    		  Pedido pedido = pedidos.get(i);
		    		  if(pedido.getStatus().equals("pendente")) {
		    			  JOptionPane.showMessageDialog(null, "Não foi possivel finalizar o pagamento pois o pedido: "+pedido.getNome()+", ainda não foi entregue ao cliente. ");
		    			  return;
		    			  
		    		  }
		    		  
		    		  if(pedido.getNumeroCliente() == 0) {
		    			  JOptionPane.showMessageDialog(null, "Não foi possivel finalizar o pagamento pois o pedido: "+pedido.getNome()+", ainda não foi associado à um cliente. ");
		    			  return;
		    		  }
		    		  gerenciaPedidos.pagarPedido(pedido);
		    		   
		    	  }
		    	  mesa.setNumeroClientes(0);
		    	  mesa.setOcupada(false);
		    	  gerenciaMesas.atualizarMesa(mesa);
		    	  
		    	  JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!");
	    		  frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	    		  frame.setVisible(false);
	    		  updatePedidos();
	    		  
		      } catch (Exception e1) {
		    	  e1.printStackTrace();
		      }
	      }
	    });
		
		JPanel CENTER = new JPanel(new GridLayout(0,1));
		CENTER.setBackground(Color.DARK_GRAY);
		CENTER.add(pedidosList);
		
		JPanel SOUTH = new JPanel();
		SOUTH.setLayout(new MigLayout("", "[784px]", "[23px][23px]"));
		SOUTH.setBackground(Color.LIGHT_GRAY);
		SOUTH.add(pagamentosIndividuais, "cell 0 0,grow");
		SOUTH.add(finalizarPagamento, "cell 0 1,grow");
		frame.getContentPane().add(CENTER, BorderLayout.CENTER);
		frame.getContentPane().add(SOUTH, BorderLayout.SOUTH);
	}

}
