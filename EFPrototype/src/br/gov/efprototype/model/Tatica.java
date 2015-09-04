package br.gov.efprototype.model;


public enum Tatica {	
	T4_4_2(1,"4-4-2"),T4_3_3(2,"4-3-3"), T3_5_2(3,"3-5-2"), T3_4_3(4,"3-4-3");
	private final int codigo;
	private String descricao;
	
	/**
	 * Construtor da enumeration
	 * @param _codigo    O codigo do registro
	 * @param _descricao A descrição do registro
	 */
	private Tatica(int _codigo, String _descricao) {
		this.codigo = _codigo;
		this.descricao = _descricao;
	}
}

