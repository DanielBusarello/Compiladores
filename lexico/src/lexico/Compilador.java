// Augusto Kalahary
// Daniel Busarello
// Fernando Butzke

package lexico;

import gals.ClasseId;
import gals.LexicalError;
import gals.Lexico;
import gals.Token;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

@SuppressWarnings("ALL")
public class Compilador extends JFrame {
	private File file = null;

	// Paineis do Compilador
	private JPanel view;
	private JPanel menuItens;
	
	// Vari�veis dos bot�es
	private JButton btnNew;
	private JButton btnOpen;
	private JButton btnSave;
	private JButton btnCopy;
	private JButton btnPaste;
	private JButton btnCut;
	private JButton btnCompile;
	private JButton btnTeam;
	
	// TextArea do Editor e Mensagens
	private JTextArea taEditor;
	private JTextArea taMessage;
	
	// Scrolls do Editor e Mensagens
	private JScrollPane spEditor;
	private JScrollPane spMessage;

	private JLabel lblStatus;
	
  public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// Altera o design do Compilador baseado no sistema Windows do usu�rio
				try {
		            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		                if ("Windows".equals(info.getName())) {
		                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		                    break;
		                }
		            }
		        } catch (ClassNotFoundException ex) {
		            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		        } catch (InstantiationException ex) {
		            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		        } catch (IllegalAccessException ex) {
		            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
		            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		        }
				
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
		initComponents();
		defineHotkeys();
	}
	
	private void initComponents() {
		// Inicia a janela e a View
		view = new JPanel();
		view.setBorder(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setMinimumSize(new Dimension(900, 600));
		setPreferredSize(new Dimension(1024, 720));
		setContentPane(view);
		setTitle("Compilador");
		
		// Editor
		taEditor = new JTextArea();
        taEditor.setColumns(20);
        taEditor.setRows(5);
        taEditor.setBorder(new NumeredBorder());
        taEditor.setMinimumSize(null);
        taEditor.setPreferredSize(null);
        taEditor.addKeyListener(new java.awt.event.KeyAdapter() {
        	public void keyPressed(java.awt.event.KeyEvent evt) {
        	}
        });
        
        spEditor = new JScrollPane(taEditor, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		spEditor.setViewportView(taEditor);
        
		// Mensagem
        taMessage = new JTextArea();
        taMessage.setEditable(false);

        spMessage = new JScrollPane(taMessage, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        spMessage.setViewportView(taMessage);
        
        // Status Bar
        lblStatus = new JLabel("");
        lblStatus.setPreferredSize(new Dimension(900, 25));
        lblStatus.setMinimumSize(new Dimension(900, 25));
        
        // Painel dos bot�es
 		menuItens = new JPanel();
 		menuItens.setMinimumSize(new Dimension(150, 500));
 		menuItens.setLayout(new GridLayout(0, 1, 0, 0));
     		
 		// Bot�es
 		btnNew = new JButton("Novo (Ctrl+N)");
 		btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("./novo.png")));
        btnNew.setBorder(null);
        btnNew.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNew.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnNew.setMaximumSize(new java.awt.Dimension(110, 50));
        btnNew.setMinimumSize(new java.awt.Dimension(110, 50));
        btnNew.setPreferredSize(new java.awt.Dimension(110, 50));
        btnNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	taEditor.setText("");
            	taMessage.setText("");
            	taMessage.setText("");
            	lblStatus.setText("");
            	file = null;
            }
        });
		menuItens.add(btnNew);
 		
		btnOpen = new JButton("Abrir (Ctrl+O)");
		btnOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("./abrir.png")));
        btnOpen.setBorder(null);
        btnOpen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOpen.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnOpen.setMaximumSize(new Dimension(110, 50));
        btnOpen.setMinimumSize(new Dimension(110, 50));
        btnOpen.setPreferredSize(new Dimension(110, 50));
        btnOpen.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	actionOpen();
            	taMessage.setText("");
            }
        });
		menuItens.add(btnOpen);
		
		btnSave = new JButton("Salvar (Ctrl+S)");
		btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("./salvar.png")));
        btnSave.setBorder(null);
        btnSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSave.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSave.setMaximumSize(new java.awt.Dimension(112, 70));
        btnSave.setMinimumSize(new java.awt.Dimension(112, 70));
        btnSave.setPreferredSize(new java.awt.Dimension(110, 70));
        btnSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	actionSave();
            }
        });
		menuItens.add(btnSave);
		
		btnCopy = new JButton("Copiar (Ctrl+C)");
		btnCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("./copiar.png")));
        btnCopy.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCopy.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnCopy.setMaximumSize(new java.awt.Dimension(112, 70));
        btnCopy.setMinimumSize(new java.awt.Dimension(112, 70));
        btnCopy.setPreferredSize(new java.awt.Dimension(110, 70));
        btnCopy.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopyActionPerformed(evt);
            }
        });
		menuItens.add(btnCopy);
		
		btnPaste = new JButton("Colar (Ctrl+V)");
		btnPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("./colar.png")));
		btnPaste.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		btnPaste.setMargin(new java.awt.Insets(0, 0, 0, 0));
		btnPaste.setMaximumSize(new java.awt.Dimension(112, 70));
		btnPaste.setMinimumSize(new java.awt.Dimension(110, 70));
		btnPaste.setPreferredSize(new java.awt.Dimension(110, 70));
		btnPaste.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		btnPaste.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPasteActionPerformed(evt);
			}
		});
		menuItens.add(btnPaste);
		
		btnCut = new JButton("Cortar (Ctrl+X)");
		btnCut.setIcon(new javax.swing.ImageIcon(getClass().getResource("./recortar.png")));
        btnCut.setText("Cortar (Ctrl-x)");
        btnCut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCut.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnCut.setMaximumSize(new java.awt.Dimension(110, 70));
        btnCut.setMinimumSize(new java.awt.Dimension(110, 70));
        btnCut.setPreferredSize(new java.awt.Dimension(110, 70));
        btnCut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnCutActionPerformed(evt);
            }
        });
		menuItens.add(btnCut);
		
		btnCompile = new JButton("Compilar (F9)");
		btnCompile.setIcon(new javax.swing.ImageIcon(getClass().getResource("./compilar.png")));
        btnCompile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCompile.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnCompile.setMaximumSize(new java.awt.Dimension(110, 70));
        btnCompile.setMinimumSize(new java.awt.Dimension(110, 70));
        btnCompile.setPreferredSize(new java.awt.Dimension(110, 70));
        btnCompile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCompile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // taMessage.setText("Fun��o n�o implementada nesta vers�o");
                try {
					actionCompile();
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
            }
        });
		menuItens.add(btnCompile);
		
		btnTeam = new JButton("Equipe (F1)");
        btnTeam.setIcon(new javax.swing.ImageIcon(getClass().getResource("./equipe.png")));
        btnTeam.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTeam.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnTeam.setMaximumSize(new java.awt.Dimension(112, 70));
        btnTeam.setMinimumSize(new java.awt.Dimension(112, 70));
        btnTeam.setPreferredSize(new java.awt.Dimension(112, 70));
        btnTeam.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taMessage.setText("Augusto Kalahary \n"
                				+ "Daniel Busarello \n"
                				+ "Fernando Butzke");
            }
        }); 
        menuItens.add(btnTeam);
        
        // Layout da View para realizar o redimensionamento dos componentes
 		GroupLayout gl_view = new GroupLayout(view);
         gl_view.setHorizontalGroup(
         	gl_view.createParallelGroup(Alignment.LEADING)
         		.addGroup(gl_view.createSequentialGroup()
         			.addComponent(menuItens, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
         			.addPreferredGap(ComponentPlacement.RELATED)
         			.addGroup(gl_view.createParallelGroup(Alignment.LEADING)
         				.addComponent(spMessage, GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
         				.addComponent(spEditor, GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE))
         			.addGap(6))
         		.addGroup(gl_view.createSequentialGroup()
         			.addGap(10)
         			.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 868, Short.MAX_VALUE)
         			.addContainerGap())
         );
         gl_view.setVerticalGroup(
         	gl_view.createParallelGroup(Alignment.LEADING)
         		.addGroup(gl_view.createSequentialGroup()
         			.addGap(6)
         			.addGroup(gl_view.createParallelGroup(Alignment.LEADING)
         				.addComponent(menuItens, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
         				.addGroup(gl_view.createSequentialGroup()
         					.addComponent(spEditor, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
         					.addPreferredGap(ComponentPlacement.RELATED)
         					.addComponent(spMessage, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)))
         			.addGap(18)
         			.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
         			.addGap(12))
         );
         view.setLayout(gl_view);
    }
	
	// Actions e Hotkeys
	private void btnCopyActionPerformed(java.awt.event.ActionEvent evt) {
		taEditor.copy();
	}
	
	private void btnCutActionPerformed(java.awt.event.ActionEvent evt) {
		taEditor.cut();
	}
	
	private void btnPasteActionPerformed(java.awt.event.ActionEvent evt) {
		taEditor.paste();
	}

	private void actionOpen() {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(
            new FileNameExtensionFilter("Arquivo .txt", "txt")
        );

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            String status = setEditorContent();
            lblStatus.setText(status);
        }
    }
	
	private void actionCompile() throws BadLocationException {
		if (taEditor.getText().trim().length() == 0) {
			taMessage.setText("Nenhum programa para compilar");
            return;
        }
        
        Lexico lexico = new Lexico();
        lexico.setInput(taEditor.getText());
        try {
            Token token = null;
            String message = "";
            while ((token = lexico.nextToken()) != null) {
                int line = taEditor.getLineOfOffset(token.getPosition()) + 1;
                String cls = ClasseId.getClasse(token.getId());

				message += line + "\t";
				message += cls + "\t";
				message += token.getLexeme() + "\n";
            }
            
            taMessage.setText("linha\tclasse\t\t\tlexema\n");
            taMessage.append(message);
            taMessage.append("\n     programa compilado com sucesso");
        } catch (LexicalError e) {
            int line = taEditor.getLineOfOffset(e.getPosition()) + 1;
            String invalidChar = taEditor.getText(e.getPosition(), 1);
            
            if ("símbolo inválido".equals(e.getMessage())) {
            	taMessage.setText("Erro na linha " + line + " - " + invalidChar + " " + e.getMessage());
            } else {
            	taMessage.setText("Erro na linha " + line + " - " + e.getMessage());
            }
        }
	}
	
	private String setEditorContent() {
        if (!file.exists()) {
            return "Arquivo não encontrado";
        }
        
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            
            String editorText = "";
            String line = reader.readLine();
            while (line != null) {
				editorText = editorText + line + "\n";
				line = reader.readLine();
            }

			reader.close();
            taEditor.setText(editorText);
            
        } catch(IOException e) {
            taMessage.append("\n" + e.getMessage());
        }
        
        return file.getPath();
    }
	
	private void actionSave() {
		if (file != null && file.exists()){
			saveContentFile();
		} else {
			saveNewFile();
		}
	}
	
	private void saveContentFile() {
		try {
    		PrintWriter pw = new PrintWriter(file);
    		
    		pw.write(taEditor.getText().replaceAll("\n", System.getProperty("line.separator")));
    		pw.flush();
    		
    		pw.close();
    		
    		taMessage.setText("");
    	} catch (IOException e) {
    		taMessage.append("\n" + e.getMessage());
    	}
    }
	
	private void saveNewFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo .txt", "txt"));
		
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			
			if(!file.getAbsolutePath().contains(".txt")) {
				file = new File(file.getPath() + ".txt");
			}
			
			saveContentFile();
			lblStatus.setText(file.getPath());
		}
	}

	private class btnMenuAction extends AbstractAction {
		private javax.swing.JButton btnAction;
		
		public btnMenuAction(javax.swing.JButton btnAcao) {
			this.btnAction = btnAcao;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			this.btnAction.doClick();
		}
	}
	
	private void defineHotkeys() {
		ActionMap actionMap = menuItens.getActionMap();
		
		actionMap.put("btnNovo", new btnMenuAction(btnNew));
		actionMap.put("btnAbrir", new btnMenuAction(btnOpen));
		actionMap.put("btnSalvar", new btnMenuAction(btnSave));
		actionMap.put("btnCopiar", new btnMenuAction(btnCopy));
		actionMap.put("btnColar", new btnMenuAction(btnPaste));
		actionMap.put("btnCortar", new btnMenuAction(btnCut));
		actionMap.put("btnCompilar", new btnMenuAction(btnCompile));
		actionMap.put("btnEquipe", new btnMenuAction(btnTeam));
		menuItens.setActionMap(actionMap);
		
		InputMap imap = menuItens.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		
		imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), "btnNovo");
		imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK), "btnAbrir");
		imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "btnSalvar");
		imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK), "btnCopiar");
		imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK), "btnColar");
		imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK), "btnCortar");
		imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), "btnCompilar");
		imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "btnEquipe");
	}
}