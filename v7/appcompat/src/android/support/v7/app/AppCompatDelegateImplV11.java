/*
 * Copyright (C) 2013 The Android Open Source Project
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

package android.support.v7.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

class AppCompatDelegateImplV11 extends AppCompatDelegateImplV7 {

    AppCompatDelegateImplV11(Context context, Window window, AppCompatCallback callback) {
        super(context, window, callback);
    }

    View callActivityOnCreateView(View parent, String name, Context context, AttributeSet attrs) {
        // First let super have a try, this allows FragmentActivity to inflate any support
        // fragments
        final View view = super.callActivityOnCreateView(parent, name, context, attrs);
        if (view != null) {
            return view;
        }

        // Now, let the Activity's LayoutInflater.Factory2 method try...
        if (mOriginalWindowCallback instanceof LayoutInflater.Factory2) {
            return ((LayoutInflater.Factory2) mOriginalWindowCallback)
                    .onCreateView(parent, name, context, attrs);
        }

        return null;
    }
}
