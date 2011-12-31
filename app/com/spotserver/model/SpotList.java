package com.spotserver.model;

import java.util.LinkedList;
import java.util.List;

public class SpotList {

	private List<ConfirmedSpot> spots;

	
	
	////////////
	
	public void addSpot(ConfirmedSpot addedSpot) {
		if (spots == null) {
			synchronized (this) {
				if (spots == null) {
					spots = new LinkedList<ConfirmedSpot>();
				}
			}
		}
		spots.add(addedSpot);
	}
	
	
	/////////
	
	
	public List<ConfirmedSpot> getSpots() {
		return spots;
	}

	public void setSpots(List<ConfirmedSpot> spots) {
		this.spots = spots;
	}
	
	
	
	
	
	

}
