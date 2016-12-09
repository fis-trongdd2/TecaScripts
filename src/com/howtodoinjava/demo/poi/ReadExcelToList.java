package com.howtodoinjava.demo.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tecapro.utils.convert.FontConverter;
import com.tecapro.utils.convert.FontType;

public class ReadExcelToList {
	private List<ObjectInfo> listObjects;

	public List<ObjectInfo> getListObjects() {
		return listObjects;
	}

	public void setListObjects(List<ObjectInfo> listObjects) {
		this.listObjects = listObjects;
	}

	public ReadExcelToList() {

	}

	private List<String> readRowToListString(Row row) {
		Iterator<Cell> cellIterator = row.cellIterator();
		List<String> results = new ArrayList<>();
		String temp;
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			int value = cell.getCellType();
			Double t;
			if (value == Cell.CELL_TYPE_NUMERIC) {
				t = cell.getNumericCellValue();
				temp = t.toString().trim();
				if (temp.contains(" ")) {
					temp = temp.substring(0,temp.indexOf(" "));
				}
			} else {
				temp = cell.getStringCellValue().trim();
			}
			results.add(temp);
		}
		return results;
	}

	public ReadExcelToList(String link) throws IOException {
		FileInputStream file = new FileInputStream(new File(link));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		listObjects = new ArrayList<>();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			List<String> str = readRowToListString(row);
			if (Objects.isNull(str.get(0)))
				break;
			ObjectInfo temp = new ObjectInfo();
			temp.setTen(FontConverter.convert(FontType.UNICODE, FontType.ACSII, str.get(0)));
			temp.setLoai1(FontConverter.convert(FontType.UNICODE, FontType.ACSII, str.get(1)));
			temp.setDbname1(str.get(2));
			String[] parts = str.get(3).split(",");
			temp.setServer(parts[0]);
			if (parts.length > 1) {
				temp.setPort(parts[1]);
			} else {
				temp.setPort("1433");
			}
			temp.setLoai2(FontConverter.convert(FontType.UNICODE, FontType.ACSII, str.get(4)));
			temp.setDbname2(str.get(5));
			temp.setMaBhxh(str.get(6));
			temp.setIdBhxh(str.get(7));
			temp.setMaTinhBv(str.get(8));
			temp.setIdKhoiQl(str.get(9));
			listObjects.add(temp);
		}
		file.close();

	}

	public Map<String, ObjectInfo> getMapObjects() {
		Map<String, ObjectInfo> result = new HashMap<>();
		String matinh = listObjects.get(0).getMaTinhBv();
		String link = listObjects.get(0).getTen();
		String linkToFile = "";

		int i = 0; 
		for (ObjectInfo temp : this.listObjects) {
			if (!temp.getMaTinhBv().equals(matinh)) {
				link = temp.getTen();
				matinh = temp.getMaTinhBv();
				i++;
			}
			linkToFile = String.format("%02d", i)+ "."+ link + "/" + temp.getTen();
			result.put(linkToFile, temp);
		}
		result.remove(linkToFile);
		return result;
	}

	public static void main(String[] args) throws IOException {
		// ReadExcelToList test = new ReadExcelToList("test2.xlsx");
		// for (ObjectInfo t : test.getListObjects())
		// System.out.println(t.toString());

		ReadExcelToList test = new ReadExcelToList("test2.xlsx");
		for (Map.Entry<String, ObjectInfo> t : test.getMapObjects().entrySet())
			System.out.println(t.getKey() + " " + t.getValue().toString());
	}
}
