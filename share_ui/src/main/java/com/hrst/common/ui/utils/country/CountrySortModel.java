
package com.hrst.common.ui.utils.country;

/**
 * 
 * @author duanbokan
 * 
 */

public class CountrySortModel extends CountryModel

{
	// 显示数据拼音的首字母
	public String sortLetters;
	
	public CountrySortToken sortToken = new CountrySortToken();
	
	public CountrySortModel(String name, String number, String countrySortKey)
	{
		super(name, number, countrySortKey);
	}
	
}
