package br.gov.efprototype;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextArea;

import br.gov.efprototype.model.Atleta;
import br.gov.efprototype.model.Comportamento;
import br.gov.efprototype.model.Evento;
import br.gov.efprototype.model.Posicao;
import br.gov.efprototype.model.TabelaModelo;
import br.gov.efprototype.model.Time;
import br.gov.efprototype.view.BatedorPenalti;

public class Partida {
	
	private List<Evento> eventos= new ArrayList<Evento>();
	List<Atleta> jogadoresTitularesTimeMandante = new ArrayList<Atleta>();
	List<Atleta> jogadoresSuplentesTimeMandante = new ArrayList<Atleta>();
	List<Atleta> jogadoresTitularesTimeVisitante = new ArrayList<Atleta>();
	List<Atleta> jogadoresSuplentesTimeVisitante = new ArrayList<Atleta>();
	List<Atleta> jogadoresCartaoAmarelo = new ArrayList<Atleta>();
	List<Atleta> jogadoresCartaoVermelho = new ArrayList<Atleta>();
	private Atleta melhorJogadorRodada;
	private Atleta piorJogadorRodada;
	private Integer placarTimeMandante;
	private Integer placarTimeVisitante;
	private Double aproximacaoMandante;
	private Double aproximacaoVisitante;
	private Integer ocupacao;
	

	JFrame frame = new JFrame("Resultado Final");
	Dimension dimensao = new Dimension();
	
	final JPanel panel = new JPanel();
	final JTextArea acontecimentosMandante = new JTextArea ();
	final JTextArea acontecimentosVisitante = new JTextArea ();
	private JProgressBar progressBar = new JProgressBar();  
	private JLabel tempo = new JLabel();
	private JLabel placarMandante = new JLabel();
	private JLabel placarVisitante = new JLabel();
	private JLabel nomeMandante = new JLabel();
	private JLabel nomeVisitante = new JLabel();
	private JLabel versus = new JLabel();
	private JButton substituirMandante = new JButton();
	private JButton substituirVisitante = new JButton();
	private BatedorPenalti BatedorPenalti;
	
	private void inicializarPainelPrincipal(){
		//progressBar.setValue(0);
		//progressBar.setStringPainted(true);
		substituirMandante.setText("Substituir");
		substituirVisitante.setText("Substituir");
		versus.setText("X");
		
		progressBar.setBackground(Color.BLUE);
		progressBar.setForeground(Color.RED); 
		dimensao.setSize(600, 400);
		frame.setPreferredSize(dimensao);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(tempo, c);
		
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 2.0;
		c.gridx = 1;
		c.gridy = 0;
		panel.add(progressBar, c);
		
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(nomeMandante, c);
		
		//c.fill = GridBagConstraints.HORIZONTAL;
		//c.weighty(2.0);
		c.gridx = 1;
		c.gridy = 1;
		panel.add(versus, c);
		
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		panel.add(nomeVisitante, c);
		
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		panel.add(placarMandante, c);
		
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		panel.add(placarVisitante,c);
		
		c.gridx = 0;
		c.gridy = 3;
		panel.add(substituirMandante, c);
		
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		panel.add(substituirVisitante,c);
		
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		panel.add(acontecimentosMandante, c);
		
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 4;
		panel.add(acontecimentosVisitante, c);
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
//	    JOptionPane.showMessageDialog(frame,
//	        mensagem);
	   // System.exit(0);
	}
	
	private void atualizarBarraProgresso(int valor){
		//System.out.println(valor);
		progressBar.setValue(valor);
	}
	
	private void adicionarAcontecimentosMandante(String texto){
		String textoAtual = acontecimentosMandante.getText();
		acontecimentosMandante.setText(textoAtual + "\n" + texto); 
	}
	
	private void adicionarAcontecimentosVisitante(String texto){
		String textoAtual = acontecimentosVisitante.getText();
		acontecimentosVisitante.setText(textoAtual + "\n" + texto); 
	}
	
	
	private Double getAproximacao(boolean mandante, boolean atacando, Double jogadaAtaque, Double jogadaDefesa){
		Double aproximacaoAtual=(jogadaAtaque - jogadaDefesa);
		Double aproximacao=0.0;
		if (mandante){
			aproximacao=aproximacaoMandante;
		}
		else {
			aproximacao=aproximacaoVisitante;
		}	
		if (atacando){				
			aproximacao = aproximacao + aproximacaoAtual;
		}
		else {
			aproximacao = aproximacao - aproximacaoAtual;
		}
		return aproximacao;
	}
	
	private Double getRandomArbitrary(Double min, Double max) {
		  return Math.random() * (max - min) + min;
		}
	
	private void verificaSeFoiGol(boolean mandante, Double aproximacao, Time timeMandante,Time timeVisitante, Integer minuto){
		if (mandante) {
			if (aproximacao>100.0){
				placarTimeMandante = placarTimeMandante + 1;
				placarMandante.setText(placarTimeMandante.toString());
				
//				String nomeJogadorGol = ((Gol)eventos.get(eventos.size()-1)).getJogador().getNome();
				String nomeJogadorGol = melhorJogadorRodada.getNome();
				adicionarAcontecimentosMandante("GOL -  " + nomeJogadorGol + " " + minuto + "'");
				//adicionarAcontecimentosMandante(ocupacao + " - " + timeMandante.getNome() + " " + placarTimeMandante + " x " + placarTimeVisitante + " " + timeVisitante.getNome() + "  " + nomeJogadorGol + " " + minuto + "'");				
				//System.out.println(ocupacao + " - " + timeMandante.getNome() + " " + placarTimeMandante + " x " + placarTimeVisitante + " " + timeVisitante.getNome() + "  " + nomeJogadorGol + " " + minuto + "'");
				atualizarBarraProgresso(40);
				aproximacaoMandante = 40.0;
				aproximacaoVisitante = 60.0;
				}
		}
		else {
			if (aproximacao>100.0){
				placarTimeVisitante = placarTimeVisitante + 1;
				placarVisitante.setText(placarTimeVisitante.toString());
				//String nomeJogadorGol = ((Gol)eventos.get(eventos.size()-1)).getJogador().getNome();
				String nomeJogadorGol = melhorJogadorRodada.getNome();
				adicionarAcontecimentosVisitante("GOL -  " + nomeJogadorGol + " " + minuto + "'");
				//adicionarAcontecimentosMandante(ocupacao + " - " + timeMandante.getNome() + " " + placarTimeMandante + " x " + placarTimeVisitante + " " + timeVisitante.getNome() + "  " + nomeJogadorGol + " " + minuto + "'");
				//System.out.println(ocupacao + " - " + timeMandante.getNome() + " " + placarTimeMandante + " x " + placarTimeVisitante + " " + timeVisitante.getNome() + "  " + nomeJogadorGol + " " + minuto + "'");
				atualizarBarraProgresso(60);
				aproximacaoVisitante = 40.0;
				aproximacaoMandante = 60.0;
			}
		}
	}

	private void verificaPenalti(boolean mandante, Double aproximacao, Time timeMandante,Time timeVisitante, Integer minuto){
		if (aproximacao > 90.0) {
			adicionarAcontecimentosMandante("Penalti - " + minuto);
			Atleta batedorSelecionado;
			if (mandante) {		
				BatedorPenalti b = new BatedorPenalti(timeMandante);
			}
			else { 
				BatedorPenalti b = new BatedorPenalti(timeVisitante);
			}							
			//System.out.println("Penalti - " + minuto);
			Double chanceGol = getRandomArbitrary(1.0, 10.0); 
			if (chanceGol >= 8.0){
				adicionarAcontecimentosMandante("Gol - " + minuto);
				//System.out.println("Gol - " + minuto);
				if (mandante) {
					placarTimeMandante = placarTimeMandante + 1;
					placarMandante.setText(placarTimeMandante.toString());
					String nomeJogadorGol = melhorJogadorRodada.getNome();
					adicionarAcontecimentosMandante("GOL -  " + nomeJogadorGol + " " + minuto + "'");
					//System.out.println(ocupacao + " - " + timeMandante.getNome() + " " + placarTimeMandante + " x " + placarTimeVisitante + " " + timeVisitante.getNome() + "  " + nomeJogadorGol + " " + minuto + "'");
					atualizarBarraProgresso(40);
					aproximacaoMandante = 40.0;
					aproximacaoVisitante = 60.0;
				}
				else {
					placarTimeVisitante = placarTimeVisitante + 1;
					placarVisitante.setText(placarTimeVisitante.toString());
					String nomeJogadorGol = melhorJogadorRodada.getNome();
					adicionarAcontecimentosVisitante("GOL - " + nomeJogadorGol + " " + minuto + "'");
					//System.out.println(ocupacao + " - " + timeMandante.getNome() + " " + placarTimeMandante + " x " + placarTimeVisitante + " " + timeVisitante.getNome() + "  " + nomeJogadorGol + " " + minuto + "'");
					atualizarBarraProgresso(60);
					aproximacaoMandante = 60.0;
					aproximacaoVisitante = 40.0;
				}
			}
			else if (chanceGol >= 7.0){
				adicionarAcontecimentosMandante("No travessÃ£o!!! - " + minuto);
				//System.out.println("No travessÃ£o!!! - " + minuto);
			}
			else if (chanceGol >= 6.0){
				adicionarAcontecimentosMandante("Defendeu!!! - " + minuto);
				//System.out.println("Defendeu!!! - " + minuto);
			}
			else if (chanceGol < 6.0){
				adicionarAcontecimentosMandante("Pra fora!!! - " + minuto);
				//System.out.println("Pra fora!!! - " + minuto);
			}
		}
	}
	
	public Integer realizaPartida(Time timeMandante, Time timeVisitante, Integer qtdRodadas){		
		adicionarAcontecimentosMandante("");
		adicionarAcontecimentosVisitante("");
		aproximacaoMandante = 50.0;
		aproximacaoVisitante = 50.0;
		placarTimeMandante = 0;
		placarMandante.setText(placarTimeMandante.toString());
		placarTimeVisitante = 0;
		placarVisitante.setText(placarTimeVisitante.toString());
		nomeMandante.setText(timeMandante.getNome());
		nomeVisitante.setText(timeVisitante.getNome());
		boolean mandanteAtacando = true;
		Double jogadaAtaqueMandante = 0.0;				
		Double jogadaAtaqueVisitante = 0.0;
		Double jogadaDefesaMandante = 0.0;
		Double jogadaDefesaVisitante = 0.0;
		ocupacao = getOcupacaoEstadio(timeMandante, timeVisitante);
		//adicionarAcontecimentosMandante(ocupacao + " - " + timeMandante.getNome() + " 0 x 0 " + timeVisitante.getNome());
		//System.out.println(ocupacao + " - " + timeMandante.getNome() + " 0 x 0 " + timeVisitante.getNome());
		inicializarPainelPrincipal();
		for (int i= 0; i<=(qtdRodadas); i++) {
			
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			tempo.setText(Integer.toString(i));
			if (mandanteAtacando){
				jogadaAtaqueMandante = getJogadaAtaque(timeMandante, i+1);
				jogadaDefesaVisitante = getJogadaDefesa(timeVisitante, i+1);
				
				mandanteAtacando = ((jogadaAtaqueMandante - jogadaDefesaVisitante)>=0);
				if (!mandanteAtacando) {
					if (ehFalta()){
						verificaCartao(piorJogadorRodada, 'V', i);
						verificaPenalti(true, aproximacaoMandante, timeMandante, timeVisitante, i);						
						mandanteAtacando=true;
					}
					else {
						aproximacaoMandante = getAproximacao(true, true, jogadaAtaqueMandante, jogadaDefesaVisitante);
						aproximacaoVisitante = getAproximacao(false, false, jogadaAtaqueMandante, jogadaDefesaVisitante);
					}
				}
				else {
					aproximacaoMandante = getAproximacao(true, true, jogadaAtaqueMandante, jogadaDefesaVisitante);
					aproximacaoVisitante = getAproximacao(false, false, jogadaAtaqueMandante, jogadaDefesaVisitante);
				}
			} else {
				jogadaAtaqueVisitante = getJogadaAtaque(timeVisitante, i+1); 
				jogadaDefesaMandante = getJogadaDefesa(timeMandante, i+1); 							
				
				mandanteAtacando = ((jogadaAtaqueVisitante - jogadaDefesaMandante)>=0);
				if (!mandanteAtacando) {
					if (ehFalta()){
						verificaCartao(piorJogadorRodada, 'M', i);
						verificaPenalti(false, aproximacaoVisitante, timeMandante, timeVisitante, i);						
						mandanteAtacando=false;
					} else {
						aproximacaoVisitante = getAproximacao(false, true, jogadaAtaqueVisitante, jogadaDefesaMandante);
						aproximacaoMandante = getAproximacao(true, false, jogadaAtaqueVisitante, jogadaDefesaMandante);
					}
				}
				else {
					aproximacaoVisitante = getAproximacao(false, true, jogadaAtaqueVisitante, jogadaDefesaMandante);
					aproximacaoMandante = getAproximacao(true, false, jogadaAtaqueVisitante, jogadaDefesaMandante);
				}
			}				
			atualizarBarraProgresso((int) aproximacaoMandante.doubleValue());
			verificaSeFoiGol(true, aproximacaoMandante, timeMandante, timeVisitante, i);
			verificaSeFoiGol(false, aproximacaoVisitante, timeMandante, timeVisitante, i);
			
		}
		adicionarAcontecimentosMandante(timeMandante.getNome() + " " + placarTimeMandante + " x " + placarTimeVisitante + " " + timeVisitante.getNome());
		//adicionarAcontecimentosMandante("Quantidade de jogadores: Time Mandante " + jogadoresTimeMandante.size() + " Time Visitante " + jogadoresTimeVisitante.size());
		if (placarTimeMandante > placarTimeVisitante) {
			return 1;
		}
		else if (placarTimeMandante == placarTimeVisitante){
			return 0;
		}
		else return 2;
	}
	public Time inicializaTimeMandante(String nomeTime, Double moral, Integer capacidadeEstadio){
		Time timeMandante = new Time(nomeTime, moral, capacidadeEstadio);
		//Jogador 1
		Atleta jogador1 = new Atleta("Victor", 8.0, 8.0, 10.0,Posicao.GOLEIRO, 32, Comportamento.Fair_Play);
		jogadoresTitularesTimeMandante.add(jogador1);
		//Jogador 2
		Atleta jogador2 = new Atleta("Marcos Rocha", 8.0, 8.0, 10.0,Posicao.DEFESA, 26, Comportamento.Cavalheiro);
		jogadoresTitularesTimeMandante.add(jogador2);
		//Jogador 3
		Atleta jogador3 = new Atleta("Leonardo Silva", 8.0, 8.0, 10.0,Posicao.DEFESA, 36, Comportamento.Sarrafeiro);
		jogadoresTitularesTimeMandante.add(jogador3);
		//Jogador 4
		Atleta jogador4 = new Atleta("Jemerson", 8.0, 8.0, 10.0,Posicao.DEFESA, 22, Comportamento.Caneleiro);
		jogadoresTitularesTimeMandante.add(jogador4);
		//Jogador 5
		Atleta jogador5 = new Atleta("Pedro Botelho", 8.0, 8.0, 10.0,Posicao.DEFESA, 25, Comportamento.Caneleiro);
		jogadoresTitularesTimeMandante.add(jogador5);
		//Jogador 6
		Atleta jogador6 = new Atleta("Rafael Carioca", 8.0, 8.0, 10.0,Posicao.MEIOCAMPO, 26, Comportamento.Caceteiro);
		jogadoresTitularesTimeMandante.add(jogador6);
		//Jogador 7
		Atleta jogador7 = new Atleta("Dodô", 8.0, 8.0, 10.0,Posicao.MEIOCAMPO, 20, Comportamento.Cavalheiro);
		jogadoresTitularesTimeMandante.add(jogador7);
		//Jogador 8
		Atleta jogador8 = new Atleta("Dátolo", 8.0, 8.0, 10.0,Posicao.MEIOCAMPO, 31, Comportamento.Caneleiro);
		jogadoresTitularesTimeMandante.add(jogador8);
		//Jogador 9
		Atleta jogador9 = new Atleta("Guilherme", 8.0, 8.0, 10.0,Posicao.MEIOCAMPO, 26, Comportamento.Cordeirinho);
		jogadoresTitularesTimeMandante.add(jogador9);
		//Jogador 10
		Atleta jogador10 = new Atleta("Luan", 8.0, 8.0, 10.0,Posicao.ATACANTE, 25, Comportamento.Caneleiro);
		jogadoresTitularesTimeMandante.add(jogador10);
		//Jogador 11
		Atleta jogador11 = new Atleta("Lucas Pratto", 8.0, 8.0, 10.0,Posicao.ATACANTE, 27, Comportamento.Cavalheiro);
		jogadoresTitularesTimeMandante.add(jogador11);
		
		//Iniciando o banco de suplentes
		//Jogador 12
		Atleta jogador12 = new Atleta("Giovanni", 8.0, 8.0, 10.0,Posicao.GOLEIRO, 28, Comportamento.Cavalheiro);
		jogadoresSuplentesTimeMandante.add(jogador12);
		
		//Jogador 13
		Atleta jogador13 = new Atleta("Edcarlos", 8.0, 8.0, 10.0,Posicao.DEFESA, 30, Comportamento.Cavalheiro);
		jogadoresSuplentesTimeMandante.add(jogador13);
				
		//Jogador 14
		Atleta jogador14 = new Atleta("Josué", 8.0, 8.0, 10.0,Posicao.MEIOCAMPO, 27, Comportamento.Cavalheiro);
		jogadoresSuplentesTimeMandante.add(jogador14);
		
		//Jogador 15
		Atleta jogador15 = new Atleta("Patric", 8.0, 8.0, 10.0,Posicao.DEFESA, 36, Comportamento.Cavalheiro);
		jogadoresSuplentesTimeMandante.add(jogador15);
		
		//Jogador 16
		Atleta jogador16 = new Atleta("Leandro Donizete", 8.0, 8.0, 10.0,Posicao.ATACANTE, 33, Comportamento.Cavalheiro);
		jogadoresSuplentesTimeMandante.add(jogador16);
		
		//Jogador 17
		Atleta jogador17 = new Atleta("Thiago Ribeiro", 8.0, 8.0, 10.0,Posicao.ATACANTE, 29, Comportamento.Cavalheiro);
		jogadoresSuplentesTimeMandante.add(jogador17);
		
		//Jogador 18
		Atleta jogador18 = new Atleta("Carlos", 8.0, 8.0, 10.0,Posicao.ATACANTE, 20, Comportamento.Cavalheiro);
		jogadoresSuplentesTimeMandante.add(jogador18);
				
		timeMandante.setJogadoresTitulares(jogadoresTitularesTimeMandante);	
		timeMandante.setJogadoresSuplentes(jogadoresSuplentesTimeMandante);
		return timeMandante;
	}
	
	public Time inicializaTimeVisitante(String nomeTime, Double moral, Integer capacidadeEstadio){
		Time timeVisitante = new Time(nomeTime, moral, capacidadeEstadio);		
		//Jogador 1
		Atleta jogador1 = new Atleta("Fábio", 8.0, 8.0, 10.0,Posicao.GOLEIRO, 34, Comportamento.Fair_Play);
		jogadoresTitularesTimeVisitante.add(jogador1);
		//Jogador 2
		Atleta jogador2 = new Atleta("Mayke", 8.0, 8.0, 10.0,Posicao.DEFESA, 22, Comportamento.Caneleiro);
		jogadoresTitularesTimeVisitante.add(jogador2);
		//Jogador 3
		Atleta jogador3 = new Atleta("Paulo André", 8.0, 8.0, 10.0,Posicao.DEFESA, 31, Comportamento.Sarrafeiro);
		jogadoresTitularesTimeVisitante.add(jogador3);
		//Jogador 4
		Atleta jogador4 = new Atleta("Manoel", 8.0, 8.0, 10.0,Posicao.DEFESA, 25, Comportamento.Caceteiro);
		jogadoresTitularesTimeVisitante.add(jogador4);
		//Jogador 5
		Atleta jogador5 = new Atleta("Mena", 8.0, 8.0, 10.0,Posicao.DEFESA, 27, Comportamento.Caneleiro);
		jogadoresTitularesTimeVisitante.add(jogador5);
		//Jogador 6
		Atleta jogador6 = new Atleta("Willians", 8.0, 8.0, 10.0,Posicao.MEIOCAMPO, 29, Comportamento.Sarrafeiro);
		jogadoresTitularesTimeVisitante.add(jogador6);
		//Jogador 7
		Atleta jogador7 = new Atleta("Henrique", 8.0, 8.0, 10.0,Posicao.MEIOCAMPO, 30, Comportamento.Caneleiro);
		jogadoresTitularesTimeVisitante.add(jogador7);
		//Jogador 8
		Atleta jogador8 = new Atleta("Charles", 8.0, 8.0, 10.0,Posicao.MEIOCAMPO, 30, Comportamento.Caceteiro);
		jogadoresTitularesTimeVisitante.add(jogador8);
		//Jogador 9
		Atleta jogador9 = new Atleta("Alisson", 8.0, 8.0, 10.0,Posicao.MEIOCAMPO, 22, Comportamento.Cordeirinho);
		jogadoresTitularesTimeVisitante.add(jogador9);
		//Jogador 10
		Atleta jogador10 = new Atleta("Marinho", 8.0, 8.0, 10.0,Posicao.ATACANTE, 25, Comportamento.Caneleiro);
		jogadoresTitularesTimeVisitante.add(jogador10);
		//Jogador 11
		Atleta jogador11 = new Atleta("Leandro Damião", 8.0, 8.0, 10.0,Posicao.ATACANTE, 26, Comportamento.Cavalheiro);
		jogadoresTitularesTimeVisitante.add(jogador11);		
		
		//Iniciando o banco de suplentes
		//Jogador 12
		Atleta jogador12 = new Atleta("Rafael", 8.0, 8.0, 10.0,Posicao.ATACANTE, 26, Comportamento.Cavalheiro);
		jogadoresSuplentesTimeVisitante.add(jogador12);
		
		//Jogador 13
		Atleta jogador13 = new Atleta("Bruno Rodrigo", 8.0, 8.0, 10.0,Posicao.DEFESA, 30, Comportamento.Cavalheiro);
		jogadoresSuplentesTimeVisitante.add(jogador13);
				
		//Jogador 14
		Atleta jogador14 = new Atleta("Ceará", 8.0, 8.0, 10.0,Posicao.DEFESA, 35, Comportamento.Cavalheiro);
		jogadoresSuplentesTimeVisitante.add(jogador14);
		
		//Jogador 15
		Atleta jogador15 = new Atleta("Eurico", 8.0, 8.0, 10.0,Posicao.ATACANTE, 21, Comportamento.Cavalheiro);
		jogadoresSuplentesTimeVisitante.add(jogador15);
		
		//Jogador 16
		Atleta jogador16 = new Atleta("Arrascaeta", 8.0, 8.0, 10.0,Posicao.MEIOCAMPO, 21, Comportamento.Cavalheiro);
		jogadoresSuplentesTimeVisitante.add(jogador16);
		
		//Jogador 17
		Atleta jogador17 = new Atleta("Joel", 8.0, 8.0, 10.0,Posicao.ATACANTE, 21, Comportamento.Cavalheiro);
		jogadoresSuplentesTimeVisitante.add(jogador17);
		
		//Jogador 18
		Atleta jogador18 = new Atleta("Marquinhos", 8.0, 8.0, 10.0,Posicao.ATACANTE, 25, Comportamento.Cavalheiro);
		jogadoresSuplentesTimeVisitante.add(jogador18);
		
		timeVisitante.setJogadoresTitulares(jogadoresTitularesTimeVisitante);	
		timeVisitante.setJogadoresSuplentes(jogadoresSuplentesTimeVisitante);
		return timeVisitante;
	}
	
	public Integer getOcupacaoEstadio(Time timeMandante, Time timeVisitante){
		Double fator = 0.0;
		Double moral = timeMandante.getMoral();
		Double minimo = moral/10.0;
		Double maximo;
		if (moral >8.0){
			maximo = 1.0;			
		} else if ((timeMandante.getMoral() >5.0) &&(timeMandante.getMoral() <=8.0)){
			maximo = 0.75;
		} else {
			maximo = 0.5;
		}
		fator = getRandomArbitrary(minimo, maximo);
		Integer ocupacao = (int)(timeMandante.getCapacidadeEstadio()*fator); 
		//timeVisitante.getQualidade();
		return ocupacao;
	}
	
	public boolean ehFalta(){
		Double aleatorio = getRandomArbitrary(1.0, 10.0);
		if (aleatorio>8.0) {
			return true;
		}
		else {
			return false;
		}				
	}
	
	public Double getJogadaAtaque(Time time, Integer minutoRodada){
		List<Atleta> jogadores = time.getJogadoresTitulares();
		Double jogadaTime = 0.0;
		Double jogadaAtleta = 0.0;
		Double melhorJogada = 0.0;		
		Atleta atletaGol = null;
		for (Atleta atleta :jogadores ){	
			// chama a jogada de ataque 
			jogadaAtleta= atleta.getJogadaAtaque();
			jogadaTime = jogadaTime + jogadaAtleta;
			if (melhorJogada <= jogadaAtleta){
				melhorJogada = jogadaAtleta;
				atletaGol = atleta;
			}
		}
		if (atletaGol != null) {
			melhorJogadorRodada = atletaGol;
		}
		return (time.getMoral()*0.01)+(jogadaTime/11);
	}
	
	public Double getJogadaDefesa(Time time, Integer minutoRodada){
		List<Atleta> jogadores = time.getJogadoresTitulares();
		Double jogadaTime = 0.0;
		Double jogadaAtleta = 0.0;
		Double piorJoagada = 0.0;
		Atleta piorJogador = null;
		for (Atleta atleta :jogadores ){	
			// chama a jogada de Defesa 
			jogadaAtleta= atleta.getJogadaDefesa();
			jogadaTime = jogadaTime + jogadaAtleta;
			if ((piorJoagada==0.0) || (piorJoagada > jogadaAtleta)){
				piorJoagada = jogadaAtleta;
				piorJogador = atleta;
			}
		}
		if (piorJogador != null) {
			piorJogadorRodada = piorJogador;
		}
		return (time.getMoral()*0.01)+(jogadaTime/jogadores.size());
	}
	
	public boolean jaPossuiAmarelo(Atleta jogador){
		boolean retorno = false;
		for (Atleta atleta :jogadoresCartaoAmarelo ){
            if (atleta.equals(jogador)) {  
            	retorno = true;
            }
		}
		return retorno;
	}
	
	public void verificaCartao(Atleta jogador, char tipo, Integer rodada){
		Double fatorViolencia=0.0;
		Double violencia=0.0;
		//boolean cartaoAmarelo = false;
		//boolean cartaoVermelho = false;
		if (jogador.getComportamento().equals(Comportamento.Sarrafeiro)){
			fatorViolencia = 10.0;
		}
		else if (jogador.getComportamento().equals(Comportamento.Caceteiro)){
			fatorViolencia = 8.0;
		}
		else if (jogador.getComportamento().equals(Comportamento.Caneleiro)){
			fatorViolencia = 6.0;
		}
		else if (jogador.getComportamento().equals(Comportamento.Cavalheiro)){
			fatorViolencia = 4.0;
		}
		else if (jogador.getComportamento().equals(Comportamento.Cordeirinho)){
			fatorViolencia = 2.0;
		}
		else if (jogador.getComportamento().equals(Comportamento.Fair_Play)){
			fatorViolencia = 1.0;
		}
		violencia = (getRandomArbitrary(1.0, 10.0) + fatorViolencia)/2;
		if (violencia>9.0){
			if (tipo == 'M') {
				jogadoresTitularesTimeMandante.remove(jogador);
				adicionarAcontecimentosMandante(jogador.getNome() + " - Vermelho - " + rodada + "'");
			}
			else {
				jogadoresTitularesTimeVisitante.remove(jogador);
				adicionarAcontecimentosVisitante(jogador.getNome() + " - Vermelho - " + rodada + "'");
			}
			//System.out.println(jogador.getNome() + " - Vermelho - " + rodada + "'");
			//cartaoVermelho = true;
			jogadoresCartaoVermelho.add(jogador);
			
		} else if ((violencia>6.0)&&(violencia<=9.0)){			
			//System.out.println(jogador.getNome() + " - CartÃ£o Amarelo - " + rodada + "'");
			if (jaPossuiAmarelo(jogador)){
				//System.out.println(jogador.getNome() + " - Segundo Amarelo = Vermelho - " + rodada + "'");
				if (tipo == 'M') {
					jogadoresTitularesTimeMandante.remove(jogador);
					adicionarAcontecimentosMandante(jogador.getNome() + " - Segundo Amarelo = Vermelho - " + rodada + "'");
				}
				else {
					jogadoresTitularesTimeVisitante.remove(jogador);
					adicionarAcontecimentosVisitante(jogador.getNome() + " - Segundo Amarelo = Vermelho - " + rodada + "'");
				}
				//cartaoVermelho = true;
				jogadoresCartaoAmarelo.remove(jogador);
				jogadoresCartaoVermelho.add(jogador);	
			}
			else {
				if (tipo == 'M') {
					adicionarAcontecimentosMandante(jogador.getNome() + " - Cartão Amarelo - " + rodada + "'");
				} 
				else {
					adicionarAcontecimentosVisitante(jogador.getNome() + " - Cartão Amarelo - " + rodada + "'");
				}
				//cartaoAmarelo = true;				
				jogadoresCartaoAmarelo.add(jogador);			
			}
		}
	}
}
