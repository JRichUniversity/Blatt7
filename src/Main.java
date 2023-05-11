import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        MyList<Integer> list = new MyList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte eine Liste von Integern, mit Leerzeichen getrennt, eingeben");
        String input = scanner.nextLine();
        String[] tokens = input.split(" "); //trennt die Eingabe anhand der Leerzeichen
        for (String token : tokens){
            int number = Integer.parseInt(token);
            list.add(number);
        }

        //For each
        for(Integer element : list){ // Only works because of iterable implementation
            System.out.print(element + " ");
        }
        System.out.println();

        // Iterator
        Iterator<Integer> iter = list.iterator();
        iter.forEachRemaining(element -> System.out.print(element + " "));
        System.out.println();

        iter = list.iterator();
        while(iter.hasNext()){
            int i = iter.next();
            if(i % 2 == 1){
                iter.remove();
            }
        }

        for (int number : list) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}
