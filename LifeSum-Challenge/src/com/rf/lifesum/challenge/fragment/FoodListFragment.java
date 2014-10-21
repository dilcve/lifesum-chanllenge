package com.rf.lifesum.challenge.fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.mobsandgeeks.adapters.InstantAdapter;
import com.mobsandgeeks.adapters.ViewHandler;
import com.rf.lifesum.challenge.R;
import com.rf.lifesum.challenge.model.FoodModel;
import com.rf.lifesum.challenge.model.SaveFoodTask;

public class FoodListFragment extends Fragment {

	public static final String HTTPS_API_LIFESUM = "https://api.lifesum.com/v1/search/query?type=food&search=";
	private static final String TAG = "FoodListFragment";
	View mView, mProgressView, mPlaceHolderView;
	EditText mEditSearch;
	Button mButtonSearch;
	TextView mTextEmptyList;
	ListView mListView;
	GridView mGridView;
	List<FoodModel> foodListCopy;

	// List styles types
	static final int STYLE_LIST = 0;
	static final int STYLE_GRID = 1;
	int listStyle = STYLE_LIST;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.food_list_fragment, container, false);
		getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
		setHasOptionsMenu(true);

		loadViewComponents();
		loadListeners();

		if (foodListCopy != null && foodListCopy.size() > 0) {
			ensureUI(foodListCopy, listStyle);
		}

		return mView;
	}

	/*
	 * Method for load the views components
	 */
	public void loadViewComponents() {
		mProgressView = mView.findViewById(R.id.layout_loading);
		mEditSearch = (EditText) mView.findViewById(R.id.editSearch);
		mButtonSearch = (Button) mView.findViewById(R.id.buttonSearch);
		mPlaceHolderView = mView.findViewById(R.id.placeHolderLayout);
		mTextEmptyList = (TextView) mView.findViewById(R.id.text_emptyList);
		mListView = (ListView) mView.findViewById(R.id.list);
		mGridView = (GridView) mView.findViewById(R.id.gridview);
	}

	/*
	 * Method for load the principals listeners
	 */
	public void loadListeners() {
		mButtonSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String query = mEditSearch.getText().toString();
				searchForFood(query);

			}
		});

		mEditSearch.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					String query = mEditSearch.getText().toString();
					searchForFood(query);
					return true;
				}
				return false;
			}
		});
	}

	/*
	 * Method for perform the internet search
	 */
	public void searchForFood(String query) {
		hideKeyboard();
		if (query.length() > 0) {
			new GetFoodTask(query.trim()).execute();
		} else {
			Toast.makeText(getActivity(),
					getString(R.string.text_empty_search), Toast.LENGTH_SHORT)
					.show();
		}
	}

	/*
	 * Method for hide the keyboard
	 */
	public void hideKeyboard() {
		try {
			InputMethodManager imm = (InputMethodManager) getActivity()
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(getActivity().getCurrentFocus()
					.getWindowToken(), 0);
		} catch (Exception e) {
			Log.e(TAG, "error trying close the keyboard: " + e.getMessage());
		}
	}

	/*
	 * Metohod for draw the UI
	 */
	public void ensureUI(final List<FoodModel> foodList, int listStyle) {
		dismissProgress();
		if (foodList != null && foodList.size() > 0) {
			int itemLayout = R.layout.food_item_card;

			mListView.setVisibility(View.VISIBLE);
			mGridView.setVisibility(View.GONE);
			mTextEmptyList.setVisibility(View.GONE);

			if (listStyle == STYLE_GRID) {

				mListView.setVisibility(View.GONE);
				mGridView.setVisibility(View.VISIBLE);
				itemLayout = R.layout.food_item_grid_card;

			}

			InstantAdapter<FoodModel> foodListAdapter = new InstantAdapter<FoodModel>(
					this.getActivity(), itemLayout, FoodModel.class, foodList);

			foodListAdapter.setViewHandler(R.id.img_verified,
					new ViewHandler<FoodModel>() {

						@Override
						public void handleView(ListAdapter adapter,
								View parent, View view, FoodModel instance,
								int position) {
							if (instance.getVerified()) {
								view.setVisibility(View.VISIBLE);
							} else {
								view.setVisibility(View.INVISIBLE);
							}

						}
					});

			mListView.setAdapter(foodListAdapter);
			mGridView.setAdapter(foodListAdapter);

			mListView.setOnItemClickListener(listItemClickListener(foodList));
			mGridView.setOnItemClickListener(listItemClickListener(foodList));

		} else {
			mListView.setVisibility(View.GONE);
			mGridView.setVisibility(View.GONE);
			mTextEmptyList.setVisibility(View.VISIBLE);
			mTextEmptyList.setText(getString(R.string.text_empty_list,
					mEditSearch.getText().toString()));

		}
	}

	private OnItemClickListener listItemClickListener(
			final List<FoodModel> foodList) {
		return new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				final FoodModel myFood = foodList.get(position);

				AlertDialog.Builder dialog = new AlertDialog.Builder(
						getActivity());
				dialog.setTitle(myFood.getTitle());
				String[] favoritesNames = new String[] { "save on favorites" };

				dialog.setItems(favoritesNames,
						new android.content.DialogInterface.OnClickListener() {

							public void onClick(
									DialogInterface dialogInterface, int pos) {
								saveFavorite(myFood);
							}

						}).show();
			}

		};
	}

	/*
	 * Method for save food on favorites
	 */
	private void saveFavorite(FoodModel food) {

		new SaveFoodTask(getActivity(), food).execute();
	}

	/*
	 * Method for show the progress
	 */
	public void showProgress() {
		mProgressView.setVisibility(View.VISIBLE);
		mListView.setVisibility(View.GONE);
		mGridView.setVisibility(View.GONE);
		mTextEmptyList.setVisibility(View.GONE);
		mPlaceHolderView.setVisibility(View.GONE);
	}

	/*
	 * Method for dismiss the progress
	 */
	public void dismissProgress() {
		mProgressView.setVisibility(View.GONE);
		mPlaceHolderView.setVisibility(View.GONE);
	}

	/*
	 * Task responsible for get the data and leave the UI Thread free for the
	 * user
	 */
	private class GetFoodTask extends AsyncTask<Void, Void, List<FoodModel>> {
		private static final String TAG = "GetFoodTask";
		String queryString;
		private List<FoodModel> foodList = null;

		private BufferedReader bufferedReader = null;
		private HttpURLConnection con = null;

		public GetFoodTask(String queryString) {
			super();
			this.queryString = queryString;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgress();
		}

		@Override
		protected List<FoodModel> doInBackground(Void... params) {

			try {
				final String urlString = HTTPS_API_LIFESUM + queryString;

				URL url = new URL(urlString);
				con = (HttpURLConnection) url.openConnection();

				// set up url connection to get retrieve information back
				con.setRequestMethod("GET");
				con.setDoInput(true);

				// stuff the authorization request header
				con.setRequestProperty("Authorization",
						"a794ecd348a3f71894426c65c37fea35da89a295bcbad687ca68a96fbfc7d371");
				con.connect();
				int status = con.getResponseCode();

				switch (status) {
				case 200:

					// pull the information back from the URL
					bufferedReader = new BufferedReader(new InputStreamReader(
							con.getInputStream()));
					StringBuilder stringBuilder = new StringBuilder();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						stringBuilder.append(line).append("\n");
					}

					// output the information
					Log.i(TAG, stringBuilder.toString());

					foodList = new ArrayList<FoodModel>();
					JSONObject responseJson = new JSONObject(
							stringBuilder.toString()).getJSONObject("response");
					JSONArray foodsJson = responseJson.getJSONArray("list");

					FoodModel food = new FoodModel();

					for (int i = 0; i < foodsJson.length(); i++) {

						food = new FoodModel(foodsJson.getJSONObject(i));

						foodList.add(food);
					}

					break;

				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			} finally {
				if (con != null) {
					con.disconnect();
				}
				if (bufferedReader != null) {
					try {
						bufferedReader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return foodList;
		}

		@Override
		protected void onPostExecute(List<FoodModel> result) {
			super.onPostExecute(result);
			foodListCopy = result;
			ensureUI(result, listStyle);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_list) {
			changeListStyle();
		}
		return super.onOptionsItemSelected(item);
	}

	/*
	 * Method for change the listStyle
	 */
	public void changeListStyle() {
		if (listStyle == STYLE_LIST) {
			listStyle = STYLE_GRID;
		} else {
			listStyle = STYLE_LIST;
		}
		if (foodListCopy != null && foodListCopy.size() > 0) {
			ensureUI(foodListCopy, listStyle);
		}
	}

}
