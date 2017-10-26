package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mygdx.game.Interface.SaveToDatabase;

import org.lwjgl.Sys;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by suraj on 25/10/17.
 */

public class AndroidSaveToDatabase implements SaveToDatabase{
    public FirebaseDatabase firebaseDatabase ;
    public DatabaseReference mRootReference;
    public DatabaseReference mChildRefernce;
    MainClass mainClass;
    public String [][] s= new String[10][1];
    String ss="";
    public Set<String> set;
    public AndroidSaveToDatabase(MainClass mainClass){
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRootReference=firebaseDatabase.getReference();
        mChildRefernce = mRootReference.child("levels");
        this.mainClass=mainClass;
        set=new HashSet<String>();
    }

    @Override
    public void load() {
        //mRootReference.child("raters").child(mainClass.Username+"@").child("dumm").setValue(1);
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

        mRootReference.child("raters").child(mainClass.Username+"@").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int i=0;
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    System.out.println("Key  "+ child.getKey());
                    set.add(child.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean save(String id,String name,String username,String json) {
        mChildRefernce.child(id).child("name").setValue(name);
        mChildRefernce.child(id).child("json").setValue(json);
        mChildRefernce.child(id).child("username").setValue(username);
        mChildRefernce.child(id).child("rating").setValue("0");
        return false;
    }

    @Override
    public String[][] get() {

        //System.out.println(s.length);
        System.out.println("jdshfjdjfj");
        System.out.println(s.length); // an error of null
        for(int i=0;i<s.length;i++) System.out.println( s[i][1]);

        return s;
    }

    @Override
    public boolean rate(String s, boolean b) {
        final  DatabaseReference ref = mChildRefernce.child(s).child("rating");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ss=dataSnapshot.getValue(String.class);
                System.out.println("SS Value"+ss);
                ref.removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        while(true) {
            if(ss.isEmpty()) {System.out.println("SS" + ss); continue;}

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
        ss="";
        return false;
    }

    @Override
    public Set<String> getset() {
        return set;
    }
}
