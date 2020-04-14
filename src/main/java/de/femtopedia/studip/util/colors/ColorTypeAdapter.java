package de.femtopedia.studip.util.colors;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public final class ColorTypeAdapter extends TypeAdapter<Color> {

    @Override
    public void write(JsonWriter out, Color value) throws IOException {
        out.value(value.getId());
    }

    @Override
    public Color read(JsonReader in) throws IOException {
        JsonToken token = in.peek();
        if (token == JsonToken.STRING) {
            return new Color(in.nextString());
        } else if (token == JsonToken.NUMBER) {
            return new Color(in.nextInt() + "");
        }
        return null;
    }

}
