package au.com.ofigroup.roomwordsample;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    private static volatile WordRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                WordDao dao = INSTANCE.wordDao();
                dao.deleteAll();

                Word word = new Word("Hello");
                dao.insert(word);
                word = new Word("World");
                dao.insert(word);
                word = new Word("how");
                dao.insert(word);
                word = new Word("are");
                dao.insert(word);
                word = new Word("you");
                dao.insert(word);
                word = new Word("today?");
                dao.insert(word);
                word = new Word("Everything");
                dao.insert(word);
                word = new Word("is");
                dao.insert(word);
                word = new Word("awesome");
                dao.insert(word);
                word = new Word("everything");
                dao.insert(word);
                word = new Word("is cool");
                dao.insert(word);
                word = new Word("when");
                dao.insert(word);
                word = new Word("you're");
                dao.insert(word);
                word = new Word("part");
                dao.insert(word);
                word = new Word("of");
                dao.insert(word);
                word = new Word("the");
                dao.insert(word);
                word = new Word("team");
                dao.insert(word);
            });
        }
    };
}
