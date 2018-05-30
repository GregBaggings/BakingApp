package io.git.movies.bakingapp.api;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class AsyncRecipesRequestHandler extends AsyncTask<Call, Void, String> {

    private AsyncEventListener asyncEventListener;
    private Call<ResponseBody> myCall;
    private Context myContext;

    public AsyncRecipesRequestHandler(Call call, Context context, AsyncEventListener eventListener) {
        myCall = call;
        myContext = context;
        asyncEventListener = eventListener;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(Call... params) {
        try {
            myCall = params[0];
            Response<ResponseBody> response = myCall.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String response) {
        if (response != null) {
            if (asyncEventListener != null) {
                asyncEventListener.onSuccess(response);
            }
        } else {
            Toast.makeText(myContext, "No result", Toast.LENGTH_SHORT).show();
        }
    }
}
