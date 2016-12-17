package script.old;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Replace {
	// public static final String ROOT =
	// "E:/user/trong/00.Script/00.db55/02.SoThe";
	// public static final String OLD = "1568";
	// doc thu muc lay danh sach ten file trong no
	public static List<String> readFolder(String link) {
		File folder = new File(link);
		File[] listOfFiles = folder.listFiles();
		List<String> nameFiles = new ArrayList<>();
		for (int i = 0; i < listOfFiles.length; i++) {
			nameFiles.add(listOfFiles[i].getName());
		}
		return nameFiles;
	}

	// thay the cac gia tri
	public static void replace(String link, String newStr, String OLD) throws IOException {
		Path path = Paths.get(link);
		Charset charset = StandardCharsets.UTF_8;
		String content = null;
		content = new String(Files.readAllBytes(path), charset);

		String oldStr;
		oldStr = OLD;
		content = content.replaceAll(oldStr, newStr);

		Files.write(path, content.getBytes(charset));
	}

	// tao folder moi
	public static void createNewScript(String newLink, String newStr, String old, String ROOT) throws IOException {
		File folderOld = new File(ROOT);
		File folderNew = new File(newLink);
		// copy file tu folder cu sang folder moi
		FileUtils.copyDirectory(folderOld, folderNew);

		// duyet list file, sua tung file
		List<String> listName = Replace.readFolder(newLink);
		for (String str : listName) {
			str = newLink + "/" + str;
			Replace.replace(str, newStr, old);
		}
		System.out.println("success!!!");
	}

	public static void main(String[] args) {

		try {
			Replace.createNewScript("E:/user/trong/00.Script/00.db55/sothenew", // thu
																				// muc
																				// cu
					"1521", // string cu

					"", // thu muc moi
					"" // string moi
			);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
