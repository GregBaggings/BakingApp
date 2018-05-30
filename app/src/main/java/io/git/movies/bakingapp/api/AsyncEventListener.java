package io.git.movies.bakingapp.api;

public interface AsyncEventListener {

    void onSuccess(String response);
    void onFailure(Exception e);

}
