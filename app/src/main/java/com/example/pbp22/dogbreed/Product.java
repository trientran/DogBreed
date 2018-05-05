package com.example.pbp22.dogbreed;

public class Product {
    private String image;
    private String breedName;

    public Product(String image, String breedName) {
        this.image = image;
        this.breedName = breedName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setName(String breedName) {
        this.breedName = breedName;
    }
}
