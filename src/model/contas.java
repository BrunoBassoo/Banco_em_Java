
package model;

/**
 *
 * @author T-Gamer
 */
public class contas {
    private double valorSalario;
    private double valorCorrente;
    private double valorPoupanca;

    public contas() {
    }
    
    

    // nao estamos usando construtor porque vamos somente instanciar os valores
    // das contas atraves do setValor*****()
    
    public double getValorSalario() {
        return valorSalario;
    }

    public void setValorSalario(double valorSalario) {
        this.valorSalario = valorSalario;
    }

    public double getValorCorrente() {
        return valorCorrente;
    }

    public void setValorCorrente(double valorCorrente) {
        this.valorCorrente = valorCorrente;
    }

    public double getValorPoupanca() {
        return valorPoupanca;
    }

    public void setValorPoupanca(double valorPoupanca) {
        this.valorPoupanca = valorPoupanca;
    }
    
    
}
