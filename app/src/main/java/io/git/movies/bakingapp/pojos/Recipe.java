package io.git.movies.bakingapp.pojos;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe implements Parcelable {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("ingredients")
    private List<Ingredients> listOfIngredients;
    @SerializedName("steps")
    private List<Steps> listOfSteps;
    @SerializedName("servings")
    private int servings;
    @SerializedName("image")
    private  String image;


    protected Recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        listOfIngredients = in.createTypedArrayList(Ingredients.CREATOR);
        listOfSteps = in.createTypedArrayList(Steps.CREATOR);
        servings = in.readInt();
        image = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", listOfIngredients=" + listOfIngredients +
                ", listOfSteps=" + listOfSteps +
                ", servings=" + servings +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeTypedList(listOfIngredients);
        dest.writeTypedList(listOfSteps);
        dest.writeInt(servings);
        dest.writeString(image);
    }

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

    public List<Ingredients> getListOfIngredients() {
        return listOfIngredients;
    }

    public void setListOfIngredients(List<Ingredients> listOfIngredients) {
        this.listOfIngredients = listOfIngredients;
    }

    public List<Steps> getListOfSteps() {
        return listOfSteps;
    }

    public void setListOfSteps(List<Steps> listOfSteps) {
        this.listOfSteps = listOfSteps;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static Creator<Recipe> getCREATOR() {
        return CREATOR;
    }
}
