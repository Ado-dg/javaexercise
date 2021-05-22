package main.java.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.model.Weapon;
import main.java.util.exceptions.*;

public class CsvWeaponsRepository {
	
	private static final String fileName = "weapons.csv";
	private static Logger logger = LogManager.getLogger(CsvWeaponsRepository.class);

	
	public List<Weapon> getWeapons() {
		try {
			String path = "D:/" + fileName;
			File file = new File(path);
			CSVParser parser = CSVParser.parse(file, Charset.defaultCharset(), CSVFormat.EXCEL.withHeader("Name", "min", "max").withSkipHeaderRecord().withTrim());
			return parseRecordsToWeapons(parser.getRecords());
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new WeaponException(e.getMessage());
		}
	}
	
	private List<Weapon> parseRecordsToWeapons(List<CSVRecord> records){
		List<Weapon> weapons = new LinkedList<Weapon>();
		for (CSVRecord record : records) {
			weapons.add(new Weapon(record.get("Name"), Integer.parseInt(record.get("min")), Integer.parseInt(record.get("max"))));
		}
		return weapons;
	}
}
