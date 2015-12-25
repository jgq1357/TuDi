package com.land.inteceptor;

import java.util.Map;

import com.land.actions.user.LoginAction;
import com.land.util.Constant;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInteceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		if(LoginAction.class == arg0.getAction().getClass()){
			return arg0.invoke(); 
		}
		//
		Map<?, ?> map = arg0.getInvocationContext().getSession();//鍙栧嚭session
		  if(map.get("user") == null)//濡傛灉鐢ㄦ埛鍚嶄负绌�琛ㄧず鏈櫥褰�杩斿洖涓�釜 login
		  {
			  
		   return Constant.LOGIN_ACTION;
		  }
		  //你好啊
		   else
		  {
		//invoke鏂规硶灏辨槸璋冪敤鎴戜滑action閲岄潰瑕佽鎵ц鐨勬柟娉�
		   return arg0.invoke();
		  }
	}

}
