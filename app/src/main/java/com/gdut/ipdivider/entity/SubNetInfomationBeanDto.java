package com.gdut.ipdivider.entity;

public class SubNetInfomationBeanDto extends SubNetInfomationBean {
	
	private static final long serialVersionUID = 1L;

	//需要ip数量加2，需要根据他来排序
	private Integer needIpCountAdd2Value;
	
	//算法中的m值
	private Integer mValue;
	
	//子网掩码长度
	private Integer subMaskLength;
	
	//根据需要ip地址排序的编号
	private Integer orderN;
	
	//设置下一个子网开始的ip地址
	private String nextStartIp;
	
	
	public String getNextStartIp() {
		return nextStartIp;
	}

	public void setNextStartIp(String nextStartIp) {
		this.nextStartIp = nextStartIp;
	}

	public Integer getOrderN() {
		return orderN;
	}

	public void setOrderN(Integer orderN) {
		this.orderN = orderN;
	}

	public Integer getSubMaskLength() {
		return subMaskLength;
	}

	public void setSubMaskLength(Integer subMaskLength) {
		this.subMaskLength = subMaskLength;
	}

	public void setNeedIpCountAdd2Value(Integer needIpCountAdd2Value) {
		this.needIpCountAdd2Value = needIpCountAdd2Value;
	}
	
	public Integer getNeedIpCountAdd2Value() {
		return needIpCountAdd2Value;
	}

	public void setMValue(Integer mValue) {
		this.mValue = mValue;
	}

	public Integer getMValue(){
		return this.mValue;
	}
	
	public SubNetInfomationBean convertToBean(){
		SubNetInfomationBean bean = new SubNetInfomationBean();
		bean.setSubMaskName(getSubMaskName());
		bean.setSubNetId(getSubNetId());
		bean.setNeedIpCount(getNeedIpCount());
		bean.setSubNetAdress(getSubNetAdress());
		bean.setMask(getMask());
		bean.setSubNetScope(getSubNetScope());
		bean.setBroadCastAdress(getBroadCastAdress());
		bean.setDefaultGetWay(getDefaultGetWay());
		bean.setAlreadyUseConut(getAlreadyUseConut());
		bean.setNotUseCount(getNotUseCount());
		bean.setRestAdressPool(getRestAdressPool());
		return bean;
	}
	
	
	@Override
	public String toString() {
		return "NeedIpCount: " + this.getNeedIpCount() + "\tNeedIpCountAdd2Value: "
				+ this.getNeedIpCountAdd2Value() + "\tMValue: " + this.getMValue();
	}

	public static void main(String[] args) {
//		List<SubNetInfomationBeanDto> dtos = new ArrayList<SubNetInfomationBeanDto>();
//		SubNetInfomationBeanDto bean1 = new SubNetInfomationBeanDto();
//		bean1.setSubNetId(1);
//		bean1.setNeedIpCount(23);
//		dtos.add(bean1);
//		SubNetInfomationBeanDto bean2 = new SubNetInfomationBeanDto();
//		bean2.setSubNetId(2);
//		bean2.setNeedIpCount(16);
//		SubNetInfomationBeanDto bean3 = new SubNetInfomationBeanDto();
//		bean3.setSubNetId(3);
//		bean3.setNeedIpCount(56);
//		SubNetInfomationBeanDto bean4 = new SubNetInfomationBeanDto();
//		bean4.setSubNetId(4);
//		bean4.setNeedIpCount(9);
//		SubNetInfomationBeanDto bean5 = new SubNetInfomationBeanDto();
//		bean5.setSubNetId(5);
//		bean5.setNeedIpCount(13);
//		dtos.add(bean2);
//		dtos.add(bean3);
//		dtos.add(bean4);
//		dtos.add(bean5);
//		IPService service = new IPService();
//		service.setSubNetOrderQAndSortOfList(dtos);
//		service.setMValueOfList(dtos);
//		System.out.println(dtos);
		SubNetInfomationBeanDto dto = new SubNetInfomationBeanDto();
		dto.setMValue(5);
		dto.setOrderN(2);
		IPService service = new IPService();
		service.setSubMaskLengthOfSubNet(dto);
		service.setSubNetAdress(dto, "202.12.3.0");
		System.out.println(dto.getSubNetAdress());
	}


	
}
