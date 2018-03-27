package ru.job4j.todolist.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Класс для сериализации объекта LocalDate
 *
 * @author Pyotr Kukharenka
 * @since 24.03.2018
 */

public class LocalDateSerializer extends StdSerializer<LocalDate> {

    protected LocalDateSerializer() {
        super(LocalDate.class);
    }

    @Override
    public void serialize(LocalDate value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(value.format(DateTimeFormatter.ISO_DATE));

    }
}
