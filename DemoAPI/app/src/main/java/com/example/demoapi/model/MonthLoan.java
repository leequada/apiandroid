package com.example.demoapi.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MonthLoan {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("month")
@Expose
private Integer month;
@SerializedName("ammount")
@Expose
private Double ammount;
@SerializedName("status")
@Expose
private Integer status;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getMonth() {
return month;
}

public void setMonth(Integer month) {
this.month = month;
}

public Double getAmmount() {
return ammount;
}

public void setAmmount(Double ammount) {
this.ammount = ammount;
}

public Integer getStatus() {
return status;
}

public void setStatus(Integer status) {
this.status = status;
}

}