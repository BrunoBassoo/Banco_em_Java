
package ControllerGerente;

import DB.DB_Gerente;
import DB.conexao_banco;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Gerente;
import views.EntrarGerente;
import views.TelaGerente;

/**
 *
 * @author T-Gamer
 */
public class ControllerLoginGerente {
    private EntrarGerente view;

    public ControllerLoginGerente(EntrarGerente view) {
        this.view = view;
    }
    
    // fazer o login do gerente
    public void LogarGerente(){
        String cpf = view.getEntrada_cpf().getText();
        String senha = view.getEntrada_senha().getText();
        Gerente gerente = new Gerente(cpf,senha);
        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Gerente db = new DB_Gerente(conn);
            
            ResultSet res = db.consultarGerente(gerente);
            if(res.next()){
                JOptionPane.showMessageDialog(view, "Login Feito", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                TelaGerente tg = new TelaGerente();
                tg.setVisible(true);
                view.setVisible(false);
            } else{
                JOptionPane.showMessageDialog(view, "Login não efetuado", "Erro", JOptionPane.ERROR_MESSAGE);
                
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexão", "Erro", JOptionPane.ERROR_MESSAGE);
                
        }
    }
}

