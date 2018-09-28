package com.del.a2b.recyclesample.controller;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.del.a2b.recyclesample.R;
import com.del.a2b.recyclesample.model.Trackable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrackableManager extends Activity{

    static ArrayList<Trackable> TrackableListName;
    private Context context;

    public TrackableManager(Context ctx)
    {
        context=ctx;
        TrackableListName=new ArrayList<>();
    }


    private void parseFile()
    {

        try {
            Scanner scan = new Scanner(context.getResources().openRawResource(R.raw.food_truck_data));
           scan.useDelimiter(",\"|\",\"|\"\\n+");
            while (scan.hasNext())
            {
                Trackable trackable=new Trackable();
             //   Log.i("Track info: ",scan.next());
                trackable.setId(Integer.parseInt(scan.next()));
             //   Log.i("Track info: ",scan.next());
                trackable.setName(scan.next());
            //    Log.i("Track info: ",scan.next());
               trackable.setDescription(scan.next());
           //     Log.i("Track info: ",scan.next());
               trackable.setURL(scan.next());
           //     Log.i("Track info: ",scan.next());
                trackable.setCategory(scan.next());
                TrackableListName.add(trackable);
            }
        }
        catch (Resources.NotFoundException e)
        {
            Log.i("TestParse", "File Not Found Exception Caught");
        }

    }

    public List<Trackable> getTrackableListName() {
        parseFile();
        return TrackableListName;
    }

    public List<Trackable> getTrackableByCategory(String category)
    {
        if(category.equals("All"))
            return getTrackableListName();
        List<Trackable> categoryTrackable = new ArrayList<>();
        for(Trackable trackable:TrackableListName)
        {
            if(trackable.getCategory().equals(category))
                categoryTrackable.add(trackable);
        }


        return categoryTrackable;
    }

    public static List<String> getCategories()
    {

        List<String> categoryList= new ArrayList<>();
        categoryList.add("All");
        for (int i=0;i<TrackableListName.size();i++)
        {
            String category= TrackableListName.get(i).getCategory();
            if(categoryList.contains(category))
                continue;
            categoryList.add(category);
        }

        return categoryList;
    }
}
