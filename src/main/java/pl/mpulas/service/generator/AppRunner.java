package pl.mpulas.service.generator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class AppRunner {

	public static void main(String[] args) {
		 System.out.println("Start ....");
	
		 String sourceCASPath = "D:\\PZU_CKI\\AGENCJE UFG\\workspace_ufg_agencje\\cas-multi_ebik-CustomerAndSales-CustomerService";
		 
		 Path sourceCasPath = Paths.get(sourceCASPath);
		 
		 
		 List<String> casCodes =  Arrays.asList( new String[]{//"multi_ceu",   
		                                                       "multi_mbu",  "multi_kjf",  
				                                               "multi_kons", "multi_promesa",  "multi_voxen",  "multi_wtw" });  
		 
		 for(String casCode: casCodes) {
			 System.out.println("\n >>> Start for " + casCode );  
			 
			 ServiceGenerator gen = new ServiceGenerator();
			 
			 ServiceGeneratorConfig casCopyConfig = new ServiceGeneratorConfig();		 
			 casCopyConfig.setConsumerCode(casCode);  
			 			 
			 gen.createCasFromExisting(sourceCasPath, casCopyConfig ); 
			 
		 }
		 
		 for(String casCode: casCodes) { 
			System.out.println(
			String.format(" svn import \"D:\\PZU_CKI\\AGENCJE UFG\\workspace_ufg_agencje\\cas-%s-CustomerAndSales-CustomerService\" "
					         + "     http://dev-svn.pzu.pl/repo-code/production/esb/osb-power/consumer/cas-%s-CustomerAndSales-CustomerService/branches/1.4"
					         + " -m \"Cas dla multi agencji,  z filtrowaniem danych zwracanych w resp.\"   "
					, casCode,casCode)  
			
			); 
			
			System.out.println(
					String.format(" svn checkout  http://dev-svn.pzu.pl/repo-code/production/esb/osb-power/consumer/cas-%s-CustomerAndSales-CustomerService/branches/1.4 " +
							      "       \"D:\\PZU_CKI\\AGENCJE UFG\\workspace_ufg_agencje\\cas-%s-CustomerAndSales-CustomerService\" " 
							      ,casCode, casCode)   
					
					); 
		 
		 }
		 
		 System.out.println("Completed ....");
		 
	}

}
