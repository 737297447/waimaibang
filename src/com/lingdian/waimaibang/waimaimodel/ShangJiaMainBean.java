package com.lingdian.waimaibang.waimaimodel;

import java.io.Serializable;
import java.util.List;

public class ShangJiaMainBean implements Serializable{
	
	private int id;
//	/**店名*/
	private String name;
	private Double latitude;
	private Double longitude;
	private String description;
	private String phone;
	private int order_mode;
	private String promotion_info;
	private String name_for_url;
	private String flavors;
	private boolean is_time_ensure;
	private int city_id;
	private boolean is_premium;
	private String time_ensure_full_description;
	private int recent_order_num;
	private String image_path;//
//	/** 评分*/
	private String rating;
	private boolean is_free_delivery;
	private int minimum_order_amount;
	private int order_lead_time;
	private boolean is_support_invoice;
	/** 是否是新店*/
	private boolean is_new;
	private boolean is_third_party_delivery;
	/** 是否在预定的时间内*/
	private boolean is_in_book_time;
	/** 评分人数*/
	private int rating_count;
	private String address;
	/** 月销售量*/
	private int month_sales;
	private int delivery_fee;
	private int minimum_free_delivery_amount;
	private String minimum_order_description;
	private int minimum_invoice_amount;
	private boolean is_open_time;
	private List<String> opening_hours;
	private List<String> book_times;
	/** 是否支持在线支付*/
	private boolean is_online_payment;
	private int status;
	private int distance;
	private List<Support> supports;
	/** (176) 月售1218份*/
	private String food_tips;
	/** 20元起送 \/ 42分钟 \/ 1.6千米*/
	private String restaurant_tips;
	private String next_business_time;
	private boolean is_coupon_enabled;
	private String business_district;
	private boolean only_use_poi;
	private int closing_count_down;
	private List<Food_activity> food_activity;
	private List<Restaurant_activity> restaurant_activity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getOrder_mode() {
		return order_mode;
	}
	public void setOrder_mode(int order_mode) {
		this.order_mode = order_mode;
	}
	public String getPromotion_info() {
		return promotion_info;
	}
	public void setPromotion_info(String promotion_info) {
		this.promotion_info = promotion_info;
	}
	public String getName_for_url() {
		return name_for_url;
	}
	public void setName_for_url(String name_for_url) {
		this.name_for_url = name_for_url;
	}
	public String getFlavors() {
		return flavors;
	}
	public void setFlavors(String flavors) {
		this.flavors = flavors;
	}
	public boolean isIs_time_ensure() {
		return is_time_ensure;
	}
	public void setIs_time_ensure(boolean is_time_ensure) {
		this.is_time_ensure = is_time_ensure;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public boolean isIs_premium() {
		return is_premium;
	}
	public void setIs_premium(boolean is_premium) {
		this.is_premium = is_premium;
	}
	public String getTime_ensure_full_description() {
		return time_ensure_full_description;
	}
	public void setTime_ensure_full_description(String time_ensure_full_description) {
		this.time_ensure_full_description = time_ensure_full_description;
	}
	public int getRecent_order_num() {
		return recent_order_num;
	}
	public void setRecent_order_num(int recent_order_num) {
		this.recent_order_num = recent_order_num;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public boolean isIs_free_delivery() {
		return is_free_delivery;
	}
	public void setIs_free_delivery(boolean is_free_delivery) {
		this.is_free_delivery = is_free_delivery;
	}
	public int getMinimum_order_amount() {
		return minimum_order_amount;
	}
	public void setMinimum_order_amount(int minimum_order_amount) {
		this.minimum_order_amount = minimum_order_amount;
	}
	public int getOrder_lead_time() {
		return order_lead_time;
	}
	public void setOrder_lead_time(int order_lead_time) {
		this.order_lead_time = order_lead_time;
	}
	public boolean isIs_support_invoice() {
		return is_support_invoice;
	}
	public void setIs_support_invoice(boolean is_support_invoice) {
		this.is_support_invoice = is_support_invoice;
	}
	public boolean isIs_new() {
		return is_new;
	}
	public void setIs_new(boolean is_new) {
		this.is_new = is_new;
	}
	public boolean isIs_third_party_delivery() {
		return is_third_party_delivery;
	}
	public void setIs_third_party_delivery(boolean is_third_party_delivery) {
		this.is_third_party_delivery = is_third_party_delivery;
	}
	public boolean isIs_in_book_time() {
		return is_in_book_time;
	}
	public void setIs_in_book_time(boolean is_in_book_time) {
		this.is_in_book_time = is_in_book_time;
	}
	public int getRating_count() {
		return rating_count;
	}
	public void setRating_count(int rating_count) {
		this.rating_count = rating_count;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getMonth_sales() {
		return month_sales;
	}
	public void setMonth_sales(int month_sales) {
		this.month_sales = month_sales;
	}
	public int getDelivery_fee() {
		return delivery_fee;
	}
	public void setDelivery_fee(int delivery_fee) {
		this.delivery_fee = delivery_fee;
	}
	public int getMinimum_free_delivery_amount() {
		return minimum_free_delivery_amount;
	}
	public void setMinimum_free_delivery_amount(int minimum_free_delivery_amount) {
		this.minimum_free_delivery_amount = minimum_free_delivery_amount;
	}
	public String getMinimum_order_description() {
		return minimum_order_description;
	}
	public void setMinimum_order_description(String minimum_order_description) {
		this.minimum_order_description = minimum_order_description;
	}
	public int getMinimum_invoice_amount() {
		return minimum_invoice_amount;
	}
	public void setMinimum_invoice_amount(int minimum_invoice_amount) {
		this.minimum_invoice_amount = minimum_invoice_amount;
	}
	public boolean isIs_open_time() {
		return is_open_time;
	}
	public void setIs_open_time(boolean is_open_time) {
		this.is_open_time = is_open_time;
	}
	public List<String> getOpening_hours() {
		return opening_hours;
	}
	public void setOpening_hours(List<String> opening_hours) {
		this.opening_hours = opening_hours;
	}
	public List<String> getBook_times() {
		return book_times;
	}
	public void setBook_times(List<String> book_times) {
		this.book_times = book_times;
	}
	public boolean isIs_online_payment() {
		return is_online_payment;
	}
	public void setIs_online_payment(boolean is_online_payment) {
		this.is_online_payment = is_online_payment;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public List<Support> getSupports() {
		return supports;
	}
	public void setSupports(List<Support> supports) {
		this.supports = supports;
	}
	public String getFood_tips() {
		return food_tips;
	}
	public void setFood_tips(String food_tips) {
		this.food_tips = food_tips;
	}
	public String getRestaurant_tips() {
		return restaurant_tips;
	}
	public void setRestaurant_tips(String restaurant_tips) {
		this.restaurant_tips = restaurant_tips;
	}
	public String getNext_business_time() {
		return next_business_time;
	}
	public void setNext_business_time(String next_business_time) {
		this.next_business_time = next_business_time;
	}
	public boolean isIs_coupon_enabled() {
		return is_coupon_enabled;
	}
	public void setIs_coupon_enabled(boolean is_coupon_enabled) {
		this.is_coupon_enabled = is_coupon_enabled;
	}
	public String getBusiness_district() {
		return business_district;
	}
	public void setBusiness_district(String business_district) {
		this.business_district = business_district;
	}
	public boolean isOnly_use_poi() {
		return only_use_poi;
	}
	public void setOnly_use_poi(boolean only_use_poi) {
		this.only_use_poi = only_use_poi;
	}
	public int getClosing_count_down() {
		return closing_count_down;
	}
	public void setClosing_count_down(int closing_count_down) {
		this.closing_count_down = closing_count_down;
	}
	public List<Food_activity> getFood_activity() {
		return food_activity;
	}
	public void setFood_activity(List<Food_activity> food_activity) {
		this.food_activity = food_activity;
	}
	public List<Restaurant_activity> getRestaurant_activity() {
		return restaurant_activity;
	}
	public void setRestaurant_activity(List<Restaurant_activity> restaurant_activity) {
		this.restaurant_activity = restaurant_activity;
	}
	
	

}
