package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.lin.contorller.wx.form.TQdata;
import com.lin.contorller.wx.form.TQforecast;
import com.lin.contorller.wx.form.TQform;
import com.lin.contorller.wx.form.TQyesterday;

public class Test {

	public static String get(String url) {
		String result = "";
		try {

			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpGet httpGet = new HttpGet(url);

			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {

				if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					System.out.println(response.getStatusLine());
					HttpEntity entity = response.getEntity();

					result = readResponse(entity, "utf-8");
				}
			} finally {
				httpclient.close();
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static String readResponse(HttpEntity resEntity, String charset) {
		StringBuffer res = new StringBuffer();
		BufferedReader reader = null;
		try {
			if (resEntity == null) {
				return null;
			}

			reader = new BufferedReader(new InputStreamReader(resEntity.getContent(), charset));
			String line = null;

			while ((line = reader.readLine()) != null) {
				res.append(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
			}
		}
		return res.toString();
	}

	/**
	 * 
	 * @开发人员 lyc<p>
	 * @创建时间 2018年1月31日<p>
	 * @详述 queryTQ(@param ctiy
	 * @详述 queryTQ(@return)<p>
	 * @param ctiy
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static TQform queryTQ(String ctiy) {
		String str = get("http://www.sojson.com/open/api/weather/json.shtml?city=" + ctiy);
		System.out.println(str);
		JSONObject fromObject = new JSONObject().fromObject(str);
		if(fromObject.opt("status") != null) {
			if(fromObject.opt("status").equals("304")) {
				TQform qform = new TQform();
				qform.setStatus("304");
				return qform;
			}
		} else {
			TQform qform = new TQform();
			qform.setStatus("404");
			return qform;
		}
		TQform fromJson = new TQform();
		Object opt = fromObject.opt("data");
		TQdata qdata = new TQdata();
		if (opt != null) {
			Object data = fromObject.opt("data");
			if (data != null) {
				JSONObject dataform = new JSONObject().fromObject(data);
				qdata.setPm10(dataform.opt("pm10").toString());
				qdata.setPm25(dataform.opt("pm25").toString());
				qdata.setQuality(dataform.opt("quality").toString());
				qdata.setShidu(dataform.opt("shidu").toString());
				qdata.setWendu(dataform.opt("wendu").toString());
				qdata.setGanmao(dataform.opt("ganmao").toString());
				JSONArray forecasts = new JSONArray().fromObject(dataform.opt("forecast").toString());
				List<TQforecast> qforecasts = new ArrayList<TQforecast>();
				TQyesterday qyesterday = new Gson().fromJson(dataform.opt("yesterday").toString(), TQyesterday.class);
				for (Object object : forecasts) {
					TQforecast qforecast = new Gson().fromJson(object.toString(), TQforecast.class);
					qforecasts.add(qforecast);
				}
				qdata.setForecast(qforecasts);
				qdata.setYesterday(qyesterday);
			}
		}
		fromJson.setCity(fromObject.opt("city").toString());
		fromJson.setCount(fromObject.opt("count").toString());
		fromJson.setDate(fromObject.opt("date").toString());
		fromJson.setMessage(fromObject.opt("message").toString());
		fromJson.setStatus(fromObject.opt("status").toString());
		fromJson.setData(qdata);
		return fromJson;
	}

	/**
	 * 
	 * @开发人员 lyc<p>
	 * @创建时间 2018年1月31日<p>
	 * @详述 WXqueryTQ(@param ctiy
	 * @详述 WXqueryTQ(@return)<p>
	 * @param ctiy
	 * @return
	 */
	public static String WXqueryTQByOne(String ctiy){
		TQform queryTQ = queryTQ(ctiy);
		if(queryTQ.getStatus().equals("404")) {
			return null;
		}
		if(queryTQ.getStatus().equals("304")) {
			return null;
		}
		List<TQforecast> forecast = queryTQ.getData().getForecast();
		StringBuffer TQ = new StringBuffer();
		for (TQforecast tQforecast : forecast) {
			TQ.append(queryTQ.getCity()+" 天气\r\n");
			TQ.append(tQforecast.getDate()+"\r\n");
			TQ.append(tQforecast.getHigh()+"\r\n");
			TQ.append(tQforecast.getLow()+"\r\n");
			TQ.append(tQforecast.getFx()+"  "+tQforecast.getFl()+"\r\n");
			TQ.append(tQforecast.getType()+"\r\n");
			TQ.append("提示："+queryTQ.getData().getGanmao()+"\r\n");
			TQ.append(tQforecast.getNotice()+"\r\n================================\r\n");
		}
		return TQ.toString();
	}
	
	/**
	 * 
	 * @开发人员 lyc<p>
	 * @创建时间 2018年2月6日<p>
	 * @详述 queryTQJson(@return)<p>
	 * @return
	 */
	public static Map<String, String> queryTQJson(){
		
		Map<String, String> map = new HashMap<String, String>();
		TQform queryTQ = queryTQ("广州");
		StringBuffer time = new StringBuffer();	
		StringBuffer high = new StringBuffer();
		StringBuffer low = new StringBuffer();
		List<TQforecast> forecast = queryTQ.getData().getForecast();
		time.append("[");
		high.append("[");
		low.append("[");
		for (TQforecast tQforecast : forecast) {
			time.append("'"+tQforecast.getDate()+"',");
			high.append(tQforecast.getHigh().replaceAll("高温", "").replaceAll("℃", "").toString()+",");
			low.append(tQforecast.getLow().replaceAll("低温 ", "").replaceAll("℃", "").toString()+",");
		}
		time.append("]");
		high.append("]");
		low.append("]");
		map.put("time", time.toString());
		map.put("high", high.toString());
		map.put("low", low.toString());
		return map;
	}

}