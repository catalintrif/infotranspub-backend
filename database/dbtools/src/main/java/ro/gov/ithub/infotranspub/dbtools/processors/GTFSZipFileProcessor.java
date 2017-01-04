package ro.gov.ithub.infotranspub.dbtools.processors;

import java.lang.String;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;


public class GTFSZipFileProcessor implements GenericProcessor{

	private String gtfsFilePath;	
	public GTFSZipFileProcessor(String gtfsFilePath){
		this.gtfsFilePath = gtfsFilePath;
	}

	@Override
	public Boolean process(){
		ZipArchiveInputStream zip = null;
		ZipFile zipFile = null;
		try {

			zipFile=new ZipFile(this.gtfsFilePath);
			Enumeration<ZipArchiveEntry> zipEntries=zipFile.getEntriesInPhysicalOrder();
			while (zipEntries.hasMoreElements()) {
				ZipArchiveEntry entry=zipEntries.nextElement();
				if (entry.isDirectory())
					continue;
				String entryName = entry.getName();
					
				InputStream zipStream=null;
				zipStream=zipFile.getInputStream(entry);

				GTFSZipEntryProcessor entryProcessor = new GTFSZipEntryProcessor(zipStream,entryName);
				entryProcessor.process();		
				zipStream.close();
				
				
			}
		} catch( FileNotFoundException e){
		    e.printStackTrace();
		    return false;
		} catch(IOException e){
		    e.printStackTrace();
			return false;
		}    

		return true;
	
	}
	@Override
	public Boolean process(String line){
		return false;
	}
}

