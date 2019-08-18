package com.bigdata.spark.popularwords.controller;

import com.bigdata.spark.CompareObject;
import com.bigdata.spark.popularwords.service.interfaces.ArtistJudge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("compare")
public class CompareController {

    @Autowired
    public ArtistJudge artistJudge;

    @PostMapping("result")
    public String comparisonResult (@RequestBody CompareObject compareObject) {
        List<String> listArtist1 = artistJudge.topX(compareObject.getArtist1(), compareObject.getAmount());
        List<String> listArtist2 = artistJudge.topX(compareObject.getArtist2(), compareObject.getAmount());
        int count = 0;
        for (String s: listArtist1) {
            if (listArtist2.contains(s)) {
                count++;
            }
        }
        return compareObject.getArtist1() + " and " + compareObject.getArtist2() + " have " + count + " common words.";
    }

    @GetMapping("popular")
    public String getPopularWords(@RequestParam String artist, @RequestParam int amount) {
        List<String> popularWords = artistJudge.topX(artist, amount);
        StringBuilder sb = new StringBuilder(artist+ ": ");
        for (String s: popularWords) {
            sb.append(s);
            sb.append(", ");
        }
        return sb.toString();
    }
}
