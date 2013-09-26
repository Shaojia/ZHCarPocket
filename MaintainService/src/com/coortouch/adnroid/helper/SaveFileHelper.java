package com.coortouch.adnroid.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.widget.Toast;

public class SaveFileHelper {
	private static final String DIR = "/sharpvision/images/";

	public static void save2SDCard(String imageUrl, Context context) {
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
		File file;
		if (!sdCardExist) {
			Toast.makeText(context, "sdcard don't exist!", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		String imagePath =
				 "/data/data/com.coortouch.app.activity/cache/http/";
		try {
			imagePath+= "cache_"+ URLEncoder.encode(imageUrl.replace("*", ""),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
		if (bitmap==null) {
			Toast.makeText(context, "picture don't exist!", Toast.LENGTH_SHORT).show();
			return;
		}

		file = new File(getSDPath());

		if (!file.exists()) {
			file.mkdirs();
		}

		String fileName=getFileName();
		File imageFile = new File(file, fileName);
		try {
			imageFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(imageFile);
			bitmap.compress(CompressFormat.PNG, 100, fos);

			Toast.makeText(context, "save success£¡Have saved to "+DIR+fileName, Toast.LENGTH_SHORT).show();

			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Toast.makeText(context, "save fail£¡", Toast.LENGTH_SHORT).show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(context, "save fail£¡", Toast.LENGTH_SHORT).show();

		}
	}

	public static String getSDPath() {
		File sdDir = Environment.getExternalStorageDirectory();

		return sdDir.toString() + DIR;
	}

	public static String getFileName() {
		String fileName = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date curDate = new Date(System.currentTimeMillis());
		fileName = formatter.format(curDate) + ".png";
		return fileName;
	}
}
