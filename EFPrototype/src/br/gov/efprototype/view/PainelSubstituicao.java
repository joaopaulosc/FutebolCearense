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

public class PainelSubstituicao {

	JFrame frame = new JFrame("Substituição");
	Dimension dimensao = new Dimension();
	
	public PainelSubstituicao(Time time) {
		String[] colunas = new String[] { "Jogador", "Posição" ,"Qualidade Ataque", "Qualidade Defesa", "Vigor" };    
		ArrayList dadosTitulares = new ArrayList();
		ArrayList dadosSuplentes = new ArrayList();
		
		for (Atleta jogador: time.getJogadoresTitulares()){
			dadosTitulares.add(new String[] { jogador.getNome(), jogador.getPosicao().toString() , jogador.getQualidadeAtaque().toString(), jogador.getQualidadeDefesa().toString(), jogador.getVigor().toString()});					
		}
		
		for (Atleta jogador: time.getJogadoresSuplentes()){
			dadosSuplentes.add(new String[] { jogador.getNome(), jogador.getPosicao().toString() , jogador.getQualidadeAtaque().toString(), jogador.getQualidadeDefesa().toString(), jogador.getVigor().toString()});					
		}
		TabelaModelo tabelaModeloTitulares = new TabelaModelo(dadosTitulares, colunas);
		TabelaModelo tabelaModeloSuplentes = new TabelaModelo(dadosSuplentes, colunas);
		JPanel panel = new JPanel();
		JLabel texto = new JLabel();
		JTable tabelaTitluares = new JTable(tabelaModeloTitulares);
		JTable tabelaSuplentes = new JTable(tabelaModeloTitulares);
		JButton substituicao = new JButton();
		JButton confirmacao = new JButton();
		texto.setText("Substituição:");
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
		panel.add(tabelaTitluares, c);
		
		c.gridx = 1;
		c.gridy = 1;
		panel.add(tabelaSuplentes, c);
		
		c.gridx = 0;
		c.gridy = 2;
		panel.add(substituicao, c);
		
		c.gridx = 0;
		c.gridy = 3;
		panel.add(confirmacao, c);
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
		
	}
}
