package entity;

/**
 * Company Finance Entity
 * 
 * @author janke
 */
public class Finance {
	
	private static final Double b0 = 2.329;
	
	private static final Double inventoryTurnoverWeight = 0.249;
	
	private static final Double  netAssetsIncomeWeight = -2.629;

	private static final Double netProfitWeight = -21.231;
	
	private static final Double netProfitIncreaseWeight1 = 2.649;
	
	private static final Double netProfitIncreaseWeight2 = 0.203;
	
	private static final Double totalAssetsIncreaseWeight = 0.656;
	
	private Double inventoryTurnover;
	
	private Double  netAssetsIncome;

	private Double netProfit;
	
	private Double netProfitIncrease1;
	
	private Double netProfitIncrease2;
	
	private Double totalAssetsIncrease;
	
	public Finance() {
		// TODO Auto-generated constructor stub
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

	public static Double getB0() {
		return b0;
	}

	public static Double getInventoryturnoverweight() {
		return inventoryTurnoverWeight;
	}

	public static Double getNetassetsincomeweight() {
		return netAssetsIncomeWeight;
	}

	public static Double getNetprofitweight() {
		return netProfitWeight;
	}

	public static Double getNetprofitincreaseweight1() {
		return netProfitIncreaseWeight1;
	}

	public static Double getNetprofitincreaseweight2() {
		return netProfitIncreaseWeight2;
	}

	public static Double getTotalassetsincreaseweight() {
		return totalAssetsIncreaseWeight;
	}	
}
