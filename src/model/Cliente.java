package model;

import javax.swing.JOptionPane;

/**
 *
 * @author uniebrunosilva
 */
public class Cliente extends Pessoa{
    private ContaSalario cs;
    private ContaPoupanca cp;
    private ContaCorrente cc;
    
    public Cliente(String nome,String senha ,String cpf){
        super(nome, cpf, senha);
    }

    public Cliente(String cpf, String senha) {
        super(cpf, senha);
    }

    public Cliente(String cpf) {
        super(cpf);
    }

    public Cliente(String nome, String cpf, String senha, double cs, double cc, double cp){
    
        super(nome,cpf,senha);
        if(cc >= -10000){
            this.cc = new ContaCorrente(cc);
        }

        if(cp >= 0){
            this.cp = new ContaPoupanca(cp);
        }
        if(cs >= 0){
            this.cs = new ContaSalario(cs);
        }
    
    }

    public void addContaCorrente(double valor){
        if(this.cc == null){
            this.cc = new ContaCorrente(valor);
        }
    }


    public boolean hasContaCorrente(){
        if(this.cc != null){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void addContaSalario(double valor){
        if(this.cs == null){
            this.cs = new ContaSalario(valor);
        }
    }


    public boolean hasContaSalario(){
        if(this.cs != null){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void addContaPoupanca(double valor){
        if(this.cp == null){
            this.cp = new ContaPoupanca(valor);
        }
    }

    public boolean hasContaPoupanca(){
        if(this.cp != null){
            return true;
        }
        else{
            return false;
        }
    }

    public double getCs() {
        if(this.hasContaSalario()){
            return this.cs.getValorSalario();
        }
        else{
            return -10001;
        }
    }

    public void setCs(ContaSalario cs) {
        this.cs = cs;
    }

    public double getCp() {
        if(this.hasContaPoupanca()){
            return this.cp.getValorPoupanca();
        }
        else{
            return -10001;
        }
    }

    public void setCp(ContaPoupanca cp) {
        this.cp = cp;
    }

    public double getCc() {
        if(this.hasContaCorrente()){
            return this.cc.getValorCorrente();
        }
        else{
            return -10001;
        }
        
    }

    public void setCc(ContaCorrente cc) {
        this.cc = cc;
    }
   
    public boolean DebitarContaSalario(double valor_deb){
        if(this.cs.getValorSalario() - (valor_deb *1.05) < 0){
            JOptionPane.showMessageDialog(null, "Conta salário não pode ter valor negativo!", "Aviso", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
            this.cs.debitar(valor_deb);
            return true;
        }
    }
    
    public boolean DebitarContaCorrente(double valor_deb){
        if(this.cc.getValorCorrente() - (valor_deb * 1.01) < -10000){
            JOptionPane.showMessageDialog(null, "Conta corrente não pode ter valor menos que -10000!", "Aviso", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
            this.cc.debitar(valor_deb);
            return true;
        }
    }
    
    public boolean DebitarContaPoupanca(double valor_deb){
        if(this.cp.getValorPoupanca() - valor_deb < 0){
            JOptionPane.showMessageDialog(null, "Conta poupança não pode ter valor negativo!", "Aviso", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
            this.cp.debitar(valor_deb);       
            return true;
        }
    }
    
    public void DepositarContaSalario(double valor_dep){
        this.cs.deposito(valor_dep);
    }
    
    public void DepositarContaCorrente(double valor_dep){
        this.cc.deposito(valor_dep);
    }
    
    public void DepositarContaPoupanca(double valor_dep){
        this.cp.deposito(valor_dep);
    }
}
