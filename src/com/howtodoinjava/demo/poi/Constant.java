package com.howtodoinjava.demo.poi;

import java.io.IOException;

public class Constant {

	public static final String NEW_DIRECTORY = "E:/SCRIPTT/";
	public static final String ROOT = "E:/user/trong/script/40.DC/10.Script/00.Script/00.db55";
	public static final String EXCEL_FILE = "map_daifix.xlsx";
	public static final String TRUNGGIAN = "10.0.120.13";

	
	
	
		// link sms goc
	public static final String ROOT_SMS = ROOT + "/01.Thu";
	// link sms so the goc
	public static final String ROOT_SOTHE = ROOT + "/02.SoThe";

	// db sms trong file root
	public static final String MIS = "_MIS";

	// db sms so the trong file root
	public static final String MIS_SOTHE = "_MIS_SOTHE";

	// mabhxh
	public static final String MA_BHXH = "00109";

	// dm_bhxh_id
	public static final String ID_BHXH = "77";

	// lay dm_bhxh_id trong dm_khoi_ql
	public static final String ID_BHXH_KHOI = "2";

	// lay dm_dbhc_id trong de map dmbenhvien : day la ma_tinh trong map_dbhc
	public static final String MA_TINH_BV = "01";

	// hosname dung chung
	public static final String HOST_NAME = "_HOST_NAME";

	// port cua db mis
	public static final String PORT = "_PORT";
	
	//server trunggian-
	public static final String SERVER_TRUNGGIAN = "_DBTRUNGGIAN";
	
	
	
	public static void main(String[] args) {
		try {
			Script.generateAllScripts(Constant.EXCEL_FILE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
