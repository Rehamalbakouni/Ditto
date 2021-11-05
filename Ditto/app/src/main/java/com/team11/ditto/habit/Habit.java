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
package com.team11.ditto.habit;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Purpose: Habit class represents a habit object and holds data for
 * title
 * reason
 * date
 * Design Rationale: set getters and setters for the data that Habit holds
 * @author Kelly Shih, Aidan Horemans
 */

public class Habit implements Serializable {

    //Atrributes
    private String habitID;
    private String title;
    private String reason;
    private ArrayList<Integer> dates;

    /**
     * Constructor for Habit object
     * @param title Habit title
     * @param reason Reason for habit
     * @param dates Days of the week for scheduling
     */
    public Habit(String title, String reason, ArrayList<Integer> dates) {
        this.title = title;
        this.reason = reason;
        this.dates = dates;
    }

    /**
     * Constructor for Habit object
     * @param id Habit id
     * @param title Habit title
     * @param reason Reason for habit
     * @param dates Days of the week for scheduling
     */
    public Habit(String id, String title, String reason, ArrayList<Integer> dates) {
        this.habitID = id;
        this.title = title;
        this.reason = reason;
        this.dates = dates;
    }

    /**
     * Getter for Habit title
     * @return Habit title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for Habit Title
     * @param title new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for reason
     * @return Habit's reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Setter for reason
     * @param reason updated reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Getter for days scheduled
     * @return list of indices for the days of the week Habit is scheduled for (0-6)
     */
    public ArrayList<Integer> getDate() {
        return dates;
    }

    /**
     * Setter for Habit schedule days
     * @param dates list of indices for the days of the week for Habit's NEW schedule (0-6)
     */
    public void setDate(ArrayList<Integer> dates) {
        this.dates = dates;
    }

    /**
     * Getter for ID
     * @return Habit id string
     */
    public String getHabitID() {
        return habitID;
    }

    /**
     * Setter for ID
     * @param habitID new ID String
     */
    public void setHabitID(String habitID) {
        this.habitID = habitID;
    }

}
