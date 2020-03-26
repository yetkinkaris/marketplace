package com.suncomp.marketplace.model;

public enum Currency {

	EUR("EUR", "Euro"),
	USD("USD", "US Dollar"),
	TRY("TRY", "Turkish Lira"),
	GBP("GBP", "Pound Sterling");
	
	private Currency(String symbol, String name) {
		this.name = name;
		this.symbol = symbol;
	}
	
	private String symbol;
	private String name;
	public String getSymbol() {
		return symbol;
	}
	public String getName() {
		return name;
	}
	
	
	
}
