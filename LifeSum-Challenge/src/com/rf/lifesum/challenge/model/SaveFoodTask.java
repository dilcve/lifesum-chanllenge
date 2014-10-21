package com.rf.lifesum.challenge.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.rf.lifesum.challenge.R;

/**
 * Async Task which shows a progress dialog while saves data into database
 * 
 */
public class SaveFoodTask extends AsyncTask<Void, Void, Void> {

	private ProgressDialog dialog;
	private FoodModel food;
	private Context context;

	public SaveFoodTask(Context context, FoodModel food) {
		this.food = food;
		this.context = context;
	}

	@Override
	protected Void doInBackground(Void... params) {
		// Saving data into database
		food.save();
		return null;
	}

	@Override
	protected void onPreExecute() {
		// Adding a busy dialog
		dialog = new ProgressDialog(context);
		dialog.setIndeterminate(true);
		dialog.setCancelable(false);
		dialog.setInverseBackgroundForced(false);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setMessage(context.getString(R.string.text_saving));
		// we show the dialog
		dialog.show();
	}

	@Override
	protected void onProgressUpdate(Void... param) {
	}

	@Override
	protected void onPostExecute(Void param) {
		dialog.dismiss();
	}
}
