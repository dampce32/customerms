package org.linys.util;

import org.apache.commons.lang.StringUtils;
import org.linys.vo.GlobalConstants;

public class StringUtil {
	/**
	 * @Description: 将字符串source根据separator分割成字符串数组
	 * @Create: 2013-1-8 下午2:28:10
	 * @author lys
	 * @update logs
	 * @param source
	 * @param separator
	 * @return
	 */
	public static String[] split(String source,String separator){
		String[] distArray ={};
		if(source==null){
			return null;
		}
		int i = 0;
		distArray = new String[StringUtils.countMatches(source, separator)+1];
		while(source.length()>0){
			String value = StringUtils.substringBefore(source,separator);
			distArray[i++] = StringUtils.isEmpty(value)?null:value;
			source = StringUtils.substringAfter(source,separator);
		}
		if(distArray[distArray.length-1]==null){//排除最后一个分隔符后放空
			distArray[distArray.length-1] = null;
		}
		return distArray;
	}
	/**
	 * @Description: 将字符串source根据全局变量GobelConstants.SPLIT_SEPARATOR分割成字符串数组
	 * @Create: 2013-1-15 上午12:06:52
	 * @author lys
	 * @update logs
	 * @param source
	 * @return
	 */
	public static String[] split(String source){
		return split(source,GlobalConstants.SPLIT_SEPARATOR);
	}
	
	/**
	 * @Description: 将字符串source根据separator分割成Integer数组
	 * @Created Time: 2013-3-4 下午10:15:04
	 * @Author lys
	 * @param source
	 * @param separator
	 * @return
	 */
	public static Integer[] splitToInteger(String source,String separator){
		Integer[] distArray ={};
		if(StringUtils.isEmpty(source)){
			return distArray;
		}
		int i = 0;
		distArray = new Integer[StringUtils.countMatches(source, separator)+1];
		while(source.length()>0){
			String value = StringUtils.substringBefore(source,separator);
			distArray[i++] = StringUtils.isEmpty(value)?null:Integer.parseInt(value);
			source = StringUtils.substringAfter(source,separator);
		}
		if(distArray[distArray.length-1]==null){//排除最后一个分隔符后放空
			distArray[distArray.length-1] = null;
		}
		return distArray;
	}
	/**
	 * @Description: 将字符串source根据全局变量GobelConstants.SPLIT_SEPARATOR分割成Integer数组
	 * @Create: 2013-1-15 上午12:06:52
	 * @author lys
	 * @update logs
	 * @param source
	 * @return
	 */
	public static Integer[] splitToInteger(String source){
		return splitToInteger(source,GlobalConstants.SPLIT_SEPARATOR);
	}
	
	/**
	 * @Description: 将字符串source根据separator分割成Integer数组，并提出其中的Null值
	 * @Created Time: 2013-3-4 下午10:15:04
	 * @Author lys
	 * @param source
	 * @param separator
	 * @return
	 */
	public static Integer[] splitToIntegerTrimNull(String source,String separator){
		Integer[] distArray ={};
		if(source==null||source.length()==0){
			return distArray;
		}
		int i = 0;
		distArray = new Integer[StringUtils.countMatches(source, separator)+1];
		while(source.length()>0){
			String value = StringUtils.substringBefore(source,separator);
			if(StringUtils.isNotEmpty(value)){
				distArray[i++] = Integer.parseInt(value);
			}
			source = StringUtils.substringAfter(source,separator);
		}
		if(distArray[distArray.length-1]==null){//排除最后一个分隔符后放空
			distArray[distArray.length-1] = null;
		}
		return distArray;
	}
	/**
	 * @Description: 将字符串source根据全局变量GobelConstants.SPLIT_SEPARATOR分割成Integer数组，并剔除Null值
	 * @Create: 2013-1-15 上午12:06:52
	 * @author lys
	 * @update logs
	 * @param source
	 * @return
	 */
	public static Integer[] splitToIntegerTrimNull(String source){
		return splitToIntegerTrimNull(source,GlobalConstants.SPLIT_SEPARATOR);
	}
	
	/**
	 * @Description: 将字符串source根据separator分割成Boolean数组
	 * @Created Time: 2013-3-4 下午10:15:04
	 * @Author lys
	 * @param source
	 * @param separator
	 * @return
	 */
	public static Boolean[] splitToBoolean(String source,String separator){
		Boolean[] distArray ={};
		if(source==null){
			return distArray;
		}
		int i = 0;
		distArray = new Boolean[StringUtils.countMatches(source, separator)+1];
		while(source.length()>0){
			String value = StringUtils.substringBefore(source,separator);
			distArray[i++] = StringUtils.isEmpty(value)?null:Boolean.parseBoolean(value);
			source = StringUtils.substringAfter(source,separator);
		}
		if(distArray[distArray.length-1]==null){//排除最后一个分隔符后放空
			distArray[distArray.length-1] = null;
		}
		return distArray;
	}
	/**
	 * @Description: 将字符串source根据全局变量GobelConstants.SPLIT_SEPARATOR分割成Boolean数组
	 * @Create: 2013-1-15 上午12:06:52
	 * @author lys
	 * @update logs
	 * @param source
	 * @return
	 */
	public static Boolean[] splitToBoolean(String source){
		return splitToBoolean(source,GlobalConstants.SPLIT_SEPARATOR);
	}
	
	/**
	 * @Description: 将字符串source根据separator分割成Boolean数组
	 * @Created Time: 2013-3-4 下午10:15:04
	 * @Author lys
	 * @param source
	 * @param separator
	 * @return
	 */
	public static Double[] splitToDouble(String source,String separator){
		Double[] distArray ={};
		if(source==null){
			return distArray;
		}
		int i = 0;
		distArray = new Double[StringUtils.countMatches(source, separator)+1];
		while(source.length()>0){
			String value = StringUtils.substringBefore(source,separator);
			distArray[i++] = StringUtils.isEmpty(value)?null:Double.parseDouble(value);
			source = StringUtils.substringAfter(source,separator);
		}
		if(distArray[distArray.length-1]==null){//排除最后一个分隔符后放空
			distArray[distArray.length-1] = null;
		}
		return distArray;
	}
	/**
	 * @Description: 将字符串source根据全局变量GobelConstants.SPLIT_SEPARATOR分割成Double数组
	 * @Create: 2013-1-15 上午12:06:52
	 * @author lys
	 * @update logs
	 * @param source
	 * @return
	 */
	public static Double[] splitToDouble(String source){
		return splitToDouble(source,GlobalConstants.SPLIT_SEPARATOR);
	}
	/**
	 * @Description: 将null等空Empty情况变为空字符串
	 * @Created Time: 2013-5-9 上午9:20:22
	 * @Author lys
	 * @param note
	 * @return
	 */
	public static String getEmptyToBlank(String source) {
		if(StringUtils.isEmpty(source)){
			return "";
		}
		return source;
	}
	
	/**
	 * @Description: 切割code，取得父code
	 * @Created: 2013-9-20 下午4:23:01
	 * @Author lys
	 * @param code
	 * @param itemLength
	 * @return
	 */
	public static String[] splitParentCode(String code,Integer itemLength){
		if(StringUtils.isEmpty(code)||code.length()%itemLength!=0){
			return null;
		}
		String[] itemArray = new String[code.length()/itemLength];
		for (int i = 0; i < itemArray.length; i++) {
			itemArray[i]= code.substring(0, itemLength+itemLength*i);
		}
		return itemArray;
	}
	
	public static void main(String[] args) {
		String[] codeArray = StringUtil.splitParentCode("0101", 3);
		for (String string : codeArray) {
			System.out.println(string);
		}
	}
	public static Float[] splitToFloat(String source) {
		return splitToFloat(source,GlobalConstants.SPLIT_SEPARATOR);
	}
	
	/**
	 * @Description: 将字符串source根据separator分割成Boolean数组
	 * @Created Time: 2013-3-4 下午10:15:04
	 * @Author lys
	 * @param source
	 * @param separator
	 * @return
	 */
	public static Float[] splitToFloat(String source,String separator){
		Float[] distArray ={};
		if(source==null){
			return distArray;
		}
		int i = 0;
		distArray = new Float[StringUtils.countMatches(source, separator)+1];
		while(source.length()>0){
			String value = StringUtils.substringBefore(source,separator);
			distArray[i++] = StringUtils.isEmpty(value)?null:Float.parseFloat(value);
			source = StringUtils.substringAfter(source,separator);
		}
		if(distArray[distArray.length-1]==null){//排除最后一个分隔符后放空
			distArray[distArray.length-1] = null;
		}
		return distArray;
	}
	
}
