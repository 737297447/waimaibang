package com.lingdian.waimaibang.utils;



import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

public class LocationBase implements AMapLocationListener {
	private LocationManagerProxy aMapLocManager;
	private boolean bWorkCheckIn = false;
	private Context mActivity;
	private LocationListener mListener;

	public LocationBase(Context paramGroupsBaseActivity,
			boolean paramBoolean3, LocationListener paramLocationListener) {
		this.mActivity = paramGroupsBaseActivity;
		this.mListener = paramLocationListener;
		this.bWorkCheckIn = paramBoolean3;
			startAmapLocation();
	}

	private LocationContent converAmaplocation(AMapLocation paramAMapLocation) {
		if (paramAMapLocation == null)
			return null;

		return new LocationContent(Double.valueOf(paramAMapLocation
				.getLatitude()), Double.valueOf(paramAMapLocation
				.getLongitude()),paramAMapLocation.getAddress(),paramAMapLocation.getCity());
	}


	private void startAmapLocation() {
		if (this.aMapLocManager == null) {
			this.aMapLocManager = LocationManagerProxy
					.getInstance(this.mActivity);
			this.aMapLocManager.requestLocationData(LocationProviderProxy.AMapNetwork, 2000L, 10.0F, this);
			return;
		}
		this.aMapLocManager.requestLocationData(LocationProviderProxy.AMapNetwork, 2000L, 10.0F, this);
	}


	private void stopGetAmapLocation() {
		if (this.aMapLocManager != null) {
			this.aMapLocManager.removeUpdates(this);
			this.aMapLocManager.destroy();
		}
	}


	public LocationContent getCheckinInLocation(
			LocationContent paramLocationContent) {
		if (paramLocationContent != null) {
			return new LocationContent(paramLocationContent.getLatitude(),
					paramLocationContent.getLongitude(),paramLocationContent.getAddress(),paramLocationContent.getCity());
		}
		return null;

	}

	public void onLocationChanged(Location paramLocation) {
	}

	public void onLocationChanged(AMapLocation paramAMapLocation) {
		LocationContent localLocationContent1 = null;
		if ((paramAMapLocation != null)
				&& (paramAMapLocation.getAMapException().getErrorCode() == 0))
			if (this.mListener != null) {
				localLocationContent1 = converAmaplocation(paramAMapLocation);
				LocationContent localLocationContent2 = getCheckinInLocation(localLocationContent1);
				if ((!this.bWorkCheckIn) || (localLocationContent2 == null))
					this.mListener.onGetGaodeLocation(localLocationContent2,
							true);
				stopGetLocation();
			}
		while (true) {
			Log.i("AlarmService",
					"Amap latitude=" + paramAMapLocation.getLatitude()
							+ " longitute=" + paramAMapLocation.getLongitude());
			stopGetAmapLocation();
			this.mListener.onGetGaodeLocation(localLocationContent1, false);
			return;
		}
	}

	public void onProviderDisabled(String paramString) {
	}

	public void onProviderEnabled(String paramString) {
	}

	public void onStatusChanged(String paramString, int paramInt,
			Bundle paramBundle) {
	}

	public void stopGetLocation() {
		stopGetAmapLocation();
	}


	public class LocationContent {
		private Double latitude = Double.valueOf(0.0D);
		private Double longitude = Double.valueOf(0.0D);
		private String address;
		private String city;

		public LocationContent(Double latitude, Double longitude,String address,String city) {
			this.latitude = latitude;
			this.longitude = longitude;
			this.address = address;
			this.city = city;
		}

		public Double getLatitude() {
			return this.latitude;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Double getLongitude() {
			return this.longitude;
		}

		public void setLatitude(Double paramDouble) {
			this.latitude = paramDouble;
		}

		public void setLongitude(Double paramDouble) {
			this.longitude = paramDouble;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}
		
	}

	public static abstract interface LocationListener {

		public abstract void onGetGaodeLocation(
				LocationBase.LocationContent paramLocationContent,
				boolean paramBoolean);
	}
}

