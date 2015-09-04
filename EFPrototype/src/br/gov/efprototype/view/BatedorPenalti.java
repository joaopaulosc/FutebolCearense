package br.gov.efprototype.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import br.gov.efprototype.model.Atleta;
import br.gov.efprototype.model.TabelaModelo;
import br.gov.efprototype.model.Time;

public class BatedorPenalti {
	JFrame frame = new JFrame("Penalti");
	Dimension dimensao = new Dimension();

	public BatedorPenalti(Time time) {
		String[] colunas = new String[] { "Jogador", "Posição" ,"Qualidade" };    
		ArrayList dados = new ArrayList();
		
		for (Atleta jogador: time.getJogadoresTitulares()){
			dados.add(new String[] { jogador.getNome(), jogador.getPosicao().toString() , jogador.getQualidadeAtaque().toString()});
					
		}
		TabelaModelo tabela = new TabelaModelo(dados, colunas);		
		JPanel panel = new JPanel();
		JLabel texto = new JLabel();
		JTable tabelaJogadores = new JTable(tabela);
		JButton confirmacao = new JButton();
		texto.setText("Selecione o batedor:");
		confirmacao.setText("Ok");
		
		
		dimensao.setSize(400, 300);
		frame.setPreferredSize(dimensao);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		panel.add(texto, c);
		
		c.gridx = 0;
		c.gridy = 1;
		panel.add(tabelaJogadores, c);
		
		c.gridx = 0;
		c.gridy = 2;
		panel.add(confirmacao, c);
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
