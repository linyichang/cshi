package com.lin.sys.utils;

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
	private static final String PATH_IMG = "/uploads/images/";		//上传图片默认保存路径
	
	
	
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
	 * 方法名：double2String
	 * 详述：double的金额转String(超过万，转成，如：3.00万)
	 * 创建时间：2016年5月24日
	 * @param num
	 * @return String
	 */
	public static String double2String(Double num) {
		String str = "";
		if (num >= 10000 && num < 1000000) {
			num = num * 0.0001;
			DecimalFormat df = new DecimalFormat("######0.0");  
			str = df.format(num);
			str += "万";
		} else if (num >= 1000000 && num < 10000000) {
			num = num * 0.000001;
			DecimalFormat df = new DecimalFormat("######0.0");  
			str = df.format(num);
			str += "百万";
		} else if (num >= 10000000) {
			num = num * 0.0000001;
			DecimalFormat df = new DecimalFormat("######0.0");  
			str = df.format(num);
			str += "千万";
		} else {
			str = Double.toString(num);
		}
		return str;
	}
	/**
	 * 方法名：getImgPrefix
	 * 详述：返回项目路径（图片前缀），到端口，没有/
	 * 开发人员：李启华
	 * 创建时间：2016年5月24日
	 * @param request
	 * @return String
	 */
	public static String getImgPrefix(HttpServletRequest request){
		return "http://" + request.getServerName() + ":" + request.getServerPort();
	}
	
	
	
	/**
	 * 方法名：toList
	 * 详述：转换绝对地址，返回List
	 * 开发人员：李启华
	 * 创建时间：2016年4月28日
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
	 * 方法名：toUrls
	 * 详述：转换绝对地址，用逗号连接的果个地址
	 * 开发人员：李启华
	 * 创建时间：2016年3月24日
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
	 * 方法名：toUrl
	 * 详述：转换绝对地址
	 * 开发人员：李启华
	 * 创建时间：2016年3月24日
	 * @param request
	 * @param imgUrl 数据库路径
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
	 * 方法名：isDouble
	 * 详述：判断一个字符串是否为小数
	 * 开发人员：李启华
	 * 创建时间：2016年3月21日
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
	 * 方法名：isDouble
	 * 详述：判断多个个字符串是否为小数
	 * 开发人员：李启华
	 * 创建时间：2016年4月22日
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
	 * 方法名：isNumber
	 * 详述：判断一个字符是否为数字字符
	 * 开发人员：李启华
	 * 创建时间：2016年4月22日
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
	 * 方法名：isNumber
	 * 详述：判断多个字符是否为数字字符
	 * 开发人员：李启华
	 * 创建时间：2016年4月22日
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
	 * 方法名：uploadImg
	 * 详述：图片上传（单张）
	 * 开发人员：李启华
	 * 创建时间：2016年3月21日
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
	 * 方法名：uploadImg
	 * 详述：图片上传（多张），返回List
	 * 开发人员：李启华
	 * 创建时间：2016年3月21日
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
	 * 方法名：uploadImgs
	 * 详述：图片上传（多张），返回String
	 * 开发人员：李启华
	 * 创建时间：2016年4月28日
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
	 * 方法名：isBlank
	 * 详述：判断String参数是否为空的方法，参数数量可变，如果其中有一个参数是空，就返回true
	 * 开发人员：李启华
	 * 创建时间：2016年3月21日
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
	 * 方法名：notIn
	 * 详述：判断一个String的值在不在可变参数里面,如果不在里面，就返回true
	 * 开发人员：李启华
	 * 创建时间：2016年3月21日
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
	 * 方法名：random
	 * 详述：随机生成6位数的字符串
	 * 开发人员：李启华
	 * 创建时间：2016年3月21日
	 * @return String
	 */
	public static String random(){
		int num = (int)((Math.random()*9+1)*100000);
		return String.valueOf(num);
	}
	
	/**
	 * 方法名：toLength
	 * 详述：返回将number补0，长度为length位后的字符
	 * 开发人员：李启华
	 * 创建时间：2016年4月14日
	 * @param number 要补0的数字
	 * @param length 补0后的长度
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
	 * 方法名：toDate
	 * 详述：把String转成Date，String格式“yyyy-MM-dd HH:mm:ss”
	 * 开发人员：李露晶
	 * 创建时间：2016年4月15日
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
	 * 方法名：toDate
	 * 详述：把String转成Date
	 * 开发人员：李启华
	 * 创建时间：2016年4月22日
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
	 * 方法名：datePlu
	 * 详述：返回日期参数 加 value天后的Date
	 * 开发人员：李启华
	 * 创建时间：2016年4月22日
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
	 * 方法名：dateSub
	 * 详述：返回日期参数 减 value天后的Date
	 * 开发人员：李启华
	 * 创建时间：2016年4月22日
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
	 * 方法名：isSameDay
	 * 详述：判断两个Date是否是同一天
	 * 开发人员：李启华
	 * 创建时间：2016年4月23日
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
	 * 方法名：encryptString
	 * 详述：MD5加密字符串，返回加密后大写
	 * 开发人员：李启华
	 * 创建时间：2016年2月29日
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
	 * 方法名：subImg
	 * 详述：处理CKFinder上传的图片
	 * 开发人员：李启华
	 * 创建时间：2016年4月21日
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
	 * 方法名：subImgs
	 * 详述：处理CKFinder上传的图片
	 * 开发人员：李启华
	 * 创建时间：2016年4月21日
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
	 * 方法名：dateToString
	 * 详述：Date转String
	 * 创建时间：2016年5月4日
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
	 * 方法名：sendSMS
	 * 详述：发送短信
	 * 开发人员：李启华
	 * 创建时间：2016年5月30日
	 * @param phone
	 * @param content void
	 */
	public static void sendSMS(String phone,String content){
		post("cdkey=8SDK-EMY-6699-RHULP&password=241781&phone="+phone+"&message=【丰乾网】"+content, "http://hprpt2.eucp.b2m.cn:8080/sdkproxy/sendsms.action");
	}
	
	/**
	 * 方法名：post
	 * 详述：发送post请求
	 * 开发人员：李启华
	 * 创建时间：2016年5月30日
	 * @param data
	 * @param postUrl
	 * @return String
	 */
	public static String post(String data, String postUrl) {
        try { 
            //发送POST请求
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

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("---------------- post() : connect failed!");
                return "";
            }
            //获取响应内容体
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
