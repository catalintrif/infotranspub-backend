package ro.gov.ithub.infotranspub.dbtools.processors;

import java.lang.String;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;


public class GTFSZipEntryProcessor implements GenericProcessor{

	private InputStream  zipStream;
	private String entryName;

	public GTFSZipEntryProcessor(InputStream zipStream, String entryName){
		this.entryName =  entryName;
		this.zipStream = zipStream;
	}
	@Override
	public Boolean process(){
		BufferedReader reader = new BufferedReader(
						new InputStreamReader(zipStream));
		GTFSCSVFileProcessor processor = new GTFSCSVFileProcessor(this.entryName);
		try {
			String line;
			while ((line = reader.readLine()) != null){
				processor.process(line);
			}
		} catch ( IOException e){
			e.printStackTrace();
		}	
		
		processor.processTail();		
		return false;
	}
	@Override
	public Boolean process(String line){
		return false;
	}
}
