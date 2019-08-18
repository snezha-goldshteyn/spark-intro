package com.bigdata.spark.withsqlcontext.controller;

import com.bigdata.spark.CompareObject;
import com.bigdata.spark.withsqlcontext.service.PopularWordsServiceUDF;
import com.bigdata.spark.withsqlcontext.utils.WordDataFrameCreator;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("compare")
public class ControllerWithFrames {
    String commonPath = "/Users/bodiabuzynovskyi/Desktop/Snezha/songs/";

    @Autowired
    public PopularWordsServiceUDF popularWordsServiceUDF;

    @Autowired
    public WordDataFrameCreator creator;

    @PostMapping("result")
    public String comparisonResult (@RequestBody CompareObject compareObject) {
        String path1 = commonPath + compareObject.getArtist1() + "/*";
        String path2 = commonPath + compareObject.getArtist2() + "/*";
        Dataset<Row> textsArtist1 = creator.create(path1);
        Dataset<Row> textsArtist2 = creator.create(path2);
        List<String> listWordsArtist1 = popularWordsServiceUDF.mostUsedWords(textsArtist1, compareObject.getAmount());
        List<String> listWordsArtist2 = popularWordsServiceUDF.mostUsedWords(textsArtist2, compareObject.getAmount());
        int count = 0;
        for (String s: listWordsArtist1) {
            if (listWordsArtist2.contains(s)) {
                count++;
            }
        }
        return compareObject.getArtist1() + " and " + compareObject.getArtist2() + " have " + count + " common words.";
    }

    @GetMapping("popular")
    public String getPopularWords(@RequestParam String artist, @RequestParam int amount) {
        String path = commonPath + artist + "/*";
        Dataset<Row> textsArtist = creator.create(path);
        List<String> popularWords = popularWordsServiceUDF.mostUsedWords(textsArtist, amount);
        StringBuilder sb = new StringBuilder(artist+ ": ");
        for (String s: popularWords) {
            sb.append(s);
            sb.append(", ");
        }
        return sb.toString();
    }

}
