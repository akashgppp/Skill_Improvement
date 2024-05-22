package com.skysoftsolution.in.skill_improvement.CameraX;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.widget.ImageView;
import androidx.camera.core.ImageProxy;

import com.skysoftsolution.in.skill_improvement.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class CameraworkerThread extends AsyncTask<Void, Long, Bitmap> {
    Context context;
    ImageView imageView;
    ProgressDialog progressDialog = null;
    String filestrpath;
    byte[] imgbyte = null;
    File outfile = null;
    ImageProxy imageProxy = null;

    public CameraworkerThread(Context context, File outfile, ImageView imageView, ImageProxy imageProxy) {
        this.context = context;
        this.outfile = outfile;
        this.imageView = imageView;
        this.imageProxy = imageProxy;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(context.getResources().getString(R.string.please_wait));
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        try {
            FileOutputStream out = new FileOutputStream(outfile);
            byte[] imgbyte = ImageUtil.imageToJpegByteArray(imageProxy);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imgbyte);

            out.write(imgbyte);
            out.flush();
            out.close();
            byteArrayInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        filestrpath = outfile.getAbsolutePath();

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.max(1, Math.min(photoW / 600, photoH / 600));

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(filestrpath, bmOptions);
        } catch (Exception e) {
            e.printStackTrace();

        }
        Bitmap finalbitmap = null;

        if (bitmap != null) {
            try {
                finalbitmap = ImageUtil.cropAndScale(bitmap, 600);
                float degrees = 0;
                Matrix matrix = new Matrix();
                matrix.setRotate(degrees);
                finalbitmap = Bitmap.createBitmap(finalbitmap, 0, 0, finalbitmap.getWidth() - 50, finalbitmap.getHeight(), matrix, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (filestrpath != null) {
            File file = new File(filestrpath);
            if (file.exists()) {
                file.delete();
            }
        }

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            finalbitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos);

            imgbyte = null;
            imgbyte = bos.toByteArray();

            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(imgbyte);
            try (FileOutputStream out = new FileOutputStream(filestrpath)) {
                out.write(imgbyte);
                out.flush();
                out.close();
                bos.close();
                byteArrayInputStream2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            filestrpath = outfile.getAbsolutePath();

        } catch (Exception e) {

        }
        return finalbitmap;
    }

    @Override
    protected void onPostExecute(Bitmap o) {
        super.onPostExecute(o);
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        if (o != null) {
            imageView.setImageBitmap(o);
        }
    }

}