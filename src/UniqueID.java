import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Long.*;

/**
 * @author Leanne Won
 * 21/03/2017
 * Took 3 hours
 */
public class UniqueID {

    public static long generate() {
        Instant now = Instant.now();

        //get milli seconds
        long epochSecond = now.getEpochSecond();

        //get nano seconds in 16 bits
        long nano = now.getNano() >>> 16;

        //as generating one id is so fast, only using milli and nano seconds could not achieve uniqueness, so adding random for uniqueness
        //add 11 bits of random value. However due to this, id's won't be sortable based on creation across other distributed nodes.
        //ids will be sortable based on creation within the same node.
        long randomIncremented = UniqueID.Random.getRandom().incrementAndGet();


        //timestamp should come first to make Id sortable based on creation
        //shift milliseconds 22 bits to make space for nanoseconds and random bits
        long id = (epochSecond << 22) | (nano << 11) | randomIncremented;
        return id;
    }

    private static class Random{
        //using AtomicLong to be thread-safe
        private static AtomicLong random = new AtomicLong((long) (MAX_VALUE*Math.random()) & 0x7ff);
        public static AtomicLong getRandom(){
            return random;
        }

        private Random() {
        }
    }
}
