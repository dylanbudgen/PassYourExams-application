package ***REMOVED***gcse.Managers;

import android.graphics.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ***REMOVED*** on 11/11/2017.
 */

public class ColourManager {

    //private final ArrayList<Integer> COLOURS = new ArrayList<>(Arrays.asList(Color.parseColor("#ffffff"),

    private static final Map<String, Integer> COLOURS = new HashMap<String, Integer>()  {{
        put("DARK_GREEN", Color.parseColor("#00B562"));
        put("LIGHT_GREEN", Color.parseColor("#00D942"));
        put("DARK_BLUE", Color.parseColor("#002B75"));
        put("LIGHT_BLUE", Color.parseColor("#0050D4"));
    }};

    public static int resolveColour(String colourName) {

        return COLOURS.get(colourName);
    }

    public static int resolveColour(String colourName, String option) {

        return COLOURS.get(option + "_" + colourName);
    }


}
