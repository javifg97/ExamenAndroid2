package com.example.milib.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.milib.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by javier.fernandez3 on 23/01/2018.
 */

public class HttpJsonAsyncTaskLogin extends AsyncTask<Usuario,Integer,String> {

public static  final String REQUEST_METHOD = "GET";
public static final  int READ_TIMEOUT = 15000;
public static final  int CONNECTION_TIMEOUT = 15000;

public HttpJsonAsyncTaskLogin(){

        }

@Override
protected void onPreExecute() {
        super.onPreExecute();
        }

@Override
protected String doInBackground(Usuario... param) {
    String result = null;
    String inputLine;
    JSONObject jsonParam;
    try {
        jsonParam = new JSONObject();
        jsonParam.put("user", param[0].getUsuario());
        jsonParam.put("pass", param[0].getPass());

        Log.v("HttpJsonAsyncTaskLogin",jsonParam.toString());

        //Create a URL object holding our url
        URL myUrl = new URL("http://10.0.2.2/Javi/Android/leeUsuario.php");
        //Create a connection
        HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
        //Set methods and timeouts
        connection.setRequestMethod(REQUEST_METHOD);
        connection.setReadTimeout(READ_TIMEOUT);
        connection.setConnectTimeout(CONNECTION_TIMEOUT);

        //Connect to our url
        connection.connect();
        //Create a new InputStreamReader
        InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
        //Create a new buffered reader and String Builder
        BufferedReader reader = new BufferedReader(streamReader);
        StringBuilder stringBuilder = new StringBuilder();
        //Check if the line we are reading is not null
        while ((inputLine = reader.readLine()) != null) {
            stringBuilder.append(inputLine);
        }
        //Close our InputStream and Buffered reader
        reader.close();
        streamReader.close();
        //Set our result equal to our stringBuilder
        result = stringBuilder.toString();
    } catch (IOException e) {
        e.printStackTrace();
        result = null;
    } catch (JSONException e) {
        e.printStackTrace();
    }
    return result;
}

@Override
protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        }

@Override
protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.v("HttpJsonAsyncTask","JSON: "+s);
        }

}
