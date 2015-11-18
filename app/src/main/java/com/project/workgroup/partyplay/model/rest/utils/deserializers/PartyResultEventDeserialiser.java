package com.project.workgroup.partyplay.model.rest.utils.deserializers;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.project.workgroup.partyplay.model.entities.Event;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Junior on 10/11/2015.
 */
public class PartyResultEventDeserialiser implements JsonDeserializer<List<Event>> {

    public static final String TAG = PartyResultEventDeserialiser.class.getName();
    @Override
    public List<Event> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException  {

        Log.e(TAG, "deserializando a objeto");

        Type listType = new TypeToken<List<Event>>(){}.getType();
        Log.e("type", ""+listType.toString());

        JsonArray resultsArray = json.getAsJsonArray();
        Log.e("data", " " + resultsArray.get(0) + " "+resultsArray.get(1));

        JsonElement eventsObject = resultsArray.get(0);
        JsonElement items = eventsObject.getAsJsonObject();

        return new Gson().fromJson(resultsArray , listType);



    }
}
