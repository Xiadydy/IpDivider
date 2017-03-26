package com.gdut.ipdivider.tool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.gdut.ipdivider.entity.SubNetInfomationBean;
import com.gdut.ipdivider.entity.SubNetInfomationBeanDto;
import com.gdut.ipdivider.entity.SubNetInfomationVo;


public class IPService {

	//计算算法中的q值并复制回去
	public void setSubNetOrderQAndSortOfList(List<SubNetInfomationBeanDto> param){
		for(SubNetInfomationBeanDto dto : param){
			dto.setNeedIpCountAdd2Value(dto.getNeedIpCount() + Integer.valueOf(2));
		}
		Collections.sort(param, new Comparator<SubNetInfomationBeanDto>() {
			@Override
			public int compare(SubNetInfomationBeanDto dto1,
					SubNetInfomationBeanDto dto2) {
				return dto2.getNeedIpCountAdd2Value() - dto1.getNeedIpCountAdd2Value();
			}
			
		});
		int i = 1;
		for(SubNetInfomationBeanDto dto : param){
			dto.setOrderN(i);
			i++;
		}
	}

	//计算算法中的m值
	public void setMValueOfList(List<SubNetInfomationBeanDto> param){
		for(SubNetInfomationBeanDto dto : param){
			double a = 2;
			for(int m = 1;;m++){
				int left = (int) Math.pow(a, (double)(m-1));
				int right = (int)Math.pow(a,(double)m); 
				if(dto.getNeedIpCountAdd2Value() >= left && dto.getNeedIpCountAdd2Value() <= right){
					dto.setMValue(m);
					break;
				}
			}
		}
	}

	//设置子网掩码长度
	public void setSubMaskLengthOfSubNet(SubNetInfomationBeanDto param){
		Integer length = 32;
		param.setSubMaskLength(length - param.getMValue());
	}

	//设置网段地址
	public void setSubNetAdress(SubNetInfomationBeanDto param, String ipAdress){
		param.setSubNetAdress(ipAdress + "/" + param.getSubMaskLength());
	}

	//设置地址范围和广播地址和默认网关
	public void setNetScopeAndBoradCastAddressAndGetWay(SubNetInfomationBeanDto param){
		int[] scope = IPv4Util.getIPIntScope(param.getSubNetAdress());
		if(scope.length != 2){
			System.err.println("网址范围不合格式");
		}
		int start = scope[0];
		int end = scope[1];
		param.setBroadCastAdress(IPv4Util.intToIp(end));
		param.setDefaultGetWay(IPv4Util.intToIp(start+1));
		param.setNextStartIp(IPv4Util.intToIp(end + 1));
		String addressScope = IPv4Util.intToIp(start+1) + "~" +IPv4Util.intToIp(end-1);
		param.setSubNetScope(addressScope);
	}

	//设置地址形式的子网掩码
	public void setSubMask(SubNetInfomationBeanDto param){
		param.setMask(IPv4Util.getMask(param.getSubMaskLength()));
	}

	//设置该子网未用ip数目和已用ip数目
	public void setUnuseAddressCount(SubNetInfomationBeanDto param){
		param.setAlreadyUseConut(param.getNeedIpCount());
		int notUseCount = (int) Math.pow(2.0, (double)(param.getMValue()))-2-param.getAlreadyUseConut();
		param.setNotUseCount(notUseCount);
	}

	//设置ip剩余地址池
	public void setRestAdressPool(SubNetInfomationBeanDto param, Integer allIpCount){
		String ip = param.getSubNetAdress();
		int[] ips = IPv4Util.getIPIntScope(ip);
		int ipCount = ips[1] - ips[0] + 1;
		int result = allIpCount - ipCount ;
		param.setRestAdressPool(result);
	}

	//进行算法汇总得出结果
	public List<SubNetInfomationBeanDto> getAllFiledOfBean(SubNetInfomationVo param){
		//算法中的x
		String X = param.getIPAdress();
		String Y = param.getMask();
		Integer allIpCount = IPv4Util.getAllIpCount(X,Integer.valueOf(Y));
		System.out.println(allIpCount);
		List<SubNetInfomationBeanDto> dtos = param.getSubNet();
		setSubNetOrderQAndSortOfList(dtos);
		setMValueOfList(dtos);
		SubNetInfomationBeanDto pre = new SubNetInfomationBeanDto();
		for(SubNetInfomationBeanDto dto : dtos){
			setSubMaskLengthOfSubNet(dto);
			setSubMask(dto);
			if(dto.getOrderN() == 1){
				setSubNetAdress(dto, X);
				pre = dto;
			}else{
				setSubNetAdress(dto, pre.getNextStartIp());
				pre = dto;
			}
			setSubMask(dto);
			setNetScopeAndBoradCastAddressAndGetWay(dto);
			setUnuseAddressCount(dto);
			if(dto.getOrderN() == 1){
				setRestAdressPool(dto, allIpCount);
				allIpCount = dto.getRestAdressPool();
			}else{
				setRestAdressPool(dto, allIpCount);
				allIpCount = dto.getRestAdressPool();
			}
		}
		Collections.sort(dtos, new Comparator<SubNetInfomationBeanDto>() {
			@Override
			public int compare(SubNetInfomationBeanDto o1,
					SubNetInfomationBeanDto o2) {
				return o1.getSubNetId() - o2.getSubNetId();
			}
		});
		return dtos;
	}

	//以SubNetInfomationBean形式返回
	public List<SubNetInfomationBean> getResult(List<SubNetInfomationBeanDto> dtos){
		List<SubNetInfomationBean> result = new ArrayList<SubNetInfomationBean>();
		for(SubNetInfomationBeanDto dto : dtos){			
			SubNetInfomationBean bean = dto.convertToBean();
			result.add(bean);
		}	
		return result;
	}

	public static String getRestIP(SubNetInfomationBean param){
		int[] ip = IPv4Util.getIPIntScope(param.getSubNetAdress());
		int endIP = ip[1];
		String restIP = IPv4Util.intToIp(endIP + param.getRestAdressPool());
		String startIP = IPv4Util.intToIp(endIP + 1);
		return startIP + "~" + restIP;
	}
}
