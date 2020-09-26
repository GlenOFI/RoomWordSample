package au.com.ofigroup.roomwordsample;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {
    @PrimaryKey
    @NonNull
    private  String word;

    public Word(@NonNull String word) {
        this.word = word;
    }

    public String getWord(){
        return this.word;
    }
}
