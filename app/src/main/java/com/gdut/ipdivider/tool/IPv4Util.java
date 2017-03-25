package com.gdut.ipdivider.tool;
import java.net.InetAddress;

public class IPv4Util {
	private final static int INADDRSZ = 4;
	/**
	 * 把IP地址转化为字节数组(暂时无用)
	 * @param ipAddr
	 * @return byte[]
	 */
	public static byte[] ipToBytesByInet(String ipAddr) {
		try {
			return InetAddress.getByName(ipAddr).getAddress();
		} catch (Exception e) {
			throw new IllegalArgumentException(ipAddr + " is invalid IP");
		}
	}
	/**
	 * 把IP地址转化为int(暂时无用)
	 * @param ipAddr
	 * @return int
	 */
	public static byte[] ipToBytesByReg(String ipAddr) {
		byte[] ret = new byte[4];
		try {
			String[] ipArr = ipAddr.split("\\.");
			ret[0] = (byte) (Integer.parseInt(ipArr[0]) & 0xFF);
			ret[1] = (byte) (Integer.parseInt(ipArr[1]) & 0xFF);
			ret[2] = (byte) (Integer.parseInt(ipArr[2]) & 0xFF);
			ret[3] = (byte) (Integer.parseInt(ipArr[3]) & 0xFF);
			return ret;
		} catch (Exception e) {
			throw new IllegalArgumentException(ipAddr + " is invalid IP");
		}
	}

	/**
	 * 字节数组转化为IP(暂时无用)
	 * @param bytes
	 * @return int
	 */
	public static String bytesToIp(byte[] bytes) {
		return new StringBuffer().append(bytes[0] & 0xFF).append('.').append(
				bytes[1] & 0xFF).append('.').append(bytes[2] & 0xFF)
				.append('.').append(bytes[3] & 0xFF).toString();
	}
	/**
	 * 根据位运算把 byte[] -> int(暂时无用)
	 * @param bytes
	 * @return int
	 */
	public static int bytesToInt(byte[] bytes) {
		int addr = bytes[3] & 0xFF;
		addr |= ((bytes[2] << 8) & 0xFF00);
		addr |= ((bytes[1] << 16) & 0xFF0000);
		addr |= ((bytes[0] << 24) & 0xFF000000);
		return addr;
	}


	/**
	 * 把IP地址转化为int(非常有用)
	 * @param ipAddr
	 * @return int
	 */
	public static int ipToInt(String ipAddr) {
		try {
			return bytesToInt(ipToBytesByInet(ipAddr));
		} catch (Exception e) {
			throw new IllegalArgumentException(ipAddr + " is invalid IP");
		}
	}

	/**
	 * ipInt -> byte[](无用)
	 * @param ipInt
	 * @return byte[]
	 */
	public static byte[] intToBytes(int ipInt) {
		byte[] ipAddr = new byte[INADDRSZ];
		ipAddr[0] = (byte) ((ipInt >>> 24) & 0xFF);
		ipAddr[1] = (byte) ((ipInt >>> 16) & 0xFF);
		ipAddr[2] = (byte) ((ipInt >>> 8) & 0xFF);
		ipAddr[3] = (byte) (ipInt & 0xFF);
		return ipAddr;
	}

	/**
	 * 把int->ip地址(有用)
	 * @param ipInt
	 * @return String
	 */
	public static String intToIp(int ipInt) {
		return new StringBuilder().append(((ipInt >> 24) & 0xff)).append('.')
				.append((ipInt >> 16) & 0xff).append('.').append(
						(ipInt >> 8) & 0xff).append('.').append((ipInt & 0xff))
						.toString();
	}
	/**
	 * 把192.168.1.1/24 转化为int数组范围
	 * @param ipAndMask
	 * @return int[]
	 */
	public static int[] getIPIntScope(String ipAndMask) {
		String[] ipArr = ipAndMask.split("/");
		if (ipArr.length != 2) {
			throw new IllegalArgumentException("invalid ipAndMask with: "
					+ ipAndMask);
		}
		int netMask = Integer.valueOf(ipArr[1].trim());
		if (netMask < 0 || netMask > 31) {
			throw new IllegalArgumentException("invalid ipAndMask with: "
					+ ipAndMask);
		}
		int ipInt = IPv4Util.ipToInt(ipArr[0]);
		int netIP = ipInt & (0xFFFFFFFF << (32 - netMask));
		int hostScope = (0xFFFFFFFF >>> netMask);
		return new int[] { netIP, netIP + hostScope };
	}
	/**
	 * 把192.168.1.1/24 转化为IP数组范围
	 * @param ipAndMask
	 * @return String[]
	 */
	public static String[] getIPAddrScope(String ipAndMask) {
		int[] ipIntArr = IPv4Util.getIPIntScope(ipAndMask);
		return new String[] { IPv4Util.intToIp(ipIntArr[0]),
				IPv4Util.intToIp(ipIntArr[1]) };
	}

	/**
	 * 根据IP 子网掩码（192.168.1.1 255.255.255.0）转化为IP段
	 * @param ipAddr ipAddr
	 * @param mask mask
	 * @return int[]
	 */
	public static int[] getIPIntScope(String ipAddr, String mask) {
		int ipInt;
		int netMaskInt = 0, ipcount = 0;
		try {
			ipInt = IPv4Util.ipToInt(ipAddr);
			if (null == mask || "".equals(mask)) {
				return new int[] { ipInt, ipInt };
			}
			netMaskInt = IPv4Util.ipToInt(mask);
			ipcount = IPv4Util.ipToInt("255.255.255.255") - netMaskInt;
			int netIP = ipInt & netMaskInt;
			int hostScope = netIP + ipcount;
			return new int[] { netIP, hostScope };
		} catch (Exception e) {
			throw new IllegalArgumentException("invalid ip scope express  ip:"
					+ ipAddr + "  mask:" + mask);
		}
	}

	/**
	 * 根据IP 子网掩码（192.168.1.1 255.255.255.0）转化为IP段
	 * @param ipAddr ipAddr
	 * @param mask mask
	 * @return String[]
	 */
	public static String[] getIPStrScope(String ipAddr, String mask) {
		int[] ipIntArr = IPv4Util.getIPIntScope(ipAddr, mask);
		return new String[] { IPv4Util.intToIp(ipIntArr[0]),
				IPv4Util.intToIp(ipIntArr[0]) };
	}


	/**
	 * 根据掩码位计算掩码
	 */
	public static String getMask(int masks)
	{
		if(masks == 1)
			return"128.0.0.0";
		else if(masks == 2)
			return"192.0.0.0";
		else if(masks == 3)
			return"224.0.0.0";
		else if(masks == 4)
			return"240.0.0.0";
		else if(masks == 5)
			return"248.0.0.0";
		else if(masks == 6)
			return"252.0.0.0";
		else if(masks == 7)
			return"254.0.0.0";
		else if(masks == 8)
			return"255.0.0.0";
		else if(masks ==9)
			return"255.128.0.0";
		else if(masks == 10)
			return"255.192.0.0";
		else if(masks == 11)
			return"255.224.0.0";
		else if(masks == 12)
			return"255.240.0.0";
		else if(masks == 13)
			return"255.248.0.0";
		else if(masks == 14)
			return"255.252.0.0";
		else if(masks == 15)
			return"255.254.0.0";
		else if(masks == 16)
			return"255.255.0.0";
		else if(masks == 17)
			return"255.255.128.0";
		else if(masks == 18)
			return"255.255.192.0";
		else if(masks == 19)
			return"255.255.224.0";
		else if(masks == 20)
			return"255.255.240.0";
		else if(masks == 21)
			return"255.255.248.0";
		else if(masks == 22)
			return"255.255.252.0";
		else if(masks == 23)
			return"255.255.254.0";
		else if(masks == 24)
			return"255.255.255.0";
		else if(masks == 25)
			return"255.255.255.128";
		else if(masks == 26)
			return"255.255.255.192";
		else if(masks == 27)
			return"255.255.255.224";
		else if(masks == 28)
			return"255.255.255.240";
		else if(masks == 29)
			return"255.255.255.248";
		else if(masks == 30)
			return"255.255.255.252";
		else if(masks == 31)
			return"255.255.255.254";
		else if(masks == 32)
			return"255.255.255.255";
		return"";
	}

	public static Integer getAllIpCount(String ip, Integer mask){
		int intIp = IPv4Util.ipToInt(ip);
		int endIp = intIp + (int)Math.pow(2.0,32-mask) - 1;
		return endIp - intIp;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String ipAddr = "192.168.8.1";
		//	        byte[] bytearr = IPv4Util.ipToBytesByInet(ipAddr);
		//	        StringBuffer byteStr = new StringBuffer();
		//	        for (byte b : bytearr) {
		//	            if (byteStr.length() == 0) {
		//	                byteStr.append(b);
		//	            } else {
		//	                byteStr.append("," + b);
		//	            }
		//	        }
		//	        System.out.println("IP: " + ipAddr + " ByInet --> byte[]: [ " + byteStr
		//	                + " ]");
		//	        bytearr = IPv4Util.ipToBytesByReg(ipAddr);
		//	        byteStr = new StringBuffer();
		//	        for (byte b : bytearr) {
		//	            if (byteStr.length() == 0) {
		//	                byteStr.append(b);
		//	            } else {
		//	                byteStr.append("," + b);
		//	            }
		//	        }
		//	        System.out.println("IP: " + ipAddr + " ByReg  --> byte[]: [ " + byteStr
		//	                + " ]");
		//	        System.out.println("byte[]: " + byteStr + " --> IP: "
		//	                + IPv4Util.bytesToIp(bytearr));
		int ipInt = IPv4Util.ipToInt(ipAddr);
		System.out.println("IP: " + ipAddr + "  --> int: " + ipInt);
		System.out.println("int: " + ipInt + " --> IP: "
				+ IPv4Util.intToIp(ipInt));
		int nextIpInt = ipInt +511 ;
		System.out.println("nextIpInt: " + nextIpInt + " --> IP: "
				+ IPv4Util.intToIp(nextIpInt));
		String ipAndMask = "202.12.3.0/26";
		int[] ipscope = IPv4Util.getIPIntScope(ipAndMask);
		System.out.println(ipAndMask + " --> int地址段：[ " + ipscope[0] + ","
				+ ipscope[1] + " ]");
		System.out.println(ipAndMask + " --> IP 地址段：[ "
				+ IPv4Util.intToIp(ipscope[0]) + ","
				+ IPv4Util.intToIp(ipscope[1]) + " ]");
		String ipAddr1 = "192.168.1.1", ipMask1 = "255.255.255.0";
		int[] ipscope1 = IPv4Util.getIPIntScope(ipAddr1, ipMask1);
		System.out.println(ipAddr1 + " , " + ipMask1 + "  --> int地址段 ：[ "
				+ ipscope1[0] + "," + ipscope1[1] + " ]");
		System.out.println(ipAddr1 + " , " + ipMask1 + "  --> IP地址段 ：[ "
				+ IPv4Util.intToIp(ipscope1[0]) + ","
				+ IPv4Util.intToIp(ipscope1[1]) + " ]");
	}

}
