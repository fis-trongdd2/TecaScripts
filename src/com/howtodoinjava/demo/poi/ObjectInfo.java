package com.howtodoinjava.demo.poi;

public class ObjectInfo {
	private String ten;
	private String loai1;
	private String dbname1;
	private String server;
	private String loai2;
	private String dbname2;
	private String maBhxh;
	private String idBhxh;
	private String maTinhBv;
	private String idKhoiQl;
	private String port;

	public ObjectInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getLoai1() {
		return loai1;
	}

	public void setLoai1(String loai1) {
		this.loai1 = loai1;
	}

	public String getDbname1() {
		return dbname1;
	}

	public void setDbname1(String dbname1) {
		this.dbname1 = dbname1;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getLoai2() {
		return loai2;
	}

	public void setLoai2(String loai2) {
		this.loai2 = loai2;
	}

	public String getDbname2() {
		return dbname2;
	}

	public void setDbname2(String dbname2) {
		this.dbname2 = dbname2;
	}

	public String getMaBhxh() {
		return maBhxh;
	}

	public void setMaBhxh(String maBhxh) {
		this.maBhxh = maBhxh;
	}

	public String getIdBhxh() {
		return idBhxh;
	}

	public void setIdBhxh(String idBhxh) {
		this.idBhxh = idBhxh;
	}

	public String getMaTinhBv() {
		return maTinhBv;
	}

	public void setMaTinhBv(String maTinhBv) {
		this.maTinhBv = maTinhBv;
	}

	public String getIdKhoiQl() {
		return idKhoiQl;
	}

	public void setIdKhoiQl(String idKhoiQl) {
		this.idKhoiQl = idKhoiQl;
	}

	@Override
	public String toString() {
		return "ObjectInfo [ten=" + ten + ", loai1=" + loai1 + ", dbname1=" + dbname1 + ", server=" + server
				+ ", loai2=" + loai2 + ", dbname2=" + dbname2 + ", maBhxh=" + maBhxh + ", idBhxh=" + idBhxh
				+ ", maTinhBv=" + maTinhBv + ", idKhoiQl=" + idKhoiQl + ", port=" + port + "]";
	}

	public ObjectInfo(String ten, String loai1, String dbname1, String server, String loai2, String dbname2,
			String maBhxh, String idBhxh, String maTinhBv, String idKhoiQl, String port) {
		super();
		this.ten = ten;
		this.loai1 = loai1;
		this.dbname1 = dbname1;
		this.server = server;
		this.loai2 = loai2;
		this.dbname2 = dbname2;
		this.maBhxh = maBhxh;
		this.idBhxh = idBhxh;
		this.maTinhBv = maTinhBv;
		this.idKhoiQl = idKhoiQl;
		this.port = port;
	}

}
