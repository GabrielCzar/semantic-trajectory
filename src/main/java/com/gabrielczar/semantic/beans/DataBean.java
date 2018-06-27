package com.gabrielczar.semantic.beans;

import lombok.Data;

import java.sql.Date;

@Data
public class DataBean {

    private Integer id;

    private Date dateTime;

    private Double longitude;

    private Double latitude;
}
