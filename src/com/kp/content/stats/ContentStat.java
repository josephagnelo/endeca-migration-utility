package com.kp.content.stats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ContentStat {
	
	private Set<String> cartridgeIdList;
	private Map<String,Set<CartridgeContentMap>> cartridgeContentMap;
	public Set<String> getCartridgeIdList() {
		return cartridgeIdList;
	}
	public void setCartridgeIdList(Set<String> cartridgeIdList) {
		this.cartridgeIdList = cartridgeIdList;
	}
	public Set<String> getActiveCartridgeIdList() {
		if(null == cartridgeIdList)
			return null;
		if(null == cartridgeContentMap)
			return null;
		
		Set<String> activeCartridges = new HashSet<String>(cartridgeIdList.size());
		
		for(String cartridgeId:cartridgeIdList)
		{
			if(isCartridgeActive(cartridgeId))
				activeCartridges.add(cartridgeId);
		}
		
		return activeCartridges;
	}
	
	private boolean isCartridgeActive(String cartridgeId) {
		boolean isActive = false;
		if(null != cartridgeContentMap)
		{
			if(null != cartridgeContentMap.get(cartridgeId))
			{
				Set<CartridgeContentMap> cartreigeMapList = cartridgeContentMap.get(cartridgeId);
				for(CartridgeContentMap map:cartreigeMapList)
				{
					if(map.isActive())
						return true;
				}
			}
		}
		
		return isActive;
	}
	public Map<String, Set<CartridgeContentMap>> getCartridgeContentMap() {
		return cartridgeContentMap;
	}
	public void setCartridgeContentMap(Map<String, Set<CartridgeContentMap>> cartridgeContentMap) {
		this.cartridgeContentMap = cartridgeContentMap;
	}
	// adding cartridge to list
	public boolean addCatridgeId(String id)
	{
		try {
			if(null == cartridgeIdList)
			{
				cartridgeIdList = new HashSet<String>(200);
			}
			cartridgeIdList.add(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean addToCartridgeContentMap(String cartridgeId,CartridgeContentMap map)
	{ 
		try
		{
			if(null == cartridgeContentMap)
			{
				cartridgeContentMap = new HashMap<String,Set<CartridgeContentMap>>(2000);
			}
			if(cartridgeContentMap.containsKey(cartridgeId))
			{
				cartridgeContentMap.get(cartridgeId).add(map);
			}
			else
			{
				Set<CartridgeContentMap> listOfMaps = new HashSet<CartridgeContentMap>(100);
				listOfMaps.add(map);
				cartridgeContentMap.put(cartridgeId, listOfMaps);
			}
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}

}
