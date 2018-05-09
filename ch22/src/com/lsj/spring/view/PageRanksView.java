package com.lsj.spring.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.lsj.spring.model.PageRank;


public class PageRanksView extends AbstractXlsView{
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		HSSFSheet sheet = createFirstSheet(workbook);
		createColumnLabel(sheet);
		@SuppressWarnings("unchecked")
		List<PageRank> pageRanks = (List<PageRank>) model.get("pageRanks");
		int rowNum =1;
		for(PageRank rank : pageRanks) {
			createPageRankRow(sheet, rank, rowNum++);
		}
	}
	private HSSFSheet createFirstSheet(Workbook workbook) {
		HSSFSheet sheet = (HSSFSheet) workbook.createSheet();
		workbook.setSheetName(0, "페이지 순위");
		sheet.setColumnWidth(1, 256*20);
		return sheet;
	}
	private void createColumnLabel(HSSFSheet sheet) {
		HSSFRow firstRow = sheet.createRow(0);
		HSSFCell cell = firstRow.createCell(0);
		cell.setCellValue("순위");
		cell = firstRow.createCell(1);
		cell.setCellValue("페이지");
	}
	private void createPageRankRow(HSSFSheet sheet,
			PageRank rank, int rowNum) {
		HSSFRow row = sheet.createRow(rowNum);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(rank.getRank());
		cell = row.createCell(1);
		cell.setCellValue(rank.getPage());
	}
	
}
