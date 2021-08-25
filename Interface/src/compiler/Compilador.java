package compiler;


import java.awt.EventQueue;

import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class Compilador extends JFrame {

	File arquivo = null;
	
	private JPanel contentPane;
	private JTextArea taEditor;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public Compilador() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 762);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel btnMenu = new JPanel();
		btnMenu.setBounds(5, 11, 131, 659);
		contentPane.add(btnMenu);
		btnMenu.setLayout(null);
		
		JScrollPane codeEditor = new JScrollPane();
        codeEditor.setBounds(146, 11, 852, 538);
        contentPane.add(codeEditor);
        
        taEditor = new JTextArea();
        codeEditor.setViewportView(taEditor);
        taEditor.setColumns(20);
        taEditor.setRows(5);
        taEditor.setBorder(new NumeredBorder());
        taEditor.setMinimumSize(null);
        taEditor.setPreferredSize(null);
        
        JScrollPane messagePanel = new JScrollPane();
        messagePanel.setBounds(146, 559, 852, 111);
        contentPane.add(messagePanel);
        
        JTextArea messageArea = new JTextArea();
        messagePanel.setViewportView(messageArea);
        taEditor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                //taEditorKeyPressed(evt);
            }
        });
		
		JButton btnNovo = new JButton("Novo (Ctrl+N)");
		btnNovo.setBounds(10, 11, 112, 70);
		btnMenu.add(btnNovo);
		
		JButton btnAbrir = new JButton("Abrir (Ctrl+O)");
		btnAbrir.setBounds(10, 92, 110, 70);
		btnMenu.add(btnAbrir);
		
		JButton btnSalvar = new JButton("Salvar (Ctrl+S)");
		btnSalvar.setBounds(10, 173, 110, 70);
		btnMenu.add(btnSalvar);
		
		JButton btnCopiar = new JButton("Copiar (Ctrl+C)");
		btnCopiar.setBounds(10, 254, 110, 70);
		btnMenu.add(btnCopiar);
		
		JButton btnColar = new JButton("Colar (Ctrl+V)");
		btnColar.setBounds(10, 335, 110, 70);
		btnMenu.add(btnColar);
		
		JButton btnCortar = new JButton("Cortar (Ctrl+X)");
		btnCortar.setBounds(10, 416, 110, 70);
		btnMenu.add(btnCortar);
		
		JButton btnCompilar = new JButton("Compilar (F9)");
		btnCompilar.setBounds(12, 497, 110, 70);
		btnMenu.add(btnCompilar);
		setTitle("Compilador");
		
		
		btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("./novo.png")));
        btnNovo.setBorder(null);
        btnNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNovo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnNovo.setMaximumSize(new java.awt.Dimension(112, 70));
        btnNovo.setMinimumSize(new java.awt.Dimension(112, 70));
        btnNovo.setPreferredSize(new java.awt.Dimension(112, 70));
        btnNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	taEditor.setText("");
            	messageArea.setText("");
            }
        });
        
        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("./abrir.png"))); // NOI18N
        btnAbrir.setBorder(null);
        btnAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAbrir.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnAbrir.setMaximumSize(new java.awt.Dimension(112, 70));
        btnAbrir.setMinimumSize(new java.awt.Dimension(112, 70));
        btnAbrir.setPreferredSize(new java.awt.Dimension(110, 70));
        btnAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //btnAbrirActionPerformed(evt);
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
                //btnCopiarActionPerformed(evt);
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
                //btnColarActionPerformed(evt);
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
                //btnCortarActionPerformed(evt);
            }
        });

        btnCompilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("./compilar.png"))); // NOI18N
        btnCompilar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCompilar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnCompilar.setMaximumSize(new java.awt.Dimension(110, 70));
        btnCompilar.setMinimumSize(new java.awt.Dimension(110, 70));
        btnCompilar.setPreferredSize(new java.awt.Dimension(110, 70));
        btnCompilar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        
        JButton btnEquipe = new JButton("Equipe (F1)");
        btnEquipe.setBounds(10, 578, 112, 70);
        btnMenu.add(btnEquipe);
        
                btnEquipe.setIcon(new javax.swing.ImageIcon(getClass().getResource("./equipe.png"))); // NOI18N
                btnEquipe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnEquipe.setMargin(new java.awt.Insets(0, 0, 0, 0));
                btnEquipe.setMaximumSize(new java.awt.Dimension(112, 70));
                btnEquipe.setMinimumSize(new java.awt.Dimension(112, 70));
                btnEquipe.setPreferredSize(new java.awt.Dimension(112, 70));
                btnEquipe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                
                JPanel statusBar = new JPanel();
                statusBar.setBounds(15, 681, 201, 31);
                contentPane.add(statusBar);
                statusBar.setLayout(null);
                
                
                
                JLabel lblStatus = new JLabel("temp");
                lblStatus.setBounds(10, 11, 74, 14);
                statusBar.add(lblStatus);
                
                btnEquipe.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        messageArea.setText("Augusto Kalahary \n"
                        				+ "Daniel Busarello \n"
                        				+ "Fernando Butzke");
                    }
                }); 
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //btnCompilarActionPerformed(evt);
            }
        });
        
        
	}
	
	private void actionSalvar() {
		if (arquivo != null && arquivo.exists()){
			salvarConteudoArquivo();
		} else {
			salvarNovoArquivo();
		}
	}
	
	private void salvarConteudoArquivo() {
		try {
    		PrintWriter pw = new PrintWriter(arquivo);
    		
    		String conteudo = taEditor.getText();
    		
    		pw.write("Teste");
    		pw.flush();
    		
    		pw.close();
    	} catch (IOException e) {
    		System.out.println(e.getMessage());
    	}
    }
	
	private void salvarNovoArquivo() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.setFileFilter(new FileNameExtensionFilter("Arquivo .txt", "txt"));
		
		if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			arquivo = jfc.getSelectedFile();
			
			if(!arquivo.getAbsolutePath().contains(".txt")) {
				arquivo = new File(arquivo.getPath() + ".txt");
			}
			
			salvarConteudoArquivo();
		}
	}
}
