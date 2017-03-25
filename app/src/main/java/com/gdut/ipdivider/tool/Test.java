package com.gdut.ipdivider.tool;

import com.gdut.ipdivider.entity.SubNetInfomationBean;
import com.gdut.ipdivider.entity.SubNetInfomationBeanDto;
import com.gdut.ipdivider.entity.SubNetInfomationVo;

import java.util.ArrayList;
import java.util.List;

public class Test {
	
	public static void main(String[] args) {
			SubNetInfomationVo vo = new SubNetInfomationVo();
			vo.setIPAdress("202.12.3.0");
			vo.setMask("24");
			
			List<SubNetInfomationBeanDto> dtos = new ArrayList<SubNetInfomationBeanDto>();
			SubNetInfomationBeanDto bean1 = new SubNetInfomationBeanDto();
			bean1.setSubNetId(1);
			bean1.setNeedIpCount(23);
			bean1.setSubMaskName("主机房");
			
			SubNetInfomationBeanDto bean2 = new SubNetInfomationBeanDto();
			bean2.setSubNetId(2);
			bean2.setNeedIpCount(16);
			bean2.setSubMaskName("行政办公室");
			
			SubNetInfomationBeanDto bean3 = new SubNetInfomationBeanDto();
			bean3.setSubNetId(3);
			bean3.setNeedIpCount(56);
			bean3.setSubMaskName("实验室");
			
			SubNetInfomationBeanDto bean4 = new SubNetInfomationBeanDto();
			bean4.setSubNetId(4);
			bean4.setNeedIpCount(9);
			bean4.setSubMaskName("研发中心");
			
			SubNetInfomationBeanDto bean5 = new SubNetInfomationBeanDto();
			bean5.setSubNetId(5);
			bean5.setNeedIpCount(13);
			bean5.setSubMaskName("教研室");
			
			dtos.add(bean1);
			dtos.add(bean2);
			dtos.add(bean3);
			dtos.add(bean4);
			dtos.add(bean5);
			vo.setSubNet(dtos);
			
			IPService service = new IPService();
			List<SubNetInfomationBeanDto> dtoResult = service.getAllFiledOfBean(vo);
			List<SubNetInfomationBean> result = service.getResult(dtoResult);
			for(SubNetInfomationBean bean : result){
				System.out.println(bean);
			}
//			System.out.print("请输入你的网络地址:" + "\t");
//			Scanner scan = new Scanner(System.in);
//			String net = scan.nextLine();
//			String[] str = net.split("/");
//			SubNetInfomationVo vo = new SubNetInfomationVo();
//			List<SubNetInfomationBeanDto> dtos = new  ArrayList<SubNetInfomationBeanDto>();
//			vo.setIPAdress(str[0]);
//			vo.setMask(str[1]);
//			System.out.println("请输入你的子网数：" + "\t");
//			Scanner scan1 = new Scanner(System.in);
//			int n = scan1.nextInt();
//			for(int i = 1; i<=n; i++){
//				System.out.print("请输入子网" + i + "的名字与所需ip数:");
//				Scanner s = new Scanner(System.in);
//				String in = s.nextLine();
//				String[] param = in.split(",");
//				SubNetInfomationBeanDto dto = new SubNetInfomationBeanDto();
//				dto.setSubMaskName(param[0]);
//				dto.setNeedIpCount(Integer.valueOf(param[1]));
//				dto.setSubNetId(i);
//				dtos.add(dto);
//			}
//			System.out.println("输入结束");
//			
	}
}
