package base.util.linux;

/**
 * 
 * @author xus
 * 2017年11月22日下午2:01:43
 * LinuxResult.java
 */
public class LinuxResult {
	private Integer code;
	private String message;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "LinuxResult [code=" + code + ", message=" + message + "]";
	}
	
}
