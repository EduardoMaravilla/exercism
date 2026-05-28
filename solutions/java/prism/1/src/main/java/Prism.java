import java.util.*;

public class Prism {

    public record LaserInfo(double x, double y, double angle) {
    }

    public record PrismInfo(int id, double x, double y, double angle) {
    }

    public static List<Integer> findSequence(
            LaserInfo laser,
            List<PrismInfo> prisms
    ) {

        List<Integer> result = new ArrayList<>();

        double x = laser.x();
        double y = laser.y();
        double angle = laser.angle();

        double eps = 1e-9;
        double angleEps = 1e-4;

        while (true) {

            double rad = angle * Math.PI / 180.0;

            double dx = Math.cos(rad);
            double dy = Math.sin(rad);

            PrismInfo closest = null;
            double bestDistance = Double.MAX_VALUE;

            for (PrismInfo prism : prisms) {

                double vx = prism.x() - x;
                double vy = prism.y() - y;

                double distance = Math.sqrt(vx * vx + vy * vy);

                if (distance < eps) {
                    continue;
                }

                double cross = dx * vy - dy * vx;
                
                if (Math.abs(cross) / distance > angleEps) {
                    continue;
                }

                double dot = dx * vx + dy * vy;
                
                if (dot <= 0) {
                    continue;
                }

                if (dot < bestDistance) {
                    bestDistance = dot;
                    closest = prism;
                }
            }

            if (closest == null) {
                break;
            }

            result.add(closest.id());

            x = closest.x();
            y = closest.y();

            angle += closest.angle();

            double rad2 = angle * Math.PI / 180.0;
            
            x += Math.cos(rad2) * eps;
            y += Math.sin(rad2) * eps;
        }

        return result;
    }
}