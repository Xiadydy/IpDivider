package com.gdut.ipdivider.tool;

import com.gdut.ipdivider.entity.SubNetInfomationBean;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class FileUtil {

	private static FileUtil instance = new FileUtil();

	private final static WritableFont wf ;
	private final static WritableCellFormat format;
	public static FileUtil getInstance() {
		return instance;
	}

	static{
		wf =  new WritableFont(WritableFont.ARIAL,WritableFont.DEFAULT_POINT_SIZE,WritableFont.NO_BOLD,
				false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
		format =  new WritableCellFormat(wf);
	}

	/**
	 * 
	 * 导出Excel文件
	 *
	 * @param out 文件导出请求的相应对象
	 * @param data  要导出的文件数据
	 * @param titles 导出的数据标题 必须和keys同时存在，且顺序保持一致，否则会出现数据错乱
	 * @param keys  导出数据的keys，必须喝title同时存，且顺序保持一致，否则会出现数据错乱
	 * @param fileName  要导出的Excel文件名称默认：yyyy年MM月dd日 HH时mm分ss秒.xls
	 * @param sheetName 数据所在的Excel工作表名称默认：sheet1
	 */
	public void exportExcel(OutputStream out, List<Map<String, Object>> data, String[] titles,
							String[] keys, String fileName, String sheetName, List<SubNetInfomationBean> source) {

		if (StringUtils.isBlank(sheetName)) {
			sheetName = "sheet1";
		}
		if (CollectionUtils.isNotEmpty(data)) {
			if (titles == null || titles.length == 0) {
				titles = data.get(0).keySet().toArray(new String[] {});
			}
			//设置文件数据的keys
			if (keys == null || keys.length == 0) {
				keys = data.get(0).keySet().toArray(new String[] {});
			}
		}
		try {
			WritableWorkbook excelFile = this.createExcel(out);
			WritableSheet sheet = excelFile.createSheet(sheetName, 0);
			this.addExcelSheetRows(sheet, data, titles, keys, source);
			excelFile.write();
			excelFile.close();
		} catch (IOException e) {
			System.err.println("创建Excel文件异常");
		} catch (RowsExceededException e) {
			System.err.println("添加数据到Excel文件时出现异常");
		} catch (WriteException e) {
			System.err.println("写入Excel文件时出现异常");
		}
	}


	/**
	 * 
	 * 为工作表添加数据
	 *
	 * @param sheet
	 * @param data
	 * @param titles
	 * @param keys
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	private void addExcelSheetRows(WritableSheet sheet, List<Map<String, Object>> data, String[] titles, String[] keys, List<SubNetInfomationBean> source)
			throws RowsExceededException, WriteException {
		if (sheet != null && !data.isEmpty() && isNotEmpty(titles) && isNotEmpty(keys)) {
			WritableCellFormat wc = new WritableCellFormat();
			int rowIndex = 1;
			int columnIndex = 0;
			for (String title : titles) {
				sheet.setColumnView(columnIndex, 25);
				sheet.addCell(new Label(columnIndex++, 0, title));
			}
			String value = null;
			for (Map<String, Object> row : data) {
				columnIndex = 0;
				if (row != null && !row.isEmpty()) {
					for (String key : keys) {
						value = isNotEmpty(row.get(key) + "") ? row.get(key).toString() : "";
						sheet.addCell(new Label(columnIndex++, rowIndex, value));
					}
					rowIndex++;
				}
			}
			Collections.sort(source, new Comparator<SubNetInfomationBean>() {
				@Override
				public int compare(SubNetInfomationBean o1,
								   SubNetInfomationBean o2) {
					return o1.getRestAdressPool() - o2.getRestAdressPool();
				}
			});
			String remark = "备注：剩余ip地址池---" + source.get(0).getRestAdressPool() + "\t网段地址为---" + IPService.getRestIP(source.get(0));
			sheet.addCell(new Label(0,rowIndex,remark, format));
			Collections.sort(source, new Comparator<SubNetInfomationBean>() {
				@Override
				public int compare(SubNetInfomationBean o1,
								   SubNetInfomationBean o2) {
					return o1.getSubNetId() - o2.getSubNetId();
				}
			});
		}
	}



	/**
	 * 
	 * 根据File创建本地Excel文件
	 *
	 * @param excelFile
	 * @return
	 * @throws IOException
	 */
	public WritableWorkbook createExcel(File excelFile) throws IOException {
		WritableWorkbook workBook = null;
		if (excelFile != null && (excelFile.exists() || (excelFile.createNewFile()))) {
			workBook = Workbook.createWorkbook(excelFile); // 建立excel文件
		}
		return workBook;
	}

	/**
	 * 根据OutputStream创建要道出Excel文件
	 * 〈功能详细描述〉
	 *
	 * @param os
	 * @return
	 * @throws IOException
	 */
	private WritableWorkbook createExcel(OutputStream os) throws IOException {
		WritableWorkbook workBook = null;
		if (os != null) {
			workBook = Workbook.createWorkbook(os); // 建立excel文件
		}
		return workBook;
	}



	private static boolean isNotEmpty(String resource) {
		return StringUtils.isNotBlank(resource) && !"null".equals(resource);
	}

	private static boolean isNotEmpty(String[] resource) {
		return resource != null && resource.length > 0;
	}


}
