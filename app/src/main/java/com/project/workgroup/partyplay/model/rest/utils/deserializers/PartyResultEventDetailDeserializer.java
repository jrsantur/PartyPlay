package com.project.workgroup.partyplay.model.rest.utils.deserializers;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.project.workgroup.partyplay.model.entities.EventDetail;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by TALLER on 24/11/2015.
 */
public class PartyResultEventDetailDeserializer implements JsonDeserializer<List<EventDetail>> {
    @Override
    public List<EventDetail> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {


        Type listType = new TypeToken<List<EventDetail>>(){}.getType();
        Log.e("type", "" + listType.toString());

        JsonArray resultArray = json.getAsJsonArray();
        Log.e("data", " " + resultArray.get(0) + " "+resultArray.get(1));

        return new Gson().fromJson(resultArray , listType);


    }
}
