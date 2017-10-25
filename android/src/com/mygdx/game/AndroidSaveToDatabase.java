package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mygdx.game.Interface.SaveToDatabase;

import org.lwjgl.Sys;

import java.util.Map;

/**
 * Created by suraj on 25/10/17.
 */

public class AndroidSaveToDatabase implements SaveToDatabase{
    public FirebaseDatabase firebaseDatabase ;
    public DatabaseReference mRootReference;
    public DatabaseReference mChildRefernce;
    public String [][] s;
    int j;
    public AndroidSaveToDatabase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRootReference=firebaseDatabase.getReference();
        mChildRefernce = mRootReference.child("levels");

        mChildRefernce.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                s= new String[(int) dataSnapshot.getChildrenCount()][4];
                int i=0;
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Map<String, String> map = (Map<String, String>) child.getValue();
                    System.out.println("Author: " + map.get("json"));
                    System.out.println("Title: " + map.get("name"));
                    System.out.println("Title: " + map.get("username"));

                    s[i++] = new String[]{child.getKey(),map.get("name"),map.get("rating"),map.get("username"),map.get("json"),};
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean save(String id,String name,String json) {
//        mChildRefernce.child("Id").child("Ratings").setValue(1);
//        mChildRefernce.child("Id").child("map").setValue("hushuh");
//        String ss= mChildRefernce.push().getKey();
//        mChildRefernce.child(ss).child("susms").setValue("qkq")

        mChildRefernce.child(id).child("name").setValue(name);
        mChildRefernce.child(id).child("json").setValue(json);
        mChildRefernce.child(id).child("username").setValue("");
        mChildRefernce.child(id).child("rating").setValue("1");
        return false;
    }

    @Override
    public String[][] get() {

        //System.out.println(s.length);
        System.out.println("jdshfjdjfj");
        System.out.println(s.length);
        for(int i=0;i<s.length;i++) System.out.println( s[i][1]);

        return s;
    }
}
