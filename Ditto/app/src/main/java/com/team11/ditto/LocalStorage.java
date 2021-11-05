/** Copyright [2021] [Reham Albakouni, Matt Asgari Motlagh, Aidan Horemans, Courtenay Laing-Kobe, Vivek Malhotra, Kelly Shih]

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.team11.ditto;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorage {

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public LocalStorage(Context context) {
        this.context = context;
        // Load preference file
        this.sharedPreferences = this.context.getSharedPreferences(
                context.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
        );
        this.editor = this.sharedPreferences.edit();
    }

    // Set the userID to be locally stored
    public void setUserID(String userID) {
        editor.putString(context.getString(R.string.user_id_key), userID);
        editor.apply();
    }

    // Get the locally stored userID
    public String getUserID() {
        return sharedPreferences.getString(
                context.getString(R.string.user_id_key),
                context.getString(R.string.null_user_id));
    }



}
