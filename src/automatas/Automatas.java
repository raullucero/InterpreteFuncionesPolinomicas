package automatas;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Automatas {

    static LinkedList<Monomio> Funcion = new LinkedList<Monomio>();
    static String numeros = "0123456789x^+-()";
    static String Parentesis = "()";

    public static void main(String[] args) {

        //Funcion.add(new Monomio(1, 1));
        //Funcion.add(new Monomio(1, 3));
        //Funcion.add(new Monomio(1, 4));
        //funcion.add(new Monomio(2,0));
        //Factorizar fact = new Factorizar(funcion);
        //Desarrollar desa = new Desarrollar(funcion);
        //System.out.println("desordenada");
        //for (Monomio mono : Funcion) {
        //  System.out.println("coeficiente: " + mono.getCoeficiente() + " exponente: " + mono.getExponente());
        //}
//        Collections.sort(Funcion, new Comparator<Monomio>() {
//            @Override
//            public int compare(Monomio o1, Monomio o2) {
//                if (o1.getExponente() < o2.getExponente()) {
//                    return 1;
//                } else {
//                    return -1;
//                }
//            }
//        });
        // System.out.println("ordenada");
        // for (Monomio mono : Funcion) {
        //   System.out.println("coeficiente: " + mono.getCoeficiente() + " exponente: " + mono.getExponente());
        //}
        ///CODIGO DE LA PAPA
        Scanner s = new Scanner(System.in);
        String strDatos, continuar;
        do {
            strDatos = "";
            System.out.println("Dame la cadena");
            Funcion.clear();
            strDatos = s.nextLine();

            if (Nums(strDatos) == strDatos.length()) {

                if (Parentesis(strDatos) /*== 1 || Parentesis(strDatos) == 2*/) {
                    int i = 0;
                    StringTokenizer TokenParentesis = new StringTokenizer(strDatos, ")");
                    while (TokenParentesis.hasMoreTokens()) {
                        String jugador = TokenParentesis.nextToken();
                        i++;
                    }

                    if (i > 2) {
                        System.out.println("Operacion invalida");
                    } else {
                        TokenParentesis = new StringTokenizer(strDatos, ")");
                        String[] jugador = new String[2];
                        i = 0;
                        while (TokenParentesis.hasMoreTokens()) {
                            jugador[i] = TokenParentesis.nextToken();
                            i++;
                        }

                        if (jugador[1].equals("^2")) {
                            Validaciones(jugador[0].substring(1, jugador[0].length()), true);
                            Desarrollar des = new Desarrollar(Funcion);
                            System.out.println(des.binomioCuadrado());
                        } else {
                            Validaciones(jugador[0].substring(1, jugador[0].length()), true);
                            Validaciones(jugador[1].substring(1, jugador[1].length()), true);
                            Desarrollar desa = new Desarrollar(Funcion);
//                            for (Monomio mono : Funcion) {
//                                System.out.println("coeficiente "+mono.getCoeficiente()+" exponente "+mono.getExponente());
//                            }

                            System.out.println(desa.desarrolloGenerico());
                        }

                    }
                } else {
//                 
                    Validaciones(strDatos, false);

                    for (Monomio mono : Funcion) {
                        System.out.println("coeficiente " + mono.getCoeficiente() + " exponente " + mono.getExponente());
                    }

                    Factorizar facto = new Factorizar(Funcion);
                    String resultado = (facto.simplificar() == "") ? "No se pudo factorizar" : facto.simplificar();
                    System.out.println(resultado);
                }
            } else {
                System.out.println("Operacion invalida");
            }

            System.out.println("Quieres continuar ingresando? Si=1");
            continuar = s.nextLine();
        } while (continuar.equals("1"));

        System.out.println("No existe esa opcion, el programa terminara");

        //String facto = fact.simplificar();
        //System.out.println(facto);
    }

    public static void convMonomio(String jugador, String strDatos) {
        double Coeficiente = 0;
        int Exponente = 0;

        if (jugador.equals("x")) {
            Coeficiente = 1;
            Exponente = 1;
            Funcion.add(new Monomio(Coeficiente, Exponente));
        }
        if (jugador.equals("-x")) {
            Coeficiente = -1;
            Exponente = 1;
            Funcion.add(new Monomio(Coeficiente, Exponente));
        }
        if (!jugador.equals("x") && !jugador.equals("-x")) {
            String cadenaArray[] = strDatos.split("x");
            int j = 0;
            for (String cadena : cadenaArray) {
                j++;
            }
            if (j == 1) {
                Coeficiente = Double.parseDouble(cadenaArray[0]);
                if (jugador.indexOf('x') == jugador.length() - 1) {
                    Exponente = 1;
                } else {
                    Exponente = 0;
                }
                Funcion.add(new Monomio(Coeficiente, Exponente));
            } else {
                Coeficiente = Double.parseDouble(cadenaArray[0]);
                Exponente = Integer.parseInt(cadenaArray[1].substring(1, cadenaArray[1].length()));
                Funcion.add(new Monomio(Coeficiente, Exponente));
            }

        }
    //    System.out.println("Coeficiente....." + Coeficiente);
        //  System.out.println("Exponente.....");

    }

    public static void validarPositivo(String strDatos) {
        StringTokenizer TokenPositivo = new StringTokenizer(strDatos, "+");
        int cont = 0;
        while (TokenPositivo.hasMoreTokens()) { //checa si hay signos  "-"
            String jugador = TokenPositivo.nextToken();
            cont++;
        }
        if (cont > 0) {
            TokenPositivo = new StringTokenizer(strDatos, "+");
            boolean positivo = false;
            cont = 0;

            while (TokenPositivo.hasMoreTokens()) { //checa si hay signos  "-"
                String jugador = TokenPositivo.nextToken();
                if (!jugador.substring(0, 1).equals("-")) {
                    positivo = true;
                }

                if (positivo) {
                    convMonomio(jugador, jugador);
                } else {
                    convMonomio(jugador, jugador);
                }
                cont++;
            }
        }
    }

    public static boolean Parentesis(String cadena) {
        int cont = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (Parentesis.indexOf(cadena.charAt(i), 0) != -1) {
                return true;
            }
        }

        return false;
    }

    public static void Validaciones(String strDatos, boolean isBino) {

        StringTokenizer TokenPositivo = new StringTokenizer(strDatos, "+");
        StringTokenizer TokenNegativo = new StringTokenizer(strDatos, "-");
        int i = 0;
        String jugador = "";
        // checa si se pueden generar cadenas con un token "*"
        while (TokenPositivo.hasMoreTokens()) {
            jugador = TokenPositivo.nextToken();
            i++;
        }

        if (i == 1) // Si no encuentra el signo "+" 
        {
            i = 0; //inicializas el contador
            while (TokenNegativo.hasMoreTokens()) { //checa si hay signos  "-"
                jugador = TokenNegativo.nextToken();
                i++;
            }
            if (i == 1) // Determina si es un momonio
            {

                convMonomio(jugador, strDatos);
            } else {
                TokenNegativo = new StringTokenizer(strDatos, "-");
                boolean positivo = false;
                i = 0;
                if (!strDatos.substring(0, 1).equals("-")) {
                    positivo = true;
                }
                while (TokenNegativo.hasMoreTokens()) { //checa si hay signos  "-"
                    jugador = TokenNegativo.nextToken();
                    if (i == 0 && positivo) {
                        convMonomio(jugador, jugador);
                    } else {
                        convMonomio(jugador, "-" + jugador);
                    }
                    i++;
                }
            }
        } else {
            boolean positivo = false;
            i = 0;
            if (!strDatos.substring(0, 1).equals("-")) {
                positivo = true;
            }
            while (TokenNegativo.hasMoreTokens()) { //checa si hay signos  "-"
                jugador = TokenNegativo.nextToken();

                if (i == 0 && positivo) {
                    validarPositivo(jugador);
                } else {
                    validarPositivo("-" + jugador);
                }
                i++;
            }

        }
//        for (Monomio monomio : Funcion) {
//            System.out.println(".... " + monomio.getCoeficiente() + ".... " + monomio.getExponente());
//      
        if (!isBino) {
            Collections.sort(Funcion, new Comparator<Monomio>() {
                @Override
                public int compare(Monomio o1, Monomio o2) {
                    if (o1.getExponente() < o2.getExponente()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            });
        }
    }

    public static int Nums(String cadena) {
        int cont = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (numeros.indexOf(cadena.charAt(i), 0) != -1) {
                cont++;
            } else {
                cont--;

            }
        }
        return cont;
    }

}
