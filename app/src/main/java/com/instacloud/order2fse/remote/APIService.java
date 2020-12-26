package com.instacloud.order2fse.remote;


import com.instacloud.order2fse.model.MStatus;
import com.instacloud.order2fse.model.LoginModel;
import com.instacloud.order2fse.ui.AddShop.Fragment.ServicesModel;
import com.instacloud.order2fse.ui.AddShop.Model.AddCustomerModel;
import com.instacloud.order2fse.ui.AddShop.Model.AddManagerModel;
import com.instacloud.order2fse.ui.Item.Model.AddMenuModel;
import com.instacloud.order2fse.ui.Item.Model.CategoryModel;
import com.instacloud.order2fse.ui.Item.Model.ExtraGroupIDModel;
import com.instacloud.order2fse.ui.Item.Model.ExtraItemModel;
import com.instacloud.order2fse.ui.Item.Model.ProductsModel.ItemModel;
import com.instacloud.order2fse.ui.Item.Model.SubCategoryModel.SubCategoryModel;
import com.instacloud.order2fse.ui.Login.Model.Order2LoginModel;
import com.instacloud.order2fse.ui.Payment.AgentCommissionModel;
import com.instacloud.order2fse.ui.Payment.PaymentModel;
import com.instacloud.order2fse.ui.home.ShopByAgentIdModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIService {
    /*
        Check users
     */
//    @POST("/api/login")
//    @FormUrlEncoded
//    Call<LoginModel> checkUser(@Field("username") String username,
//                               @Field("password") String password,
//                               @Field("type") String type);

    @POST("/api/login")
    @FormUrlEncoded
//    @Headers("Content-Type: application/json")
    Call<Order2LoginModel> checkUserOrder2(@Field("email") String email,
                                     @Field("password") String password,
                                     @Field("device_token") String device_token);

    @POST("/api/restaurantpayment")
    @FormUrlEncoded
    Call<PaymentModel> sendPaymentMode(@Field("restaurant_id") String restaurant_id,
                                       @Field("payment_option") String payment_option);

    @POST("/api/agentcommission")
    @FormUrlEncoded
    Call<AgentCommissionModel> sendAgentCommission(@Field("user_id") String user_id,
                                                   @Field("restaurants_id") String restaurants_id,
                                                   @Field("agent_commision") String agent_commision);

    @POST("/api/tracking")
    @FormUrlEncoded
    Call <MStatus> tracking(@Field("latitude") String lat,
                            @Field("longitude") String longi,
                            @Field("agent_id") String agents);

    @POST("/api/manager/restaurants")
    @FormUrlEncoded
    Call<AddCustomerModel> addCustomer(@Query("api_token") String api_token,
                                       @Field("agent_id") String agent_id,
                                       @Field("name") String shopname,
                                       @Field("description") String description,
                                       @Field("address") String address,
                                       @Field("latitude") String latitude,
                                       @Field("longitude") String longitude,
                                       @Field("phone") String phone,
                                       @Field("mobile") String mobile,
                                       @Field("information") String information,
                                       @Field("admin_commission") String admin_commission,
                                       @Field("default_tax") String default_tax,
                                       @Field("delivery_range") String delivaryrange,
                                       @Field("available_for_delivery") String availablefordelivery,
                                       @Field("closed") String closed,
                                       @Field("active") String active,
                                       @Field("state") String state,
                                       @Field("city") String city,
                                       @Field("voter_aadhaar") String voterAadahaar,
                                       @Field("service") int service,
                                       @Field("pan") String panNumber,
                                       @Field("min_order_value") String minimumOrders,
                                       @Field("ifsc") String ifsc,
                                       @Field("gst") String gstin,
                                       @Field("bank_name") String bankname,
                                       @Field("account_no") String accountno,
                                       @Field("account_name") String accountnam


    );

    @POST("/api/manager/register")
    @FormUrlEncoded
    Call<AddManagerModel> addManager(@Field("name") String name,
                                     @Field("email") String email);

    @POST("/api/windsuploadbyfield")
    @Multipart
    Call<MStatus> windsUploadByField(@Part("customer_id") RequestBody customerID,
                                     @Part("field_name") RequestBody fieldName,
                                     @Part MultipartBody.Part str);

    @POST("/api/windsfilecheck")
    @FormUrlEncoded
    Call<MStatus> windsFileCheck(@Field("customer_id") String cid);

    @GET("/api/services")
    Call<ServicesModel> addServicesCategory();

    @GET("/api/categories")
    Call<CategoryModel> addCategory();

    @GET("api/itemcategories")
    Call<SubCategoryModel> addSubCategory();

    @GET("/api/items")
    Call<ItemModel> addProducts(@Query("item_category") String ic_id);

    @GET("/api/extra_groups")
    Call<ExtraGroupIDModel> addExtraGroupID();

    @POST("/api/fooditem")
    @FormUrlEncoded
    Call<AddMenuModel> addMenuView(@Field("restaurant_id") String restaurantid);

    @POST("/api/foods")
    @FormUrlEncoded
    Call<AddMenuModel> addMenu(@Field("name") String name,
                               @Field("price") String price,
                               @Field("discount_price") String discount_price,
                               @Field("description") String description,
                               @Field("ingredients") String ingredients,
                               @Field("weight") String weight,
                               @Field("package_items_count") String package_items_count,
                               @Field("unit") String unit,
                               @Field("featured") String featured,
                               @Field("deliverable") String deliverable,
                               @Field("restaurant_id") String restaurant_id,
                               @Field("category_id") String category_id
    );

    @POST("/api/extras")
    @FormUrlEncoded
    Call<ExtraItemModel> addExtraItems(@Field("name") String name,
                                       @Field("description") String description,
                                       @Field("price") String price,
                                       @Field("food_id") String food_id,
                                       @Field("extra_group_id") String extra_group_id
    );

    @POST("/api/restaurantbyagentid")
    @FormUrlEncoded
    Call<ShopByAgentIdModel> getRestaurantByAgentId(@Field("agent_id") String agentId);//import okhttp3.ResponseBody;
}


//    /*
//        get all customers
//     */
//    @POST("/api/graph")
//    @FormUrlEncoded
//    Call<Mgraph> graph(@Field("agent_id") String agent,
//                       @Field("user_type") String utype);
//
//    /*
//        get all customers
//     */
//    @POST("/api/tracking")
//    @FormUrlEncoded
//    Call<MStatus> tracking(@Field("latitude") String lat,
//                           @Field("longitude") String longi,
//                           @Field("agent_id") String agents);
//
//    /*
//        get all customers
//     */
//    @POST("/api/addcustomer")
//    @Multipart
//    Call<MStatus> addCustomer(@Part("agent_id") RequestBody agentId,
//                              @Part("agent_type") RequestBody agentType,
//                              @Part("customer_name") RequestBody customerName,
//                              @Part("mobile") RequestBody mobile,
//                              @Part("location") RequestBody location,
//                              @Part("category") RequestBody category,
//                              @Part("customs") RequestBody customs,
//                              @Part MultipartBody.Part selfie,
//                              @Part MultipartBody.Part proof);
//
//    @POST("/api/listservices")
//    @FormUrlEncoded
//    Call<List<DetailModel>> ListService(@Field("agent_id") String agentId,
//                                        @Field("agent_type") String agentType,
//                                        @Field("category") String category);
//
//    @POST("/api/view_customers_agent")
//    @FormUrlEncoded
//    Call<List<CustomerButtonModel>> ListCustomerButton(@Field("agent_id") String agentId,
//                                                       @Field("agent_type") String agentType,
//                                                       @Field("category") String category,
//                                                       @Field("title") String title);
//
//    @POST("/api/view_cust_test")
//    @FormUrlEncoded
//    Call<TestModel> Test(@Field("agent_id") String agentId,
//                         @Field("agent_type") String agentType,
//                         @Field("category") String category);
//
//    @GET("/api/checkversion")
//    Call<String> check_version();
//
//    @GET("/api/app_view_customer")
//    Call<String> viewCustomer();
//
//    @GET("/api/states")
//    Call<MStatus> states();
//
//    /*
//        get all customers
//     */
//    @POST("/api/cmobile")
//    @FormUrlEncoded
//    Call<String> customerMobile(@Field("mobile") String mobile);
//
//    /*
//        add Winds
//     */
//    @POST("/api/addwindcustomer")
//    @FormUrlEncoded
//    Call<MStatus> addWinds(@Field("shopname") String shopName,
//                           @Field("aoro") String aoro,
//                           @Field("pan") String pan,
//                           @Field("mobile") String mobile,
//                           @Field("email") String email,
//                           @Field("ahn") String ahn,
//                           @Field("bankname") String bankname,
//                           @Field("bankaddress") String baddress,
//                           @Field("accountno") String acno,
//                           @Field("ifsc") String ifsc,
//                           @Field("peraddress") String padd,
//                           @Field("dob") String dob,
//                           @Field("pin") String pin,
//                           @Field("fhname") String fhname,
//                           @Field("vidano") String vidno,
//                           @Field("nominee") String nominee,
//                           @Field("customer_name") String customername,
//                           @Field("state") String state,
//                           @Field("district") String district,
//                           @Field("gender") String gender,
//                           @Field("relation") String relation,
//                           @Field("agent_id") String agentId,
//                           @Field("agent_type") String agentType
//    );
//
//    /*
//        Winds Files Upload
//     */
//    @POST("/api/windsupload")
//    @Multipart
//    Call<MStatus> WindsUpload(@Part("customer_id") RequestBody customerID,
//                              @Part MultipartBody.Part adhrf,
//                              @Part MultipartBody.Part adhrb,
//                              @Part MultipartBody.Part pan,
//                              @Part MultipartBody.Part shop_pic,
//                              @Part MultipartBody.Part pass,
//                              @Part MultipartBody.Part selfie);
//
//    /*
//        Winds Files Upload
//     */
//
//    @POST("/api/view_customers_agent")
//    @FormUrlEncoded
//    Call<List<CustomerButtonModel>> ListCustomerButton(@Field("agent_id") String agentId,
//                                                       @Field("agent_type") String agentType,
//                                                       @Field("category") String category,
//                                                       @Field("title") String title);
//
//
//    @POST("/api/view_cust_test")
//    @FormUrlEncoded
//    Call<ViewModel> View(@Field("agent_id") String agentId,
//                         @Field("agent_type") String agentType,
//                         @Field("category") String category);
//
//    @POST("/api/windsuploadbyfield")
//    @Multipart
//    Call<MStatus> windsUploadByField(@Part("customer_id") RequestBody customerID,
//                                     @Part("field_name") RequestBody fieldName,
//                                     @Part MultipartBody.Part str);
//
//    @POST("/api/windsfilecheck")
//    @FormUrlEncoded
//    Call<MStatus> windsFileCheck(@Field("customer_id") String cid);
//
//    @POST("/api/listdistrict")
//    @FormUrlEncoded
//    Call<String> listDistrict(@Field("state") String state);
