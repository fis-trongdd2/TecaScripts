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

public class Thu {
	public static final String ROOT = "D:/Setup/script_convert/document/40.DC/10.Script/daotao/28.VinhLong/02.thanhpho/01.thu";
	public static final String MA_BHXH = "08601";
	public static final String ID_BHXH = "741";
	public static final String MIS_BHXH = "MISBHXH_01";
	public static final String ID_BHXH_KHOI = "56";
	public static final String MA_TINH_BV = "86";
	public static final String HOST_NAME = "10.86.254.135";
	
	//doc thu muc lay danh sach ten file trong no
	public static List<String> readFolder (String link) {
		File folder = new File(link);
		File[] listOfFiles = folder.listFiles();
		List<String> nameFiles = new ArrayList<>();
		for (int i = 0; i < listOfFiles.length; i++) {
			nameFiles.add(listOfFiles[i].getName()) ;
		}
		return nameFiles;
	}
	
	//thay the cac gia tri 
	public static void replace (String link,
			String newMaDvi,
			String newBhxhId,
			String newMis,
			String newHost,
			String newIdKhoi,
			String newMaTinhBv
			   ) throws IOException {
		Path path = Paths.get(link);
		Charset charset = StandardCharsets.UTF_8;
		String content = null;
		content = new String(Files.readAllBytes(path), charset);
		
		String oldMaDvi;
		oldMaDvi = "&#x27;" + MA_BHXH + "&#x27;" ;
		newMaDvi = "&#x27;" + newMaDvi+ "&#x27;" ;
		content = content.replaceAll(oldMaDvi,newMaDvi);
		
		String oldBhxhId;
		oldBhxhId = "WHERE DM_BHXH_ID&#x3d;&#x27;" + ID_BHXH +"&#x27";
		newBhxhId = "WHERE DM_BHXH_ID&#x3d;&#x27;" + newBhxhId +"&#x27";
		content = content.replaceAll(oldBhxhId,newBhxhId);

		String oldMis;
		oldMis = "<database>" + MIS_BHXH;
		newMis = "<database>" + newMis;
		content = content.replaceAll(oldMis,newMis);
		
		String oldHost;
		oldHost =  HOST_NAME;
		content = content.replaceAll(oldHost,newHost);
		
		
		String oldIdKhoi;
		oldIdKhoi = "WHERE DM_BHXH_ID IN &#x28;1," + ID_BHXH_KHOI +"&#x29";
		newIdKhoi = "WHERE DM_BHXH_ID IN &#x28;1," + newIdKhoi    +"&#x29";
		content = content.replaceAll(oldIdKhoi,newIdKhoi);
		
		//OR MA_TINH IS NULL THEN  &#x27;01&#x27;
		String oldMaTinhBv;
		oldMaTinhBv = "OR MA_TINH IS NULL THEN  &#x27;"+  MA_TINH_BV + "&#x27";
		newMaTinhBv = "OR MA_TINH IS NULL THEN  &#x27;"+  newMaTinhBv + "&#x27";
		content = content.replaceAll(oldMaTinhBv,newMaTinhBv);
				
		String oldMaTinh,newMaTinh; // trong dmsobhxh capsobhxh
		oldMaTinh = "&#x27;" + MA_TINH_BV +" &#x27; MA_TINH";
		newMaTinh = "&#x27;" + newMaTinhBv +" &#x27; MA_TINH";
		content = content.replaceAll(oldMaTinh,newMaTinh);
		
		
		Files.write(path, content.getBytes(charset));
	}
	
	//tao folder moi
	public static void createNewScript (
			String newLink,
			String newMaDvi,
			String newBhxhId,
			String newMis,
			String newHost,
			String newIdKhoi,
			String newMaTinhBv    
			) throws IOException {
		File folderOld = new File(ROOT);
		File folderNew = new File(newLink);
		//copy file tu folder cu sang folder moi
		FileUtils.copyDirectory(folderOld, folderNew);
		
		//duyet list file, sua tung file
		List<String> listName =  Thu.readFolder(newLink);
		for (String str : listName) {
			str = newLink + "/" + str; 
			Thu.replace(str, newMaDvi, newBhxhId, newMis, newHost,newIdKhoi,newMaTinhBv );
		}
		System.out.println("success!!!");
	}
	public static void main (String []args) {
		
		try {
			Thu.createNewScript(
					"D:/Setup/script_convert/document/40.DC/10.Script/daotao/ThanhHoa/02.thanhpho/01.thu",	//directory
					"03801",   					//mabhxh
					  "361",		 			//id bhxh
					   "MISBHXH_THANHPHO",						//sqlserver
					  "10.38.240.113",					//host name
					  "27",							//khoi ql
					  "38"										//ma bv	
					   );						
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
} 
	
 
	
