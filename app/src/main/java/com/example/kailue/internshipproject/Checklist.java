package com.example.kailue.internshipproject;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class Checklist extends AppCompatActivity {

    private Button btnpdf, btnopen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);

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
                File f = new File(Environment.getExternalStorageDirectory() + "/MyApp/test.pdf");
                Uri uri = Uri.fromFile(f);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "application/pdf");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
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
//        View content = findViewById(R.id.activity_checklist);   ////  for using the xml itself as canvas

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

            File f = new File(Environment.getExternalStorageDirectory() + "/MyApp/test.pdf");
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

}
