public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double g = 6.67 * 1E-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos =xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
        
        //this(b.xxPos, b.yyPos, b.xxVel, b.yyVel, b.mass, b.imgFileName)
    }

    public double calcDistance(Planet b) {
        double r;
        r = Math.sqrt((this.xxPos - b.xxPos) * (this.xxPos - b.xxPos) + (this.yyPos - b.yyPos) * (this.yyPos - b.yyPos));
        return r;
    }


    public double calcForceExertedBy(Planet b) {
        double r = this.calcDistance(b);
        double f;
        f = g * this.mass * b.mass / (r * r);
        return f;
    }

    public double calcForceExertedByX(Planet b){
        double fx;
        double r = this.calcDistance(b);
        double f = this.calcForceExertedBy(b);
        fx = f * (b.xxPos - this.xxPos) / r;
        return fx;
    }  

    public double calcForceExertedByY(Planet b){
        double fy;
        double r = this.calcDistance(b);
        double f = this.calcForceExertedBy(b);
        fy = f * (b.yyPos - this.yyPos) / r;
        return fy;
    }

    public double calcNetForceExertedByX(Planet[] b){
        double fnx = 0.0;
        for (int i = 0; i < b.length; i++){
            if (this.equals(b[i])){
                continue;
            }
            fnx = fnx + this.calcForceExertedByX(b[i]);
        }
        return fnx;
    }

    public double calcNetForceExertedByY(Planet[] b){
        double fny = 0.0;
        for (int i = 0; i < b.length; i++){
            if (this.equals(b[i])){
                continue;
            }
            fny = fny + this.calcForceExertedByY(b[i]);
        }
        return fny;
    }

    public void update(double dt, double fnx, double fny){
        double ax, ay, vnx, vny, pnx, pny;
        ax = fnx / this.mass;
        ay = fny / this.mass;
        vnx = this.xxVel + dt * ax;
        vny = this.yyVel + dt * ay;
        pnx = this.xxPos + dt * vnx;
        pny = this.yyPos + dt * vny;

        this.xxVel = vnx;
        this.yyVel = vny;
        this.xxPos = pnx;
        this.yyPos = pny;  
    }

    public void draw(){
        String img_path = "./images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, img_path);
        StdDraw.show();
    }
}

       