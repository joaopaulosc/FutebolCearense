package br.gov.efprototype.model;

import br.gov.efprototype.Partida;

public class Principal {
	
	public static void main(String[] args) {
		Integer vitoriaMandante = 0;
		Integer empate = 0;
		Integer vitoriaVisitante = 0;
		for (int j=0; j<1; j++) {
			Partida partida = new Partida();
			Time time1 = partida.inicializaTimeMandante("Atlético Mineiro", 7.0, 15000);
			Time time2 = partida.inicializaTimeVisitante("Cruzeiro", 9.0, 28000);
			switch (partida.realizaPartida(time1, time2, 90)) {
				case 1: 
					vitoriaMandante += 1;
					break;
				case 0: 
					empate += 1;
					break;
				case 2: 
					vitoriaVisitante += 1;
					break;
			}
		}
		//System.out.println("Vitórias Mandante: " + vitoriaMandante + " Empates: " + empate + " Vitória Visitante: " + vitoriaVisitante);
	}

}
