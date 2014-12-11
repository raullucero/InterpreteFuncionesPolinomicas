
package automatas;

public class Monomio {
    
    private double coeficiente;
    private int exponente;

    public Monomio() {
    }

    public Monomio(double coeficiente, int exponente) {
        this.coeficiente = coeficiente;
        this.exponente = exponente;
    }

    /**
     * @return the coeficiente
     */
    
    public double getCoeficiente() {
        return coeficiente;
    }

    /**
     * @param coeficiente the coeficiente to set
     */
    public void setCoeficiente(double coeficiente) {
        this.coeficiente = coeficiente;
    }

    /**
     * @return the exponente
     */
    public int getExponente() {
        return exponente;
    }

    /**
     * @param exponente the exponente to set
     */
    public void setExponente(int exponente) {
        this.exponente = exponente;
    }
    
    
}
