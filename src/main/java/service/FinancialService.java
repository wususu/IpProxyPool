package service;

import java.util.List;

import org.springframework.stereotype.Service;

import entity.Finance;

/**
 * 
 * @author janke
 * 
 */
@Service
public interface FinancialService {
	
	List<Double> getFinancialTargetList(Finance finance);
	
	List<Double> getFInancialWeightList();

}
