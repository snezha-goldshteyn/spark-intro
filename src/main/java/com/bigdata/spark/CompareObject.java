package com.bigdata.spark;

public class CompareObject {
    private String artist1;
    private String artist2;
    private int amount;

    public CompareObject(String artist1, String artist2, int amount) {
        this.artist1 = artist1;
        this.artist2 = artist2;
        this.amount = amount;
    }

    public String getArtist1() {
        return artist1;
    }

    public void setArtist1(String artist1) {
        this.artist1 = artist1;
    }

    public String getArtist2() {
        return artist2;
    }

    public void setArtist2(String artist2) {
        this.artist2 = artist2;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CompareObject{" +
                "artist1='" + artist1 + '\'' +
                ", artist2='" + artist2 + '\'' +
                ", amount=" + amount +
                '}';
    }
}
