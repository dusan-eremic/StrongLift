package me.stronglift.stronglift.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Custom adapter za UTC time format koji će biti prosleđen Gson
 * biblioteci koja vrši konverziju JSON-Java
 * <p>
 * Created by Dusan Eremic.
 */
public class DateToUTCAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

    /**
     * UTC time format
     */
    public static final String UTC_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    /**
     * UTC time zone
     */
    public static final String UTC = "UTC";

    /**
     * Date formatter
     */
    private final DateFormat dateFormat;

    /**
     * Konstruktor
     */
    public DateToUTCAdapter() {
        dateFormat = new SimpleDateFormat(UTC_TIME_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone(UTC));
    }

    /**
     * Metoda se poziva kada se Java Date konvertuje u String.
     */
    @Override
    public synchronized JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(dateFormat.format(date));
    }

    /**
     * Metoda se poziva kada se String konvertuje u Java Date.
     */
    @Override
    public synchronized Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        try {
            return dateFormat.parse(jsonElement.getAsString());
        } catch (ParseException e) {
            throw new JsonParseException(e);
        }
    }
}
