package cn.edu.jlu.ccst.firstaidoflove.functions.beans.friends;

import android.os.Bundle;
import cn.edu.jlu.ccst.firstaidoflove.functions.RequestParam;
import cn.edu.jlu.ccst.firstaidoflove.functions.beans.AidException;

/**
 * 
 * @author wangchangshuai(415939252@qq.com)
 * 
 *         friends.search接口请求参数封装
 * 
 */
public class FriendsSearchRequestParam extends RequestParam
{
	private static final String METHOD = "friends.search";
	/**
	 * 搜索姓名（name和uid只需写一个）
	 */
	private String name = null;
	/**
	 * 搜索人人网ID（name和uid只需写一个）
	 */
	private Long uid = null;
	/**
	 * string 查询条件。json格式如：[{"t":"base","gend":"男生","prov":"天津"},{"t":"high","year":"2000"},{"t":"univ","name":"东北大学"}] “t”搜索条件类型，第个类型包含的条件如下： 1. “t”:”base” 基本信息，”gend”性别，”prov”省份，"city"城市 2. "t":"univ" 大学，” name”大学名称，"id"大学的id见主站，"depa"学院，"year"入学年份 3. "t":"high" 高中，"name"高中名称，"year"入学年份 4. "t":"sect"中专技校，"name"名称，"year"入学年份 5. "t":"juni"初中，"name"初中名称，"year":入学年份 6. "t":"elem"小学，"name"小学称，"year"入学年份 7. "t":"birt"生日，"year"年，"month"月，"day"日，"astr"星座 8. "t":"work"公司，"name"公司名称
	 */
	private String condition = null;
	/**
	 * 分页显示的页码 缺省值为1
	 */
	private int page = -1;
	/**
	 * 每页显示的日志的数量, 缺省值为20
	 */
	private int count = -1;

	/**
	 * 默认的请求参数
	 */
	public FriendsSearchRequestParam()
	{}

	public FriendsSearchRequestParam(String name, Long uid, String condition,
			int page, int count)
	{
		super();
		this.name = name;
		this.uid = uid;
		this.condition = condition;
		this.page = page;
		this.count = count;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Long getUid()
	{
		return uid;
	}

	public void setUid(Long uid)
	{
		this.uid = uid;
	}

	public String getCondition()
	{
		return condition;
	}

	public void setCondition(String condition)
	{
		this.condition = condition;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public static String getMethod()
	{
		return FriendsSearchRequestParam.METHOD;
	}

	@Override
	public Bundle getParams() throws AidException
	{
		Bundle parameters = new Bundle();
		parameters.putString("method", FriendsSearchRequestParam.METHOD);
		if (name != null)
		{
			parameters.putString("name", name);
		}
		if (uid != null)
		{
			parameters.putString("uid", String.valueOf(uid));
		}
		parameters.putString("condition", condition);
		if (page != -1)
		{
			parameters.putString("page", String.valueOf(page));
		}
		if (count != -1)
		{
			parameters.putString("count", String.valueOf(count));
		}
		return parameters;
	}
}
