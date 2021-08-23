package compiler;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class Compilador extends JFrame {

	private JPanel contentPane;

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
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel btnMenu = new JPanel();
		btnMenu.setBounds(5, 5, 874, 80);
		contentPane.add(btnMenu);
		
		JButton btnNovo = new JButton("Novo (Ctrl+N)");
		btnMenu.add(btnNovo);
		
		JButton btnAbrir = new JButton("Abrir (Ctrl+O)");
		btnMenu.add(btnAbrir);
		
		JButton btnSalvar = new JButton("Salvar (Ctrl+S)");
		btnMenu.add(btnSalvar);
		
		JButton btnCopiar = new JButton("Copiar (Ctrl+C)");
		btnMenu.add(btnCopiar);
		
		JButton btnColar = new JButton("Colar (Ctrl+V)");
		btnMenu.add(btnColar);
		
		JButton btnCortar = new JButton("Cortar (Ctrl+X)");
		btnMenu.add(btnCortar);
		
		JButton btnCompilar = new JButton("Compilar (F9)");
		btnMenu.add(btnCompilar);
		
		JButton btnEquipe = new JButton("Equipe (F1)");
		btnMenu.add(btnEquipe);
		
		JPanel statusBar = new JPanel();
		statusBar.setBounds(5, 546, 874, 10);
		contentPane.add(statusBar);
		
		JScrollPane scrollCodeEditor = new JScrollPane();
		scrollCodeEditor.setBounds(5, 85, 2, 461);
		contentPane.add(scrollCodeEditor);
		
		JScrollPane scrollMessage = new JScrollPane();
		scrollMessage.setBounds(877, 85, 2, 461);
		contentPane.add(scrollMessage);
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
                //btnNovoActionPerformed(evt);
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
                //btnSalvarActionPerformed(evt);
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
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //btnCompilarActionPerformed(evt);
            }
        });

        btnEquipe.setIcon(new javax.swing.ImageIcon(getClass().getResource("./equipe.png"))); // NOI18N
        btnEquipe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEquipe.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnEquipe.setMaximumSize(new java.awt.Dimension(112, 70));
        btnEquipe.setMinimumSize(new java.awt.Dimension(112, 70));
        btnEquipe.setPreferredSize(new java.awt.Dimension(112, 70));
        btnEquipe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEquipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //btnEquipeActionPerformed(evt);
            }
        });
		
	}

}
