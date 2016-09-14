package com.lingdian.waimaibang.model;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lingdian.waimaibang.model.common.Photos;
import com.lingdian.waimaibang.model.wanfa.Comments;
import com.lingdian.waimaibang.model.wanfa.WanfaModel;
import com.lingdian.waimaibang.model.wanfa.WriteWanfaModel;
import com.lingdian.waimaibang.waimaimodel.Ads;
import com.lingdian.waimaibang.waimaimodel.BaiduContent;
import com.lingdian.waimaibang.waimaimodel.Position;
import com.lingdian.waimaibang.waimaimodel.ShangJiaMainBean;
import com.lingdian.waimaibang.waimaimodel.XiachufangModel;

public class GsonUtil {

	private static Type WanfaListsType;
	private static Type WanfaModelType;
	private static Type CommentsType;
	private static Type DestinationV2Type;
	private static Type PersonalizeDestinationType;
	private static Type UserInfoType;
	private static Type RelateDestinationListType;
	private static Type RelateDestinationInfoType;
	private static Type RelateDestinationInfoListType;
	private static Type ProductType;

	private static Type TopDestinationV2ListType;
	private static Type DescriptionopListType;
	private static Type ArticleListType;
	private static Type WantV2ListType;
	private static Type RegisterType;
	private static Type SecondCityList;
	private static Type CategoryList;
	private static Type GerenInfoModel;
	private static Type PsdErrorModelType;
	private static Type PsdUserIconModellType;
	private static Type XiaoxiModelListType;
	private static Type ZanModelType;
	private static Type CreateDestionModelType;
	private static Type WriteWanfaModelType;
	private static Type ShoucangModelType;
	private static Type JXDetailModelType;
	private static Type AdminPicType;
	
	
	private static Type ShangJiaMainBeanType;
	private static Type PositionType;
	private static Type adsListType;
	private static Type BaiduPositionType;
	private static Type XiachufangModelType;
	
	
	private static Gson gson = new Gson();

	/** 获取玩法的list数据 */
	public static List<WanfaModel> getWanfaLists(String result) {
		if (WanfaListsType == null) {
			WanfaListsType = new TypeToken<List<WanfaModel>>() {
			}.getType();
		}
		return gson.fromJson(result, WanfaListsType);
	}

	/** 获取玩法的具体数据 */
	public static WanfaModel getWanfa(String result) {
		if (WanfaModelType == null) {
			WanfaModelType = new TypeToken<WanfaModel>() {
			}.getType();
		}
		return gson.fromJson(result, WanfaModelType);
	}

	/** 获取玩法的评论 */
	public static List<Comments> getComments(String result) {
		if (CommentsType == null) {
			CommentsType = new TypeToken<List<Comments>>() {
			}.getType();
		}
		return gson.fromJson(result, CommentsType);
	}

	/** 获取目的地 */
	public static DestinationV2 getDestination(String result) {
		if (DestinationV2Type == null) {
			DestinationV2Type = new TypeToken<DestinationV2>() {
			}.getType();
		}
		return gson.fromJson(result, DestinationV2Type);
	}

	/** 获取用户信息 */
	public static UserInfo getUserInfo(String result) {
		if (UserInfoType == null) {
			UserInfoType = new TypeToken<UserInfo>() {
			}.getType();
		}
		return gson.fromJson(result, UserInfoType);
	}

	/** 获取周边信息列表 */
	public static List<RelateDestination> getRelateDestinationList(String result) {
		if (RelateDestinationListType == null) {
			RelateDestinationListType = new TypeToken<List<RelateDestination>>() {
			}.getType();
		}
		return gson.fromJson(result, RelateDestinationListType);
	}

	/** 获取周边信息 */
	public static RelateDestinationInfo getRelateDestinationInfo(String result) {
		if (RelateDestinationInfoType == null) {
			RelateDestinationInfoType = new TypeToken<RelateDestinationInfo>() {
			}.getType();
		}
		return gson.fromJson(result, RelateDestinationInfoType);
	}

	/** 获取周边信息列表 */
	public static List<RelateDestinationInfo> getRelateDestinationInfoList(
			String result) {
		if (RelateDestinationInfoListType == null) {
			RelateDestinationInfoListType = new TypeToken<List<RelateDestinationInfo>>() {
			}.getType();
		}
		return gson.fromJson(result, RelateDestinationInfoListType);
	}

	/** 获取商品信息列表 */
	public static List<Product> getProductList(String result) {
		if (ProductType == null) {
			ProductType = new TypeToken<List<Product>>() {
			}.getType();
		}
		return gson.fromJson(result, ProductType);
	}

	/** 获取目的地信息列表 */
	public static List<TopDestinationV2> getTopDestinationV2List(String result) {
		if (TopDestinationV2ListType == null) {
			TopDestinationV2ListType = new TypeToken<List<TopDestinationV2>>() {
			}.getType();
		}
		return gson.fromJson(result, TopDestinationV2ListType);
	}

	/** 获取目的地信息 */
	public static PersonalizeDestination getPersonalizeDestination(String result) {
		if (PersonalizeDestinationType == null) {
			PersonalizeDestinationType = new TypeToken<PersonalizeDestination>() {
			}.getType();
		}
		return gson.fromJson(result, PersonalizeDestinationType);
	}

	/** 获取描述列表 */
	public static List<Descriptionop> getDescriptionopList(String result) {
		if (DescriptionopListType == null) {
			DescriptionopListType = new TypeToken<List<Descriptionop>>() {
			}.getType();
		}
		return gson.fromJson(result, DescriptionopListType);
	}

	/** 获取主题列表 */
	public static ArticleList getArticleList(String result) {
		if (ArticleListType == null) {
			ArticleListType = new TypeToken<ArticleList>() {
			}.getType();
		}
		return gson.fromJson(result, ArticleListType);
	}

	/** 获取主题列表 */
	public static List<WantV2> getWantV2List(String result) {
		if (WantV2ListType == null) {
			WantV2ListType = new TypeToken<List<WantV2>>() {
			}.getType();
		}
		return gson.fromJson(result, WantV2ListType);
	}
	/** 获取主题列表 */
	public static List<XiaoxiModel> getXiaoxiModelList(String result) {
		if (XiaoxiModelListType == null) {
			XiaoxiModelListType = new TypeToken<List<XiaoxiModel>>() {
			}.getType();
		}
		return gson.fromJson(result, XiaoxiModelListType);
	}
	
	/** 获取注册信息 */
	public static Register getRegisterInfo(String result) {
		if (RegisterType == null) {
			RegisterType = new TypeToken<Register>() {
			}.getType();
		}
		return gson.fromJson(result, RegisterType);
	}
	
	/** 获取二级城市列表 */
	public static List<SecondCity> getSecondCityList(String result) {
		if (SecondCityList == null) {
			SecondCityList = new TypeToken<List<SecondCity>>() {
			}.getType();
		}
		return gson.fromJson(result, SecondCityList);
	}
	/** 获取分类列表 */
	public static List<PopupFenleiModel> getCategoryList(String result) {
		if (CategoryList == null) {
			CategoryList = new TypeToken<List<PopupFenleiModel>>() {
			}.getType();
		}
		return gson.fromJson(result, CategoryList);
	}
	
	/** 获取个人信息 */
	public static GerenInfo getGerenInfo(String result) {
		if (GerenInfoModel == null) {
			GerenInfoModel = new TypeToken<GerenInfo>() {
			}.getType();
		}
		return gson.fromJson(result, GerenInfoModel);
	}
	
	/** 获取密码正确或错误信息 */
	public static PsdErrorModel getPsdErrorModel(String result) {
		if (PsdErrorModelType == null) {
			PsdErrorModelType = new TypeToken<PsdErrorModel>() {
			}.getType();
		}
		return gson.fromJson(result, PsdErrorModelType);
	}
	/** 获取上传图片成功失败信息*/
	public static UserIconModel getUserIconModel(String result) {
		if (PsdUserIconModellType == null) {
			PsdUserIconModellType = new TypeToken<UserIconModel>() {
			}.getType();
		}
		return gson.fromJson(result, PsdUserIconModellType);
	}
	/** 获取赞的信息*/
	public static ZanModel getZanModelModel(String result) {
		if (ZanModelType == null) {
			ZanModelType = new TypeToken<ZanModel>() {
			}.getType();
		}
		return gson.fromJson(result, ZanModelType);
	}
	/** 写玩法----创建目的地之后返回地址*/
	public static CreateDestionModel getCreateDestionModel(String result) {
		if (CreateDestionModelType == null) {
			CreateDestionModelType = new TypeToken<CreateDestionModel>() {
			}.getType();
		}
		return gson.fromJson(result, CreateDestionModelType);
	}
	
	/** 写玩法----创建玩法成功之后返回*/
	public static WriteWanfaModel getWriteWanfaModel(String result) {
		if (WriteWanfaModelType == null) {
			WriteWanfaModelType = new TypeToken<WriteWanfaModel>() {
			}.getType();
		}
		return gson.fromJson(result, WriteWanfaModelType);
	}
	
	/**收藏信息*/
	public static ShoucangModel getShoucangModel(String result) {
		if (ShoucangModelType == null) {
			ShoucangModelType = new TypeToken<ShoucangModel>() {
			}.getType();
		}
		return gson.fromJson(result, ShoucangModelType);
	}
	/**精选玩法详细信息*/
	public static JXDetailModel getJXDetailModelModel(String result) {
		if (JXDetailModelType == null) {
			JXDetailModelType = new TypeToken<JXDetailModel>() {
			}.getType();
		}
		return gson.fromJson(result, JXDetailModelType);
	}
	
	/**admin*/
	public static List<Photos> getAdminPic(String result) {
		if (AdminPicType == null) {
			AdminPicType = new TypeToken<List<Photos>>() {
			}.getType();
		}
		return gson.fromJson(result, AdminPicType);
	}
	
	/** -------------------外卖帮--------------------------  */
	
	/** 获取饿了么数据*/
	public static List<ShangJiaMainBean> getShangJiaMainBeans(String result) {
		if (ShangJiaMainBeanType == null) {
			ShangJiaMainBeanType = new TypeToken<List<ShangJiaMainBean>>() {
			}.getType();
		}
		return gson.fromJson(result, ShangJiaMainBeanType);
	}
	
	/** 获取饿了么地址信息*/
	public static Position getPosition(String result) {
		if (PositionType == null) {
			PositionType = new TypeToken<Position>() {
			}.getType();
		}
		return gson.fromJson(result, PositionType);
	}
	
	/** 获取饿了么广告信息*/
	public static List<Ads> getAdsList(String result) {
		if (adsListType == null) {
			adsListType = new TypeToken<List<Ads>>() {
			}.getType();
		}
		return gson.fromJson(result, adsListType);
	}
	/** 获取百度位置信息*/
	public static List<BaiduContent> getBaiduPosition(String result) {
		if (BaiduPositionType == null) {
			BaiduPositionType = new TypeToken<List<BaiduContent>>() {
			}.getType();
		}
		return gson.fromJson(result, BaiduPositionType);
	}
	
	/** 获取下厨房信息*/
	public static XiachufangModel getXiachufangModel(String result) {
		if (XiachufangModelType == null) {
			XiachufangModelType = new TypeToken<XiachufangModel>() {
			}.getType();
		}
		return gson.fromJson(result, XiachufangModelType);
	}
	
}
