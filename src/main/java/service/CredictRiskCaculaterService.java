package service;

import org.springframework.stereotype.Service;

import entity.Company;
import entity.Finance;
import entity.LegalEvaluation;

/**
 * 
 * @author janke
 *
 */
@Service
public interface CredictRiskCaculaterService {

	void caculater(Finance finance, Company company, LegalEvaluation legalEvaluation);

	Double caculate();

}
