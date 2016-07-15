/*
 *CON ESTA CLASE SE TIENEN DIVERSAS ECUACIONES LAS CUALES REPRESENTAN EL TIEMPO DE RETARDO DE UN PIC A 4 MHZ
 * LAS ECUACIONES VAN EN ORDEN DE MENOR A MENOR LA MENOR SOPORTA HASTA 197,633 uS SOLO QUE EN ALGUNOS TIEMPOS
 * PARA RETARDOS EL ERROR ES AMPLIO, NO EN TODO PERO SI EN VARIOS QUE SON DESCONOCIDOS; SIN EMBARGO SI SE OCUPA LA ECUACION 
 * SIGUIENTE ESE ERROR SE REDUCE A 0% POR LO TANTO HAY UN METODO EL CUAL DESIDE SI EXTISTE ERROR ALGUNO, EL CUAL TOMARA LA SIGUIENTE ECUACION
 * PARA VOLVER A CALCULAR EL TIEMPO Y TENER 0% DE ERROR
 */
package delay_pic;

/**
 *
 * @author XIZUTH
 */
public class Calculo2 {

    public Calculo2() {//CONSTRUCTOR VACIO
    }
// *************  CAMPOS DE LA CLASE ******************        
    private int m;         //primer anillo
    private int n;         //segundo anillo
    private int p;         //tercer anillo
    private int w;         //cuarto y ultimo anillo
    private int resultado; //es el resultado en comparacion con retardo
    private int s;         //numeros de NOP
    private int retardo;   //tiempo de retardo
    private double error;  //error provocado por el calculo
    String Hex;            //conversion de Hexadecimal

// **************  INICIO DE METODOS ***************************
    public void setTime2(int retardo) {     // como maximo alcanza 197,633uS con un rango de error en algunos tiempos inferiores a este numero, se sugiere si tiene error pasar al siguiente

        this.retardo = retardo;

        for (int i = 0; i <= 255; i++) {
            n += 1;
            m = 0;
            for (int j = 0; j <= 255; j++) {
                s = 0;
                m += 1;
                for (int z = 0; z <= 5; z++) {
                    s += 1;
                    resultado = 3 * m * n + 4 * m + s;
                    if (resultado == retardo) {
                        break;
                    }
                }
                if (resultado == retardo) {
                    break;
                }
            }
            if (resultado == retardo) {
                break;
            }
        }

        setRetardo(retardo);
        setResultado(resultado);
        setM(m);
        setN(n);
        setS(s);

    }//calculo para el segundo ciclo

    public void setTime3(int retardo) {       // como maximo alcanza 50,594,817uS con un rango de error en algunos tiempos inferiores a este numero, se sugiere si tiene error pasar al siguiente
        this.retardo = retardo;

        for (int i = 0; i <= 255; i++) {

            n += 1;
            m = 0;
            for (int j = 0; j <= 255; j++) {
                p = 0;
                m += 1;
                for (int z = 0; z <= 255; z++) {
                    p++;
                    s = 0;
                    for (int r = 0; r <= 5; r++) {
                        s++;
                        resultado = (3 * m * n + 4 * m + 1) * p + 3 * p + s;
                        if (resultado == retardo) {
                            break;
                        }
                    }
                    if (resultado == retardo) {
                        break;
                    }
                }
                if (resultado == retardo) {
                    break;
                }
            }
            if (resultado == retardo) {
                break;
            }
        }
        setRetardo(retardo);
        setResultado(resultado);
        setM(m);
        setN(n);
        setP(p);
        setS(s);
    }

    public void setTime4(int retardo) {// como maximo alcanza 133,563,392 uS con un rango de error en algunos tiempos inferiores a este numero, se sugiere si tiene error pasar al siguiente

        this.retardo = retardo;

        for (int i = 0; i <= 255; i++) {
            n += 1;
            m = 0;
            for (int j = 0; j <= 255; j++) {
                p = 0;
                m += 1;
                for (int z = 0; z <= 255; z++) {
                    p++;
                    w = 0;
                    for (int r = 0; r <= 255; r++) {
                        w++;
                        s = 0;
                        for (int y = 0; y <= 5; y++) {
                            s++;
                            resultado = ((3 * m * n + 4 * m + 1) * p + 3 * p + 1) * w + 3 * w + s;
                            if (resultado < 0) {
                                resultado = resultado * -1;
                            }

                            if (resultado == retardo) {
                                break;
                            }
                        }
                        if (resultado == retardo) {
                            break;
                        }
                    }
                    if (resultado == retardo) {
                        break;
                    }
                }
                if (resultado == retardo) {
                    break;
                }
            }
            if (resultado == retardo) {
                break;
            }
        }
        setRetardo(retardo);
        setResultado(resultado);
        setM(m);
        setN(n);
        setP(p);
        setS(s);
        setW(w);

    }

    public int getRetardo() {
        return retardo;
    }

    public void setRetardo(int retardo) {
        this.retardo = retardo;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int x) {

        this.resultado = x;
    }

    public void setError(double x, double y) { //valor obtenido - valor real /valor real

        error = ((x - y) / y) * 100;          //resultado - retardo /retardo
        error = Math.abs(error);

    }

    public double getError() {
        return error;
    }

    public int getM() {

        return m;
    }

    public void setM(int m) {
        if (m == 256) {
            m = 0;
        }
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        if (n == 256) {
            n = 0;
        }
        this.n = n;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        if (p == 256) {
            p = 0;
        }
        this.p = p;
    }

    public int getS() {//NOP's
        return s;
    }

    public void setS(int s) {//NOP's
        this.s = s;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        if (w == 256) {
            w = 0;
        }
        this.w = w;
    }

    public String getTime2() {

        String texto1 = ";*********** REGISTROS PARA EL CONTADOR*********************************************** \n"
                + "time0\tequ\t0x08\n"
                + "time1\tequ\t0x09\n"
                + "\n";
        setHex(getM());

        String texto2 = ";*********** RUTINA DE TIEMPO********************************************************* \n"
                + "Delay_m\tmovlw\th'" + getHex() + "'\t;carga valor para el tiempo deseado/ Delay_main\n" + // M
                "\tmovwf\ttime1\t;0x08 <-- es una posicion por default, adaptar a su programa\n";
        setHex(getN());
        String texto3 = "Delay_1\tmovlw\th'" + getHex() + "'\t;carga valor para el tiempo deseado\n"; // N
        String texto4 = "\tmovwf\ttime0\t;0x09 <-- es una posicion por default, adaptar a su programa\n";
        String texto5 = "Delay_0\tdecfsz\ttime0,f\t;decremnta y espera a que termine\n";
        defS(getS());
        String texto6 = "\tgoto\tDelay_0\t;regresa a Delay_0\n";
        String texto7 = "\tdecfsz\ttime1,f\t;decrementa time1\n";
        String texto8 = "\tgoto\tDelay_1\t;regresa a Delay_1\n";

        return texto1 + texto2 + texto3 + texto4 + texto5 + texto6 + texto7 + texto8;
    }

    public String getTime3() {

        String texto1 = ";*********** REGISTROS PARA EL CONTADOR*********************************************** \n"
                + "time0\tequ\t0x08\n";
        String texto2 = "time1\tequ\t0x09\n";
        String texto3 = "time2\tequ\t0x0A\n";
        String texto4 = "\n";
        setHex(getP());
        String texto5 = ";*********** RUTINA DE TIEMPO********************************************************* \n"
                + "Delay_m\tmovlw\th'" + getHex() + "'\t;carga valor para el tiempo deseado/ Delay_main\n"; // P
        String texto6 = "\tmovwf\ttime2\t;0x0A <-- es una posicion por default, adaptar a su programa\n";
        setHex(getM());
        String texto7 = "Delay_2\tmovlw\th'" + getHex() + "'\t;carga valor para el tiempo deseado\n"; // M
        String texto8 = "\tmovwf\ttime1\t;0x09 <-- es una posicion por default, adaptar a su programa\n";
        setHex(getN());
        String texto9 = "Delay_1\tmovlw\th'" + getHex() + "'\t;carga valor para el tiempo deseado\n"; // N
        String texto10 = "\tmovwf\ttime0\t;0x08 <-- es una posicion por default, adaptar a su programa\n";
        String texto11 = "Delay_0\tdecfsz\ttime0,f\t;decremnta y espera a que termine\n";
        defS(getS());
        String texto12 = "\tgoto\tDelay_0\t;regresa a Delay_0\n";
        String texto13 = "\tdecfsz\ttime1,f\t;decrementa time1\n";
        String texto14 = "\tgoto\tDelay_1\t;regresa a Delay_1\n";
        String texto15 = "\tdecfsz\ttime2,f\t;decrementa time1\n";
        String texto16 = "\tgoto\tDelay_2\t;regresa a Delay_1\n";

        return texto1 + texto2 + texto3 + texto4 + texto5 + texto6 + texto7 + texto8 + texto9 + texto10 + texto11 + texto12 + texto13
                + texto14 + texto15 + texto16;
    }

    public String getTime4() {

        String texto1 = ";*********** REGISTROS PARA EL CONTADOR*********************************************** \n"
                + "time0\tequ\t0x08\n";
        String texto2 = "time1\tequ\t0x09\n";
        String texto3 = "time2\tequ\t0x0A\n";
        String texto4 = "time3\tequ\t0x0B\n";
        String texto5 = "\n";
        setHex(getW());

        String texto6 = ";*********** RUTINA DE TIEMPO********************************************************* \n"
                + "Delay_m\tmovlw\th'" + getHex() + "'\t;carga valor para el tiempo deseado/ Delay_main\n"; // W
        String texto7 = "\tmovwf\ttime3\t;0x0B <-- es una posicion por default, adaptar a su programa\n";
        setHex(getP());
        String texto8 = "Delay_3\tmovlw\th'" + getHex() + "'\t;carga valor para el tiempo deseado\n"; // P
        String texto9 = "\tmovwf\ttime2\t;0x0A <-- es una posicion por default, adaptar a su programa\n";
        setHex(getM());
        String texto10 = "Delay_2\tmovlw\th'" + getHex() + "'\t;carga valor para el tiempo deseado\n"; // M
        String texto11 = "\tmovwf\ttime1\t;0x09 <-- es una posicion por default, adaptar a su programa\n";
        setHex(getN());
        String texto12 = "Delay_1\tmovlw\th'" + getHex() + "'\t;carga valor para el tiempo deseado\n"; // N
        String texto13 = "\tmovwf\ttime0\t;0x08 <-- es una posicion por default, adaptar a su programa\n";
        String texto14 = "Delay_0\tdecfsz\ttime0,f\t;decremnta y espera a que termine\n";
        defS(getS());
        String texto15 = "\tgoto\tDelay_0\t;regresa a Delay_0\n";
        String texto16 = "\tdecfsz\ttime1,f\t;decrementa time1\n";
        String texto17 = "\tgoto\tDelay_1\t;regresa a Delay_1\n";
        String texto18 = "\tdecfsz\ttime2,f\t;decrementa time1\n";
        String texto19 = "\tgoto\tDelay_2\t;regresa a Delay_1\n";

        return texto1 + texto2 + texto3 + texto4 + texto5 + texto6 + texto7 + texto8 + texto9 + texto10 + texto11 + texto12 + texto13
                + texto14 + texto15 + texto16 + texto17 + texto18 + texto19;
    }

    public void setHex(int x) {

        Hex = Integer.toHexString(x);///////////////// falta agregar variable

    }

    public String getHex() {
        return Hex;
    }

    public String defS(int u) {     //define el numero de "NOP" en funcion de numero de la variable S

        String df = "";

        for (int a = 1; a < getS(); a++) {

            df = "\tnop\t;gasta un ciclo de reloj, no hace nada\n";
            df += df;
        }//fin del for
        return df;
    }

}//fin de la clase
