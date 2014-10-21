package com.rf.lifesum.challenge.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.mobsandgeeks.adapters.InstantText;
import com.orm.SugarRecord;
import com.rf.lifesum.challenge.R;

public class FoodModel extends SugarRecord<FoodModel> {

	private String categoryid = "";
	private String fiber = "";
	private String headimage = "";
	private String pcsingram = "";
	private String brand = "";
	private String unsaturatedfat = "";
	private String fat = "";
	private String servingcategory = "";
	private String typeofmeasurement = "";
	private String protein = "";
	private String defaultserving = "";
	private String mlingram = "";
	private String foodId = "";
	private String saturatedfat = "";
	private String category = "";
	private Boolean verified;
	private String title = "";
	private String pcstext = "";
	private String sodium = "";
	private String carbohydrates = "";
	private String showonlysametype = "";
	private String calories = "";
	private String serving_version = "";
	private String sugar = "";
	private String measurementid = "";
	private String cholesterol = "";
	private String gramsperserving = "";
	private String showmeasurement = "";
	private String potassium = "";

	public FoodModel() {

	};

	public FoodModel(JSONObject jsonObject) throws JSONException {
		this(jsonObject.getString("serving_version"), jsonObject
				.getString("categoryid"), jsonObject.getString("fiber"),
				jsonObject.getString("headimage"), jsonObject
						.getString("pcsingram"), jsonObject.getString("brand"),
				jsonObject.getString("unsaturatedfat"), jsonObject
						.getString("fat"), jsonObject
						.getString("servingcategory"), jsonObject
						.getString("typeofmeasurement"), jsonObject
						.getString("protein"), jsonObject
						.getString("defaultserving"), jsonObject
						.getString("mlingram"), jsonObject.getString("id"),
				jsonObject.getString("saturatedfat"), jsonObject
						.getString("category"), jsonObject
						.getBoolean("verified"), jsonObject.getString("title"),
				jsonObject.getString("pcstext"),
				jsonObject.getString("sodium"), jsonObject
						.getString("carbohydrates"), jsonObject
						.getString("showonlysametype"), jsonObject
						.getString("calories"), jsonObject.getString("sugar"),
				jsonObject.getString("measurementid"), jsonObject
						.getString("cholesterol"), jsonObject
						.getString("gramsperserving"), jsonObject
						.getString("showmeasurement"), jsonObject
						.getString("potassium"));
	}

	public FoodModel(String serving_version, String categoryid, String fiber,
			String headimage, String pcsingram, String brand,
			String unsaturatedfat, String fat, String servingcategory,
			String typeofmeasurement, String protein, String defaultserving,
			String mlingram, String foodId, String saturatedfat,
			String category, boolean verified, String title, String pcstext,
			String sodium, String carbohydrates, String showonlysametype,
			String calories, String sugar, String measurementid,
			String cholesterol, String gramsperserving, String showmeasurement,
			String potassium) {

		this.serving_version = serving_version;
		this.categoryid = categoryid;
		this.fiber = fiber;
		this.headimage = headimage;
		this.pcsingram = pcsingram;
		this.brand = brand;
		this.unsaturatedfat = unsaturatedfat;
		this.fat = fat;
		this.servingcategory = servingcategory;
		this.typeofmeasurement = typeofmeasurement;
		this.protein = protein;
		this.defaultserving = defaultserving;
		this.mlingram = mlingram;
		this.foodId = foodId;
		this.saturatedfat = saturatedfat;
		this.category = category;
		this.verified = verified;
		this.title = title;
		this.pcstext = pcstext;
		this.sodium = sodium;
		this.carbohydrates = carbohydrates;
		this.showonlysametype = showonlysametype;
		this.calories = calories;
		this.sugar = sugar;
		this.measurementid = measurementid;
		this.cholesterol = cholesterol;
		this.gramsperserving = gramsperserving;
		this.showmeasurement = showmeasurement;
		this.potassium = potassium;

	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getFiber() {
		return fiber;
	}

	public void setFiber(String fiber) {
		this.fiber = fiber;
	}

	public String getHeadimage() {
		return headimage;
	}

	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}

	public String getPcsingram() {
		return pcsingram;
	}

	public void setPcsingram(String pcsingram) {
		this.pcsingram = pcsingram;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getUnsaturatedfat() {
		return unsaturatedfat;
	}

	public void setUnsaturatedfat(String unsaturatedfat) {
		this.unsaturatedfat = unsaturatedfat;
	}

	public String getFat() {
		return fat;
	}

	public void setFat(String fat) {
		this.fat = fat;
	}

	public String getServingcategory() {
		return servingcategory;
	}

	public void setServingcategory(String servingcategory) {
		this.servingcategory = servingcategory;
	}

	public String getTypeofmeasurement() {
		return typeofmeasurement;
	}

	public void setTypeofmeasurement(String typeofmeasurement) {
		this.typeofmeasurement = typeofmeasurement;
	}

	public String getProtein() {
		return protein;
	}

	public void setProtein(String protein) {
		this.protein = protein;
	}

	public String getDefaultserving() {
		return defaultserving;
	}

	public void setDefaultserving(String defaultserving) {
		this.defaultserving = defaultserving;
	}

	public String getMlingram() {
		return mlingram;
	}

	public void setMlingram(String mlingram) {
		this.mlingram = mlingram;
	}

	public String getFoodId() {
		return foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	public String getSaturatedfat() {
		return saturatedfat;
	}

	public void setSaturatedfat(String saturatedfat) {
		this.saturatedfat = saturatedfat;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean getVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	@InstantText(viewId = R.id.text_title)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPcstext() {
		if (pcstext.length() > 0) {
			return "- " + pcstext;
		}
		return pcstext;
	}

	public void setPcstext(String pcstext) {
		this.pcstext = pcstext;
	}

	public String getSodium() {
		return sodium;
	}

	public void setSodium(String sodium) {
		this.sodium = sodium;
	}

	public String getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(String carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public String getShowonlysametype() {
		return showonlysametype;
	}

	public void setShowonlysametype(String showonlysametype) {
		this.showonlysametype = showonlysametype;
	}

	@InstantText(viewId = R.id.text_calories)
	public String getCalories() {
		return calories + " kcal " + getPcstext();
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	public String getServing_version() {
		return serving_version;
	}

	public void setServing_version(String serving_version) {
		this.serving_version = serving_version;
	}

	public String getSugar() {
		return sugar;
	}

	public void setSugar(String sugar) {
		this.sugar = sugar;
	}

	public String getMeasurementid() {
		return measurementid;
	}

	public void setMeasurementid(String measurementid) {
		this.measurementid = measurementid;
	}

	public String getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(String cholesterol) {
		this.cholesterol = cholesterol;
	}

	public String getGramsperserving() {
		return gramsperserving;
	}

	public void setGramsperserving(String gramsperserving) {
		this.gramsperserving = gramsperserving;
	}

	public String getShowmeasurement() {
		return showmeasurement;
	}

	public void setShowmeasurement(String showmeasurement) {
		this.showmeasurement = showmeasurement;
	}

	public String getPotassium() {
		return potassium;
	}

	public void setPotassium(String potassium) {
		this.potassium = potassium;
	}

}
