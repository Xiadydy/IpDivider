package com.gdut.ipdivider.entity;

import java.io.*;

public class SubnetEntity implements Serializable
{
    private static final long serialVersionUID = 6742L;
    private int ipBlockCount;
    private int ipCount;
    private String ipRange;
    private String mask;
    private String name;
    private String segment;

    public int getIpBlockCount() {
        return this.ipBlockCount;
    }

    public int getIpCount() {
        return this.ipCount;
    }

    public String getIpRange() {
        return this.ipRange;
    }

    public String getMaskNum() {
        return this.mask;
    }

    public String getName() {
        return this.name;
    }

    public int getNoUseIPCount() {
        return this.getIpBlockCount() - this.getIpCount() - 2;
    }

    public String getSegment() {
        return this.segment;
    }

    public void setIpBlockCount(final int ipBlockCount) {
        this.ipBlockCount = ipBlockCount;
    }

    public void setIpCount(final int ipCount) {
        this.ipCount = ipCount;
    }

    public void setIpRange(final String s, final String s2) {
        this.ipRange = String.valueOf(s) + "~" + s2;
    }

    public void setMaskNum(final String mask) {
        this.mask = mask;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setSegment(final String s, final String s2) {
        this.segment = String.valueOf(s) + "/" + s2;
    }
}
