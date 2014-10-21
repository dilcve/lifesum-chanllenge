package com.rf.lifesum.challenge.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.rf.lifesum.challenge.R;
import com.rf.lifesum.challenge.fragment.FoodListFragment;
import com.rf.lifesum.challenge.fragment.LocalFoodListFragment;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new FoodListFragment()).setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.menu_favorites) {
			getFragmentManager().beginTransaction()
			.replace(R.id.container, new LocalFoodListFragment()).addToBackStack(LocalFoodListFragment.class.getSimpleName()).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
