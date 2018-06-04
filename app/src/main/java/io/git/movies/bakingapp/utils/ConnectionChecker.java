package io.git.movies.bakingapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class ConnectionChecker {

    public static boolean checkInternetConnection(Context context) throws NullPointerException {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
