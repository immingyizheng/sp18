import java.util.ArrayList;

public class NBody{

	public static double readRadius(String dir){

		In in = new In(dir);
		
		int N = in.readInt();
		double R = in.readDouble();
		
		return R;
	}

	public static Planet[] readPlanets(String dir){
		In in = new In(dir);
		
		int N = in.readInt();
		double R = in.readDouble();

		Planet[] p = new Planet[N];

		for (int i = 0; i < N; i ++) {
			p[i] = new Planet(0.0, 0.0, 0.0, 0.0, 0.0, "");
			p[i].xxPos = in.readDouble();
			p[i].yyPos = in.readDouble();
			p[i].xxVel = in.readDouble();
			p[i].yyVel = in.readDouble();
			p[i].mass = in.readDouble();
			p[i].imgFileName = in.readString();
		}

		return p;
	}

	public static void main(String[] args) {
		/* Collect All Needed Input
			arg[0] = T
			arg[1] = dt
			arg[2] = filename
		*/

		double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];


        double time = 0;
        double Radius = readRadius(filename);
        Planet[] p = readPlanets(filename);

		//StdDraw.enableDoubleBuffering();

        /* Draw Background */
        
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-Radius, Radius);
		StdDraw.clear();

         /*Draw All Planets */
         // for (int i = 0; i < p.length; i++){
         // 	p[i].draw();
         // }

         while (time != T){
         	double xForces[] = new double[p.length];
        	double yForces[] = new double[p.length];

         	for (int i = 0; i < p.length; i++){
         		xForces[i] = p[i].calcNetForceExertedByX(p);
         		yForces[i] = p[i].calcNetForceExertedByY(p);
         	}
         	
         	StdDraw.setScale(-Radius, Radius);
        	StdDraw.picture(0, 0, "images/starfield.jpg");
        	StdDraw.enableDoubleBuffering();

         	for (int j = 0; j < p.length; j++){
         		p[j].update(dt, xForces[j], yForces[j]);
         	}


        	StdDraw.picture(0, 0, "images/starfield.jpg");
        	
         	for (int k = 0; k < p.length; k++){
         		p[k].draw();
         	}

         	StdDraw.show();
         	StdDraw.pause(5);
         	time = time + dt; 
         }

         StdOut.printf("%d\n", p.length);
		 StdOut.printf("%.2e\n", Radius);
		 for (int i = 0; i < p.length; i++) {
		     StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                   p[i].xxPos, p[i].yyPos, p[i].xxVel,
		                   p[i].yyVel, p[i].mass, p[i].imgFileName);   
		 }
        

    }
}














