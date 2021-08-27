package compiler;


import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Compilador extends JFrame {
	private File file = null;

	// Paineis do Compilador
	private JPanel view;
	private JPanel btnMenu;
	
	
	private JTextArea taEditor;
	private JTextArea taMessage;
	private JButton btnNovo;
	private JButton btnAbrir;
	private JButton btnSalvar;
	private JButton btnCopiar;
	private JButton btnColar;
	private JButton btnCortar;
	private JButton btnCompilar;
	private JButton btnEquipe;
	private JLabel lblStatus;
	
  public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Compilador frame = new Compilador();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Compilador() {		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setMinimumSize(new Dimension(900, 600));
		view = new JPanel();
		view.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(view);
		
		btnMenu = new JPanel();
		btnMenu.setMinimumSize(new Dimension(150, 500));
		
		JScrollPane spEditor = new JScrollPane(taEditor, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        
        taEditor = new JTextArea();
        spEditor.setViewportView(taEditor);
        taEditor.setColumns(20);
        taEditor.setRows(5);
        taEditor.setBorder(new NumeredBorder());
        taEditor.setMinimumSize(null);
        taEditor.setPreferredSize(null);
        
        JScrollPane spMessage = new JScrollPane(taMessage, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        

        spMessage.setViewportView(taMessage);
        JTextArea messageArea = new JTextArea();
        messageArea.setEditable(false);
        spMessage.setViewportView(messageArea);

        taEditor.addKeyListener(new java.awt.event.KeyAdapter() {
        	public void keyPressed(java.awt.event.KeyEvent evt) {
        	}
        });
        
		JButton btnNovo = new JButton("Novo (Ctrl+N)");
		btnMenu.setLayout(new GridLayout(0, 1, 0, 0));
		btnNovo = new JButton("Novo (Ctrl+N)");
		btnMenu.add(btnNovo);
		
		btnAbrir = new JButton("Abrir (Ctrl+O)");
		btnMenu.add(btnAbrir);
		
		btnSalvar = new JButton("Salvar (Ctrl+S)");
		btnMenu.add(btnSalvar);
		
		btnCopiar = new JButton("Copiar (Ctrl+C)");
		btnMenu.add(btnCopiar);
		
		btnColar = new JButton("Colar (Ctrl+V)");
		btnMenu.add(btnColar);
		
		btnCortar = new JButton("Cortar (Ctrl+X)");
		btnMenu.add(btnCortar);
		
		btnCompilar = new JButton("Compilar (F9)");
		btnMenu.add(btnCompilar);
		setTitle("Compilador");

		btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("./novo.png")));
        btnNovo.setBorder(null);
        btnNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNovo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnNovo.setMaximumSize(new java.awt.Dimension(110, 50));
        btnNovo.setMinimumSize(new java.awt.Dimension(110, 50));
        btnNovo.setPreferredSize(new java.awt.Dimension(110, 50));
        btnNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	taEditor.setText("");
            	messageArea.setText("");
            	messageArea.setText("");
            	lblStatus.setText("");
            	file = null;
            }
        });
        
        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("./abrir.png"))); // NOI18N
        btnAbrir.setBorder(null);
        btnAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAbrir.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnAbrir.setMaximumSize(new Dimension(110, 50));
        btnAbrir.setMinimumSize(new Dimension(110, 50));
        btnAbrir.setPreferredSize(new Dimension(110, 50));
        btnAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	acaoAbrir();
            	messageArea.setText("");
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("./salvar.png"))); // NOI18N
        btnSalvar.setBorder(null);
        btnSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalvar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSalvar.setMaximumSize(new java.awt.Dimension(112, 70));
        btnSalvar.setMinimumSize(new java.awt.Dimension(112, 70));
        btnSalvar.setPreferredSize(new java.awt.Dimension(110, 70));
        btnSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	actionSalvar();
            }
        });

        btnCopiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("./copiar.png"))); // NOI18N
        btnCopiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCopiar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnCopiar.setMaximumSize(new java.awt.Dimension(112, 70));
        btnCopiar.setMinimumSize(new java.awt.Dimension(112, 70));
        btnCopiar.setPreferredSize(new java.awt.Dimension(110, 70));
        btnCopiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopiarActionPerformed(evt);
            }
        });

        btnCortar.setIcon(new javax.swing.ImageIcon(getClass().getResource("./recortar.png"))); // NOI18N
        btnCortar.setText("recortar [ctrl-x]");
        btnCortar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCortar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnCortar.setMaximumSize(new java.awt.Dimension(110, 70));
        btnCortar.setMinimumSize(new java.awt.Dimension(110, 70));
        btnCortar.setPreferredSize(new java.awt.Dimension(110, 70));
        btnCortar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnRecortarActionPerformed(evt);
            }
        });
        
        btnColar.setIcon(new javax.swing.ImageIcon(getClass().getResource("./colar.png"))); // NOI18N
        btnColar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnColar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnColar.setMaximumSize(new java.awt.Dimension(112, 70));
        btnColar.setMinimumSize(new java.awt.Dimension(110, 70));
        btnColar.setPreferredSize(new java.awt.Dimension(110, 70));
        btnColar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnColar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColarActionPerformed(evt);
            }
        });

        btnCompilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("./compilar.png"))); // NOI18N
        btnCompilar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCompilar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnCompilar.setMaximumSize(new java.awt.Dimension(110, 70));
        btnCompilar.setMinimumSize(new java.awt.Dimension(110, 70));
        btnCompilar.setPreferredSize(new java.awt.Dimension(110, 70));
        btnCompilar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        
        btnEquipe = new JButton("Equipe (F1)");
        btnMenu.add(btnEquipe);
        

        btnEquipe.setIcon(new javax.swing.ImageIcon(getClass().getResource("./equipe.png"))); // NOI18N
        btnEquipe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEquipe.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnEquipe.setMaximumSize(new java.awt.Dimension(112, 70));
        btnEquipe.setMinimumSize(new java.awt.Dimension(112, 70));
        btnEquipe.setPreferredSize(new java.awt.Dimension(112, 70));
        btnEquipe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        
        lblStatus = new JLabel("");
        lblStatus.setPreferredSize(new Dimension(900, 25));
        lblStatus.setMinimumSize(new Dimension(900, 25));
        GroupLayout gl_view = new GroupLayout(view);
        gl_view.setHorizontalGroup(
        	gl_view.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_view.createSequentialGroup()
        			.addGroup(gl_view.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_view.createSequentialGroup()
        					.addComponent(btnMenu, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(gl_view.createParallelGroup(Alignment.LEADING)
        						.addComponent(spEditor, GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
        						.addComponent(spMessage)))
        				.addGroup(gl_view.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGap(0))
        );
        gl_view.setVerticalGroup(
        	gl_view.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_view.createSequentialGroup()
        			.addGap(6)
        			.addGroup(gl_view.createParallelGroup(Alignment.LEADING)
        				.addComponent(btnMenu, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_view.createSequentialGroup()
        					.addComponent(spEditor, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(spMessage, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)))
        			.addGap(18)
        			.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(27))
        );
        view.setLayout(gl_view);
        btnEquipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messageArea.setText("Augusto Kalahary \n"
                				+ "Daniel Busarello \n"
                				+ "Fernando Butzke");
            }
        }); 
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("Função ainda não implementada");
            }
        });
        
        
        
        
        definirTeclasAtalho();
	}
	
	private void btnCopiarActionPerformed(java.awt.event.ActionEvent evt) {
		taEditor.copy();
	}
	
	private void btnRecortarActionPerformed(java.awt.event.ActionEvent evt) {
		taEditor.cut();
	}
	
	private void btnColarActionPerformed(java.awt.event.ActionEvent evt) {
		taEditor.paste();
	}

	private void acaoAbrir() {

        JFileChooser gerenciadorArquivo = new JFileChooser();

        gerenciadorArquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

        gerenciadorArquivo.setFileFilter(
            new FileNameExtensionFilter("Arquivo .txt", "txt")
        );
        

        if (gerenciadorArquivo.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            

            file = gerenciadorArquivo.getSelectedFile();
            

            String status = setTextoEditor();

            lblStatus.setText(status);
        }
    }
	
	private String setTextoEditor() {
        
        if (!file.exists()) {
            return "Arquivo nÃ£o encontrado";
        }
        
        try {
            BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            
            String textoEditor = "";
            String linha = leitor.readLine();
            while (linha != null) {
                textoEditor = textoEditor + linha + "\n";
                linha = leitor.readLine();
            }
            
            leitor.close();
            
            taEditor.setText(textoEditor);
            
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        
        return file.getPath();
    }
	
	private void actionSalvar() {
		if (file != null && file.exists()){
			salvarConteudoArquivo();
		} else {
			salvarNovoArquivo();
		}
	}
	
	private void salvarConteudoArquivo() {
		try {
    		PrintWriter pw = new PrintWriter(file);
    		
    		pw.write(taEditor.getText().replaceAll("\n", System.getProperty("line.separator")));
    		pw.flush();
    		
    		pw.close();
    		
    		taMessage.setText("");
    	} catch (IOException e) {
    		System.out.println(e.getMessage());
    	}
    }
	
	private void salvarNovoArquivo() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.setFileFilter(new FileNameExtensionFilter("Arquivo .txt", "txt"));
		
		if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = jfc.getSelectedFile();
			
			if(!file.getAbsolutePath().contains(".txt")) {
				file = new File(file.getPath() + ".txt");
			}
			
			salvarConteudoArquivo();
			lblStatus.setText(file.getPath());
		}
	}


	
	private void definirTeclasAtalho() {
        ActionMap actionMap = btnMenu.getActionMap();
        
        actionMap.put("btnNovo", new btnAcaoMenu(btnNovo));
        actionMap.put("btnAbrir", new btnAcaoMenu(btnAbrir));
        actionMap.put("btnSalvar", new btnAcaoMenu(btnSalvar));
        actionMap.put("btnCopiar", new btnAcaoMenu(btnCopiar));
        actionMap.put("btnColar", new btnAcaoMenu(btnColar));
        actionMap.put("btnCortar", new btnAcaoMenu(btnCortar));
        actionMap.put("btnCompilar", new btnAcaoMenu(btnCompilar));
        actionMap.put("btnEquipe", new btnAcaoMenu(btnEquipe));
        btnMenu.setActionMap(actionMap);

        InputMap imap = btnMenu.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);

        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), "btnNovo");
        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK), "btnAbrir");
        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "btnSalvar");
        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK), "btnCopiar");
        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK), "btnColar");
        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK), "btnCortar");
        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), "btnCompilar");
        imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "btnEquipe");
    }

	private class btnAcaoMenu extends AbstractAction {
		private javax.swing.JButton btnAcao;
		
		public btnAcaoMenu(javax.swing.JButton btnAcao) {
			this.btnAcao = btnAcao;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			this.btnAcao.doClick();
			
		}
	}
}