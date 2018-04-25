package com.lin.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;


public class Tool {
	private static final String PATH_IMG = "/uploads/images/";		
	
	
	
	public static int ceil(int num, int size) {
		if (size == 0) {
			return 0;
		}
		int n1 = num/size;
		double n2 = num%size;
		if (n2 > 0) {
			n1 += 1;
		}
		return n1;
	}
	/**
	 * 鏂规硶鍚嶏細double2String
	 * 璇﹁堪锛歞ouble鐨勯噾棰濊浆String(瓒呰繃涓囷紝杞垚锛屽锛�3.00涓�)
	 * 鍒涘缓鏃堕棿锛�2016骞�5鏈�24鏃�
	 * @param num
	 * @return String
	 */
	public static String double2String(Double num) {
		String str = "";
		if (num >= 10000 && num < 1000000) {
			num = num * 0.0001;
			DecimalFormat df = new DecimalFormat("######0.0");  
			str = df.format(num);
			str += "涓�";
		} else if (num >= 1000000 && num < 10000000) {
			num = num * 0.000001;
			DecimalFormat df = new DecimalFormat("######0.0");  
			str = df.format(num);
			str += "鐧句竾";
		} else if (num >= 10000000) {
			num = num * 0.0000001;
			DecimalFormat df = new DecimalFormat("######0.0");  
			str = df.format(num);
			str += "鍗冧竾";
		} else {
			str = Double.toString(num);
		}
		return str;
	}
	/**
	 * 鏂规硶鍚嶏細getImgPrefix
	 * 璇﹁堪锛氳繑鍥為」鐩矾寰勶紙鍥剧墖鍓嶇紑锛夛紝鍒扮鍙ｏ紝娌℃湁/
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�5鏈�24鏃�
	 * @param request
	 * @return String
	 */
	public static String getImgPrefix(HttpServletRequest request){
		return "http://" + request.getServerName() + ":" + request.getServerPort();
	}
	
	
	
	/**
	 * 鏂规硶鍚嶏細toList
	 * 璇﹁堪锛氳浆鎹㈢粷瀵瑰湴鍧�锛岃繑鍥濴ist
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�4鏈�28鏃�
	 * @param request
	 * @param imgsStr
	 * @return List<String>
	 */
	public static List<String> toList(HttpServletRequest request,String imgsStr){
		if(Tool.isBlank(imgsStr)){
			return null;
		}
		List<String> imgsList = null;
		String[] imgsArr = imgsStr.split(",");
		if(imgsArr != null && imgsArr.length>0){
			imgsList = new ArrayList<String>();
			for(String imgStr : imgsArr){
				String img = Tool.toUrl(request, imgStr);
				if(!isBlank(img)){
					imgsList.add(img);
				}
			}
		}
		return imgsList;
	}
	
	
	/**
	 * 鏂规硶鍚嶏細toUrls
	 * 璇﹁堪锛氳浆鎹㈢粷瀵瑰湴鍧�锛岀敤閫楀彿杩炴帴鐨勬灉涓湴鍧�
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�3鏈�24鏃�
	 * @param request
	 * @param imgsStr
	 * @return String
	 */
	public static String toUrls(HttpServletRequest request, String imgsStr){
		if(!Tool.isBlank(imgsStr)){
			String[] imgs = imgsStr.split(",");
			if(imgs!=null && imgs.length>0){
				StringBuffer sb = new StringBuffer();
				for(int i=0;i<imgs.length;i++){
					sb.append(Tool.toUrl(request, imgs[i]));
					if(i<(imgs.length-1)){
						sb.append(",");
					}
				}
				return sb.toString();
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	/**
	 * 鏂规硶鍚嶏細toUrl
	 * 璇﹁堪锛氳浆鎹㈢粷瀵瑰湴鍧�
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�3鏈�24鏃�
	 * @param request
	 * @param imgUrl 鏁版嵁搴撹矾寰�
	 * @return String
	 */
	public static String toUrl(HttpServletRequest request, String imgUrl){
		if(isBlank(imgUrl)){
			return null;
		}
		if(imgUrl.contains("http")){
			return imgUrl;
		}
		return "http://" + request.getServerName() + ":" + request.getServerPort() + imgUrl;
	}
	
	/**
	 * 鏂规硶鍚嶏細isDouble
	 * 璇﹁堪锛氬垽鏂竴涓瓧绗︿覆鏄惁涓哄皬鏁�
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�3鏈�21鏃�
	 * @param str
	 * @return boolean
	 */
	public static boolean isDouble(String str){
		if(isBlank(str)){
			return false;
		}
		Pattern pattern = Pattern.compile("\\d+\\.\\d+$|-\\d+\\.\\d+$");
		Matcher m = pattern.matcher(str);
		return m.matches();
	}
	
	/**
	 * 鏂规硶鍚嶏細isDouble
	 * 璇﹁堪锛氬垽鏂涓釜瀛楃涓叉槸鍚︿负灏忔暟
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�4鏈�22鏃�
	 * @param str
	 * @param strs
	 * @return boolean
	 */
	public static boolean isDouble(String ... strs){
		for(String s : strs){
			if(!isDouble(s)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 鏂规硶鍚嶏細isNumber
	 * 璇﹁堪锛氬垽鏂竴涓瓧绗︽槸鍚︿负鏁板瓧瀛楃
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�4鏈�22鏃�
	 * @param str
	 * @return boolean
	 */
	public static boolean isNumber(String str){
		if(isBlank(str)){
			return false;
		}
		if(StringUtils.isNumeric(str)){
			return true;
		}
		return true;
	}
	
	/**
	 * 鏂规硶鍚嶏細isNumber
	 * 璇﹁堪锛氬垽鏂涓瓧绗︽槸鍚︿负鏁板瓧瀛楃
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�4鏈�22鏃�
	 * @param str
	 * @param strs
	 * @return boolean
	 */
	public static boolean isNumber(String ... strs){
		for(String s : strs){
			if(!isNumber(s)){
				return false;
			}
		}
		return true;
	}
	
	
	
	/**
	 * 鏂规硶鍚嶏細uploadImg
	 * 璇﹁堪锛氬浘鐗囦笂浼狅紙鍗曞紶锛�
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�3鏈�21鏃�
	 * @param request
	 * @param uploadFile
	 * @return String
	 */
	public static String uploadImg(HttpServletRequest request,MultipartFile uploadFile,String path){
		String pathImg = null;
		String dir = (path==null?PATH_IMG:path);
		try {
			if(uploadFile != null && uploadFile.getSize()>0){
				String saveDirStr = request.getSession().getServletContext().getRealPath(dir);
				File saveDir = new File(saveDirStr);
				saveDir.mkdirs();
				String suffix = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."));
				String newFileName =  UUID.randomUUID().toString().replace("-", "")+System.currentTimeMillis()+suffix;
		        File newFile = new File(saveDirStr,newFileName);
				newFile.createNewFile();
				uploadFile.transferTo(newFile);
				pathImg = request.getContextPath()+dir+newFileName;
				System.out.println("Tool uploadImg() : "+pathImg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pathImg;
	}
	
	/**
	 * 鏂规硶鍚嶏細uploadImg
	 * 璇﹁堪锛氬浘鐗囦笂浼狅紙澶氬紶锛夛紝杩斿洖List
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�3鏈�21鏃�
	 * @param request
	 * @param uploadFiles
	 * @return List<String>
	 */
	public static List<String> uploadImg(HttpServletRequest request,MultipartFile[] uploadFiles,String path){
		List<String> list = new ArrayList<String>();
		String dir = (path==null?PATH_IMG:path);
		try {
			if(uploadFiles != null && uploadFiles.length>0){
				for(MultipartFile uploadFile : uploadFiles){
					if(uploadFile != null && uploadFile.getSize()>0){
						String saveDirStr = request.getSession().getServletContext().getRealPath(dir);
						File saveDir = new File(saveDirStr);
						saveDir.mkdirs();
						String suffix = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."));
						String newFileName =  UUID.randomUUID().toString().replace("-", "")+System.currentTimeMillis()+suffix;
				        File newFile = new File(saveDirStr,newFileName);
						newFile.createNewFile();
						uploadFile.transferTo(newFile);
						list.add(request.getContextPath()+dir+newFileName);
						System.out.println("Tool uploadImg() : "+request.getContextPath()+dir+newFileName);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 鏂规硶鍚嶏細uploadImgs
	 * 璇﹁堪锛氬浘鐗囦笂浼狅紙澶氬紶锛夛紝杩斿洖String
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�4鏈�28鏃�
	 * @param request
	 * @param uploadFiles
	 * @return String
	 */
	public static String uploadImgs(HttpServletRequest request,MultipartFile[] uploadFiles,String path){
		List<String> imgsList = uploadImg(request, uploadFiles,(path==null?PATH_IMG:path));
		if(imgsList == null || imgsList.size()<1){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for(String img : imgsList){
			sb.append(img).append(",");
		}
		return sb.toString().substring(0, sb.toString().length()-1);
	}
	

	
	/**
	 * 鏂规硶鍚嶏細isBlank
	 * 璇﹁堪锛氬垽鏂璖tring鍙傛暟鏄惁涓虹┖鐨勬柟娉曪紝鍙傛暟鏁伴噺鍙彉锛屽鏋滃叾涓湁涓�涓弬鏁版槸绌猴紝灏辫繑鍥瀟rue
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�3鏈�21鏃�
	 * @param strs
	 * @return boolean
	 */
	public static boolean isBlank(String ... strs){
		for(String s : strs){
			if(StringUtils.isBlank(s)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNotBlank(String ... strs){
		return !isBlank(strs);
	}

	/**
	 * 鏂规硶鍚嶏細notIn
	 * 璇﹁堪锛氬垽鏂竴涓猄tring鐨勫�煎湪涓嶅湪鍙彉鍙傛暟閲岄潰,濡傛灉涓嶅湪閲岄潰锛屽氨杩斿洖true
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�3鏈�21鏃�
	 * @param str
	 * @param strs
	 * @return boolean
	 */
	public static boolean notIn(String str,String ... strs){
		for(String s : strs){
			if(s.equals(str)){
				return false;
			}
		}
		return true;
	}
	
	public static boolean in(String str,String ... strs){
		for(String s : strs){
			if(s.equals(str)){
				return true;
			}
		}
		return false;
	}
	

	/**
	 * 鏂规硶鍚嶏細random
	 * 璇﹁堪锛氶殢鏈虹敓鎴�6浣嶆暟鐨勫瓧绗︿覆
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�3鏈�21鏃�
	 * @return String
	 */
	public static String random(){
		int num = (int)((Math.random()*9+1)*100000);
		return String.valueOf(num);
	}
	
	/**
	 * 鏂规硶鍚嶏細toLength
	 * 璇﹁堪锛氳繑鍥炲皢number琛�0锛岄暱搴︿负length浣嶅悗鐨勫瓧绗�
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�4鏈�14鏃�
	 * @param number 瑕佽ˉ0鐨勬暟瀛�
	 * @param length 琛�0鍚庣殑闀垮害
	 * @return String
	 */
	public static String toLength(int number,int length){
		return String.format("%0"+length+"d", number);
	}
	
	
	
	public static String formatDate(Date date,String format){
		SimpleDateFormat sf = null;
		if(Tool.isBlank(format)){
			sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}else{
			sf = new SimpleDateFormat(format);
		}
		return sf.format(date);
	}
	
	/**
	 * 鏂规硶鍚嶏細toDate
	 * 璇﹁堪锛氭妸String杞垚Date锛孲tring鏍煎紡鈥測yyy-MM-dd HH:mm:ss鈥�
	 * 寮�鍙戜汉鍛橈細鏉庨湶鏅�
	 * 鍒涘缓鏃堕棿锛�2016骞�4鏈�15鏃�
	 * @param str
	 * @return Date
	 */
	public static Date toDate(String str){
		try{
			if(isBlank(str)){
				return null;
			}
			Date date = null;
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sf.parse(str);
			return date;
		}catch(ParseException e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 鏂规硶鍚嶏細toDate
	 * 璇﹁堪锛氭妸String杞垚Date
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�4鏈�22鏃�
	 * @param str
	 * @param format
	 * @return
	 * @throws ParseException Date
	 */
	public static Date toDate(String str,String format) throws ParseException {
		if(isBlank(str)){
			return null;
		}
		Date date = null;
		SimpleDateFormat sf = new SimpleDateFormat(format);
		date = sf.parse(str);
		return date;
	}
	
	public static Date getFirstDay(Date date) throws ParseException {
		if(date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		return calendar.getTime();
	}
	
	public static Date getLastDay(Date date) throws ParseException {
		if(date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	
	/**
	 * 鏂规硶鍚嶏細datePlu
	 * 璇﹁堪锛氳繑鍥炴棩鏈熷弬鏁� 鍔� value澶╁悗鐨凞ate
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�4鏈�22鏃�
	 * @param date
	 * @param value
	 * @return Date
	 */
	public static Date datePlu(Date date,int value){
		if(date == null || value < 1){
			return null;
		}
		Calendar calendar=Calendar.getInstance(); 
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+value);
		return calendar.getTime();
	}
	
	/**
	 * 鏂规硶鍚嶏細dateSub
	 * 璇﹁堪锛氳繑鍥炴棩鏈熷弬鏁� 鍑� value澶╁悗鐨凞ate
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�4鏈�22鏃�
	 * @param date
	 * @param value
	 * @return Date
	 */
	public static Date dateSub(Date date,int value){
		if(date == null || value < 1){
			return null;
		}
		Calendar calendar=Calendar.getInstance(); 
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-value);
		return calendar.getTime();
	}
	
	
	
	/**
	 * 鏂规硶鍚嶏細isSameDay
	 * 璇﹁堪锛氬垽鏂袱涓狣ate鏄惁鏄悓涓�澶�
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�4鏈�23鏃�
	 * @param d1
	 * @param d2
	 * @return boolean
	 */
	public static boolean isSameDay(Date d1,Date d2){
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(d1);
		cal2.setTime(d2);
		if(cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)){
			return false;
		}
		if(cal1.get(Calendar.MONTH) != cal2.get(Calendar.MONTH)){
			return false;
		}
		if(cal1.get(Calendar.DAY_OF_MONTH) != cal2.get(Calendar.DAY_OF_MONTH)){
			return false;
		}
		return true;
	}
	
	
	
	/**
	 * 鏂规硶鍚嶏細encryptString
	 * 璇﹁堪锛歁D5鍔犲瘑瀛楃涓诧紝杩斿洖鍔犲瘑鍚庡ぇ鍐�
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�2鏈�29鏃�
	 * @param str
	 * @return
	 * @throws Exception String
	 */
	public static String MD5(String str){
		try{
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] md5 = digest.digest(str.getBytes("UTF-8"));
			StringBuffer md5StringBuffer = new StringBuffer();
			String part = null;
			for (int i=0;i<md5.length;i++) {
				part = Integer.toHexString(md5[i] & 0xFF);
				if (part.length()==1) {
					part = "0"+part;
				}
				md5StringBuffer.append(part);
			}
			return md5StringBuffer.toString().toUpperCase();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 鏂规硶鍚嶏細subImg
	 * 璇﹁堪锛氬鐞咰KFinder涓婁紶鐨勫浘鐗�
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�4鏈�21鏃�
	 * @param img
	 * @return String
	 */
	public static String subImg(String img){
		if(isBlank(img)){
			return null;
		}
		String imgStr = img.replace("|", ",");
		if(imgStr.subSequence(0, 1).equals(",")){
			imgStr = imgStr.substring(1);
		}
		String[] iconArr = imgStr.split(",");
		if(iconArr != null && iconArr.length>1){
			imgStr = iconArr[0];
		}
		return imgStr;
	}
	
	/**
	 * 鏂规硶鍚嶏細subImgs
	 * 璇﹁堪锛氬鐞咰KFinder涓婁紶鐨勫浘鐗�
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�4鏈�21鏃�
	 * @param imgs
	 * @return String
	 */
	public static String subImgs(String imgs){
		if(isBlank(imgs)){
			return null;
		}
		String imgsStr = imgs.replace("|", ",");
		if(imgsStr.subSequence(0, 1).equals(",")){
			imgsStr = imgsStr.substring(1);
		}
		return imgsStr;
	}
	
	/**
	 * 鏂规硶鍚嶏細dateToString
	 * 璇﹁堪锛欴ate杞琒tring
	 * 鍒涘缓鏃堕棿锛�2016骞�5鏈�4鏃�
	 * @param date
	 * @param type
	 * @return String
	 */
	public static String dateToString(Date date, String type) {
		SimpleDateFormat sdf=new SimpleDateFormat(type); 
		String str=sdf.format(date); 
		return str;
	}

	
	/**
	 * 鏂规硶鍚嶏細sendSMS
	 * 璇﹁堪锛氬彂閫佺煭淇�
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�5鏈�30鏃�
	 * @param phone
	 * @param content void
	 */
	public static void sendSMS(String phone,String content){
		post("cdkey=8SDK-EMY-6699-RHULP&password=241781&phone="+phone+"&message=銆愪赴涔剧綉銆�"+content, "http://hprpt2.eucp.b2m.cn:8080/sdkproxy/sendsms.action");
	}
	
	/**
	 * 鏂规硶鍚嶏細post
	 * 璇﹁堪锛氬彂閫乸ost璇锋眰
	 * 寮�鍙戜汉鍛橈細鏉庡惎鍗�
	 * 鍒涘缓鏃堕棿锛�2016骞�5鏈�30鏃�
	 * @param data
	 * @param postUrl
	 * @return String
	 */
	public static String post(String data, String postUrl) {
        try { 
            //鍙戦�丳OST璇锋眰
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + data.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(data);
            out.flush();
            out.close();

            //鑾峰彇鍝嶅簲鐘舵��
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("---------------- post() : connect failed!");
                return "";
            }
            //鑾峰彇鍝嶅簲鍐呭浣�
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
	
}
