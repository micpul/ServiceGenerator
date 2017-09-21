package pl.mpulas.fileutils;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyDirVisitor extends SimpleFileVisitor<Path> {
	

    private Path fromPath;
    private Path toPath;
    private StandardCopyOption copyOption;
    private ProjectFileProcessor projectFileProcessor = new ProjectFileProcessor(); 

    private String replaceFrom, replaceTo; 

    public CopyDirVisitor(Path fromPath, Path toPath, StandardCopyOption copyOption, String replaceFrom, String replaceTo) {
        this.fromPath = fromPath;
        this.toPath = toPath;
        this.copyOption = copyOption;
        this.replaceFrom = replaceFrom;
        this.replaceTo = replaceTo;
        
    }

    public CopyDirVisitor(Path fromPath, Path toPath, String replaceFrom, String replaceTo) {
        this(fromPath, toPath, StandardCopyOption.REPLACE_EXISTING, replaceFrom, replaceTo ); 
    }

    
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println("CopyDirVisitor.preVisitDirectory: "+ dir);
        
        if(dir.getFileName().toString().equalsIgnoreCase(".svn")) {  // pomijanie svn 
        	return FileVisitResult.SKIP_SUBTREE; 
        }
        
        Path targetPath = toPath.resolve(fromPath.relativize(dir));
        if(!Files.exists(targetPath)){
            Files.createDirectory(targetPath);
        }
        return FileVisitResult.CONTINUE;
    }

    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    	// System.out.println("CopyDirVisitor.visitFile: "+ file);
    	
    	Path fileToPath = toPath.resolve(fromPath.relativize(file));
    	
    	projectFileProcessor.copyWithReplace(file, fileToPath, copyOption, replaceFrom, replaceTo);
    	
    	// przeniesione do kopiowania z filtrowaniem:    Files.copy(file, fileToPath, copyOption);
        
    	return FileVisitResult.CONTINUE;
    }
    
    
}