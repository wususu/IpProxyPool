package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import entity.Finance;

/**
 * 
 * @author janke
 *
 */
@Service
public class FinancialServiceImpl implements FinancialService{
	
	public List<Double> getFinancialTargetList(Finance finance){
		List<Double> financialTargetList = new ArrayList<Double>();
		financialTargetList.add(finance.getInventoryTurnover());
		financialTargetList.add(finance.getNetAssetsIncome());
		financialTargetList.add(finance.getNetProfit());
		financialTargetList.add(finance.getNetProfitIncrease1());
		financialTargetList.add(finance.getNetProfitIncrease2());
		financialTargetList.add(finance.getTotalAssetsIncrease());
		return financialTargetList;
	}
	
	public List<Double> getFInancialWeightList(){
		List<Double> financialWeightList = new ArrayList<Double>();
		financialWeightList.add(Finance.InventoryTurnoverWeight);
		financialWeightList.add(Finance.NetAssetsIncomeWeight);
		financialWeightList.add(Finance.NetProfitWeight);
		financialWeightList.add(Finance.NetProfitIncreaseWeight1);
		financialWeightList.add(Finance.NetProfitIncreaseWeight2);
		financialWeightList.add(Finance.TotalAssetsIncreaseWeight);
		return financialWeightList;
	}
}
