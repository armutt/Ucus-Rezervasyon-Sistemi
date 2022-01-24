import java.text.ParseException;

enum Havayolu {
    klm("KLM", "klm airlines"),
    lh("LH", "Lufthansa Airlines"),
    thy("THY", "turkish airlines");
    public final String code;
    public final String name;

    Havayolu(String code, String name) {
        this.code = code;
        this.name = name;
    }

    static Havayolu Parse(String s) throws ParseException {

        for (var value : Havayolu.values()) {
            if (s.toUpperCase().equals(value.code) || s.toLowerCase().equals(value.name))
                return value;
        }
        throw new ParseException(s, 0);
    }

    @Override
    public String toString() {
        return code + "-" + name;
    }
}//