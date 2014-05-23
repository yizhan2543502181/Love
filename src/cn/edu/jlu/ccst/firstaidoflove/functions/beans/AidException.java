package cn.edu.jlu.ccst.firstaidoflove.functions.beans;

/**
 * 
 * 接口调用异常，需要开发者进行处理
 * 
 * @author hecao (he.cao@renre-inc.com)
 * 
 */
public class AidException extends Exception
{
	private static final long	serialVersionUID	= 1L;
	private int					errorCode;
	private String				orgResponse;

	public AidException(String errorMessage)
	{
		super(errorMessage);
	}

	public AidException(int errorCode, String errorMessage, String orgResponse)
	{
		super(errorMessage);
		this.errorCode = errorCode;
		this.orgResponse = orgResponse;
	}

	public AidException(AidError error)
	{
		super(error.getMessage());
		errorCode = error.getErrorCode();
		orgResponse = error.getOrgResponse();
	}

	public String getOrgResponse()
	{
		return orgResponse;
	}

	public int getErrorCode()
	{
		return errorCode;
	}

	@Override
	public String toString()
	{
		return "AidException [errorCode=" + errorCode + ", orgResponse="
				+ orgResponse + "]";
	}
}
