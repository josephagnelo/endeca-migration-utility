package com.kp.dimensions.beans;

import java.util.ArrayList;
import java.util.List;

public class StateEntry {
	
	private String dimensionName;
	private String dimensionValue;
	private String dimensionValueId;
	public String getDimensionName() {
		return dimensionName;
	}
	public String getDimensionValue() {
		return dimensionValue;
	}
	public String getDimensionValueId() {
		return dimensionValueId;
	}
	public StateEntry(String dimensionName, String dimensionValue, String dimensionValueId) {
		super();
		this.dimensionName = dimensionName;
		this.dimensionValue = dimensionValue;
		this.dimensionValueId = dimensionValueId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dimensionName == null) ? 0 : dimensionName.hashCode());
		result = prime * result + ((dimensionValue == null) ? 0 : dimensionValue.hashCode());
		//result = prime * result + ((dimensionValueId == null) ? 0 : dimensionValueId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StateEntry other = (StateEntry) obj;
		if (dimensionName == null) {
			if (other.dimensionName != null)
				return false;
		} else if (!dimensionName.equals(other.dimensionName))
			return false;
		if (dimensionValue == null) {
			if (other.dimensionValue != null)
				return false;
		} else if (!dimensionValue.equals(other.dimensionValue))
			return false;
		/*if (dimensionValueId == null) {
			if (other.dimensionValueId != null)
				return false;
		} else if (!dimensionValueId.equals(other.dimensionValueId))
			return false;*/
		return true;
	}
	public void print() {
		if(this == null)
		{
			System.out.println("State Entry - NULL");
			return;
		}
		System.out.println(dimensionName+"--"+dimensionValue+"--"+dimensionValueId);
		
		
	}

	public List getValues()
	{
		List<String> values = new ArrayList<String>(3);
		values.add(dimensionName);
		values.add(dimensionValue);
		values.add(dimensionValueId);
		return values;
	}
}
