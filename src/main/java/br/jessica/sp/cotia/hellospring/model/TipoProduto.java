package br.jessica.sp.cotia.hellospring.model;

public enum TipoProduto {
	PERECIVEL("Perecível"),
	NAO_PERECIVEL("Não perecível"),
	MEIO_PERECIVEL("Meio perecível");
	
	String desc;
	
	private TipoProduto(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return this.desc;
	}
}
