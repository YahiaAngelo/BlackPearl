package com.angelo.blackpearl;

import android.app.Application;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class PostViewModel extends AndroidViewModel {

    private PostDao postDao;
    private ExecutorService executorService;

    public PostViewModel(@NonNull Application application) {
        super(application);
        postDao = PostDatabase.getInstance(application).postDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    LiveData<List<Post>> getAllPosts() {
        return postDao.findLiveData();
    }

    void savePost(Post post) {
        executorService.execute(() -> postDao.save(post));
    }

    void deletePost(Post post) {
        executorService.execute(() -> postDao.delete(post));
    }
}