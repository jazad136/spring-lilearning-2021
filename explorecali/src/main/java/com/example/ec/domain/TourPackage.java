package com.example.ec.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;

public class TourPackage {
	@Id
	private String code;
	
	@Column
	private String name;
	
	protected TourPackage() {  }
	
	
	public TourPackage(String code, String name) {
		this.code = code;
		this.name = name;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "TourPackage {"
				+   "code='" + code + "'"
				+ ", name='" + name + "'"
				+ "}";
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourPackage that = (TourPackage) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(name, that.name);
    }
	@Override
	public int hashCode() { return Objects.hash(code, name); }
}
