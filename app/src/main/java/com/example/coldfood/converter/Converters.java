package com.example.coldfood.converter;

import android.os.Build;

import androidx.room.TypeConverter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class Converters {
    @TypeConverter
    public static LocalDate fromTimestamp(Long value) {
        System.out.println("LONGGGGGGGGGGGGGGGGGGGG   "+value+"\n");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return value == null ? null : Instant                           // Represent a moment in UTC, an offset of zero hours-minutes-seconds.
                    .ofEpochMilli(value)      // Parse a count of milliseconds since 1970-01-01T00:00Z.
                    .atOffset(                        // Convert from `Instant` (always in UTC, an offset of zero) to `OffsetDateTime` which can have any offset.
                            ZoneOffset.UTC                // A constant representing an offset of zero hours-minutes-seconds, that is, UTC itself.
                    )                                 // Returns a `OffsetDateTime` object.
                    .toLocalDate();
        }
        return null;
    }

    @TypeConverter
    public static Long dateToTimestamp(LocalDate date) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return date == null ? null : date.toEpochDay();
        }
        return null;
    }

}