public class NBody{
    public static double readRadius (String fileName){
        In in = new In(fileName);
        int firstItemInFile = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets (String fileName){
        In in = new In(fileName);
        int firstItemInFile = in.readInt();
        double radius = in.readDouble();
        Planet[] b = new Planet[5];
        for (int i = 0; i < 5; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            b[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return b;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        Planet[] b = new Planet[5];
        b = readPlanets("./data/planets.txt");
        double unRadius = readRadius("./data/planets.txt");

        StdDraw.enableDoubleBuffering();
        

        

        double time = 0.0;
        double[] xForces = new double[5];
        double[] yForces = new double[5];
        
        while (time < T) {

            for(int i = 0; i < 5; i++){
                xForces[i] = b[i].calcNetForceExertedByX(b);
                yForces[i] = b[i].calcNetForceExertedByY(b);
                b[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.setScale(-unRadius, unRadius);
            StdDraw.clear();
            StdDraw.picture(0, 0, "./images/starfield.jpg");
        
            for(int i = 0; i < 5; i++){
                b[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            time = time + dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i += 1) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
