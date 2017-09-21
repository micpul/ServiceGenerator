package pl.mpulas.fileutils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

import org.apache.commons.io.FileUtils;

import pl.mpulas.service.generator.ServiceGeneratorConfig;

public class GeneratorFileUtils {
	
	@Deprecated	
	public static void copyCasProjectFolderOLD(Path sourceCasFolder, ServiceGeneratorConfig targetConfig) {
 
		try {
			String newCasFolderName = sourceCasFolder.getParent().resolve("Wygenerowany_cas_"+targetConfig.getConsumerCode()).toString();
			
			if(Files.isDirectory(Paths.get(newCasFolderName))){
				System.out.println(" Kasowanie folderu: " + newCasFolderName); 
				FileUtils.deleteDirectory(new File(newCasFolderName));
			}
			
			System.out.println(" Kopiowanie folderu: "+ sourceCasFolder +" -> "+ newCasFolderName);
			
			org.apache.commons.io.FileUtils.copyDirectory(sourceCasFolder.toFile(), new File(newCasFolderName) );
		
		
		} catch (IOException e) {			 
			e.printStackTrace();
		}
		
	}

	
	
	public static void copyCasProjectFolder(Path sourceCasFolder, ServiceGeneratorConfig targetConfig) {
		 
		try {
			String sourceConsumerCode = extractConsumerCodeFromCasName(sourceCasFolder.getFileName().toString());
			String newConsumerCode    = targetConfig.getConsumerCode();
			String newCasName         = createCasName(sourceCasFolder, newConsumerCode ); 
			
			String newCasFolderName = sourceCasFolder.getParent().resolve(newCasName).toString();
			Path newCasFolderPath = Paths.get(newCasFolderName); 
			
			
			System.out.println("Worcowy cas:   "+sourceCasFolder.getFileName() + 
					           "  ("+sourceConsumerCode +")   " +
					           " generowany cas:   " + newCasName  + "   ("+newConsumerCode+")");
			
			if(Files.isDirectory(newCasFolderPath)){
				System.out.println(" Kasowanie folderu: " + newCasFolderName); 
				FileUtils.deleteDirectory(new File(newCasFolderName)); 
				System.out.println(" Kasowanie folderu zakoñczone: " );
			}
			
			
			System.out.println(" Kopiowanie folderu: "+ sourceCasFolder +" -> "+ newCasFolderName);
			 
			// KOPIOWANIE PLIKOW Z REPLACEM sourceConsumerCode -> newConsumerCode 
			
		    Files.walkFileTree( sourceCasFolder,  
		    		            EnumSet.of(FileVisitOption.FOLLOW_LINKS), 
		    		            Integer.MAX_VALUE,  
		    		            new CopyDirVisitor(sourceCasFolder, newCasFolderPath, 
		    		            		           sourceConsumerCode, newConsumerCode) );
		  
		    System.out.println(" Kopiowanie folderu zakoñczone: " );
		
		} catch (IOException e) {			 
			e.printStackTrace();
		}
		
	}
	
	public static String createCasName(Path sourceCasFolder, String targetConsumerCode) {
		
		String sourceConsumerName  = sourceCasFolder.getFileName().toString();
		int idxStartConsumerCode = sourceConsumerName.indexOf("-");
		int idxEndConsumerCode = sourceConsumerName.indexOf("-", idxStartConsumerCode+1);
		return sourceConsumerName.substring(0, idxStartConsumerCode+1)
			   + targetConsumerCode   
			   +  sourceConsumerName.substring(idxEndConsumerCode, sourceConsumerName.length());
		
	}
	
	public static String extractConsumerCodeFromCasName(String casModuleName ) {
 
		int idxStartConsumerCode = casModuleName.indexOf("-");
		int idxEndConsumerCode = casModuleName.indexOf("-", idxStartConsumerCode+1);
		
		return casModuleName.substring(idxStartConsumerCode+1, idxEndConsumerCode ) ;
		
	}
	
	
}
