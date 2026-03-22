import java.util.*;

public class ShoppingCart {

    public static int calculateTotal(int price, int quantity, int sumTotalCart) {
        int sum = price * quantity;
        return sumTotalCart + sum;
    }

    public static Locale getLocale(String language) {
        switch (language.toLowerCase()) {
            case "swedish":
                return new Locale("sv", "SE");
            case "finnish":
                return new Locale("fi", "FI");
            case "japanese":
                return new Locale("ja", "JP");
            default:
                return new Locale("en", "US");
        }
    }

    public static void main(String[] args){

        List<String> languages = Arrays.asList("English", "Swedish", "Finnish", "Japanese");
        Scanner sc = new Scanner(System.in);
        Locale locale;
        String selectedLanguage;

        int sumTotalCart = 0;

        System.out.println("Select a language:");
        for (String language : languages){
            System.out.println("-" + language);
        }

        selectedLanguage = sc.nextLine().toLowerCase();

        locale = getLocale(selectedLanguage);

        ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle", locale);


        System.out.println(rb.getString("prompt1"));
        int item = sc.nextInt();
        for(int i =0; i<item; i++){
            System.out.println(rb.getString("prompt2") + (i+1) + ":");
            int price = sc.nextInt();
            System.out.println(rb.getString("prompt3") + (i+1) + ":");
            int quantity = sc.nextInt();
            sumTotalCart = calculateTotal(price, quantity, sumTotalCart);
        }

        System.out.println(rb.getString("prompt4")  + sumTotalCart + "$");


    }
}
