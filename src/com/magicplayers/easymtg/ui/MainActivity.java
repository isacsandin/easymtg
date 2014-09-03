package com.magicplayers.easymtg.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.magicplayers.easymtg.R;
import com.magicplayers.easymtg.ui.tabs.DummySectionFragment;
import com.magicplayers.easymtg.ui.tabs.ListViewFragment;
import com.magicplayers.easymtg.ui.tabs.SearchFragment;

public class MainActivity extends ActionBarActivity implements TabListener {

	private AppSectionsPagerAdapter mAppSectionsPagerAdapter;
	private ViewPager mViewPager;
	public String COMPRESSED_DATABASE_PATH;
	public String DATABASE_PATH;
	public String ROOT_PATH;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ROOT_PATH = getExternalFilesDir(null).getAbsolutePath()+"/databases";
	    File myDir = new File(ROOT_PATH);    
	    myDir.mkdirs();
	    COMPRESSED_DATABASE_PATH = ROOT_PATH+"/easymtg.sqlite.gz";
	    DATABASE_PATH = ROOT_PATH+"/easymtg.sqlite";

		mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(
				getSupportFragmentManager());

		final ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mAppSectionsPagerAdapter);
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab()
					.setText(mAppSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		
		File compressedFile = new File(COMPRESSED_DATABASE_PATH);
		File databaseFile = new File(DATABASE_PATH);
		Log.e("BLABLA",COMPRESSED_DATABASE_PATH);
		if(!compressedFile.exists()){
			DownloadTask downloadTask = new DownloadTask(this,COMPRESSED_DATABASE_PATH);
			downloadTask.execute("https://dl.dropboxusercontent.com/u/5958311/database/current.sqlite.gz");
		}
	}

	public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

		public AppSectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			switch (i) {
			case 0:
				return new ListViewFragment();
			case 1:
				return new SearchFragment();
			default:
				Fragment fragment = new DummySectionFragment();
				Bundle args = new Bundle();
				args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
				fragment.setArguments(args);
				return fragment;
			}
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return "Deck Library";
			case 1:
				return "Card Search";
			default:
				return "Life Counter";
			}
		}
	}
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
		
	//usually, subclasses of AsyncTask are declared inside the activity class.
	//that way, you can easily modify the UI thread from here
	private class DownloadTask extends AsyncTask<String, Integer, String> {

		private Context context;
		private PowerManager.WakeLock mWakeLock;
		private String outputFilename;
		private ProgressDialog mProgressDialog;

		public DownloadTask(Context context, String outpuFilename) {
			this.context = context;
			this.outputFilename = outpuFilename;
			mProgressDialog = new ProgressDialog(this.context);
			mProgressDialog.setMessage("Downloading data...");
			mProgressDialog.setIndeterminate(true);
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setCancelable(false);
		}

		@Override
		protected String doInBackground(String... sUrl) {
			InputStream input = null;
			OutputStream output = null;
			HttpURLConnection connection = null;
			try {
				URL url = new URL(sUrl[0]);
				connection = (HttpURLConnection) url.openConnection();
				connection.connect();

				// expect HTTP 200 OK, so we don't mistakenly save error report
				// instead of the file
				if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
					return "Server returned HTTP " + connection.getResponseCode()
							+ " " + connection.getResponseMessage();
				}

				// this will be useful to display download percentage
				// might be -1: server did not report the length
				int fileLength = connection.getContentLength();

				// download the file
				input = connection.getInputStream();
				output = new FileOutputStream(this.outputFilename);

				byte data[] = new byte[4096];
				long total = 0;
				int count;
				while ((count = input.read(data)) != -1) {
					// allow canceling with back button
					if (isCancelled()) {
						if (input != null)
							input.close();
						return null;
					}
					total += count;
					// publishing the progress....
					if (fileLength > 0) // only if total length is known
						publishProgress((int) (total * 100 / fileLength));
					output.write(data, 0, count);
				}
			} catch (Exception e) {
				return e.toString();
			} finally {
				try {
					if (output != null)
						output.close();
					if (input != null)
						input.close();
				} catch (IOException ignored) {
				}

				if (connection != null)
					connection.disconnect();
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// take CPU lock to prevent CPU from going off if the user
			// presses the power button during download
			PowerManager pm = (PowerManager) context
					.getSystemService(Context.POWER_SERVICE);
			mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass()
					.getName());
			mWakeLock.acquire();
			mProgressDialog.show();
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			super.onProgressUpdate(progress);
			// if we get here, length is known, now set indeterminate to false
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.setMax(100);
			mProgressDialog.setProgress(progress[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			DecompressTask decompressTask = new DecompressTask(context,COMPRESSED_DATABASE_PATH,DATABASE_PATH);
			decompressTask.execute();
			mWakeLock.release();
			mProgressDialog.dismiss();
			if (result != null)
				Toast.makeText(context, "Download error: " + result,
						Toast.LENGTH_LONG).show();
			else
				Toast.makeText(context, "File downloaded", Toast.LENGTH_SHORT)
						.show();
		}
	}
	
	private class DecompressTask extends AsyncTask<Void, Integer, Integer> {

		   private String mZipFile;   
		   private String mLocation;
		   private Context context;
		   private PowerManager.WakeLock mWakeLock;
		   private ProgressDialog mProgressDialog;
		   private int per = 0;

		    public DecompressTask(Context context, String input, String output) {
		            this.mZipFile = input;     
		            this.mLocation = output;
		            this.context = context;
					mProgressDialog = new ProgressDialog(this.context);
					mProgressDialog.setMessage("Decompressing data...");
					mProgressDialog.setIndeterminate(true);
					mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
					mProgressDialog.setCancelable(false);
		    }
			@Override
		     protected Integer doInBackground(Void... params) {
		        	 byte[] buffer = new byte[1024];
		        	 int total_bites_read = 0;
		     		 try {
		     			FileInputStream fileIn = new FileInputStream(mZipFile);
		     			long fileLength = fileIn.getChannel().size();
		     			GZIPInputStream gZIPInputStream = new GZIPInputStream(fileIn);
		     			FileOutputStream fileOutputStream = new FileOutputStream(mLocation);
		     			int bytes_read;
		     			while ((bytes_read = gZIPInputStream.read(buffer)) > 0) {
		     				fileOutputStream.write(buffer, 0, bytes_read);
			     			total_bites_read += bytes_read;
			     			publishProgress((int) (total_bites_read * 100 / fileLength));
		     			}
		     			gZIPInputStream.close();
		     			fileOutputStream.close();
		     			
		     		} catch (IOException ex) {
		     			ex.printStackTrace();
		     		}
		     		return total_bites_read;
		     }
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				// take CPU lock to prevent CPU from going off if the user
				// presses the power button during download
				PowerManager pm = (PowerManager) context
						.getSystemService(Context.POWER_SERVICE);
				mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass()
						.getName());
				mWakeLock.acquire();
				mProgressDialog.show();
			}
		    @Override
		     protected void onProgressUpdate(Integer... progress) {
		    	super.onProgressUpdate(progress);
				mProgressDialog.setIndeterminate(false);
				mProgressDialog.setMax(100);
				mProgressDialog.setProgress(progress[0]);
		    }
		    @Override
		    protected void onPostExecute(Integer result) {
		    	mWakeLock.release();
		    	new AlertDialog.Builder(context)
		        //.setIcon(android.R.drawable.ic_dialog_alert)
		        .setTitle("Restart App")
		        .setMessage("All done, now click ok to restart application")
		        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
				    {
				        @Override
				        public void onClick(DialogInterface dialog, int which) {
				           Intent i = getBaseContext().getPackageManager()
				                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
					       i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					       startActivity(i);   
				        }
			
				    })
			    .show();
		    	 mProgressDialog.dismiss();
		         Log.i("TAG","Completed. Total size: "+result);
		    }
	 }		     
}

