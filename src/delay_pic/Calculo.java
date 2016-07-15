/*
 * EN ESTA CLASE SOLO SE TIENE UNA ECUACION QUE ES LA PRIMERA PARA TIEMPO PEQUEÑOS INFERIORES A  767uS
 * TIENE 0% DE ERROR SOLO EN NUMERO DEMASIADOS PEQUEÑOS INFEIORES A 10uS TIENEN RANGO DE ERROR PEQUEÑO 
 */
package delay_pic;

/**
 *
 * @author XIZUTH
 */
public class Calculo {

    int N;          //primer ciclo
    int n;
    String Hex;    //convercion hex

    public Calculo() {//construtor vacio
    }

    public String setCiclos(int ciclos) {// compara para ver que ecuacion realizar y dar el resultado mas preciso
        this.N = ciclos;
        int s = (ciclos - 1) % 3;

        if (s == 0) //con residuo 0
        {
            return getResiduo0();
        }

        if (s == 1) //con residuo 1
        {
            return getResiduo1();
        }

        if (s == 2) {
            return getResiduo2();  //con residuo 2
        }
        return "";
    }

    public void setHex(int x) {

        Hex = Integer.toHexString(n);

    }

    public String getHex() {
        return Hex;
    }

    public String getResiduo0() {

        n = (N - 1) / 3;  //CON RESIDUO 0
        setHex(n);
        String texto = ";*********** REGISTROS PARA EL CONTADOR************************************************* \n "
                + "time0\t\tequ\t0x08\n"
                + "\n"
                + ";*********** RUTINA DE TIEMPO*********************************************************** \n"
                + "Delay_m\tmovlw\th'" + getHex() + "'\t;carga valor para el tiempo deseado/ Delay_main\n"
                + "\tmovwf\ttime0\t;0x08 <-- es una posicion por default, adaptar a su programa\n"
                + "Delay_0\tdecfsz\ttime0,f\t;decremnta y espera a que termine\n"
                + "\tgoto\tDelay_0\t;regresa a Delay_0\n";
        return texto;
    }

    public String getResiduo1() {

        n = (N - 2) / 3;     //CON RESIDUO 1
        setHex(n);
        String texto = ";*********** REGISTROS PARA EL CONTADOR********************************************* \n "
                + "time0\tequ\t0x08\n"
                + "\n"
                + ";*********** RUTINA DE TIEMPO****************************************************** \n "
                + "Delay_m\tmovlw\th'" + getHex() + "'\t;carga valor para el tiempo deseado/ Delay_main\n"
                + "\tmovwf\ttime0\t;0x08 <-- es una posicion por default, adaptar a su programa\n"
                + "\tnop\t\t;gasta un ciclo de reloj, no hace nada\n" + // NOP
                "Delay_0\tdecfsz\ttime0,f\t;decremnta y espera a que termine\n"
                + "\tgoto\tDelay_0\t;regresa a Delay_0\n";

        return texto;

    }

    public String getResiduo2() {

        n = (N - 2) / 3;     //CON RESIDUO 2
        setHex(n);
        String texto = ";*********** REGISTROS PARA EL CONTADOR*********************************************** \n"
                + "time0\tequ\t0x08\n"
                + "\n"
                + ";*********** RUTINA DE TIEMPO********************************************************* \n"
                + "Delay_m\tmovlw\th'" + getHex() + "'\t;carga valor para el tiempo deseado/ Delay_main\n"
                + "\tmovwf\ttime0\t;0x08 <-- es una posicion por default, adaptar a su programa\n"
                + "\tnop\t\t;gasta un ciclo de reloj, no hace nada\n" + // NOP
                "\tnop\t\t;gasta un ciclo de reloj, no hace nada\n" + // NOP
                "Delay_0\tdecfsz\ttime0,f\t;decremnta y espera a que termine\n"
                + "\tgoto\tDelay_0\t;regresa a Delay_0\n";
        return texto;
    }

}
