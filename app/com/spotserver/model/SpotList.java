package com.spotserver.model;

import java.util.LinkedList;
import java.util.List;

public class SpotList {

	private Status status;

	private List<ConfirmedSpot> spots;

	// //////////

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

	// ///////

	public List<ConfirmedSpot> getSpots() {
		return spots;
	}

	public void setSpots(List<ConfirmedSpot> spots) {
		this.spots = spots;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
