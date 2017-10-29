package com.mygdx.game;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mygdx.game.Interface.SaveToDatabase;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * This class implements the SaveToDatabase class , it has functions saving stages in the data base,
 * retraving stages from the database
 */

public class AndroidSaveToDatabase implements SaveToDatabase {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mRootReference;
    DatabaseReference mChildRefernce;
    MainClass mainClass;
    String[][] s = new String[10][1];
    String ss = "";
    Set<String> set;

    /**
     * Constuctor
     * @param mainClass
     */
    public AndroidSaveToDatabase(MainClass mainClass) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRootReference = firebaseDatabase.getReference();
        mChildRefernce = mRootReference.child("levels");
        this.mainClass = mainClass;
        set = new HashSet<>();
    }

    /**
     * Loads the maps from the database in firebase
     */
    @Override
    public void load() {
        mChildRefernce.addValueEventListener(new ValueEventListener() {
            /**
             * Called when there is datachange at the server
             * @param dataSnapshot
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                s = new String[(int) dataSnapshot.getChildrenCount()][4];
                int i = 0;
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Map<String, String> map = (Map<String, String>) child.getValue();
                    System.out.println("Author: " + map.get("json"));
                    System.out.println("Title: " + map.get("name"));
                    System.out.println("Title: " + map.get("username"));
                    s[i++] = new String[]{child.getKey(), map.get("name"), map.get("rating"), map.get("username"), map.get("json"),};
                }
            }

            /**
             * Called when there is an error reciving the data from the server, presently does nothing
             * @param databaseError
             */
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mRootReference.child("raters").child(mainClass.Username + "@").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                set.clear();
                int i = 0;
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    System.out.println("Key  " + child.getKey());
                    set.add(child.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Saves the map into the database
     * @param id unique id of the map, timestamp
     * @param name name of map
     * @param username username of user
     * @param json Stores the stage
     * @return false always
     */
    @Override
    public boolean save(String id, String name, String username, String json) {
        mChildRefernce.child(id).child("name").setValue(name);
        mChildRefernce.child(id).child("json").setValue(json);
        mChildRefernce.child(id).child("username").setValue(username);
        mChildRefernce.child(id).child("rating").setValue("0");
        return false;
    }

    /**
     * Getter function for the array of maps
     * @return
     */
    @Override
    public String[][] get() {
        return s;
    }

    /**
     * Rates the map, when the user clicks love button
     * @param s the id of the map
     * @param b boolian for like or unlick
     * @return false always
     */
    @Override
    public boolean rate(String s, boolean b) {
        final DatabaseReference ref = mChildRefernce.child(s).child("rating");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ss = dataSnapshot.getValue(String.class);
                System.out.println("SS Value" + ss);
                ref.removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        while (true) {
            if (ss.isEmpty()) {
                System.out.println("SS" + ss);
                continue;
            }

            System.out.println(1);
            if (b) {
                mRootReference.child("raters").child(mainClass.Username + "@").child(s).removeValue();
                int g = Integer.parseInt(ss) - 1;
                System.out.println("SS  " + g);
                mChildRefernce.child(s).child("rating").setValue(Integer.toString(g));
            } else {
                mRootReference.child("raters").child(mainClass.Username + "@").child(s).setValue("1");
                int g = Integer.parseInt(ss) + 1;
                System.out.println("SS  " + g);
                mChildRefernce.child(s).child("rating").setValue(Integer.toString(g));

            }
            break;
        }
        ss = "";
        return false;
    }

    /**
     *
     * @return Setcontaings maps liked by the user
     */
    @Override
    public Set<String> getset() {
        return set;
    }
}
