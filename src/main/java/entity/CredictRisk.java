package entity;

import org.springframework.stereotype.Component;

/**
 * 
 * @author janke
 *
 */
@Component
public class CredictRisk {
	
	public static final Double FinancialRiskWeight = 0.7;
	
	public  static final Double NetWorkRiskWeight = 0.2;
	
	public static final Double LegalEvaluationRiskWeight = 0.1;
	
	private Company company;
	
	private Finance finance;
	
	private LegalEvaluation legalEvaluation;

	public CredictRisk() {
		// TODO Auto-generated constructor stub
	}
	
	public CredictRisk(Finance finance, Company company, LegalEvaluation legalEvaluation) {
		this.company = company;
		this.finance = finance;
		this.legalEvaluation = legalEvaluation;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Finance getFinance() {
		return finance;
	}

	public void setFinance(Finance finance) {
		this.finance = finance;
	}

	public LegalEvaluation getLegalEvaluation() {
		return legalEvaluation;
	}

	public void setLegalEvaluation(LegalEvaluation legalEvaluation) {
		this.legalEvaluation = legalEvaluation;
	}
}	
