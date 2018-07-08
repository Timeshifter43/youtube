import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {

   

    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=7563ce58427d1362f820e98bb159d6bc");


        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));


        JSONArray getArray = object.getJSONArray("weather");
        for(int i = 0;i<getArray.length();i++){
            JSONObject obj = getArray.getJSONObject(i);

            model.setIcon((String) obj.get("icon"));
            model.setMain((String) obj.get("main"));
        }


        return "City: " + model.getName() + "\n" +
                "Temperature: " + model.getTemp() + "\n" +
                "Humidity: " + model.getHumidity() + "%" + "\n";
    }

}
