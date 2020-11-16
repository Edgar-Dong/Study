package com.android.example.ui.bitmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.example.R;
import com.android.example.common.log.Logger;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class BitmapActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"
    };

    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);

        ivImage = findViewById(R.id.ivImage);

        verifyStoragePermissions(this);

        //setBitmapForResource();

        //getBitmapForReuse();

        //getBitmapForCompressMemory();
    }

    private void setBitmap(ImageView ivImage) {
        String dcimPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        Logger.get().d(BitmapActivity.class.getSimpleName(), "setBitmap dcimPath:" + dcimPath);
        //4032 * 3024 px * 4 = 48771072
        String imagePath = dcimPath + File.separator + "Camera/IMG_20200910_105704.jpg";
        Logger.get().d(BitmapActivity.class.getSimpleName(), "setBitmap imagePath:" + imagePath);

        File imageFile = new File(imagePath);
        Logger.get().d(BitmapActivity.class.getSimpleName(), "setBitmap imageFile exists:" + imageFile.exists() + ", imageFilePath:" + imageFile.getAbsolutePath());

        BitmapFactory.Options options = new BitmapFactory.Options();
        //ARGB_8888:4bit
        //options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
        Logger.get().d(BitmapActivity.class.getSimpleName(), "setBitmap bitmap:" + bitmap);

        int bitmapSize = getBitmapSize(bitmap);
        Logger.get().d(BitmapActivity.class.getSimpleName(), "setBitmap bitmapSize:" + bitmapSize);

        //转换bitmap
        //创建模板
        Bitmap copyBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
        //创建画笔
        Paint paint = new Paint();
        //创建画布
        Canvas canvas = new Canvas(copyBitmap);
        //穿件矩阵（一般变化需要）
        Matrix matrix = new Matrix();
        //顺时针旋转90度，setXXX和postXXX的区别是setXXX会覆盖之前的设置，postXXX会在之前变换的基础上设置新的变换
        matrix.setRotate(90,bitmap.getWidth()/2,bitmap.getHeight()/2);
        //长宽缩小0.5
        matrix.postScale(0.5f,0.5f);
        //延Y轴正防线移动1000px
        matrix.postTranslate(0,1000);
        canvas.drawBitmap(bitmap,matrix,paint);

        //ivImage.setImageBitmap(bitmap);
        ivImage.setImageBitmap(copyBitmap);
    }

    private void setBitmapForResource() {
        //4032 * 3024 px * 4 = 48771072
        //width * height * (inTargetDensity/inDensity) * (inTargetDensity/inDensity) * 4
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image_pic, options);
        Logger.get().d(BitmapActivity.class.getSimpleName(), "setBitmapForResource bitmap:" + bitmap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Logger.get().d(BitmapActivity.class.getSimpleName(), "Bitmap byteCount:" + bitmap.getByteCount() + ", allocationByteCout" + bitmap.getAllocationByteCount());
        }
        Logger.get().d(BitmapActivity.class.getSimpleName(), "Bitmap width:" + bitmap.getWidth() + ", height" + bitmap.getHeight());
        Logger.get().d(BitmapActivity.class.getSimpleName(), "Bitmap options.inDensity:" + options.inDensity + ", options.inTargetDensity:" + options.inTargetDensity);

        Logger.get().d(BitmapActivity.class.getSimpleName(), "===================华丽分割线===================");

        BitmapFactory.Options setParamsOptions = new BitmapFactory.Options();
        setParamsOptions.inDensity = 480;
        setParamsOptions.inTargetDensity = 480;
        Bitmap setParamsBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image_pic, setParamsOptions);
        Logger.get().d(BitmapActivity.class.getSimpleName(), "setBitmapForResource setParamsBitmap:" + setParamsBitmap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Logger.get().d(BitmapActivity.class.getSimpleName(), "setParamsBitmap byteCount:" + setParamsBitmap.getByteCount() + ", allocationByteCout" + setParamsBitmap.getAllocationByteCount());
        }
        Logger.get().d(BitmapActivity.class.getSimpleName(), "setParamsBitmap width:" + setParamsBitmap.getWidth() + ", height" + setParamsBitmap.getHeight());
        Logger.get().d(BitmapActivity.class.getSimpleName(), "setParamsBitmap options.inDensity:" + setParamsOptions.inDensity + ", options.inTargetDensity:" + setParamsOptions.inTargetDensity);

    }

    private void getBitmapForCompressMemory() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        options.inDensity = 480;
        options.inTargetDensity = 480;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image_pic, options);
        Logger.get().d(BitmapActivity.class.getSimpleName(), "getBitmapForCompressMemory bitmap:" + bitmap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Logger.get().d(BitmapActivity.class.getSimpleName(), "bitmap byteCount:" + bitmap.getByteCount() + ", allocationByteCount:" + bitmap.getAllocationByteCount());
        }

        //rawWidth:3024 rawHeight:4032
        Bitmap compressBitmap = decodeSampledBitmapFromResource(getResources(), R.mipmap.image_pic, 1512, 2016);
        Logger.get().d(BitmapActivity.class.getSimpleName(), "compressBitmap:" + bitmap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Logger.get().d(BitmapActivity.class.getSimpleName(), "compressBitmap byteCount:" + compressBitmap.getByteCount() + ", allocationByteCount:" + compressBitmap.getAllocationByteCount());
        }
    }

    private Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = 4/*calculateInSampleSize(options, reqWidth, reqHeight)*/;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while ((halfHeight / inSampleSize >= reqHeight) && (halfWidth / inSampleSize >= reqWidth)) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private void getBitmapForCompress() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        options.inDensity = 480;
        options.inTargetDensity = 480;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image_pic, options);
        Logger.get().d(BitmapActivity.class.getSimpleName(), "getBitmapForCompress bitmap:" + bitmap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Logger.get().d(BitmapActivity.class.getSimpleName(), "bitmap byteCount:" + bitmap.getByteCount() + ", allocationByteCount:" + bitmap.getAllocationByteCount());
        }

        String compressFilePath = getExternalCacheDir().getAbsolutePath() + File.separator + "bitmap_compress";
        File dir = new File(compressFilePath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String fileName = "bitmap_compress_sample_01.jpg";
        String filePath = dir.getAbsolutePath() + File.separator + fileName;
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            Logger.get().d(BitmapActivity.class.getSimpleName(), "getBitmapForCompress error");
        }
        //质量压缩
        Logger.get().d(BitmapActivity.class.getSimpleName(), "bitmap compress file:" + file.getAbsolutePath() + ", length:" + file.length());

        //质量压缩，解码后内存占用空间不变
        BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
        decodeOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap decodeBitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), decodeOptions);
        Logger.get().d(BitmapActivity.class.getSimpleName(), "decodeBitmap:" + decodeBitmap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Logger.get().d(BitmapActivity.class.getSimpleName(), "decodeBitmap byteCount:" + decodeBitmap.getByteCount() + ", allocationByteCount:" + decodeBitmap.getAllocationByteCount());
        }
    }

    private void getBitmapForReuse() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        options.inDensity = 480;
        options.inTargetDensity = 480;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image_pic, options);
        Logger.get().d(BitmapActivity.class.getSimpleName(), "getBitmapForReuse bitmap:" + bitmap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Logger.get().d(BitmapActivity.class.getSimpleName(), "bitmap byteCount:" + bitmap.getByteCount() + ", allocationByteCount:" + bitmap.getAllocationByteCount());
        }

        //复用bitmap
        options.inBitmap = bitmap;
        options.inDensity = 480;
        options.inTargetDensity = 240;
        options.inMutable = true;
        Bitmap bitmapReuse = BitmapFactory.decodeResource(getResources(), R.mipmap.image_pic_reuse, options);
        Logger.get().d(BitmapActivity.class.getSimpleName(), "getBitmapForReuse bitmapReuse:" + bitmapReuse);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Logger.get().d(BitmapActivity.class.getSimpleName(), "bitmap byteCount:" + bitmap.getByteCount() + ", allocationByteCount:" + bitmap.getAllocationByteCount());
            Logger.get().d(BitmapActivity.class.getSimpleName(), "bitmapReuse byteCount:" + bitmapReuse.getByteCount() + ", allocationByteCount:" + bitmapReuse.getAllocationByteCount());
        }
    }

    private int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return bitmap.getAllocationByteCount();
        } else {
            return bitmap.getByteCount();
        }
    }

    private void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE");
        Logger.get().d(BitmapActivity.class.getSimpleName(), "verifyStoragePermissions permission:" + permission);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        } else {
            setBitmap(ivImage);
            //getBitmapForCompress();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Logger.get().d(BitmapActivity.class.getSimpleName(), "onRequestPermissionsResult requestCode:" + requestCode + ", permissions:" + Arrays.toString(permissions) + ", grantResults:" + Arrays.toString(grantResults));
        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限被同意
                setBitmap(ivImage);
                //getBitmapForCompress();
            } else {
                //权限被拒绝
                Toast.makeText(this, "申请的读写SD卡权限被拒绝！", Toast.LENGTH_SHORT).show();
            }
        }
    }
}