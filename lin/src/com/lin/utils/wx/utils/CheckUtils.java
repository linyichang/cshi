package com.lin.utils.wx.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpUtils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONObject;

import com.lin.utils.wx.domain.MassageDomain;
import com.thoughtworks.xstream.XStream;

public class CheckUtils {

	public static String token = "linyichang";

	private static String access_token = "IgH45Mh-X8rsDZDo-H5q6_jZzx1m76d0Nsjfny8w-xjscPnL691qT50lSOzOYORVjjs-r2LxMnZOz7_sEBGKtpeeoWQlDD1ccOpVVeOA9MkOQPcAHAUWV";

	private static String appid = "wx9f0c533c01143516";

	private static String secret = "1c92aff8b073a3c15c6de2857512de25";

	/** �����˵� */
	private static String create = "create";

	/** ɾ���˵� */
	private static String delete = "delete";

	public static String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9f0c533c01143516&redirect_uri=http://182c274u30.iask.in/lin/a/wx/wxCallback&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";

	/**
	 * ��ȡtemplate_id
	 */
	private static String template_id_url = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=";
	private static Map<String, String> xml = new HashMap<String, String>();
	public static Map<String, String> MgsToMap(HttpServletRequest request) {
		try {
			InputStream stream = request.getInputStream();
			SAXReader reader = new SAXReader();
			Document read = reader.read(stream);
			Element root = read.getRootElement();
			List<Element> elements = root.elements();
			for (Element el : elements) {
				xml.put(el.getName(), el.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}

	public static String getAccessToken(String secret, String appid) {
		String src = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
		StringBuffer data = new StringBuffer();
		try {
			URL url = null;
			HttpURLConnection http = null;
			url = new URL(src);
			http = (HttpURLConnection) url.openConnection();
			http.setDoInput(true);
			http.setDoOutput(true);
			http.setUseCaches(false);
			http.setConnectTimeout(50000);// �������ӳ�ʱ
			http.setReadTimeout(50000);// ���ö�ȡ��ʱ
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/json; encoding=utf-8");
			http.connect();
			InputStream inputStream = http.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader bu = new BufferedReader(reader);
			String le;
			while ((le = bu.readLine()) != null) {
				data.append(le);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject(data.toString());

		return json.get("access_token").toString();
	}

//	public static void main(String[] args) {
//		// String openId = "osHf-1ag8SguRRRFubTsj98LudAc";
//		// String context = "你好吗，哈哈哈哈哈，恭喜发财啊！";
//		// sendInfoOew(openId,context);
//		// findtempIdlate();
//		createMean(delete);
//		createMean(create);
//	}

	/**
	 * ��ȡ��ά��
	 * 
	 * @return
	 */
	public static String Erweima() {
		String src = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + getAccessToken(secret, appid);
		System.out.println(getAccessToken(secret, appid));
		String datas = "{'expire_seconds': 1800, 'action_name': 'QR_STR_SCENE', 'action_info': {'scene': {'scene_id': 123}}}";
		StringBuffer data = new StringBuffer();
		try {
			URL url = null;
			HttpURLConnection http = null;
			url = new URL(src);
			http = (HttpURLConnection) url.openConnection();
			http.setDoInput(true);
			http.setDoOutput(true);
			http.setUseCaches(false);
			http.setConnectTimeout(50000);// �������ӳ�ʱ
			http.setReadTimeout(50000);// ���ö�ȡ��ʱ
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/json; encoding=utf-8");
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(new JSONObject(datas).toString().getBytes("UTF-8"));

			InputStream inputStream = http.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader bu = new BufferedReader(reader);
			String le;
			while ((le = bu.readLine()) != null) {
				data.append(le);
			}
			bu.close();
			reader.close();
			inputStream.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject(data.toString());
		System.out.println(json);
		return json.getString("ticket");
	}

	/**
	 * ��ȡ��ά��
	 */
	public static void getewm() {
		String src = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + Erweima();
		try {
			URL url = null;
			HttpURLConnection http = null;
			url = new URL(src);
			http = (HttpURLConnection) url.openConnection();
			http.setDoInput(true);
			http.setDoOutput(true);
			http.setUseCaches(false);
			http.setConnectTimeout(50000);// �������ӳ�ʱ
			http.setReadTimeout(50000);// ���ö�ȡ��ʱ
			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type", "application/json; encoding=utf-8");
			http.connect();
			InputStream inputStream = http.getInputStream();
			FileOutputStream outputStream = new FileOutputStream(new File("d://er//ewm.jpg"));
			int a = 0;
			byte[] ty = new byte[1024];
			while ((a = inputStream.read(ty)) != -1) {
				outputStream.write(ty, 0, a);
			}
			outputStream.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("�ɹ���");
	}

	public static boolean CheckSinginUtil(String signature, String timestamp, String nonce) {
		String[] arr = new String[]{token, timestamp, nonce};
		// ����
		Arrays.sort(arr);
		// �����ַ���
		StringBuffer context = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {
			context.append(arr[i]);
		}
		// sh����
		String tm = getSha1(context.toString());
		return signature.equals(tm);

	}

	/**
	 * sha1����
	 * 
	 * @param str
	 * @return
	 */
	public static String getSha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public static String objToXml(Object obj) {
		XStream xml = new XStream();
		xml.alias("xml", obj.getClass());
		String xml2 = xml.toXML(obj);
		return xml2;
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	public static String sendInfo(Map<String, String> map) {
		MassageDomain massage = new MassageDomain();
		massage.setFromUserName(map.get("ToUserName"));
		massage.setToUserName(map.get("FromUserName"));
		massage.setCreateTime(new Date().getTime() + "");
		massage.setMsgType("text");
		if (map.get("Content") != null) {
			massage.setContent(map.get("Content"));
		} else {
			massage.setContent("��ӭ��ע����������޹�˾���ٷ����ںţ�");
		}

		String objToXml = objToXml(massage);
		return objToXml;
	}

	public static String filerString() {

		FileReader fr = null;
		StringBuffer buffer = new StringBuffer();
		try {
			String url = "d://mean.txt";
			fr = new FileReader(url);
			// ����read�������������̨��
			int len = 0;
			try {
				while ((len = fr.read()) != -1) {
					buffer.append((char) len);

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return buffer.toString();
	}

	public static void createMean(String type) {
		StringBuffer json = new StringBuffer();
		try {
			String filtUrl = "";
			String getUrl = "https://api.weixin.qq.com/cgi-bin/menu/" + type + "?access_token=" + getAccessToken(secret, appid);
			URL url = null;
			HttpURLConnection http = null;
			url = new URL(getUrl);
			http = (HttpURLConnection) url.openConnection();
			http.setDoInput(true);
			http.setDoOutput(true);
			http.setUseCaches(false);
			http.setConnectTimeout(50000);// �������ӳ�ʱ
			http.setReadTimeout(50000);// ���ö�ȡ��ʱ
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/json; encoding=utf-8");
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(filerString().getBytes("UTF-8"));

			InputStream inputStream = http.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String len = "";
			while ((len = bufferedReader.readLine()) != null) {
				json.append(len);

			}
			bufferedReader.close();
			reader.close();
			inputStream.close();
			os.flush();
			os.close();
			JSONObject jsonObject = new JSONObject(json.toString());
			if ("create".equals(type)) {
				if ("ok".equals(jsonObject.get("errmsg").toString())) {
					System.out.println("���ɲ˵��ɹ���" + jsonObject);
				} else {
					System.out.println("���ɲ˵�ʧ�ܣ�" + jsonObject);
				}
			} else {
				if ("ok".equals(jsonObject.get("errmsg").toString())) {
					System.out.println("ɾ���˵��ɹ���" + jsonObject);
				} else {
					System.out.println("ɾ���˵�ʧ�ܣ�" + jsonObject);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 通过openid获取用户信息
	 */
	public static String getUserInfo(String openid) {
		String src = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + getAccessToken(secret, appid) + "&openid=" + openid + "&lang=zh_CN";
		StringBuffer json = new StringBuffer();
		try {

			URL url = null;
			HttpURLConnection http = null;
			url = new URL(src);
			http = (HttpURLConnection) url.openConnection();
			http.setDoInput(true);
			http.setDoOutput(true);
			http.setUseCaches(false);
			http.setConnectTimeout(50000);
			http.setReadTimeout(50000);
			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type", "application/json; encoding=utf-8");
			http.connect();
			InputStream inputStream = http.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String len = "";
			while ((len = bufferedReader.readLine()) != null) {
				json.append(len);

			}
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}

	private static String toutf_8(String val) {
		System.out.println(getEncoding(val));
		String value = null;
		try {
			value = new String(val.getBytes("UTF-8"), "gbk");
		} catch (Exception e) {
			// TODO: handle exception
		}

		return value;
	}

	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { // �ж��ǲ���GB2312
				String s = encode;
				return s; // �ǵĻ������ء�GB2312�������´���ͬ��
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { // �ж��ǲ���ISO-8859-1
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { // �ж��ǲ���UTF-8
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { // �ж��ǲ���GBK
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return ""; // ��������ǣ�˵����������ݲ����ڳ����ı����ʽ��
	}

	public static void getsave(String urls) {
		try {
			StringBuffer json = new StringBuffer();
			HttpURLConnection http = null;
			URL url = new URL(urls);
			http = (HttpURLConnection) url.openConnection();
			http.setDoInput(true);
			http.setDoOutput(true);
			http.setUseCaches(false);
			http.setConnectTimeout(50000);// �������ӳ�ʱ
			http.setReadTimeout(50000);// ���ö�ȡ��ʱ
			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type", "application/json; encoding=utf-8");
			http.connect();
			InputStream inputStream = http.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String len = "";
			while ((len = bufferedReader.readLine()) != null) {
				json.append(len);

			}
			inputStream.close();
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡtempIdlate
	 */
	public static void findtempIdlate() {
		String json = "{\"template_id_short\":\"TM00015\"}";
		connectWeiXinInterface(template_id_url + getAccessToken(secret, appid), json);
	}

	/**
	 * ����΢����Ϣ
	 * 
	 * @param openid
	 * @param context
	 */
	public static void sendInfoOew(String userOpenid, String context) {
		String json = "{\"touser\": \"" + userOpenid + "\",\"msgtype\": \"text\", \"text\": {\"content\": \"" + context + "\"}}";
		String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + getAccessToken(secret, appid);
		connectWeiXinInterface(action, json);

	}

	/**
	 * Ⱥ����Ϣ
	 */
	public static void sendInfoMsaa(String userOpenid, String context) {
		String json = "{\"touser\": \"" + userOpenid + "\",\"msgtype\": \"text\", \"text\": {\"content\": \"" + context + "\"}}";
		String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + getAccessToken(secret, appid);
		connectWeiXinInterface(action, json);
	}

	public static void connectWeiXinInterface(String action, String json) {

		URL url;

		try {

			url = new URL(action);

			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("POST");

			http.setRequestProperty("Content-Type",

			"application/x-www-form-urlencoded");

			http.setDoOutput(true);

			http.setDoInput(true);

			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// ���ӳ�ʱ30��

			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // ��ȡ��ʱ30��

			http.connect();

			OutputStream os = http.getOutputStream();

			os.write(json.getBytes("UTF-8"));// �������

			InputStream is = http.getInputStream();

			int size = is.available();

			byte[] jsonBytes = new byte[size];

			is.read(jsonBytes);

			String result = new String(jsonBytes, "UTF-8");

			System.out.println("发送状态:" + result);

			os.flush();

			os.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public static HttpURLConnection httpURLConnection(String url) {
		URL delurl = null;
		HttpURLConnection http = null;
		try {
			delurl = new URL(url);
			http = (HttpURLConnection) delurl.openConnection();
			http.setDoInput(true);
			http.setDoOutput(true);
			http.setUseCaches(false);
			http.setConnectTimeout(50000);
			http.setReadTimeout(50000);
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/json; encoding=utf-8");
			http.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return http;
	}

	public static JSONObject getAccessTokenByCode(String code) {
		String src = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + secret + "&code=" + code + "&grant_type=authorization_code";
		StringBuffer data = new StringBuffer();
		try {
			HttpURLConnection http = httpURLConnection(src);
			InputStream inputStream = http.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader bu = new BufferedReader(reader);
			String le;
			while ((le = bu.readLine()) != null) {
				data.append(le);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject(data.toString());
		return json;
	}

}
