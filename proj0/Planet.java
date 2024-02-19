public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    ;

    public double calcDistance(Planet p) {
        double xxDistance = this.xxPos - p.xxPos;
        double yyDistance = this.yyPos - p.yyPos;
        double Distance = Math.sqrt(xxDistance * xxDistance + yyDistance * yyDistance);
        return Distance;
    }

    public double calcForceExertedBy(Planet p) {
        double G = 6.67e-11;
        double Mass = this.mass * p.mass;
        return G * Mass / (this.calcDistance(p) * this.calcDistance(p));
    }

    public double calcForceExertedByX(Planet p) {
        double xxDistance = p.xxPos - this.xxPos;
        return this.calcForceExertedBy(p) * xxDistance / this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        double yyDistance = p.yyPos - this.yyPos;
        return this.calcForceExertedBy(p) * yyDistance / this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double result = 0;
        for (int i = 0; i < planets.length; i++) {
            if (this.equals(planets[i]))
                continue;
            result += this.calcForceExertedByX(planets[i]);
        }
        return result;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double result = 0;
        for (int i = 0; i < planets.length; i++) {
            if (this.equals(planets[i]))
                continue;
            result += this.calcForceExertedByY(planets[i]);
        }
        return result;
    }

    public void update(double dt,double fx,double fy){
        double ax=fx/this.mass;
        double ay=fy/this.mass;
        this.xxVel=this.xxVel+ax*dt;
        this.yyVel=this.yyVel+ay*dt;
        this.xxPos=this.xxPos+this.xxVel*dt;
        this.yyPos=this.yyPos+this.yyVel*dt;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }



}


