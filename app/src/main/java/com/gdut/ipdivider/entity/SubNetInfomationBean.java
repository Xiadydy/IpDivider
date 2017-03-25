package com.gdut.ipdivider.entity;

import java.io.Serializable;

/**
 * 
 * 返回数据的bean
 * @date 2017年2月28日
 */
public class SubNetInfomationBean implements Serializable{
	
	//按填写子网编号 第一个填写的为1 递增
	private Integer subNetId;
	
	//子网别名 
	private String subMaskName;
	
	//所需ip数量
	private Integer needIpCount;
	
	//网段地址
	private String subNetAdress;
	
	//范围
	private String subNetScope;
	
	//子网
	private String mask;
	
	//已使用数目
	private Integer alreadyUseConut;
	
	//未使用数目
	private Integer notUseCount;
	
	//推荐默认网关
	private String defaultGetWay;
	
	//广播地址
	private	String broadCastAdress;
	
	
	//总体剩余地址池大小
	private int restAdressPool;
	
	
	public int getRestAdressPool() {
		return restAdressPool;
	}

	public void setRestAdressPool(int restAdressPool) {
		this.restAdressPool = restAdressPool;
	}

	public String getBroadCastAdress() {
		return broadCastAdress;
	}

	public void setBroadCastAdress(String broadCastAdress) {
		this.broadCastAdress = broadCastAdress;
	}

	public String getDefaultGetWay() {
		return defaultGetWay;
	}

	public void setDefaultGetWay(String defaultGetWay) {
		this.defaultGetWay = defaultGetWay;
	}

	public Integer getSubNetId() {
		return subNetId;
	}

	public void setSubNetId(Integer subNetId) {
		this.subNetId = subNetId;
	}

	public String getSubMaskName() {
		return subMaskName;
	}

	public void setSubMaskName(String subMaskName) {
		this.subMaskName = subMaskName;
	}

	public Integer getNeedIpCount() {
		return needIpCount;
	}

	public void setNeedIpCount(Integer needIpCount) {
		this.needIpCount = needIpCount;
	}

	public String getSubNetAdress() {
		return subNetAdress;
	}

	public void setSubNetAdress(String subNetAdress) {
		this.subNetAdress = subNetAdress;
	}

	public String getSubNetScope() {
		return subNetScope;
	}

	public void setSubNetScope(String subNetScope) {
		this.subNetScope = subNetScope;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public Integer getAlreadyUseConut() {
		return alreadyUseConut;
	}

	public void setAlreadyUseConut(Integer alreadyUseConut) {
		this.alreadyUseConut = alreadyUseConut;
	}

	public Integer getNotUseCount() {
		return notUseCount;
	}

	public void setNotUseCount(Integer notUseCount) {
		this.notUseCount = notUseCount;
	}

	@Override
	public String toString() {
		return "SubNetInfomationBean [subNetId=" + subNetId + ", subMaskName="
				+ subMaskName + ", needIpCount=" + needIpCount
				+ ", subNetAdress=" + subNetAdress + ", subNetScope="
				+ subNetScope + ", mask=" + mask + ", alreadyUseConut="
				+ alreadyUseConut + ", notUseCount=" + notUseCount
				+ ", defaultGetWay=" + defaultGetWay + ", broadCastAdress="
				+ broadCastAdress + ", restAdressPool=" + restAdressPool + "]";
	}
	
	
	
	
}
