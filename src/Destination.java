import java.text.ParseException;

public enum Destination {
    isl("ISL", "istanbul"),
    ams("AMS", "schiphol"),
    fra("FRA", "frankfurt");

    public final String code;
    public final String name;

    Destination(String code, String name) {
        this.code = code;
        this.name = name;
    }

    static Destination Parse(String s) throws ParseException {
        for (var value : Destination.values()) {
            if (s.toUpperCase().equals(value.code) || s.toLowerCase().equals(value.name))
                return value;
        }
        throw new ParseException(s,0);
    }

    @Override
    public String toString() {
        return code + "-" + name;
    }
}
