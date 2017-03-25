package com.gdut.ipdivider.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 传递数据进来的bean
 * @date 2017年2月28日
 */
public class SubNetInfomationVo implements Serializable{
	
	//输入的IP地址信息
	private static String IPAdress;
	
	//输入的掩码
	private static String Mask;
	
	//用户填写的子网名称与所需ip数量都在subNetInfomationBean里面
	List<SubNetInfomationBeanDto> subNet;
	
	
	
	public List<SubNetInfomationBeanDto> getSubNet() {
		return subNet;
	}

	public void setSubNet(List<SubNetInfomationBeanDto> subNet) {
		this.subNet = subNet;
	}

	public String getIPAdress() {
		return IPAdress;
	}

	public void setIPAdress(String iPAdress) {
		IPAdress = iPAdress;
	}

	public String getMask() {
		return Mask;
	}

	public void setMask(String mask) {
		Mask = mask;
	}
	
	
}
