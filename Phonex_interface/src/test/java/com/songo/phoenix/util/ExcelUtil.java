package com.songo.phoenix.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	/**
	 * 读取excel数据
	 * 
	 * @param path
	 *            文件路径
	 * @param startRow
	 *            开始的行数
	 * @param endRow
	 *            结束的行数
	 * @param startColum
	 *            开始的列
	 * @param endColumn
	 *            结束的列
	 */
	public static Object[][] read(String path, int sheetNum, int startRow, int endRow, int startCell, int endCell) {
		Object[][] datas = new Object[endRow - startRow + 1][endCell - startCell + 1];
		try {
			Workbook workbook = WorkbookFactory.create(ExcelUtil.class.getResourceAsStream(path));
			Sheet sheet = workbook.getSheetAt(sheetNum);
			for (int i = startRow; i <= endRow; i++) {
				Row row = sheet.getRow(i - 1);
				for (int j = startCell; j <= endCell; j++) {
					Cell cell = row.getCell(j - 1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					// 设置每一列的数据类型为字符串
					cell.setCellType(CellType.STRING);
					String value = cell.getStringCellValue();
					datas[i - startRow][j - startCell] = value;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datas;
	}

	/**
	 * 往excel指定行的指定列写入数据
	 * 
	 * @param sheetNum
	 *            要操作的表单索引
	 * @param caseId
	 *            用例编号
	 * @param cellNum
	 *            列编号
	 * @param result
	 *            要写入的结果数据
	 * @param filePath
	 *            要写入的文件路径（非classpath路径）
	 */
	public static void write(int sheetNum, String caseId, int cellNum, String result, String filePath) {
		try {
			InputStream is = new FileInputStream(new File(filePath));
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(sheetNum);
			int lastRowNum = sheet.getLastRowNum();
			for (int i = 0; i <= lastRowNum; i++) {
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				String value = cell.getStringCellValue();
				if (value.equals(caseId)) {
					Cell cellToBeWirte = row.getCell(cellNum - 1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cellToBeWirte.setCellType(CellType.STRING);
					cellToBeWirte.setCellValue(result);
					break;
				}
			}
			OutputStream os = new FileOutputStream(new File(filePath));
			workbook.write(os);
			os.close();
			is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void write(String resultString) {

	}

	/**
	 * 往excel指定行的指定列写入数据
	 * 
	 * @param file_classPath：要读取的文件calssPath路径
	 * @param sheetNum：要操作的表单索引
	 * @param caseId：用例编号
	 * @param cellNum：列编号
	 * @param result：要写入的结果数据
	 * @param filePath：要写入的文件路径(非classPath路径)
	 */
	public static void write(String file_classPath, int sheetNum, String caseId, int cellNum, String result,
			String filePath) {
		OutputStream os = null;
		try {
			Workbook workbook = WorkbookFactory.create(ExcelUtil.class.getResourceAsStream(file_classPath));
			Sheet sheet = workbook.getSheetAt(sheetNum);
			int lastRowNum = sheet.getLastRowNum();
			for (int i = 0; i < lastRowNum; i++) {
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				String value = cell.getStringCellValue();
				if (value.equals(caseId)) {
					Cell cellToBeWrite = row.getCell(cellNum, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cellToBeWrite.setCellType(CellType.STRING);
					cellToBeWrite.setCellValue(result);
				}
			}
			os = new FileOutputStream(new File(filePath));
			workbook.write(os);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
