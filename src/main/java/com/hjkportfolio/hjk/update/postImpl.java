package com.hjkportfolio.hjk.update;

import java.util.List;

public interface postImpl {
    public List<Class<?>> getPostList();
    public Class<?> getPost();
    public void insertPost();
    public void deletePost();
    public void updatePost();
}
