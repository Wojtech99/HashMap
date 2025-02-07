public class Main {
    public static void main(String[] args) {
        MyMap<Integer, String> nameToLen = new MyMap<>();

        nameToLen.put(1, "a");
        nameToLen.put(6, "wojtek");
        nameToLen.put(5, "absxd");
        nameToLen.put(3, "abc");
        nameToLen.put(5, "ketjo");
        System.out.println(nameToLen.get(5));
        System.out.println(nameToLen.get(1));
        System.out.println(nameToLen.get(6));
        System.out.println(nameToLen.get(3));
        System.out.println(nameToLen.get(7));
    }}