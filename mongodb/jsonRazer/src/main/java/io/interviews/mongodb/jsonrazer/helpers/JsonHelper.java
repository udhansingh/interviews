package io.interviews.mongodb.jsonrazer.helpers;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.LongSerializationPolicy;

import io.interviews.mongodb.jsonrazer.types.Style;

/**
 * 
 * JSON utilities
 * 
 * @author udhansingh
 *
 */
public class JsonHelper {
	/**
	 * Obtain handle to new Gson instance without custom deserializers
	 * 
	 * @return
	 */
	public static Gson newGson() {
		return newGson(null);
	}
	
	/**
	 * Obtain handle to new Gson instance without custom deserializers
	 * 
	 * @return
	 */
	public static Gson newGson(List<Style> styles) {
		return newGson(null, styles);
	}
	
	/**
	 * Build a new Gson builder with customizations
	 * 
	 * @param map of custom JSON deserializers
	 * @return Gson instance
	 */
	public static Gson newGson(Map<Class<?>, JsonDeserializer<?>> map, List<Style> styles) {
		final GsonBuilder gsonBuilder = new GsonBuilder();
		
		/*
		 * Apply formatting styles
		 */
		applyStyles(gsonBuilder, styles);
		
		if(map != null && map.size() > 0) {
			map.forEach((clazz, deserializer) -> {
				gsonBuilder.registerTypeAdapter(clazz, deserializer);
			});
		}
		
		return gsonBuilder.create();
	}

	/**
	 * Conditionally apply stiles
	 * 
	 * @param gsonBuilder handle to Gson Builder
	 * @param styles list of formatting styles
	 */
	static void applyStyles(GsonBuilder gsonBuilder, List<Style> styles) {
		if(styles == null) return;
		
		if(styles.contains(Style.PRETTY)) {
			gsonBuilder.setPrettyPrinting();
		}
		
		if(styles.contains(Style.INCLUDE_NULLS)) {
			gsonBuilder.serializeNulls();
		}
		
		if(styles.contains(Style.DO_NOT_ESCAPE_HTML)) {
			gsonBuilder.disableHtmlEscaping();
		}
		
		if(styles.contains(Style.TREAT_INTEGER_AS_STRING)) {
			gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
		}
		
		if(styles.contains(Style.ALLOW_SPECIAL_DECIMALS)) {
			gsonBuilder.serializeSpecialFloatingPointValues();
		}
	}
}
