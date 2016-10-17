package com.JonesRandom.TagihanPLN.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query{
	@SerializedName("tahun")
	@Expose
	private String tahun;
	@SerializedName("id_pelanggan")
	@Expose
	private String id_pelanggan;
	@SerializedName("bulan")
	@Expose
	private String bulan;

	public String getBulan(){
		return bulan; 
	}

	public void setTahun(String tahun){
		this.tahun = tahun; 
	}

	public String getId_pelanggan(){
		return id_pelanggan; 
	}

	public String getTahun(){
		return tahun; 
	}

	public void setBulan(String bulan){
		this.bulan = bulan; 
	}

	public void setId_pelanggan(String id_pelanggan){
		this.id_pelanggan = id_pelanggan; 
	}

}