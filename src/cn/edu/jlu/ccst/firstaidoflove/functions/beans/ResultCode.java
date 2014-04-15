package cn.edu.jlu.ccst.firstaidoflove.functions.beans;

import org.json.JSONObject;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class ResultCode implements Parcelable
{
	public static String	KEY_RESULT_CODE	= "result_code";
	private int				resultCode		= -1;

	public ResultCode()
	{
		// TODO Auto-generated constructor stub
	}

	public ResultCode(int resultCode)
	{
		super();
		this.resultCode = resultCode;
	}

	public int getResultCode()
	{
		return resultCode;
	}

	public void setResultCode(int resultCode)
	{
		this.resultCode = resultCode;
	}

	public ResultCode parse(JSONObject object) throws AidException
	{
		if (object == null)
		{
			return null;
		}
		resultCode = object.optInt(ResultCode.KEY_RESULT_CODE);
		return this;
	}

	@Override
	public int describeContents()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		Bundle bundle = new Bundle();
		if (-1 != resultCode)
		{
			bundle.putLong(ResultCode.KEY_RESULT_CODE, resultCode);
		}
		dest.writeBundle(bundle);
	}

	public static final Parcelable.Creator<ResultCode>	CREATOR	= new Parcelable.Creator<ResultCode>() {
																	@Override
																	public ResultCode createFromParcel(
																			Parcel in)
																	{
																		return new ResultCode(
																				in);
																	}

																	@Override
																	public ResultCode[] newArray(
																			int size)
																	{
																		return new ResultCode[size];
																	}
																};

	public ResultCode(Parcel in)
	{
		Bundle bundle = in.readBundle();
		if (bundle.containsKey(ResultCode.KEY_RESULT_CODE))
		{
			resultCode = bundle.getInt(ResultCode.KEY_RESULT_CODE);
		}
	}
}
