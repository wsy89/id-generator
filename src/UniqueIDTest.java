
/**
 * @author Leanne Won
 */
public class UniqueIDTest {


    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();


        for (int i = 0; i < 1000; i++) {
            long id = UniqueID.generate();
            System.out.println(id);

        }

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("totalTime: " +  totalTime);
    }
}
