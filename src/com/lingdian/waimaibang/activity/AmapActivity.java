package com.lingdian.waimaibang.activity;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.lingdian.waimaibang.R;
import com.lingdian.waimaibang.model.DestinationV2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.umeng.analytics.MobclickAgent;

public class AmapActivity extends BaseActivity {
	private MapView mapView;
    private AMap aMap;
    private DestinationV2 destinationV2;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_amap);
		destinationV2 = (DestinationV2) getIntent().getSerializableExtra("model");
		
		mapView = (MapView) findViewById(R.id.map);
	    mapView.onCreate(savedInstanceState);// 必须要写
	    aMap = mapView.getMap();
	    MarkerOptions markerOptions = new MarkerOptions();	    
	    LatLng latLng = new LatLng(destinationV2.getLatitude(), destinationV2.getLongitude());
	    Log.e("amap", latLng.toString());
	    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,(float) 16.75));
	    markerOptions.position(latLng);
	    markerOptions.title(destinationV2.getName());
	    markerOptions.icon(BitmapDescriptorFactory.defaultMarker());
	    Log.e("amap", markerOptions.toString());
	    aMap.addMarker(markerOptions);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mapView.onResume();
		MobclickAgent.onResume(this);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mapView.onPause();
		MobclickAgent.onPause(this);
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mapView.onDestroy();
	}


	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}

}
