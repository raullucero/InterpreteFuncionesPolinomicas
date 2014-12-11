package automatas;

import java.util.LinkedList;
import java.math.*;

public class Factorizar {

    LinkedList<Monomio> polinomio;
    String result;

    public Factorizar() {
    }

    public Factorizar(LinkedList<Monomio> polinomio) {
        this.polinomio = polinomio;
        this.result = "";
    }

    public String simplificar() {
        boolean bandera = true;
        
        if (polinomio.size() == 3) {
            if (binomioAlCuadrado()) {
                bandera =false;
                return result;
            }
            if (formaABC()) {
                bandera=false;
                return result;
                
            }
        } else if (polinomio.size() == 2) {
            if (diferenciaCuadrados()) {
                bandera=false;
                return result;
            }
        }else if(!bandera){
            if(factorComun())
                return result;
        }

        //if (factorComun()) {
        //  return result;
        //}
        return result;
    }

    public boolean binomioAlCuadrado() {
        boolean resultado = false;
        int exponenteTermino1 = 0;
        double coeficienteTermino1 = 0;

        int exponenteTermino2 = 0;
        double coeficienteTermino2 = 0;

        int i = 0;
        for (Monomio mono : polinomio) {
            if (i == 0) {
                exponenteTermino1 = mono.getExponente() / 2;
                coeficienteTermino1 = Math.sqrt(mono.getCoeficiente());
            } else if (i == 2) {
                exponenteTermino2 = mono.getExponente() / 2;
                coeficienteTermino2 = Math.sqrt(mono.getCoeficiente());
            }
            i++;
        }

        if (Math.abs(polinomio.get(1).getCoeficiente()) == ((coeficienteTermino1 * 2) * coeficienteTermino2)
                && polinomio.get(1).getExponente() == (exponenteTermino1 + exponenteTermino2)) {

            String signo = (polinomio.get(1).getCoeficiente() < 0) ? "-" : "+";

            polinomio.clear();
            resultado = true;

            polinomio.offer(new Monomio(coeficienteTermino1, exponenteTermino1));
            polinomio.offer(new Monomio(coeficienteTermino2, exponenteTermino2));

            if (polinomio.peek().getExponente() == 1) {
                result = "(" + polinomio.peek().getCoeficiente() + "x";
            } else {
                result = "(" + polinomio.peek().getCoeficiente() + "x^" + polinomio.peek().getExponente();
            }
            polinomio.poll();
            if (polinomio.peek().getExponente() > 0) {
                if (polinomio.peek().getExponente() == 1) {
                    result += signo + polinomio.peek().getCoeficiente() + "x)^2";
                } else {
                    result += signo + polinomio.peek().getCoeficiente() + "x^" + polinomio.peek().getExponente() + ")^2";
                }
            } else {
                result += signo + polinomio.peek().getCoeficiente() + ")^2";
            }
        }
        return resultado;
    }

    public boolean diferenciaCuadrados() {
        boolean resultado = false;
        int exponenteTermino1 = 0;
        double coeficienteTermino1 = 0;

        int exponenteTermino2 = 0;
        double coeficienteTermino2 = 0;

        String auxiliar = "";

        int i = 0;
        for (Monomio mono : polinomio) {
            if (i == 0) {
                exponenteTermino1 = mono.getExponente() / 2;
                coeficienteTermino1 = Math.sqrt(mono.getCoeficiente());
            } else if (i == 1) {
                exponenteTermino2 = mono.getExponente() / 2;
                coeficienteTermino2 = Math.sqrt(Math.abs(mono.getCoeficiente()));
            }
            i++;
        }

        if (polinomio.get(1).getCoeficiente() < 0) {
            polinomio.clear();
            resultado = true;

            polinomio.offer(new Monomio(coeficienteTermino1, exponenteTermino1));
            polinomio.offer(new Monomio(coeficienteTermino2, exponenteTermino2));

            if (polinomio.peek().getExponente() == 1) {
                result = "(" + polinomio.peek().getCoeficiente() + "x";
                polinomio.poll();
                if (polinomio.peek().getExponente() == 1) {
                    auxiliar = result;
                    result += "+" + polinomio.peek().getCoeficiente() + "x)";
                    auxiliar += "-" + polinomio.peek().getCoeficiente() + "x)";
                    result += auxiliar;
                } else if (polinomio.peek().getExponente() == 0) {
                    auxiliar = result;
                    result += "+" + polinomio.peek().getCoeficiente() + ")";
                    auxiliar += "-" + polinomio.peek().getCoeficiente() + ")";
                    result += auxiliar;
                } else {
                    auxiliar = result;
                    result += "+" + polinomio.peek().getCoeficiente() + "x" + polinomio.peek().getExponente() + ")";
                    auxiliar += "-" + polinomio.peek().getCoeficiente() + "x" + polinomio.peek().getExponente() + ")";
                    result += auxiliar;
                }
            } else {
                result = "(" + polinomio.peek().getCoeficiente() + "x^" + polinomio.peek().getExponente();
                polinomio.poll();
                if (polinomio.peek().getExponente() == 1) {
                    auxiliar = result;
                    result += "+" + polinomio.peek().getCoeficiente() + "x)";
                    auxiliar += "-" + polinomio.peek().getCoeficiente() + "x)";
                    result += auxiliar;
                } else if (polinomio.peek().getExponente() == 0) {
                    auxiliar = result;
                    result += "+" + polinomio.peek().getCoeficiente() + ")";
                    auxiliar += "-" + polinomio.peek().getCoeficiente() + ")";
                    result += auxiliar;
                } else {
                    auxiliar = result;
                    result += "+" + polinomio.peek().getCoeficiente() + "x^" + polinomio.peek().getExponente() + ")";
                    auxiliar += "-" + polinomio.peek().getCoeficiente() + "x^" + polinomio.peek().getExponente() + ")";
                    result += auxiliar;
                }
            }
        }
        return resultado;
    }

    public boolean formaABC() {
        boolean resultado = false;

        int exponenteTermino1 = 0;
        double coeficienteTermino1 = 0;

        int i = 1;

        exponenteTermino1 = polinomio.get(0).getExponente() / 2;
        coeficienteTermino1 = (polinomio.get(0).getCoeficiente() == 1) ? 1.0 : polinomio.get(0).getCoeficiente() / 2;

        boolean encontrado = false;
        boolean salir = false;
        double numero1 = 0;
        double numero2 = 0;
        int exponenteTermino2 = 0;
        String auxiliar = "";

        do {
            if (polinomio.get(2).getCoeficiente() % i == 0) {
                numero1 = polinomio.get(2).getCoeficiente() / i;
                if (polinomio.get(1).getCoeficiente() == (numero1 + i)) {
                    numero2 = i;
                    encontrado = true;
                    exponenteTermino2 = polinomio.get(0).getExponente() - polinomio.get(1).getExponente();
                } else if (polinomio.get(1).getCoeficiente() == (numero1 - i)) {
                    numero2 = -i;
                    encontrado = true;
                    exponenteTermino2 = polinomio.get(0).getExponente() - polinomio.get(1).getExponente();
                } else if (polinomio.get(1).getCoeficiente() == (-numero1 - i)) {
                    numero2 = -i;
                    numero1 = -numero1;
                    encontrado = true;
                    exponenteTermino2 = polinomio.get(0).getExponente() - polinomio.get(1).getExponente();
                } else if (polinomio.get(1).getCoeficiente() == (-numero1 + i)) {
                    numero2 = i;
                    numero1 = -numero1;
                    encontrado = true;
                    exponenteTermino2 = polinomio.get(0).getExponente() - polinomio.get(1).getExponente();
                }
            }
            i++;

        } while (!encontrado && i != 200);

        String signo = (polinomio.get(1).getCoeficiente() < 0) ? "-" : "+";
        if (encontrado) {
            polinomio.clear();
            resultado = true;
            //result += (numero1 > 0) ? "+" + numero1 : numero1;
            signo = (numero2 > 0) ? "+" + numero2 : numero2 + "";
            polinomio.offer(new Monomio(coeficienteTermino1, exponenteTermino1));

            if (polinomio.get(0).getExponente() == 1) {
                result = "(" + polinomio.get(0).getCoeficiente() + "x";
                auxiliar = result;
                result += (numero1 > 0) ? "+" + numero1 : numero1;
                //result += numero1;
                if (exponenteTermino2 == 1) {
                    result += "x)";
                    auxiliar += signo + "x)";
                    result += auxiliar;
                } else if (exponenteTermino2 == 0) {
                    result += ")";
                    auxiliar += signo + ")";
                    result += auxiliar;
                } else {
                    result += "x^" + exponenteTermino2 + ")";
                    auxiliar += signo + "x^" + exponenteTermino2 + ")";
                    result += auxiliar;
                }
            } else {
                result = "(" + polinomio.get(0).getCoeficiente() + "x^" + polinomio.get(0).getExponente();
                auxiliar = result;
                result += (numero1 > 0) ? "+" + numero1 : numero1;
                if (exponenteTermino2 == 1) {
                    result += "x)";
                    auxiliar += signo + "x)";
                    result += auxiliar;
                } else if (exponenteTermino2 == 0) {
                    result += ")";
                    auxiliar += signo + ")";
                    result += auxiliar;
                } else {
                    result += "x^" + exponenteTermino2 + ")";
                    auxiliar += signo + "x^" + exponenteTermino2 + ")";
                    result += auxiliar;
                }
            }

        }
        return resultado;
    }

    public boolean factorComun() {
        boolean resultado = false;
        boolean isnotFactor = true;
        int menor = 99999;
        double coeficienteFactor = 0;

        for (Monomio mono : polinomio) {
            if (mono.getExponente() > 0 && mono.getCoeficiente() > 0) {
                isnotFactor = false;
            } else if (mono.getExponente() < menor) {
                menor = mono.getExponente();
            }
        }

        if (isnotFactor) {
            if (menor > 1) {
                result = "x^" + menor + "(";
            } else if (menor == 1) {
                result = "x(";
            }
            int i = 0;
            String signo = "";
            for (Monomio mono : polinomio) {
                if (i > 0) {
                    signo = (mono.getCoeficiente() > 0) ? "+" + mono.getCoeficiente() : mono.getCoeficiente() + "";
                } else {
                    signo = mono.getCoeficiente() + "";
                }
                if (mono.getExponente() - menor == 1) {
                    result += signo + "x";
                } else if (mono.getExponente() - menor == 0) {
                    result += signo + "";
                } else {
                    result += signo + "x^" + (mono.getExponente() - menor);
                }
                if (i == polinomio.size() - 1) {
                    result += ")";
                }
                i++;
            }
        }
        return resultado;
    }
}
