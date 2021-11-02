package com.team11.ditto;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public interface Firebase {
    Map<String, Object> userMap = new HashMap<>();
    Map<String, Object> habitMap = new HashMap<>();
    Map<String, Object> habitEventMap = new HashMap<>();

    default void addUserToDB(FirebaseFirestore database, User newUser) {
        database.collection("Users").get();
        userMap.put(newUser.getUsername(), newUser);
    }
}