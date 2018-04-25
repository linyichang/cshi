package com.lin.contorller.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.resizers.BilinearResizer;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.google.gson.Gson;
import com.lin.contorller.wx.form.UserInfo;
import com.lin.entity.User;
import com.lin.entity.WxUser;
import com.lin.utils.Info.ApiConstance;
import com.lin.utils.Info.BaseController;
import com.lin.utils.Info.BaseResult;
import com.lin.utils.wx.domain.Massage;
import com.lin.utils.wx.domain.MassageDomain;
import com.lin.utils.wx.utils.CheckUtils;
import com.lin.utils.wx.utils.SignUtil;
import com.test.Test;
import com.test.Excel.FilePreview;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Api(value = "wx", description = "微信接入")
@Controller
@RequestMapping(value = "/a/wx")
public class WachetApi extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(WachetApi.class);

	@ApiOperation(value = "微信接入", notes = "200", httpMethod = "get")
	@ApiResponses({@ApiResponse(code = 400, message = "接入成功")})
	@RequestMapping(value = "/contactTheCustomer.do", method = RequestMethod.GET)
	public void wachatGet(HttpServletRequest request, HttpServletResponse response) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		PrintWriter out;
		try {
			System.out.println("成功接入！");
			out = response.getWriter();
			if (CheckUtils.CheckSinginUtil(signature, timestamp, nonce)) {
				out.print(echostr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * 
     * @开发人员 lyc<p>
     * @创建时间 2018年4月25日<p>
     * @详述 wachatGetPost(@param request
     * @详述 wachatGetPost(@param response)<p>
     * @param request
     * @param response
     */
	@ApiOperation(value = "微信回调", notes = "200", httpMethod = "post")
	@ApiResponses({@ApiResponse(code = 200, message = "回调成功")})
	@RequestMapping(value = "/contactTheCustomer.do", method = RequestMethod.POST)
	public void wachatGetPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			Map<String, String> mgsToMap = CheckUtils.MgsToMap(request);
			MassageDomain fromJson = new Gson().fromJson(mgsToMap.toString(), MassageDomain.class);
			if (fromJson.getMsgType().equals("text")) {
				if (fromJson.getContent().endsWith("天气")) {
					Test test = new Test();
					String TQ = test.WXqueryTQByOne(fromJson.getContent().substring(0, (fromJson.getContent().length() - 2)));
					CheckUtils.sendInfoOew(fromJson.getFromUserName(), TQ);
				} else {
					response.getWriter().append(Massage.sendMassage(fromJson.getFromUserName(), fromJson.getToUserName(), "你需要什么服务呢？"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * 
     * @开发人员 lyc<p>
     * @创建时间 2018年4月25日<p>
     * @详述 wxCallback(@param request
     * @详述 wxCallback(@param model
     * @详述 wxCallback(@return)<p>
     * @param request
     * @param model
     * @return
     */
	@RequestMapping(value = "/wxCallback", method = {RequestMethod.GET, RequestMethod.POST})
	public String wxCallback(HttpServletRequest request, Model model) {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		JSONObject accessTokenByCode = CheckUtils.getAccessTokenByCode(code);
		if (accessTokenByCode.opt("openid") == null) {
			return "redirect:/a/wx/shouquan";
		}
		String user = CheckUtils.getUserInfo(accessTokenByCode.get("openid").toString());
		UserInfo userInfo = new Gson().fromJson(user, UserInfo.class);
		model.addAttribute("userInfo", userInfo);

		return returnPath("/wx/userInfo");
	}

	/**
	 * 
	 * @开发人员 lyc<p>
	 * @创建时间 2018年4月25日<p>
	 * @详述 weiche(@return)<p>
	 * @return
	 */
	@RequestMapping(value = "/shouquan", method = {RequestMethod.GET, RequestMethod.POST})
	public String weiche() {
		return "redirect:" + CheckUtils.url;
	}

	@RequestMapping(value = "/TQ", method = {RequestMethod.GET})
	public ResponseEntity<BaseResult> queryTq() {
		return buildSuccessResultInfo1(Test.queryTQ("湛江"));
	}

}
