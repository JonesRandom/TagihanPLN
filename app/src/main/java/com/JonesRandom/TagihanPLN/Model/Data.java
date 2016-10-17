package com.JonesRandom.TagihanPLN.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data{
	@SerializedName("tglbacalalu")
	@Expose
	private String tglbacalalu;
	@SerializedName("terbilang")
	@Expose
	private String terbilang;
	@SerializedName("tarif")
	@Expose
	private String tarif;
	@SerializedName("tagihan")
	@Expose
	private String tagihan;
	@SerializedName("nama")
	@Expose
	private String nama;
	@SerializedName("pemkwh")
	@Expose
	private String pemkwh;
	@SerializedName("idpel")
	@Expose
	private String idpel;
	@SerializedName("tglbacaakhir")
	@Expose
	private String tglbacaakhir;
	@SerializedName("daya")
	@Expose
	private String daya;
	@SerializedName("alamat")
	@Expose
	private String alamat;

	public String getAlamat(){
		return alamat; 
	}

	public String getTglbacaakhir(){
		return tglbacaakhir; 
	}

	public void setAlamat(String alamat){
		this.alamat = alamat; 
	}

	public void setTerbilang(String terbilang){
		this.terbilang = terbilang; 
	}

	public String getNama(){
		return nama; 
	}

	public void setIdpel(String idpel){
		this.idpel = idpel; 
	}

	public void setTglbacaakhir(String tglbacaakhir){
		this.tglbacaakhir = tglbacaakhir; 
	}

	public void setPemkwh(String pemkwh){
		this.pemkwh = pemkwh; 
	}

	public void setDaya(String daya){
		this.daya = daya; 
	}

	public void setTglbacalalu(String tglbacalalu){
		this.tglbacalalu = tglbacalalu; 
	}

	public String getDaya(){
		return daya; 
	}

	public void setTagihan(String tagihan){
		this.tagihan = tagihan; 
	}

	public String getTagihan(){
		return tagihan; 
	}

	public String getTerbilang(){
		return terbilang; 
	}

	public String getPemkwh(){
		return pemkwh; 
	}

	public String getTarif(){
		return tarif; 
	}

	public void setTarif(String tarif){
		this.tarif = tarif; 
	}

	public void setNama(String nama){
		this.nama = nama; 
	}

	public String getIdpel(){
		return idpel; 
	}

	public String getTglbacalalu(){
		return tglbacalalu; 
	}

}