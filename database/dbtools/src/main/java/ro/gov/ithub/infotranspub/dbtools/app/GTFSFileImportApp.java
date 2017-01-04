package ro.gov.ithub.infotranspub.dbtools.app;
import ro.gov.ithub.infotranspub.dbtools.processors.GTFSZipFileProcessor;

public class GTFSFileImportApp{
	public static void main(String[] args){
		GTFSFileImportApp app = new GTFSFileImportApp();
		app.run();
	}

	public GTFSFileImportApp(){
	}

	public void run(){
		System.out.println("GTFSFileImportApp.run() start");
		GTFSZipFileProcessor processor = new GTFSZipFileProcessor("sfmta.gtfs");
		processor.process();
		// open stream file from disk
		// quick check sanity
		// loop through entries
		// process each entry	
			// open stream for entry
			// read header
			// quick check
			// loop through lines
			// process lines
		// proces whole
		System.out.println("GTFSFileImportApp.run() stop");
	}
	


}
