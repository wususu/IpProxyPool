package entity;


/**
 * Company Finance Entity
 * 
 * @author janke
 */
public class Finance {
	
	public static final Double B0 = 2.329;
	
	public static final Double InventoryTurnoverWeight = 0.249;
	
	public static final Double  NetAssetsIncomeWeight = -2.629;

	public static final Double NetProfitWeight = -21.231;
	
	public static final Double NetProfitIncreaseWeight1 = 2.649;
	
	public static final Double NetProfitIncreaseWeight2 = 0.203;
	
	public static final Double TotalAssetsIncreaseWeight = 0.656;
	
	private Double inventoryTurnover;
	
	private Double  netAssetsIncome;

	private Double netProfit;
	
	private Double netProfitIncrease1;
	
	private Double netProfitIncrease2;
	
	private Double totalAssetsIncrease;
	
	private Double financeRisk;
	
	public Finance() {
		// TODO Auto-generated constructor stub
	}
	
	public Finance(Double inventoryTurnover, Double  netAssetsIncome,  Double netProfit, 
			Double netProfitIncrease1, Double netProfitIncrease2, Double totalAssetsIncrease) {
		// TODO Auto-generated constructor stub
		this.inventoryTurnover = inventoryTurnover;
		this.netAssetsIncome = netAssetsIncome;
		this.netProfit = netProfit;
		this.netProfitIncrease1 = netProfitIncrease1;
		this.netProfitIncrease2 = netProfitIncrease2;
		this.totalAssetsIncrease = totalAssetsIncrease;
	}

	public Double getInventoryTurnover() {
		return inventoryTurnover;
	}

	public void setInventoryTurnover(Double inventoryTurnover) {
		this.inventoryTurnover = inventoryTurnover;
	}

	public Double getNetAssetsIncome() {
		return netAssetsIncome;
	}

	public void setNetAssetsIncome(Double netAssetsIncome) {
		this.netAssetsIncome = netAssetsIncome;
	}

	public Double getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(Double netProfit) {
		this.netProfit = netProfit;
	}

	public Double getNetProfitIncrease1() {
		return netProfitIncrease1;
	}

	public void setNetProfitIncrease1(Double netProfitIncrease1) {
		this.netProfitIncrease1 = netProfitIncrease1;
	}

	public Double getNetProfitIncrease2() {
		return netProfitIncrease2;
	}

	public void setNetProfitIncrease2(Double netProfitIncrease2) {
		this.netProfitIncrease2 = netProfitIncrease2;
	}

	public Double getTotalAssetsIncrease() {
		return totalAssetsIncrease;
	}

	public void setTotalAssetsIncrease(Double totalAssetsIncrease) {
		this.totalAssetsIncrease = totalAssetsIncrease;
	}

	public Double getFinanceRisk() {
		return financeRisk;
	}

	public void setFinanceRisk(Double financeRisk) {
		this.financeRisk = financeRisk;
	}
}
