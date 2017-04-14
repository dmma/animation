package ua.dp.dmma;

import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * @author dmma
 */
public class AnimationTest {
    private Animation animation = new Animation();

    @Test
    public void testAnimation() {
        String[] result = animation.animate(2, "..R....");
        System.out.println(Arrays.toString(result));

        result = animation.animate(3, "RR..LRL");
        System.out.println(Arrays.toString(result));

        result = animation.animate(2, "LRLR.LRLR");
        System.out.println(Arrays.toString(result));

        result = animation.animate(10, "RLRLRLRLRL");
        System.out.println(Arrays.toString(result));

        result = animation.animate(1, "...");
        System.out.println(Arrays.toString(result));

        result = animation.animate(1, "LRRL.LR.LRR.R.LRRL.");
        for (String s : result) {
            System.out.println(s);
        }
    }
}
