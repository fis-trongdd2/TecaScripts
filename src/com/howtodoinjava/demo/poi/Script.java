package com.howtodoinjava.demo.poi;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class Script {
	// doc tat ca cac file trong 1 folder luu 1 list ten file
	private static List<String> readFolder(String link) {
		File folder = new File(link);
		File[] listOfFiles = folder.listFiles();
		List<String> nameFiles = new ArrayList<>();
		for (int i = 0; i < listOfFiles.length; i++) {
			nameFiles.add(listOfFiles[i].getName());
		}
		return nameFiles;
	}

	// thay the cac gia tri
	private static void replace(String link, String newMaDvi, String newBhxhId, String newMis, String newPort,
			String newHost, String newIdKhoi, String newMaTinhBv, String instance, int type) throws IOException {
		Path path = Paths.get(link);
		Charset charset = StandardCharsets.UTF_8;
		String content = null;
		content = new String(Files.readAllBytes(path), charset);

		String oldMaDvi;
		oldMaDvi = "&#x27;" + Constant.MA_BHXH + "&#x27;";
		newMaDvi = "&#x27;" + newMaDvi + "&#x27;";
		content = content.replaceAll(oldMaDvi, newMaDvi);

		String oldBhxhId;
		oldBhxhId = "WHERE DM_BHXH_ID&#x3d;&#x27;" + Constant.ID_BHXH + "&#x27";
		newBhxhId = "WHERE DM_BHXH_ID&#x3d;&#x27;" + newBhxhId + "&#x27";
		content = content.replaceAll(oldBhxhId, newBhxhId);

		String oldMis;
		if (type == 1) {
			oldMis = "<database>" + Constant.MIS;
		} else {
			oldMis = "<database>" + Constant.MIS_SOTHE;
		}
		newMis = "<database>" + newMis;
		content = content.replaceAll(oldMis, newMis);

		String oldPort;
		oldPort = ">" + Constant.PORT + "<";
		newPort = ">" + newPort + "<";
		content = content.replaceAll(oldPort, newPort);

		String oldHost;
		oldHost = Constant.HOST_NAME;
		content = content.replaceAll(oldHost, newHost);

		String oldIdKhoi;
		oldIdKhoi = "WHERE DM_BHXH_ID IN &#x28;1," + Constant.ID_BHXH_KHOI + "&#x29";
		newIdKhoi = "WHERE DM_BHXH_ID IN &#x28;1," + newIdKhoi + "&#x29";
		content = content.replaceAll(oldIdKhoi, newIdKhoi);

		// OR MA_TINH IS NULL THEN &#x27;01&#x27;
		String oldMaTinhBv, maTinhBv;
		oldMaTinhBv = "OR MA_TINH IS NULL THEN  &#x27;" + Constant.MA_TINH_BV + "&#x27";
		maTinhBv = "OR MA_TINH IS NULL THEN  &#x27;" + newMaTinhBv + "&#x27";
		content = content.replaceAll(oldMaTinhBv, maTinhBv);

		// tronginthe &#x27;01&#x27;--fix_dbhc
		String old_ma_tinh_inthe, ma_tinh_inthe;
		old_ma_tinh_inthe = "&#x27;" + Constant.MA_TINH_BV + "&#x27;--fix_dbhc";
		ma_tinh_inthe = "&#x27;" + newMaTinhBv + "&#x27;--fix_dbhc";
		content = content.replaceAll(old_ma_tinh_inthe, ma_tinh_inthe);

		// &#x27;01&#x27; DM_TINH_DBHC
		// DM_TINH_DBHC trong inthe
		String old_ma_tinh2_inthe, ma_tinh2_inthe;
		old_ma_tinh2_inthe = "&#x27;" + Constant.MA_TINH_BV + "&#x27; DM_TINH_DBHC";
		ma_tinh2_inthe = "&#x27;" + newMaTinhBv + "&#x27; DM_TINH_DBHC";
		content = content.replaceAll(old_ma_tinh2_inthe, ma_tinh2_inthe);

		String oldMaTinh, capsoMaTinh; // trong dmsobhxh capsobhxh
		oldMaTinh = "&#x27;" + Constant.MA_TINH_BV + " &#x27; MA_TINH";
		capsoMaTinh = "&#x27;" + newMaTinhBv + " &#x27; MA_TINH";
		content = content.replaceAll(oldMaTinh, capsoMaTinh);
		String oldInstace, newInstance;
			oldInstace = "<attribute><code>EXTRA_OPTION_MSSQL.instance</code><attribute>_INSTANCE</attribute></attribute>";
			newInstance = "<attribute><code>EXTRA_OPTION_MSSQL.instance</code><attribute>"+instance+"</attribute></attribute>";
			content = content.replaceAll(oldInstace, newInstance);
		
		// fix trung gian
		String old_trunggian, new_trunggian;
		old_trunggian = Constant.SERVER_TRUNGGIAN;
		new_trunggian = Constant.TRUNGGIAN;
		content = content.replaceAll(old_trunggian, new_trunggian);

		Files.write(path, content.getBytes(charset));
	}

	// dua vao link
	private static void createNewScript(String newLink, String newMaDvi, String newBhxhId, String newMis,
			String newPort, String newHost, String newIdKhoi, String newMaTinhBv, String newInstance, int type)
			throws IOException {
		String oldLink;
		if (type == 1) {
			oldLink = Constant.ROOT_SMS;
		} else {
			oldLink = Constant.ROOT_SOTHE;
		}
		File folderOld = new File(oldLink);
		File folderNew = new File(newLink);
		// copy file tu folder cu sang folder moi
		FileUtils.copyDirectory(folderOld, folderNew);

		// duyet list file, sua tung file
		List<String> listName = Script.readFolder(newLink);
		for (String str : listName) {
			str = newLink + "/" + str;
			Script.replace(str, newMaDvi, newBhxhId, newMis, newPort, newHost, newIdKhoi, newMaTinhBv, newInstance,
					type);
		}
		System.out.println("success!!!");
	}

	public static void generateAllScripts(String link) throws IOException {
		ReadExcelToList test = new ReadExcelToList(link);
		for (Map.Entry<String, ObjectInfo> temp : test.getMapObjects().entrySet()) {
			createNewScript(Constant.NEW_DIRECTORY + temp.getKey() + "/" + temp.getValue().getLoai1(),
					temp.getValue().getMaBhxh(), temp.getValue().getIdBhxh(), temp.getValue().getDbname1(),
					temp.getValue().getPort(), temp.getValue().getServer(), temp.getValue().getIdKhoiQl(),
					temp.getValue().getMaTinhBv(), temp.getValue().getInstatnce(), 1);
			createNewScript(Constant.NEW_DIRECTORY + temp.getKey() + "/" + temp.getValue().getLoai2(),
					temp.getValue().getMaBhxh(), temp.getValue().getIdBhxh(), temp.getValue().getDbname2(),
					temp.getValue().getPort(), temp.getValue().getServer(), temp.getValue().getIdKhoiQl(),
					temp.getValue().getMaTinhBv(), temp.getValue().getInstatnce(), 2);
		}

	}
}
