package pl.mpulas.service.generator;

import java.nio.file.Path;

import org.apache.commons.io.FileUtils;

import pl.mpulas.fileutils.GeneratorFileUtils;

public class ServiceGenerator {
	
	
	/**
	 * Tworzy modu³ CAS us³ugi w oparciu o kopiê wzorcowego CASa
	 * 
	 * @param config ffffffffffffff gggggggggggggggg  fffffffff
	 */
	public void createCasFromExisting(Path sourceCasPath, ServiceGeneratorConfig config) {
		//d 
		
		GeneratorFileUtils.copyCasProjectFolder(sourceCasPath, config); //dddd  hhhhhhh qq   qqqq
		
		//dddd ffffff  ggg   ddd
		GeneratorFileUtils.copyCasProjectFolder(sourceCasPath, config); //dddd  hhhhhhh qqqqqq
		
		//dddd
		 //fffffffffffffffffff hujjjj 2 4444
	}
	
	
	
	
	
}
