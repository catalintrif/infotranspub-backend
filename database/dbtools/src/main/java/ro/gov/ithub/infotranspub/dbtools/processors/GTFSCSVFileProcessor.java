package ro.gov.ithub.infotranspub.dbtools.processors;
import ro.gov.ithub.infotranspub.gtfs.MetaPool;

public class GTFSCSVFileProcessor implements GenericProcessor{

	private int lineCount = 0;
    	private String []  header = null;
	private Boolean skipIt = false;
	private String entryName = null;
	private MetaPool metaPool = new MetaPool();
	private SQLTempTableProcessor tempTableProcessor = null;	
	public GTFSCSVFileProcessor(String entryName){
		this.entryName = entryName;
		if (this.metaPool.getColumns(entryName) == null){
			skipIt = true;
		} else {
			tempTableProcessor = new SQLTempTableProcessor(entryName);	
		}
	}

	@Override
	public Boolean process(){
		return false;
	}
	@Override
	public Boolean process(String line){
		if (skipIt){
			return false;
		}


		if (this.lineCount == 0 ){
			this.processHeader(line);
		} else {
			this.processBody(line);
		}
		this.lineCount ++;
		return false;
	}

	void processHeader(String line){
		if (skipIt){
			return ;
		}
		System.out.println(this.entryName);
		System.out.println(line);
		this.tempTableProcessor.processHeader(line);
	}

	void processBody(String line){
		if (skipIt){
			return ;
		}
		this.tempTableProcessor.processBody(line);
	}

	void processTail(){
		if (skipIt){
			return ;
		}
		this.tempTableProcessor.processTail();
	}
}
