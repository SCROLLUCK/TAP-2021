package WindowBuilder;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import dao.CardapioDAO;
import dao.GarcomDAO;
import dao.MesaDAO;
import dao.PedidoDAO;

import model.Garcom;
import model.Mesa;
import model.Opcao;
import model.Pedido;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class WindowBuilder {

	private JFrame frame;
	private JPanel panel;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private ArrayList<Garcom> garcons;
	private ArrayList<Mesa> mesas;
	private ArrayList<Opcao> cardapio;
	private ArrayList<Pedido> pedidos;
	
	private GarcomDAO gerenciaGarcom = new GarcomDAO();
	private MesaDAO gerenciaMesa = new MesaDAO();
	private PedidoDAO gerenciaPedido = new PedidoDAO();
	private CardapioDAO gerenciaCardapio = new CardapioDAO();
	
	private JPanel garconsList = new JPanel();
	private JPanel mesasList = new JPanel();
	private JPanel cardapioList = new JPanel();
	private JPanel pedidosList = new JPanel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowBuilder window = new WindowBuilder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void updateGarcons() {
		
		garcons = gerenciaGarcom.listarGarcons();
		garconsList.removeAll();
		
		for(int i=0; i < garcons.size(); i++) {
			
			Garcom garcom = garcons.get(i);
			
			JPanel garcomInfo = new JPanel(new GridLayout(0, 1));
			garcomInfo.setBounds(10, 35*i, 100, 30);
			JLabel garcomNome = new JLabel("Nome: "+garcom.getNome());
			JLabel garcomCPF = new JLabel("CPF: "+garcom.getCpf());
			JLabel garcomGenero = new JLabel("Genero: "+garcom.getGenero());
			JLabel garcomNascimento = new JLabel("Nascimento: "+garcom.getNascimento());
			
			garcomInfo.add(garcomNome);
			garcomInfo.add(garcomCPF);
			garcomInfo.add(garcomGenero);
			garcomInfo.add(garcomNascimento);
			
			JPanel garcomBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JButton editarGarcom = new JButton("Editar");
			editarGarcom.setToolTipText("Editar dados de "+garcom.getNome());
			editarGarcom.setBackground(Color.LIGHT_GRAY);
			editarGarcom.setForeground(Color.DARK_GRAY);
			editarGarcom.setBounds(0, 0, 50, 100);
			editarGarcom.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					AtualizaGarcom atualizaGarcom = new AtualizaGarcom(garcom);
					atualizaGarcom.frame.setLocationByPlatform(true);
					atualizaGarcom.frame.setVisible(true);
					atualizaGarcom.frame.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					        updateGarcons();
					    }
					});
			    }
			});
			
			JButton removerGarcom = new JButton("X");
			removerGarcom.setToolTipText("Remover "+garcom.getNome());
			removerGarcom.setBackground(Color.red);
			removerGarcom.setForeground(Color.white);
			removerGarcom.setBounds(0, 0, 50, 100);
			removerGarcom.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent e){
		    	  if (gerenciaGarcom.removerGarcom(garcom)) {
		    		  JOptionPane.showMessageDialog(null, "Garçom removido com sucesso!");
		    		  updateGarcons();
		    	  }
		    	  else JOptionPane.showMessageDialog(null, "Erro ao tentar remover este Garçom. Porfavor, tente novamente.");

		      }
		    });
			
			garcomBox.add(garcomInfo);
			garcomBox.add(editarGarcom);
			garcomBox.add(removerGarcom);
			garconsList.add(garcomBox);
		
		}
		
		garconsList.repaint();
		garconsList.revalidate();
	}
	
	public void updateMesas() {
		
		mesas = gerenciaMesa.listarMesas();
		mesasList.removeAll();
		
		for(int i=0; i < mesas.size(); i++) {
			
			Mesa mesa = mesas.get(i);
			
			JPanel mesaInfo = new JPanel(new GridLayout(0, 1));
			
			mesaInfo.setBounds(10, 35*i, 100, 30);
			
			JLabel mesaNumero = new JLabel("Numero: "+mesa.getId());
			JLabel mesaNumeroClientes = new JLabel("Numero Clientes: "+mesa.getNumeroClientes());
			JLabel mesaStatus;
			if(mesa.getOcupada()) {
				mesaStatus = new JLabel("Status: Ocupada");
				mesaStatus.setForeground(Color.orange);
			}else {
				mesaStatus = new JLabel("Status: Disponível");
				mesaStatus.setForeground(Color.blue);
			}
			mesaStatus.setFont(new Font("Dialog", Font.ITALIC, 12));
			
			mesaInfo.add(mesaNumero);
			mesaInfo.add(mesaNumeroClientes);
			mesaInfo.add(mesaStatus);
			
			JPanel mesaBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JButton atualizarMesa = new JButton("Atualizar");
			atualizarMesa.setToolTipText("atualizar dados da mesa: "+mesa.getId());
			atualizarMesa.setBackground(Color.LIGHT_GRAY);
			atualizarMesa.setForeground(Color.DARK_GRAY);
			atualizarMesa.setBounds(0, 0, 50, 100);
			atualizarMesa.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					AtualizaMesa atualizaMesa = new AtualizaMesa(mesa);
					atualizaMesa.frame.setLocationByPlatform(true);
					atualizaMesa.frame.setVisible(true);
					atualizaMesa.frame.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					        updateMesas();
					    }
					});
			    }
			});
			
			mesaBox.add(mesaInfo);
			mesaBox.add(atualizarMesa);
			mesasList.add(mesaBox);
		
		}
		
		mesasList.repaint();
		mesasList.revalidate();
	}
	
	public void updateCardapio() {
		
		cardapio = gerenciaCardapio.listarCardapio();
		cardapioList.removeAll();
		
		for(int i=0; i < cardapio.size(); i++) {
			
			Opcao opcao = cardapio.get(i);
			
			JPanel opcaoInfo = new JPanel(new GridLayout(0, 1));
			opcaoInfo.setBounds(10, 35*i, 100, 30);
			
			JLabel opcaoNome = new JLabel("Nome: "+opcao.getNome());
			JLabel opcaoPreco = new JLabel("Preço: "+opcao.getPreco());
			
			opcaoInfo.add(opcaoNome);
			opcaoInfo.add(opcaoPreco);
			
			JPanel opcaoBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JButton atualizarOpcao = new JButton("Atualizar");
			atualizarOpcao.setToolTipText("Atualizar dados da opção: "+opcao.getNome());
			atualizarOpcao.setBackground(Color.LIGHT_GRAY);
			atualizarOpcao.setForeground(Color.DARK_GRAY);
			atualizarOpcao.setBounds(0, 0, 50, 100);
			atualizarOpcao.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					AtualizaOpcao atualizaOpcao = new AtualizaOpcao(opcao);
					atualizaOpcao.frame.setLocationByPlatform(true);
					atualizaOpcao.frame.setVisible(true);
					atualizaOpcao.frame.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					        updateCardapio();
					    }
					});
			    }
			});
			
			JButton removerOpcao = new JButton("X");
			removerOpcao.setToolTipText("Remover "+opcao.getNome());
			removerOpcao.setBackground(Color.red);
			removerOpcao.setForeground(Color.white);
			removerOpcao.setBounds(0, 0, 50, 100);
			removerOpcao.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent e){
		    	  if (gerenciaCardapio.removerOpcao(opcao)) {
		    		  JOptionPane.showMessageDialog(null, "Opção :"+opcao.getNome()+"removida com sucesso!");
		    		  updateCardapio();
		    	  }
		    	  else JOptionPane.showMessageDialog(null, "Erro ao tentar remover esta Opção. Porfavor, tente novamente.");
		      }
		    });
			
			opcaoBox.add(opcaoInfo);
			opcaoBox.add(atualizarOpcao);
			opcaoBox.add(removerOpcao);
			
			cardapioList.add(opcaoBox);
		
		}
		
		cardapioList.repaint();
		cardapioList.revalidate();
	}
	
	public void updatePedidos(){
		
		pedidos = gerenciaPedido.listarPedidosPendentes();
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

		    	  if (gerenciaPedido.entregarPedido(pedido)) {
		    		  JOptionPane.showMessageDialog(null, "Pedido atualizado com sucesso!");
		    		  updatePedidos();
		    	  }
		    	  else JOptionPane.showMessageDialog(null, "Erro ao tentar atualizar este pedido. Porfavor, tente novamente.");

		      }
		    });
			
			JPanel pedidoBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pedidoBox.add(pedidoInfo);
			if(pedido.getStatus().equals("pendente")) pedidoBox.add(entregarPedido);
			pedidosList.add(pedidoBox);
		
		}
		
		pedidosList.repaint();
		pedidosList.revalidate();

	}
	
	 public class Loop extends Thread {

		public void run(){
			while(true) {
				updatePedidos();
				try {
					TimeUnit.MINUTES.sleep(1);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	 }

	/**
	 * Create the application.
	 */
	public WindowBuilder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Gerenciamento lanchonete V1.0");
		frame.setBackground(Color.lightGray);
		frame.setBounds(100, 100, 800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.lightGray);
		frame.getContentPane().add(tabbedPane);		
		
		panel = new JPanel();
		panel.setBackground(Color.lightGray);
		panel.setLayout(new BorderLayout(0, 0));
		tabbedPane.addTab("Gerenciar Garçons", null, panel, null);
		
		JLabel titulo = new JLabel("Lista de garçons:");
		titulo.setBounds(20,10,740,30);
		titulo.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(titulo, BorderLayout.NORTH);

		garconsList.setBackground(Color.lightGray);
		updateGarcons();
		
		JButton btnNewButton = new JButton("Adicionar Garçom");
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				CriaGarcom criaGarcom = new CriaGarcom();
				criaGarcom.frame.setLocationByPlatform(true);
				criaGarcom.frame.setVisible(true);
				criaGarcom.frame.addWindowListener(new java.awt.event.WindowAdapter() {
				    @Override
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				        updateGarcons();
				    }
				});
		    }
		});
		
		panel.add(btnNewButton, BorderLayout.SOUTH);
		panel.add(garconsList, BorderLayout.CENTER);
		
		panel2 = new JPanel();
		panel2.setLayout(new BorderLayout(0, 0));
		panel2.setBackground(Color.lightGray);
		tabbedPane.addTab("Gerenciar Mesas", null, panel2, null);
		
		JLabel titulo2 = new JLabel("Lista de mesas:");
		titulo2.setBounds(20,10,740,30);
		titulo2.setHorizontalAlignment(SwingConstants.LEFT);
		panel2.add(titulo2, BorderLayout.NORTH);
		
		mesasList.setBackground(Color.lightGray);
		updateMesas();
		
		JButton adicionarMesa = new JButton("Adicionar Mesa");
		adicionarMesa.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Mesa mesa = new Mesa();
				if(gerenciaMesa.adicionarMesa(mesa)) {
					JOptionPane.showMessageDialog(null, "Mesa adicinada com sucesso!");
					updateMesas();
				}else {
					JOptionPane.showMessageDialog(null, "Um erro ocorreu ao tentar adicinar nova Mesa.");
				}
		    }
		});
		
		panel2.add(mesasList, BorderLayout.CENTER);
		panel2.add(adicionarMesa, BorderLayout.SOUTH);
		
		
		panel3 = new JPanel();
		panel3.setLayout(new BorderLayout(0, 0));
		panel3.setBackground(Color.lightGray);
		tabbedPane.addTab("Gerenciar Cardápio", null, panel3, null);
		
		JLabel titulo3 = new JLabel("Cardápio:");
		titulo3.setBounds(20,10,740,30);
		titulo3.setHorizontalAlignment(SwingConstants.LEFT);
		panel3.add(titulo3, BorderLayout.NORTH);
		
		cardapioList.setBackground(Color.lightGray);
		updateCardapio();
		
		JButton adicionarOpcao = new JButton("Adicionar Opção");
		adicionarOpcao.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				CriaOpcao criaOpcao = new CriaOpcao();
				criaOpcao.frame.setLocationByPlatform(true);
				criaOpcao.frame.setVisible(true);
				criaOpcao.frame.addWindowListener(new java.awt.event.WindowAdapter() {
				    @Override
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				        updateCardapio();
				    }
				});
		    }
		});
		
		panel3.add(cardapioList, BorderLayout.CENTER);
		panel3.add(adicionarOpcao, BorderLayout.SOUTH);
		
		panel4 = new JPanel();
		panel4.setLayout(new BorderLayout(0, 0));
		panel4.setBackground(Color.lightGray);
		tabbedPane.addTab("Gerenciar Pedidos", null, panel4, null);
		
		JLabel titulo4 = new JLabel("Pedidos:");
		titulo4.setBounds(20,10,740,30);
		titulo4.setHorizontalAlignment(SwingConstants.LEFT);
		panel4.add(titulo4, BorderLayout.NORTH);
		
		
		cardapioList.setBackground(Color.lightGray);		
		panel4.add(pedidosList, BorderLayout.CENTER);
		
		Loop loop = new Loop();
		loop.start();
		
	}

}
