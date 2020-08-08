public class advanced {
    public double evaluate(String trig, double deg) {
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream

        double ans = sc.nextDouble();
        //double result;
        double b = Math.toRadians(deg);
        switch(trig) {
            case "sin" :
                double a1 = Math.sin(b);
                if(a1 == ans)
                    return a1;
                else
                    return 0;

            case "cos" :	double a2 = Math.cos(b);
                if(a2 == ans)
                    return a2;
                else
                    return 0;

            case "tan" :	if (deg % 180 != 0 && deg % 90 == 0) {
                return (double)Integer.MAX_VALUE;
            }
                double a3 = Math.tan(b);
                if(a3 == ans)
                    return a3;
                else
                    return 0;

            case "cosec" :	double a4 = 1/Math.sin(b);
                if(a4 == ans)
                    return a4;
                else
                    return 0;

            case "sec" :	double a5 = 1 / Math.cos(b);
                if(a5 == ans)
                    return a5;
                else
                    return 0;

            case "cot" :	if (deg % 180 == 0) {
                return (double)Integer.MAX_VALUE;
            }
                double a6 = 1 / Math.tan(b);
                if(a6 == ans)
                    return a6;
                else
                    return 0;

            default :		return (double)Integer.MIN_VALUE;
        }
    }
}
