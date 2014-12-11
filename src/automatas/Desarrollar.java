package automatas;

import java.util.LinkedList;

public class Desarrollar {

    LinkedList<Monomio> polinomio;
    boolean diferencia;

    public Desarrollar(LinkedList<Monomio> polinomio) {
        this.polinomio = polinomio;
        this.diferencia = false;
    }

    public String desarrollar() {

        return desarrolloGenerico();
    }

    public String binomioCuadrado() {
        String result = "";
        double cuadradoCoefi1 = Math.pow(polinomio.get(0).getCoeficiente(), 2);
        double segundoTermino = (polinomio.get(0).getCoeficiente() * 2) * polinomio.get(1).getCoeficiente();
        double cuadradoCoefi2 = Math.pow(polinomio.get(1).getCoeficiente(), 2);
        int sumaExp = polinomio.get(0).getExponente() + polinomio.get(1).getExponente();

        String signo = (polinomio.get(1).getCoeficiente() < 0) ? "-" : "+";

        if (polinomio.get(1).getExponente() == 0) {
            result = cuadradoCoefi1 + "x^" + (polinomio.get(0).getExponente() * 2) + signo + segundoTermino + "x+" + cuadradoCoefi2;
        } else {
            result = cuadradoCoefi1 + "x^" + (polinomio.get(0).getExponente() * 2) + signo + segundoTermino + "x^" + sumaExp + "+" + cuadradoCoefi2 + "x^" + polinomio.get(1).getExponente() * 2;
        }
        return result;
    }

    public String desarrolloGenerico() {
        String result = "";
        double coeficientePrimerTermino = polinomio.get(0).getCoeficiente() * polinomio.get(2).getCoeficiente();
        int exponentePrimerTermino = polinomio.get(0).getExponente() + polinomio.get(2).getExponente();

        double coeficienteSegTermino = polinomio.get(0).getCoeficiente() * polinomio.get(3).getCoeficiente();
        int exponenteSegTermino = polinomio.get(0).getExponente() + polinomio.get(3).getExponente();

        double coeficienteTercerTermino = polinomio.get(1).getCoeficiente() * polinomio.get(2).getCoeficiente();
        int exponenteTercerTermino = polinomio.get(1).getExponente() + polinomio.get(2).getExponente();

        double coeficienteCuartoTermino = polinomio.get(1).getCoeficiente() * polinomio.get(3).getCoeficiente();
        int exponenteCuartoTermino = polinomio.get(1).getExponente() + polinomio.get(3).getExponente();

        double Segundo = coeficienteSegTermino + coeficienteTercerTermino;

        String signoPrim = (Segundo > 0) ? "+" + Segundo : Segundo + "";
        String signoSeg = (coeficienteCuartoTermino > 0) ? "+" + coeficienteCuartoTermino : coeficienteCuartoTermino + "";

        if (exponenteCuartoTermino > 0) {
            signoSeg += (exponenteCuartoTermino == 1) ? "x" : "x^" + exponenteCuartoTermino;
            
            if (Segundo == 0) {
                result = coeficientePrimerTermino + "x^" + exponentePrimerTermino + signoSeg;
            } else if (exponenteSegTermino > 1) {
                result = coeficientePrimerTermino + "x^" + exponentePrimerTermino + signoPrim + "x^" + exponenteSegTermino + signoSeg;
            } else if (exponenteSegTermino == 1) {
                result = coeficientePrimerTermino + "x^" + exponentePrimerTermino + signoPrim + "x" + signoSeg;
            }
        } else {
            if (Segundo == 0) {
                result = coeficientePrimerTermino + "x^" + exponentePrimerTermino + signoSeg;
            } else if (exponenteSegTermino > 1) {
                result = coeficientePrimerTermino + "x^" + exponentePrimerTermino + signoPrim + "x^" + exponenteSegTermino + signoSeg;
            } else if (exponenteSegTermino == 1) {
                result = coeficientePrimerTermino + "x^" + exponentePrimerTermino + signoPrim + "x" + signoSeg;
            }
        }
        return result;
    }
}
