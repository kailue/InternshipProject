package com.example.kailue.internshipproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ChecklistTest extends AppCompatActivity {

    private Button btnpdf, btnopen, send;
    private EditText filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklisttest);

        filename = (EditText) findViewById(R.id.filename);

        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String token = FirebaseInstanceId.getInstance().getToken();
//                sendNotification(token);
//                sendNotification("cyUW6l0y_YI:APA91bHhoeOStjcj6gZ8Xf5vxOmkISrfs-cpWd5IprIQPwQrRSL2Ug20H5J-F_WK1lmF0gYZvtHo3uw0AOPnzmS7Iblior0vfb8JfutTtwjLdgRVn2AtDUBTyZsCppE-idAzTBjIL3Fh");
                new AsyncNotify().execute("cyUW6l0y_YI:APA91bHhoeOStjcj6gZ8Xf5vxOmkISrfs-cpWd5IprIQPwQrRSL2Ug20H5J-F_WK1lmF0gYZvtHo3uw0AOPnzmS7Iblior0vfb8JfutTtwjLdgRVn2AtDUBTyZsCppE-idAzTBjIL3Fh");
            }
        });

        btnpdf = (Button) findViewById(R.id.btnpdf);
        btnpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatePDF();
            }
        });

        btnopen = (Button) findViewById(R.id.btnopen);
        btnopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    File f = new File(Environment.getExternalStorageDirectory() + "/MyApp/" + filename.getText().toString().trim() + ".pdf");
//                    Uri uri = Uri.fromFile(f);
                    Uri uri = FileProvider.getUriForFile(ChecklistTest.this, BuildConfig.APPLICATION_ID + ".provider", f);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(uri, "application/pdf");
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(intent);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    private void generatePDF() {
        int pageNumber = 1;
        // create a new document
        PdfDocument document = new PdfDocument();

        // crate a page description
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, pageNumber).create();

        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);

//        // draw something on the page
//        View content = findViewById(R.id.activity_checklisttest);   ////  for using the xml itself as canvas

        Canvas canvas = page.getCanvas();

        int titleBaseLine = 72;
        int leftMargin = 54;

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);
        canvas.drawText("Test Print Document Page " + pageNumber, leftMargin, titleBaseLine, paint);

        paint.setTextSize(14);
        canvas.drawText("This is some test content to verify that custom document printing works", leftMargin, titleBaseLine + 35, paint);

//        content.draw(canvas);   ///// draw the xml itself onto the canvas

        // finish the page
        document.finishPage(page);

        // add more pages

        // write the document content
        try {
            File dir = new File(Environment.getExternalStorageDirectory() + "/MyApp");
            if (!dir.exists()) {
                dir.mkdir();
            }

            File f = new File(Environment.getExternalStorageDirectory() + "/MyApp/" + filename.getText().toString().trim() + ".pdf");
            FileOutputStream fos = new FileOutputStream(f);
            document.writeTo(fos);
            document.close();
            fos.close();

            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
        // close the document
    }

    public void sendNotification(String userDeviceIdKey) {
//        public final static String AUTH_KEY_FCM = "AIzaSyAM3Kki9BrdfIUIt-hVbaySrifEW2jln5s";
//        public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

        String AUTH_KEY_FCM = "AIzaSyAM3Kki9BrdfIUIt-hVbaySrifEW2jln5s";
        String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";


        String authKey = AUTH_KEY_FCM;   // You FCM AUTH key
        String FMCurl = API_URL_FCM;

        try {
            URL url = new URL(FMCurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "key=" + authKey);
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject json = new JSONObject();
            json.put("to", userDeviceIdKey.trim());
            JSONObject info = new JSONObject();
            info.put("title", "Notificatoin Title");   // Notification title
            info.put("body", "Hello Test notification"); // Notification body
            json.put("notification", info);

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(json.toString());
            wr.flush();
            conn.getInputStream();
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }



    private class AsyncNotify extends AsyncTask<String, String, String> {
        public static final int CONNECTION_TIMEOUT=10000;
        public static final int READ_TIMEOUT=15000;

        ProgressDialog pdLoading = new ProgressDialog(ChecklistTest.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected String doInBackground(String... params) {
            try {

                url = new URL("https://fcm.googleapis.com/fcm/send");

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "exception";
            }
            try {
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestProperty("Authorization", "key=AAAAl1zpGS4:APA91bE5LU6YGt7vs1fJCTps9GdGDtPKRtDPtQIcqjF1IuqtodSnxADS5RMlBou-W_0CHpQm6PtJnARSGKGQkJX0L0xzq9kz7TOs_ixeZuwt4wB4_yzi-bMh1AsQG4ln_UJnhilEYT-3");
                conn.setRequestProperty("Content-Type", "application/json");

                JSONObject json = new JSONObject();
                json.put("to", params[0].trim());
                JSONObject info = new JSONObject();
                info.put("title", "Notification Title");   // Notification title
                info.put("body", "Hello Test notification"); // Notification body
                json.put("notification", info);

//                Uri.Builder builder = new Uri.Builder()
//                        .appendQueryParameter("username", params[0])
//                        .appendQueryParameter("password", params[1]);
//                String query = builder.build().getEncodedQuery();
//
//                // Open connection for sending data
//                OutputStream os = conn.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
//                writer.write(query);
//                writer.flush();
//                writer.close();
//                os.close();
//                conn.connect();

                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(json.toString());
                wr.flush();
//                conn.getInputStream();
                conn.connect();

            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();
                System.out.println(response_code);
                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return(result.toString());

                }else{

                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread
            System.out.println(result);
            pdLoading.dismiss();

//            token.setText(result);

//            try {
//                JSONObject obj = new JSONObject(result);
//                token.setText(obj.getString("token"));
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }




//            if(result.equalsIgnoreCase("true"))
//            {
//                /* Here launching another activity when login successful. If you persist login state
//                use sharedPreferences of Android. and logout button to clear sharedPreferences.
//                 */
//
//                Intent intent = new Intent(Login.this,TabsActivity.class);
//                startActivity(intent);
//                Login.this.finish();
//
//            }else if (result.equalsIgnoreCase("false")){
//
//                // If username and password does not match display a error message
//                Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_LONG).show();
//
//            } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {
//
//                Toast.makeText(getApplicationContext(), "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();
//
//            }
        }

    }
}
