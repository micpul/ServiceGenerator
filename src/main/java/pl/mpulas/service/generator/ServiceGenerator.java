package pl.mpulas.service.generator;

import java.nio.file.Path;

import org.apache.commons.io.FileUtils;

import pl.mpulas.fileutils.GeneratorFileUtils;

public class ServiceGenerator {
	
	
	/**
	 * Tworzy modu� CAS us�ugi w oparciu o kopi� wzorcowego CASa
	 * 
	 * @param config
	 */
	public void createCasFromExisting(Path sourceCasPath, ServiceGeneratorConfig config) {
		
		
		GeneratorFileUtils.copyCasProjectFolder(sourceCasPath, config);
		
		
	}
	
	
	
	
	
}
