package com.rf.lifesum.challenge.fragment;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mobsandgeeks.adapters.InstantAdapter;
import com.mobsandgeeks.adapters.ViewHandler;
import com.rf.lifesum.challenge.R;
import com.rf.lifesum.challenge.model.FoodModel;

public class LocalFoodListFragment extends Fragment {

	View mView, mEmptyView;
	Button mButtonSearch;
	ListView mListView;
	GridView mGridView;
	List<FoodModel> mFoodList;

	static final int STYLE_LIST = 0;
	static final int STYLE_GRID = 1;
	int listStyle = STYLE_LIST;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.local_food_list_fragment, container,
				false);
		loadViewComponents();
		loadListeners();
        
		setHasOptionsMenu(true);
		getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);

		mFoodList = FoodModel.listAll(FoodModel.class);
		ensureUI(mFoodList, listStyle);

		return mView;
	}

	/*
	 * Method for load the view components
	 */
	public void loadViewComponents() {
		mButtonSearch = (Button) mView
				.findViewById(R.id.button_local_empty_list);
		mEmptyView = mView.findViewById(R.id.local_empty_list_layout);
		mListView = (ListView) mView.findViewById(R.id.local_list);
		mGridView = (GridView) mView.findViewById(R.id.local_gridview);
	}

	/*
	 * Method for load the principals listeners
	 */
	public void loadListeners() {
		mButtonSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getFragmentManager().popBackStackImmediate();
			}
		});

	}

	/*
	 * Method for show the UI with listStyle
	 */
	public void ensureUI(final List<FoodModel> foodList, int listStyle) {
		if (foodList != null && foodList.size() > 0) {
			mListView.setVisibility(View.VISIBLE);
			mGridView.setVisibility(View.GONE);
			mEmptyView.setVisibility(View.GONE);

			int itemLayout = R.layout.food_item_card;

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
							Log.d("teste",
									"food vefified " + instance.getVerified());
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
			mEmptyView.setVisibility(View.VISIBLE);

		}
	}

	private OnItemClickListener listItemClickListener(
			final List<FoodModel> foodList) {
		return new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Toast.makeText(
						getActivity(),
						"here i have to show informations about "
								+ foodList.get(position).getTitle(),
						Toast.LENGTH_SHORT).show();

			}
		};
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		menu.getItem(0).setVisible(false);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			getFragmentManager().popBackStackImmediate();
		} else if (item.getItemId() == R.id.menu_list) {
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
		if (mFoodList != null && mFoodList.size() > 0) {
			ensureUI(mFoodList, listStyle);
		}
	}

}
