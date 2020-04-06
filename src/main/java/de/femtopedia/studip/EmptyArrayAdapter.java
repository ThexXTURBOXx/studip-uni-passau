package de.femtopedia.studip;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class EmptyArrayAdapter<C> implements TypeAdapterFactory {
    private final Class<C> customizedClass;

    public EmptyArrayAdapter(Class<C> customizedClass) {
        this.customizedClass = customizedClass;
    }

    @SuppressWarnings("unchecked")
    public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        return type.getRawType() == customizedClass
                ? (TypeAdapter<T>) customizeMyClassAdapter(gson, (TypeToken<C>) type)
                : null;
    }

    private TypeAdapter<C> customizeMyClassAdapter(Gson gson, TypeToken<C> type) {
        final TypeAdapter<C> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
        return new TypeAdapter<C>() {
            @Override
            public void write(JsonWriter out, C value) throws IOException {
                JsonElement tree = delegate.toJsonTree(value);
                elementAdapter.write(out, tree);
            }

            @Override
            public C read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.BEGIN_ARRAY) {
                    in.skipValue();
                    return null;
                }
                JsonElement tree = elementAdapter.read(in);
                return delegate.fromJsonTree(tree);
            }
        };
    }

}
