/*
* Copyright 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.user0.cardviewwebjson;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

public class CardViewFragment extends Fragment {

    private static final String TAG = CardViewFragment.class.getSimpleName();

     public CardViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.card_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CardView cv1 = view.findViewById(R.id.cv1);
        cv1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Toast toast = Toast.makeText(getActivity().getBaseContext(),"Waitng ... get IP-Adress", Toast.LENGTH_SHORT);
                toast.show();
                new ParseWebJsonTask_1().execute();
            }
        });

        CardView cv2 = view.findViewById(R.id.cv2);
        cv2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Toast toast = Toast.makeText(getActivity().getBaseContext(),"Waiting ...get Data and Time", Toast.LENGTH_SHORT);
                toast.show();
                new ParseWebJsonTask_2().execute();
            }
        });

        CardView cv3 = view.findViewById(R.id.cv3);
        cv3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder( getActivity() );
                alert.setTitle("Enter Keys and Values and press Submit");
                alert.setMessage("Key1:Value1:Key2:Value2...");

                // Set an EditText view to get user input
                final EditText input = new EditText( getActivity() );
                alert.setView(input);

                alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        String request = "http://echo.jsontest.com";

                        String[] tokens = input.getEditableText().toString().split(":");
                        for ( String token : tokens) {

                             request += "/" + token;
                        }
                        //Log.d(TAG, request);

                        Toast toast = Toast.makeText(getActivity() , "Waiting... Json Request Answer" , Toast.LENGTH_SHORT );
                        toast.show();
                        new ParseWebJsonTask_3().execute( request );
                    }

                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();

            }
        });

        CardView cv4 = view.findViewById(R.id.cv4);
        cv4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                // Set an Dialog EditText view to get user input
                AlertDialog.Builder alert = new AlertDialog.Builder( getActivity() );
                alert.setTitle("Enter JSON Object to validate and press Submit"); //Set Alert dialog title here
                alert.setMessage("JSON=");
                final EditText input = new EditText( getActivity() );
                alert.setView(input);

                alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        String request = "http://validate.jsontest.com/?json=" + input.getEditableText().toString() ;
//                      Log.d(TAG, request);
                        Toast toast = Toast.makeText( getActivity() , "Waiting... Json validating" , Toast.LENGTH_SHORT );
                        toast.show();
                        new ParseWebJsonTask_4().execute( request );
                    }

                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });

        CardView cv5 = view.findViewById(R.id.cv5);
        cv5.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Toast toast = Toast.makeText(getActivity().getBaseContext(),"Waiting... get Headers", Toast.LENGTH_SHORT);
                toast.show();
                new ParseWebJsonTask_5().execute();
            }
        });
    }

    private class ParseWebJsonTask_1 extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {

            try {
                //URL url = new URL("http://androiddocs.ru/api/friends.json");
                URL url = new URL("http://ip.jsontest.com/");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(String stringJson) {
            super.onPostExecute(stringJson);

            Log.d(TAG, stringJson);
            JSONObject dataJsonObj;

            try {
                dataJsonObj = new JSONObject(stringJson);
                TextView tv = getView().findViewById(R.id.cv1_text);
                tv.setText(dataJsonObj.getString("ip"));
                //Log.d(TAG, "ip:" + dataJsonObj.getString("ip") );
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private class ParseWebJsonTask_2 extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {

            try {
                URL url = new URL("http://date.jsontest.com");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(String stringJson) {
            super.onPostExecute(stringJson);
            // log full json
            Log.d(TAG, stringJson);
            JSONObject dataJsonObj;

            try {
                dataJsonObj = new JSONObject(stringJson);
                TextView tv = getView().findViewById(R.id.cv2_text);
                tv.setText(dataJsonObj.getString("date") + ", " + dataJsonObj.getString("time") );
                //Log.d(TAG, "ip:" + dataJsonObj.getString("ip") );
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private class ParseWebJsonTask_3 extends AsyncTask<String, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(String... params) {

            try {

                URL urlToRequest = new URL( params[0] );
                urlConnection = (HttpURLConnection) urlToRequest.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                int statusCode = urlConnection.getResponseCode();
                if (statusCode != HttpURLConnection.HTTP_OK) {

                    Toast toast = Toast.makeText(getActivity().getBaseContext(),"Not HTTP_OK!!", Toast.LENGTH_SHORT);
                    toast.show();

                }

                urlConnection.getInputStream();
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(String stringJson) {
            super.onPostExecute(stringJson);
            Log.d(TAG, stringJson);
            JSONObject dataJsonObj ;
            String out="";
            try {
                dataJsonObj = new JSONObject(stringJson);

                Iterator<String> it = dataJsonObj.keys();
                while ( it.hasNext() ) {

                    String key = it.next();
                    Object val = dataJsonObj.get( key );
                    out += key + ":" + val + "\n";
                }

                TextView tv = getView().findViewById(R.id.cv3_text);
                tv.setText( out );
                //Log.d(TAG, "ip:" + dataJsonObj.getString("ip") );
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private class ParseWebJsonTask_4 extends AsyncTask<String, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(String... params) {

            try {

                URL url = new URL( params[0] );

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();

            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(String stringJson) {
            super.onPostExecute(stringJson);

            Log.d(TAG, stringJson);

            TextView tv = getView().findViewById(R.id.cv4_text);
            tv.setText( stringJson );
        }
    }
    private class ParseWebJsonTask_5 extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {

            try {

                URL url = new URL("http://headers.jsontest.com");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();

            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(String stringJson) {
            super.onPostExecute(stringJson);

            Log.d(TAG, stringJson);

            JSONObject dataJsonObj ;
            String out ="";
            try {

                dataJsonObj = new JSONObject(stringJson);
                TextView tv = getView().findViewById(R.id.cv5_text);

                Iterator<String> it = dataJsonObj.keys();
                while ( it.hasNext() ) {

                    String key = it.next();
                    Object val = dataJsonObj.get( key );
                    out += key + ":" + val + "\n";
                }

                tv.setText( out );

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NullPointerException e ) {
                e.printStackTrace();
            }
        }
    }
}

