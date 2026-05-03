package pc.crawler;
import java.nio.file.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.*;


public class WebCrawlerParallel_Q4 {
	
	public static class Paire{
		private String url;
		private int profondeur;
		
		public Paire(String url, int profondeur) {
			this.url = url;
			this.profondeur = profondeur;
		}

		public String getUrl() {
			return url;
		}
		
		public int getProfondeur() {
			return profondeur;
		}
		
		//Question 4 
		public boolean isPoisonPill() {
			return url == null;
		}

	}
	
	
    public static void main(String[] args) {
    	
    	
    	int nb_Thread = 4; // pour le executor
    	int maxProfondeur = 2;
    	BlockingQueue<Paire> queue = new LinkedBlockingQueue<>();
    	ExecutorService executor = Executors.newFixedThreadPool(nb_Thread);
    	
    	//question 2 
    	ConcurrentHashMap<String, Boolean> visited = new ConcurrentHashMap<>(); //j'ai mis boolean mais en soi ça peut être un int c'est surtout utilisé comme un ensemble atomique ici
    	
    	//question4
    	ActivityMonitor monitor = new ActivityMonitor();
    	
    	
        // Hardcoded base URL to start crawling from
        String baseUrl = "https://www-licence.ufr-info-p6.jussieu.fr/lmd/licence/2023/ue/LU3IN001-2023oct/index.php";
        
        // Hardcoded output directory where downloaded pages will be saved
        Path outputDir = Paths.get("/tmp/crawler/");
        
        try {
            // Ensure the output directory exists; create it if it doesn't
            if (!Files.exists(outputDir)) {
                Files.createDirectories(outputDir);
                System.out.println("Created output directory: " + outputDir.toAbsolutePath());
            }
            
            visited.put(baseUrl, true);
            
            try {
            	monitor.taskStarted();
				queue.put(new Paire(baseUrl,maxProfondeur));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            for(int i = 0; i<nb_Thread; i++) {
            	executor.submit(()->{
            		List<String> extractedUrls = Collections.emptyList();
            		while(true) {
            			Paire tache = null;
            			try {
            				
            				tache = queue.take(); //bloque si la file est vide
            				if(tache.isPoisonPill()) {
            					break;
            				}
            				
            				System.out.println("Processing (Depth " + tache.getProfondeur() + "): " + tache.getUrl());
            		    	extractedUrls = WebCrawlerUtils.processUrl(tache.getUrl(), baseUrl, outputDir);
            		    	if (tache.getProfondeur() > 0) {
            		    		for (String url : extractedUrls) {
            		    			if(visited.putIfAbsent(url, true)==null) {
            		    				monitor.taskStarted();
            		    				queue.put(new Paire(url, tache.getProfondeur() - 1));
            		    			}
            		    			
            		    		}
            		    	}
            		    	monitor.taskCompleted();
            		    } catch (InterruptedException e) {
            		    	Thread.currentThread().interrupt();
                            break;
                        } catch (URISyntaxException | IOException e) {
                        	System.err.println("Error during crawling: " + e.getMessage());
                        	//on s'est rendu compte que si on mettait pas cette ligne le programme ne s'arrête pas parce qu'on a pas pris en compte le fait que ça peut échouer sans baisse le compteur de moniteur, ce qui va bloquer monitor.awaitCompletion
                        	if(tache != null && !tache.isPoisonPill()) {
                        		monitor.taskCompleted();
                        	}
                        }
            		}
            	});
            }
            
            try {
				monitor.awaitCompletion();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            //insertion de poison pill comme dit l'énoncé pour débloquer les threads:
            
            for(int i = 0; i<nb_Thread; i++) {
            	try {
					queue.put(new Paire(null,0));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            
            executor.shutdown();
            System.out.println("Parallel crawling completed successfully.");
            
        }
        catch (IOException e) {
            System.err.println("Error during crawling: " + e.getMessage());
            e.printStackTrace();
            
        }
    }
}
