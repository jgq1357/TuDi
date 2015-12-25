//package com.land.actions.xtsz;
package com.land.actions.xtsz;
import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.domain.Sys;

public class SystemConfigAction extends LandSupport{
	
	private Logger log = Logger.getLogger(SystemConfigAction.class);
	private Sys sys;
	
	public String execute() {
		//log.setLevel(sys.getLogLevel());
		//log.setLogFilePath(sys.getLogPath());
		return "success";
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}
	
	
	
	
}
