package ru.kpfu.itis.transportprojectimpl.util;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

public class JsonReader {
    @Value("${google.maps.key}")
    private String key;

    private static String readAll(final Reader rd) throws IOException {
        final StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject read(final String url) throws IOException, JSONException {
        final InputStream is = new URL(url).openStream();
        try {
            final BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            final String jsonText = readAll(rd);
            final JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private static String encodeParams(final Map<String, String> params) {
        final String paramsUrl = Joiner.on('&').join(// получаем значение вида key1=value1&key2=value2...
                Iterables.transform(params.entrySet(), new Function<Map.Entry<String, String>, String>() {

                    @Override
                    public String apply(final Map.Entry<String, String> input) {
                        try {
                            final StringBuffer buffer = new StringBuffer();
                            buffer.append(input.getKey());// получаем значение вида key=value
                            buffer.append('=');
                            buffer.append(URLEncoder.encode(input.getValue(), "utf-8"));// кодируем строку в соответствии со стандартом HTML 4.01
                            return buffer.toString();
                        } catch (final UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }));
        return paramsUrl;
    }

    public double[] main(String city, String country) throws IOException, JSONException, InterruptedException, ApiException {
        final String baseUrl = "https://maps.googleapis.com/maps/api/geocode/json";// путь к Geocoding API по HTTP
        final Map<String, String> params = Maps.newHashMap();
        params.put("key", key);// исходит ли запрос на геокодирование от устройства с датчиком местоположения
        params.put("address", city + ", " + country);// адрес, который нужно геокодировать
        final String url = baseUrl + '?' + encodeParams(params);// генерируем путь с параметрами
        System.out.println(url);// Путь, что бы можно было посмотреть в браузере ответ службы
        final JSONObject response = JsonReader.read(url);// делаем запрос к вебсервису и получаем от него ответ
        JSONObject location = response.getJSONArray("results").getJSONObject(0);
        location = location.getJSONObject("geometry");
        location = location.getJSONObject("location");
        final double lng = location.getDouble("lng");// долгота
        final double lat = location.getDouble("lat");// широта
        System.out.println(String.format("%f,%f", lat, lng));// итоговая широта и долгота
        return new double[]{lat, lng};
    }
}

