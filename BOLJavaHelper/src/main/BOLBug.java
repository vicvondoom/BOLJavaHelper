package main;

import java.io.Serializable;
import java.util.Date;

public class BOLBug  implements Serializable {
	private String AppName = "";
    private String AppVersion = "";
    private String MachineName = "";
    private String Token = "";

    private int ErrNumber = 0;
    private String Description = "";
    private String StackTrace = "";
    private Date BugDate;

    private String Routine = "";
    private String OS = "";
    private String OSVersion = "";
    
    public BOLBug(String AppName, String AppVersion, String MachineName, String OS, String OSVersion) throws Exception {
    	if(AppName!="" && AppVersion!="" && MachineName!="" && OS!="" && OSVersion != "") {
 	    	this.AppName = AppName;
	    	this.AppVersion = AppVersion;
	    	this.MachineName = MachineName;
	    	this.OS = OS;
	    	this.OSVersion = OSVersion;   		
    	}
    	else {
    		throw new Exception("Constructor values must not be null or void!");
    	}

    }
    
	public String getAppName() {
		return AppName;
	}
	public void setAppName(String appName) {
		AppName = appName;
	}
	public String getAppVersion() {
		return AppVersion;
	}
	public void setAppVersion(String appVersion) {
		AppVersion = appVersion;
	}
	public String getMachineName() {
		return MachineName;
	}
	public void setMachineName(String machineName) {
		MachineName = machineName;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	public int getErrNumber() {
		return ErrNumber;
	}
	public void setErrNumber(int errNumber) {
		ErrNumber = errNumber;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getStackTrace() {
		return StackTrace;
	}
	public void setStackTrace(String stackTrace) {
		StackTrace = stackTrace;
	}
	public Date getBugDate() {
		return BugDate;
	}
	public void setBugDate(Date bugDate) {
		BugDate = bugDate;
	}
	public String getRoutine() {
		return Routine;
	}
	public void setRoutine(String routine) {
		Routine = routine;
	}
	public String getOS() {
		return OS;
	}
	public void setOS(String oS) {
		OS = oS;
	}
	public String getOSVersion() {
		return OSVersion;
	}
	public void setOSVersion(String oSVersion) {
		OSVersion = oSVersion;
	}

    
}
