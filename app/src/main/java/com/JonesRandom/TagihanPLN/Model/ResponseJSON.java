package com.JonesRandom.TagihanPLN.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseJSON{
	@SerializedName("data")
	@Expose
	private Data data;
	@SerializedName("query")
	@Expose
	private Query query;
	@SerializedName("status")
	@Expose
	private String status;

	public void setData(Data data){
		this.data = data; 
	}

	public void setStatus(String status){
		this.status = status; 
	}

	public void setQuery(Query query){
		this.query = query; 
	}

	public Data getData(){
		return data; 
	}

	public String getStatus(){
		return status; 
	}

	public Query getQuery(){
		return query; 
	}

}