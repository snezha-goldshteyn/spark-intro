package com.bigdata.spark.popularwords.service.interfaces;

import java.util.List;

public interface ArtistJudge {
    List<String> topX(String artist, int x);
    int compare(String artist1, String artist2, int x);
}
