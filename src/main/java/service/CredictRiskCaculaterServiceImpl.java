package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import entity.Company;
import entity.CredictRisk;
import entity.Finance;
import entity.LegalEvaluation;

/**
 * Caculater for credict risk
 * z = b0 + ~bi*xi
 * p = 1 / (1 + e ^ (-z))
 * @author janke
 * 
 */
@Service
public class CredictRiskCaculaterServiceImpl implements CredictRiskCaculaterService{
	
	@Autowired
	private CredictRisk credictRisk;
	
	@Autowired
	@Qualifier("financialServiceImpl")
	private FinancialService financialService;
	
	@Override
	public void caculater(Finance finance, Company company, LegalEvaluation legalEvaluation){
		credictRisk.setCompany(company);
		credictRisk.setFinance(finance);
		credictRisk.setLegalEvaluation(legalEvaluation);
	}
	
	private Double zCaculater() {
		// TODO Auto-generated method stub
		Double zDouble = Finance.B0;
		List<Double> bList = financialService.getFinancialTargetList(credictRisk.getFinance());
		List<Double> xList = financialService.getFInancialWeightList();
		for (int i = 0; i < bList.size(); i++) {
			Double bDouble = bList.get(i);
			Double xDouble = xList.get(i);
			zDouble += bDouble*xDouble;
		}
		return zDouble;
	}

	private Double financialCaculater(Double zDouble) {
		// TODO Auto-generated method stub
		Double negativeZ = - zDouble;
		Double financialRisk =(double)1 / (1 + Math.pow(Math.E, negativeZ));
		return financialRisk;
	}
	
	@Override
	public  Double caculate() {
		Double financialRisk = financialCaculater(zCaculater());
		credictRisk.getFinance().setFinanceRisk(financialRisk);
		Double netWorkRisk = credictRisk.getCompany().getCompanyAssess();
		Double legalEvaluation = (double)0;
		Double risk = financialRisk * CredictRisk.FinancialRiskWeight + netWorkRisk * CredictRisk.NetWorkRiskWeight + legalEvaluation * CredictRisk.LegalEvaluationRiskWeight;
		return risk;
	}

}
