package com.example.everis.android.androidbasicoaplicado.model;

import java.io.Serializable;

public class MarvelCharacter implements Serializable {

    public static final int SIZE_SMALL = 1;
    public static final int SIZE_MEDIUM = 2;
    public static final int SIZE_XLARGE = 3;
    public static final int SIZE_FANTASTIC = 4;
    public static final int SIZE_UNCANNY = 5;
    public static final int SIZE_INCREDIBLE = 6;

    private String name;
    private String description;
    private String thumbnailUrl;
    private String thumbnailExtension;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getThumbnailExtension() {
        return thumbnailExtension;
    }

    public void setThumbnailExtension(String thumbnailExtension) {
        this.thumbnailExtension = thumbnailExtension;
    }

    public String getImageUrl(int size) {
        String variant = null;
        switch (size) {
            case SIZE_INCREDIBLE:
                variant = "/portrait_incredible";
                break;

            case SIZE_UNCANNY:
                variant = "/portrait_uncanny";
                break;

            case SIZE_FANTASTIC:
                variant = "/portrait_fantastic";
                break;

            case SIZE_XLARGE:
                variant = "/portrait_xlarge";
                break;

            case SIZE_MEDIUM:
                variant = "/portrait_medium";
                break;

            case SIZE_SMALL:
            default:
                variant = "/portrait_small";
                break;
        }

        return thumbnailUrl + variant + "." + thumbnailExtension;
    }
}
