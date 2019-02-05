public class Main {
    public static void main(String args[]) {
        if (args.length <= 0) {
            System.err.println("Please specify a correct portnumber, and nothing else.");
            System.exit(1);
        }

        try {
            DataReceiver dataReceiver = new DataReceiver(args[0]);
            dataReceiver.start("DataReceiver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}