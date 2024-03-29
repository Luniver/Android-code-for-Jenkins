/*
 * Copyright (C) 2016 venshine.cn@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hrst.common.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Fragment操作
 * 
 * @author venshine
 */
public class FragmentUtils {

	/**
	 * Replace an existing fragment that was added to a container.
	 * 
	 * @param activity
	 * @param containerViewId
	 * @param newFragment
	 * @param bundle
	 * @param canBack
	 */
	public static void replaceFragment(Activity activity, int containerViewId, Fragment newFragment, Bundle bundle,
			boolean canBack) {
		FragmentTransaction mFragmentTransaction = activity.getFragmentManager().beginTransaction();
		mFragmentTransaction.replace(containerViewId, newFragment, newFragment.getClass().getName());
		if (bundle != null) {
			newFragment.setArguments(bundle);
		}
		if (canBack) {
			mFragmentTransaction.addToBackStack(null);
		}
		mFragmentTransaction.commit();
	}

	/**
	 * Add a fragment to the activity state. This fragment may optionally also have its view (if
	 * {@link Fragment#onCreateView Fragment.onCreateView} returns non-null) into a container view of the activity.
	 * 
	 * @param activity
	 * @param containerViewId
	 * @param newFragment
	 * @param bundle
	 * @param canBack
	 */
	public static void addFragment(Activity activity, int containerViewId, Fragment newFragment, Bundle bundle,
			boolean canBack) {
		FragmentTransaction mFragmentTransaction = activity.getFragmentManager().beginTransaction();
		mFragmentTransaction.add(containerViewId, newFragment, newFragment.getClass().getName());
		if (bundle != null) {
			newFragment.setArguments(bundle);
		}
		if (canBack) {
			mFragmentTransaction.addToBackStack(null);
		}
		mFragmentTransaction.commit();
	}

	/**
	 * Hides an existing fragment. This is only relevant for fragments whose views have been added to a container, as
	 * this will cause the view to be hidden.
	 * 
	 * @param activity
	 * @param containerViewId
	 * @param previousFragment
	 * @param newFragment
	 * @param bundle
	 * @param canBack
	 */
	public static void hideFragment(Activity activity, int containerViewId, Fragment previousFragment,
			Fragment newFragment, Bundle bundle, boolean canBack) {
		FragmentTransaction mFragmentTransaction = activity.getFragmentManager().beginTransaction();
		if (bundle != null) {
			newFragment.setArguments(bundle);
		}

		if (null != previousFragment) {
			mFragmentTransaction.hide(previousFragment);
		}
		mFragmentTransaction.add(containerViewId, newFragment, newFragment.getClass().getName());
		if (canBack && previousFragment != null) {
			mFragmentTransaction.addToBackStack(newFragment.getClass().getName());
		}
		mFragmentTransaction.commit();
	}

	/**
	 * Remove an existing fragment. If it was added to a container, its view is also removed from that container.
	 * 
	 * @param activity
	 * @param names
	 */
	public static void finishFragement(Activity activity, String... names) {
		FragmentManager manager = activity.getFragmentManager();
		int length = names.length;
		for (int i = 0; i < length; i++) {
			String name = names[i];
			manager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE);
		}
		FragmentTransaction transaction = manager.beginTransaction();
		for (int i = 0; i < length; i++) {
			String name = names[i];
			Fragment fragment = manager.findFragmentByTag(name);
			transaction.remove(fragment);
		}
		transaction.commit();
	}
}
