package com.example.everis.android.androidbasicoaplicado.model;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Api {

    public interface ApiCallListener {
        void success(String response);
    }

    public static class ApiCallPost extends AsyncTask<String, String, String> {

        private ApiCallListener mListener;

        public ApiCallPost(ApiCallListener listener) {
            mListener = listener;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String res = null;

            String urlString = params[0]; // URL to call
            String data = params[1]; //data to post

            OutputStream out = null;
            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestMethod("POST");

                out = new BufferedOutputStream(urlConnection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                writer.write(data);
                writer.flush();
                writer.close();
                out.close();
                urlConnection.connect();
                res = readInputStreamToString(urlConnection);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            return res;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            if (mListener != null) {
                mListener.success(response);
            }
        }
    }

    public static class ApiCallGet extends AsyncTask<String, String, String> {
        private ApiCallListener mListener;

        public ApiCallGet(ApiCallListener listener) {
            mListener = listener;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String res = null;
            String urlString = params[0]; // URL to call

            OutputStream out = null;
            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                res = readInputStreamToString(urlConnection);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            return res;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            if (mListener != null) {
                mListener.success(response);
            }
        }
    }

    private static String readInputStreamToString(HttpURLConnection connection) {
        String result;
        StringBuffer sb = new StringBuffer();
        InputStream is = null;

        try {
            is = new BufferedInputStream(connection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}