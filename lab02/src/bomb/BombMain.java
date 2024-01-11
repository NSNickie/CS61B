package bomb;

import common.IntList;

public class BombMain {
    public static void main(String[] args) {
        int phase = 2;
        if (args.length > 0) {
            phase = Integer.parseInt(args[0]);
        }
        // TODO: Find the correct inputs (passwords) to each phase using debugging techniques
        Bomb b = new Bomb();
        if (phase >= 0) {
            b.phase0("39291226");
        }
        if (phase >= 1) {
            b.phase1(IntList.of(0,9,3,0,8)); // Figure this out too
        }
        if (phase >= 2) {
            StringBuilder passwordBuilder = new StringBuilder();

            // Add 1336 words (to make i=1337 at the 1337th position)
            for (int i = 0; i < 1337; i++) {
                passwordBuilder.append("random ");
            }
            passwordBuilder.append("-81201430");
            b.phase2(passwordBuilder.toString());
        }
    }
}
