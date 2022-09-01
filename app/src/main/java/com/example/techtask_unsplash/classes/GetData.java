package com.example.techtask_unsplash.classes;

public class GetData {
    private String urlImg;
    private String imgCreator;
    private String imgName;

    public GetData(String urlImg, String imgCreator, String imgName) {
        this.urlImg = urlImg;
        this.imgCreator = imgCreator;
        this.imgName = imgName;
    }

    public String getImageUrl() {
        return urlImg;
    }

    public String getImageCreator() {
        return imgCreator;
    }

    public String getImageName() {
        return imgName;
    }


}
