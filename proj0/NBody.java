
public class NBody {
    public static double readRadius(String args) {
        In in = new In(args);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String args) {
        In in = new In(args);
        int N = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[N];
        for (int i = 0; i < planets.length && (!in.isEmpty()); i++) {
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return planets;
    }


    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        /** Drawing the background */
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "./images/starfield.jpg");
        StdDraw.show();

        /** Drawing one planet */
        for (Planet p:planets) {
            p.draw();
        }

        /** enable double buffering*/
        StdDraw.enableDoubleBuffering();

        /** Creating an Animation*/
        for (int time = 0; time < T; time += dt) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++)
                planets[i].update(dt,xForces[i], yForces[i]);

            /** Drawing the Background */
            String imageToDraw = "images/starfield.jpg";
            StdDraw.setScale(radius, -radius);
            StdDraw.picture(0, 0, imageToDraw);

            /** Drawing All of the Planets*/
            for (Planet p:planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}