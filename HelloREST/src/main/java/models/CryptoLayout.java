package models;

public class CryptoLayout {
	public String symbol;
	public double priceChange;
	
	public CryptoLayout(String symbol, double priceDifference) {
		super();
		this.symbol = symbol;
		this.priceChange = priceDifference;
	}
}
