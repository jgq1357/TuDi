package com.land.actions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.land.daos.AppLogDAO;
import com.land.daos.AppLogDAOImp;
import com.land.domain.AppLog;
import com.land.domain.User;
import com.land.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LandSupport extends ActionSupport implements SessionAware,ServletRequestAware{
	protected HttpServletRequest request;
	protected Map<String,Object> session;
	private Map<String,String> merrors;//存放服务器校验时的错误消息
	private AppLogDAO log = new AppLogDAOImp();
	
	/**
	 * 向集合中添加服务器校验的错误消息
	 * @param key
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	public void setErrors(String key,String value){
		ActionContext ctx = ActionContext.getContext();
		//获取context中存入的错误集合
		merrors = (Map<String,String>)ctx.get(Constant.ERRORS);
       //如果错误集合为空，则创建一个错误集合
		if(merrors == null){
			merrors = new HashMap<String,String>();
		}
       //向其中添加错误消息
		merrors.put(key, value);
		ctx.put(Constant.ERRORS, merrors);
	}
	
	/**
	 * 检查服务器端校验是否有错误消息
	 * 如果有错误消息返回true，否则返回false
	 */
	@SuppressWarnings("unchecked")
	public boolean hasErrorMessages(){
		ActionContext ctx = ActionContext.getContext();
		merrors = (Map)ctx.get(Constant.ERRORS);
		if(merrors!= null && !merrors.isEmpty()){
			return true;
		}
		return false;
	}
	
	public void logIntoDb(String ways,String context) {
		
		logIntoDb(ways, context, "");
	}
	
	public void logIntoDb(String ways,String context,String category) {
		AppLog applog = new AppLog();
		User user = (User) session.get("user");
		applog.setUserId(user.getId());
		applog.setUserName(user.getUserName());
		applog.setWays(ways);
		applog.setIp(request.getRemoteAddr());
		applog.setPcName(request.getRemoteHost());
		//applog.setoTime(TimeUtil.getCurrentTime());
		applog.setCategory(category);
		applog.setContext(context);
		log.writeToDb(applog);
	}

	@SuppressWarnings("unchecked")
	public Map getMerrors() {
		return merrors;
	}

	@SuppressWarnings("unchecked")
	public void setMerrors(Map merrors) {
		this.merrors = merrors;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
