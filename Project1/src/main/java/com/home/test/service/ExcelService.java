package com.home.test.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class ExcelService {
	
	public void excelDownload(HttpServletResponse res, Map<String, Object> data) {
		System.out.println("exceldownload 실행");
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		List<String> listSheet = new ArrayList<String>();
		if (data.get("sheet") instanceof List) {
			for (Object element : (List<?>) data.get("sheet")) {
				listSheet.add((String) element);
			}
		}
		
		for (String sheets : listSheet) {
			
			
			
			XSSFSheet sheet = workbook.getSheet(sheets);
			if (sheet == null) {
				sheet = workbook.createSheet(sheets);
			}
			
			Map<String, Object> mapHead = new HashMap<String, Object>();
			if (data.get("head") instanceof Map) {
				mapHead = (Map<String, Object>) data.get("head");
			}
			List<String> listHead = new ArrayList<String>();
			if (mapHead.get(sheets) instanceof List) {
				for (Object element : (List<?>)mapHead.get(sheets)) {
					listHead.add((String)element);
				}
			}
			int rowNum = 0;
			int cellNum = 0;
			for (Object heads : listHead) {
				
				XSSFRow row = sheet.getRow(rowNum);
				if (row == null) {
					System.out.println("row 생성");
					row = sheet.createRow(0);
					row = sheet.createRow(1);
				}
				
				if (heads instanceof String) {

					sheet.addMergedRegion(new CellRangeAddress(0, 1, cellNum, cellNum));
					
					System.out.println("row : " + row);
					
					XSSFCell cell = row.createCell(cellNum);
					System.out.println("cell : " + cell);
					cell.setCellValue((String)heads);
					System.out.println("cell : " + cell);
					
					cellNum ++;
				} else if (heads instanceof List) {
					sheet.addMergedRegion(new CellRangeAddress(0, 0, cellNum, cellNum + ((List<String>)heads).size()));
					row = sheet.getRow(0);
					
					for (String list : (List<String>) heads) {
						cellNum = 0;
						row = sheet.getRow(1);
						
						for (String datas : list) {
							XSSFCell cell = row.createCell(cellNum);
							cell.setCellValue(datas);
							cellNum++;
						}
						rowNum++;
					}
					
					
					
					
				}
			}
			rowNum = 2;

			
			Map<String, Object> mapData = new HashMap<String, Object>();
			if (data.get("data") instanceof Map) {
				mapData = (Map<String, Object>) data.get("data");
			}
			List<List<String>> listData = new ArrayList<List<String>>();
			if (mapData.get(sheets) instanceof List) {
				for (Object element : (List<?>)mapData.get(sheets)) {
					if (element instanceof List) {
						listData.add((List<String>)element);
					}
				}
			}
			
			for (List<String> list : listData) {
				cellNum = 0;
				XSSFRow row = sheet.createRow(rowNum);
				
				for (String datas : list) {
					XSSFCell cell = row.createCell(cellNum);
					cell.setCellValue(datas);
					cellNum++;
				}
				rowNum++;
			}
		}
		
		HttpHeaders headers = new HttpHeaders();
		res.setHeader("Content-Disposition", "attachment; filename=test.xlsx");
		res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		
		OutputStream out;
		
		try {
			out = res.getOutputStream();
			workbook.write(out);
			out.flush();
			workbook.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
	}
	
	
	public Map<String, Object> sampleData() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> sheet = new ArrayList<String>();
		Map<String, Object> head = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		
		sheet.add("testsheet1");
		sheet.add("testsheet2");
		
		head.put("testsheet1", Arrays.asList(
				"id", 
				Arrays.asList("pw1", "pw2", "pw3"),
				"name", 
				"age", 
				"address"));
		head.put("testsheet2", Arrays.asList("num", "num2", "num3", "nu4", "num5"));
		
		data.put("testsheet1", Arrays.asList(
				Arrays.asList("aid", "apw", "aname", "aage", "aaddress"),
				Arrays.asList("bid", "bpw", "bname", "bage", "baddress"),
				Arrays.asList("cid", "cpw", "cname", "cage", "caddress")
				));
		data.put("testsheet2", Arrays.asList(
				Arrays.asList("1", "11", "111", "1111", "11111"),
				Arrays.asList("2", "22", "222", "2222", "22222"),
				Arrays.asList("3", "33", "333", "3333", "33333")
				));
		
		map.put("sheet", sheet);
		map.put("head", head);
		map.put("data", data);
		
		System.out.println("sheet : " + map.get("sheet"));
		System.out.println("head : " + map.get("head"));
		System.out.println("data : " + map.get("data"));
		
		return map;
	}
}
