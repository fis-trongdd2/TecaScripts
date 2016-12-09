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
			String newHost, String newIdKhoi, String newMaTinhBv, int type) throws IOException {
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
		String oldMaTinhBv;
		oldMaTinhBv = "OR MA_TINH IS NULL THEN  &#x27;" + Constant.MA_TINH_BV + "&#x27";
		newMaTinhBv = "OR MA_TINH IS NULL THEN  &#x27;" + newMaTinhBv + "&#x27";
		content = content.replaceAll(oldMaTinhBv, newMaTinhBv);

		String oldMaTinh, newMaTinh; // trong dmsobhxh capsobhxh
		oldMaTinh = "&#x27;" + Constant.MA_TINH_BV + " &#x27; MA_TINH";
		newMaTinh = "&#x27;" + newMaTinhBv + " &#x27; MA_TINH";
		content = content.replaceAll(oldMaTinh, newMaTinh);

		Files.write(path, content.getBytes(charset));
	}

	// dua vao link
	private static void createNewScript(String newLink, String newMaDvi, String newBhxhId, String newMis,
			String newPort, String newHost, String newIdKhoi, String newMaTinhBv, int type) throws IOException {
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
			Script.replace(str, newMaDvi, newBhxhId, newMis, newPort, newHost, newIdKhoi, newMaTinhBv, type);
		}
		System.out.println("success!!!");
	}

	public static void generateAllScripts(String link) throws IOException {
		ReadExcelToList test = new ReadExcelToList(link);
		for (Map.Entry<String, ObjectInfo> temp : test.getMapObjects().entrySet()) {
			createNewScript(Constant.NEW_DIRECTORY + temp.getKey() + "/" + temp.getValue().getLoai1(),
					temp.getValue().getMaBhxh(), temp.getValue().getIdBhxh(), temp.getValue().getDbname1(),
					temp.getValue().getPort(), temp.getValue().getServer(), temp.getValue().getIdKhoiQl(),
					temp.getValue().getMaTinhBv(), 1);
			createNewScript(Constant.NEW_DIRECTORY + temp.getKey() + "/" + temp.getValue().getLoai2(),
					temp.getValue().getMaBhxh(), temp.getValue().getIdBhxh(), temp.getValue().getDbname2(),
					temp.getValue().getPort(), temp.getValue().getServer(), temp.getValue().getIdKhoiQl(),
					temp.getValue().getMaTinhBv(), 2);
		}

	}
}
