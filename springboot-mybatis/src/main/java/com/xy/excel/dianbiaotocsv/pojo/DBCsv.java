package com.xy.excel.dianbiaotocsv.pojo;

/**
 * Created by xiayun on 2017/6/2.
 */
public class DBCsv {
    private String plcLabel;
    private String label;
    private String type;
    private String description;
    private String channel;

    public DBCsv(String plcLabel, String label, String type, String description, String channel) {
        this.plcLabel = plcLabel;
        this.label = label;
        this.type = type;
        this.description = description;
        this.channel = channel;
    }

    public String getPlcLabel() {

        return plcLabel;
    }

    public void setPlcLabel(String plcLabel) {
        this.plcLabel = plcLabel;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
