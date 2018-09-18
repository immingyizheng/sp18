public class Planet{
	public double xxPos;       // Its current x position
    public double yyPos;       // Its current y position
    public double xxVel;       // Its current velocity in the x direction
    public double yyVel;       // Its current velocity in the y direction
    public double mass;        // Its mass
    public String imgFileName; // The name of the file that corresponds to the image that depicts the planet 

    /** Initialize a Planet object.
        Args:
            xP(double): current x position of planet p.
            yP(double): current y position of planet p.
            xV(): current velocity in the x direction of planet p
            yV(): current velocity in the y direction of planet p
            m(double): mass of planet p
            img(String): name of the file that corresponds to the image that depicts the planet p
    */


	public Planet(double xP, double yP, double xV,
				  double yV, double m, String imge){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = imge;

	}
    /** Initialize an identical Planet object as Planet p.
        Args: 
            Planet Object p.
        Returns: 
            None.
    */

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p2){
		double dist = (this.xxPos - p2.xxPos)*(this.xxPos-p2.xxPos) + (this.yyPos-p2.yyPos)*(this.yyPos-p2.yyPos);
		return Math.sqrt(dist);
	}

	public double calcForceExertedBy(Planet p3){
		double G = 6.67e-11;
		return G * this.mass * p3.mass / (calcDistance(p3)*calcDistance(p3));
	}

	public double calcForceExertedByX(Planet p4){
        double F = this.calcForceExertedBy(p4);
        double r = this.calcDistance(p4);
        double dx = p4.xxPos - this.xxPos;

        return (F * dx ) / r;
    }

    public double calcNetForceExertedByX(Planet[] p4){
        double Fx_Net = 0.0;
        for (int i= 0; i < p4.length; i++){
            if (this != p4[i]) {
                Fx_Net = Fx_Net + this.calcForceExertedByX(p4[i]);
            }
        }
        return Fx_Net;
    }

    public double calcForceExertedByY(Planet p5){
        double F = this.calcForceExertedBy(p5);
        double r = this.calcDistance(p5);
        double dy = p5.yyPos - this.yyPos;

        return (F * dy ) / r;
    }

    public double calcNetForceExertedByY(Planet[] p5){
        double Fy_Net = 0.0;
        for (int i= 0; i < p5.length; i++){
            if (this != p5[i]) {
                Fy_Net = Fy_Net + this.calcForceExertedByY(p5[i]);
            }
        }
        return Fy_Net;
    }


	public void update(double dt, double F_x, double F_y){
		double a_x = F_x/this.mass;
		double a_y = F_y/this.mass;

		this.xxVel = this.xxVel + a_x * dt;
		this.yyVel = this.yyVel + a_y * dt;

		this.xxPos = this.xxPos + this.xxVel * dt;
		this.yyPos = this.yyPos + this.yyVel * dt;
	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}









