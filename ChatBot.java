import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatBot {
    private static final Map<String, String> reflections = new HashMap<>();

    static {
        reflections.put("am", "are");
        reflections.put("i", "you");
        reflections.put("you", "me");
        reflections.put("me", "you");
        reflections.put("my", "your");
        reflections.put("your", "my");
    }

    private static class Pair {
        private final Pattern pattern;
        private final String[] responses;

        public Pair(String pattern, String[] responses) {
            this.pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
            this.responses = responses;
        }

        public String[] respond(String input) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                return responses;
            }
            return null;
        }
    }

    private static Pair[] pairs = {
            new Pair("(.*)Robot", new String[]{"hi Human, how can I help you "}),
            new Pair("(.*)question", new String[]{"I am here for your help"}),
            new Pair("(.*)developed", new String[]{"Hanvi created me using Java"}),
            new Pair("(.*)alive", new String[]{"I am Robot, I am just a computer program."}),
            new Pair("(.)my name(.)", new String[]{"Hello %2, How are you today ?"}),
            new Pair("(.)help(.)", new String[]{"I can help you "}),
            new Pair("what(.*)name?", new String[]{"My name is AI Robot, but you can just call me robot and I'm a chatbot."}),
            new Pair("how (.*) you ?", new String[]{"I'm doing very well", "i am great !"}),
            new Pair("sorry (.*)", new String[]{"Its alright", "Its OK, never mind that"}),
            new Pair("i'm (.*) (good|well|okay|ok)", new String[]{"Nice to hear that", "Alright, great !"}),
            new Pair("(hi|hey|hello|hola|holla)(.*)", new String[]{"Hello", "Hey there"}),
            new Pair("what (.*) want ?", new String[]{"Make me an offer I can't refuse"})
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(reflections);

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            if ("exit".equalsIgnoreCase(userInput)) {
                System.out.println("Goodbye!");
                break;
            }

            for (Pair pair : pairs) {
                String[] responses = pair.respond(userInput);
                if (responses != null) {
                    for (String response : responses) {
                        System.out.println("AI Robot: " + response);
                    }
                    break;
                }
            }
        }
        scanner.close();
    }
}