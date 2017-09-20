package pl.mpulas.fileutils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;

public class ProjectFileProcessor {
	
	
	private static final List<String> filteredFileTypes = Arrays.asList(new String[] {"wsdl", "xsd", "proxy", "xq", "xml", "project", "component"}); 
 
	
	
	public void copyWithReplace(Path fileFromPath, Path fileToPath, StandardCopyOption copyOption, String replaceFrom, String replaceTo ) {
		
		// zamiast prostego kopiowania jak to:   Files.copy(file, fileToPath, copyOption);
		// kopiowanie z filtrowaniem 
 
		if(!filteredFileTypes.contains( FilenameUtils.getExtension(fileFromPath.getFileName().toString()))) {
			try {
				System.out.println("ProjectFileProcessor.copyWithReplace:    " + fileToPath + "  [BEZ ZMIAN]");
				Files.copy(fileFromPath, fileToPath, copyOption);
				return;  
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println("ProjectFileProcessor.copyWithReplace:    "+ fileToPath + "  [FILTROWANIE "+ replaceFrom+" -> "+replaceTo+ " ]");
		try ( Stream<String> lines = Files.lines(fileFromPath, StandardCharsets.UTF_8) ) {
			
            // for (String line : (Iterable<String>) lines::iterator) {
            //    List<String> result = new ArrayList<>();
            //     result.add(line.replaceAll(replaceFrom, replaceTo));
            //    Files.write(fileToPath, result, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            // }            
            
            List <String> replaced = lines.map(line -> line.replaceAll(replaceFrom, replaceTo)).collect(Collectors.toList()); 
            
            Files.write(fileToPath, replaced, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
 		
		} catch (IOException ex) {
           ex.printStackTrace();
        }
		
	}
	
	
	
}
