package com.project.workgroup.partyplay.model.rest.utils.deserializers;

import com.google.gson.Gson;
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

    @Override
    public List<Event> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Type listType = new TypeToken<List<Event>>(){}.getType();
        JsonElement result = json.getAsJsonObject().get("Events");

        return new Gson().fromJson(result, listType);
    }
}
