/**
 * 
 */
package com.texus.malayalamshort.xml;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Sandeep Kumar
 * 
 */
public class XMLElement {
	public XMLElement parent = null;
	public ArrayList<XMLElement> children = new ArrayList<XMLElement>();
	public HashMap<String, String> attributesList = new HashMap<String, String>();
	public String Name = null;

	public XMLElement(String name) {
		this.Name = name;
	}
	
	public void SetAttribute(String name, String value) 
	{
		this.attributesList.put(name, value);
	}

	public String GetAttribute(String name) {
		if (!this.attributesList.containsKey(name)) {
			return null;
		}
		String returnStr = this.attributesList.get(name);
		if( (null != returnStr) && (returnStr.length() > 0)) {
			return returnStr;
		}
		return  null;
	}

	public void Add(XMLElement ChildElement) {
		ChildElement.parent = this;
		children.add(ChildElement);
	}

	public XMLElement SelectSingleELementByAttribute(String AttributeName,
													 String AttributeValue) {
		try {
			if ((null == AttributeName) || (AttributeName.length() < 1)
					|| (null == AttributeValue)) {
				return null;
			}
			for (XMLElement curChild : children) {
				if (null == curChild) {
					continue;
				}
				String curAttr = curChild.GetAttribute(AttributeName);
				if ((null != curAttr) && (0 == curAttr.compareTo(AttributeValue))) {
						return curChild;
				}
			}
		}catch(Exception e){}
		return null;
	}
	
	
	public ArrayList<XMLElement>  SelectELementsByAttribute(String AttributeName,
															String AttributeValue) {
		ArrayList<XMLElement> returnList = new ArrayList<XMLElement>();
		try {
			if ((null == AttributeName) || (AttributeName.length() < 1) ||
                    (null == AttributeValue)) {
				return returnList;
			}
			
			for (XMLElement curChild : children) {
				if (null == curChild) { continue; }
			
                String curAttr = curChild.GetAttribute(AttributeName);
                if ((null != curAttr) && (0 == curAttr.compareTo(AttributeValue))) {
						returnList.add(curChild);
                }
			}
		}catch(Exception e){}
		return returnList;
	}
	
	public XMLElement SelectSingleELementByName(String Name) {
		try {
			if ((null == Name) || (Name.length() < 1)) {
				return null;
			}
			
			for (XMLElement curChild : children) {
				if ((null != curChild) && (null != curChild.Name)
                        && (0 == curChild.Name.compareToIgnoreCase(Name))) {
					return curChild;
				}
			}
		}catch(Exception e){}
		return null;
	}
	
	
	public ArrayList<XMLElement> SelectELementsByName(String Name) {
		ArrayList<XMLElement> returnList = new ArrayList<XMLElement>();
		try {
			if ((null == Name) || (Name.length() < 1)) {
				return returnList;
			}
			
			for (XMLElement curChild : children) {
				if ((null != curChild) && (null != curChild.Name)
                        && (0 == curChild.Name.compareToIgnoreCase(Name))) {
					returnList.add(curChild);
				}
			}
		}catch(Exception e){}
		return returnList;
	}
	
	
}
