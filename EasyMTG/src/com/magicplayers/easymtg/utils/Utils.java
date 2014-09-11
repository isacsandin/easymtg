package com.magicplayers.easymtg.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Utils {
	public static Bitmap getBitmapFromURL(String src) {
		try {
			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			return myBitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
              int count=is.read(bytes, 0, buffer_size);
              if(count==-1)
                  break;
              os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
    
    public String md5checksum(File file) {
    	  try {
    	    InputStream fin = new FileInputStream(file);
    	    java.security.MessageDigest md5er =
    	        MessageDigest.getInstance("MD5");
    	    byte[] buffer = new byte[1024];
    	    int read;
    	    do {
    	      read = fin.read(buffer);
    	      if (read > 0)
    	        md5er.update(buffer, 0, read);
    	    } while (read != -1);
    	    fin.close();
    	    byte[] digest = md5er.digest();
    	    if (digest == null)
    	      return null;
    	    String strDigest = "0x";
    	    for (int i = 0; i < digest.length; i++) {
    	      strDigest += Integer.toString((digest[i] & 0xff) 
    	                + 0x100, 16).substring(1).toUpperCase();
    	    }
    	    return strDigest;
    	  } catch (Exception e) {
    	    return null;
    	  }
    	}

}
