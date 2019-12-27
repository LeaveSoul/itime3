package com.example.itime;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class timesaver {
    public timesaver(Context context) {
        this.context = context;
    }

    private Context context;

    public timesaver(ArrayList<time> times) {
        this.times = times;
    }

    private ArrayList<time> times=new ArrayList<time>();

    public void save() throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(context.openFileOutput("Serializable.txt" , Context.MODE_PRIVATE));
                outputStream.writeObject(times);
                outputStream.close();

    }
    public void load() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(context.openFileInput("Serializable.txt"));
        times = (ArrayList<time>) inputStream.readObject();
        inputStream.close();

    }
}
